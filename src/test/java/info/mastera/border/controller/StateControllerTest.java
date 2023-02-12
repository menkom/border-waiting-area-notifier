package info.mastera.border.controller;

import info.mastera.border.model.State;
import info.mastera.border.model.Status;
import info.mastera.border.service.SchedulerService;
import info.mastera.border.service.StateStorageService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Map;

@QuarkusTest
public class StateControllerTest {

    @InjectMock
    private SchedulerService schedulerService;
    @InjectMock
    private StateStorageService stateStorageService;

    @BeforeEach
    protected void init() {
        Mockito.doNothing()
                .when(schedulerService)
                .retrieveStateData();
        State testState = new State(
                1,
                Status.ARRIVED,
                LocalDateTime.of(2023, 1, 20, 21, 59),
                LocalDateTime.of(2023, 1, 20, 23, 58)

        );
        Mockito.doReturn(Map.of("PlateNum", testState))
                .when(stateStorageService)
                .getStates();
        Mockito.doReturn(testState)
                .when(stateStorageService)
                .getState(ArgumentMatchers.anyString());
    }

    @Test
    public void getStatesTest() {
        RestAssured.given()
                .when()
                .get("/states")
                .then()
                .statusCode(200)
                .body(
                        "size()", Matchers.equalTo(1),
                        "PlateNum.changedDate", Matchers.equalTo("2023-01-20T23:58:00"),
                        "PlateNum.orderId", Matchers.equalTo(1),
                        "PlateNum.registrationDate", Matchers.equalTo("2023-01-20T21:59:00"),
                        "PlateNum.status", Matchers.equalTo(Status.ARRIVED.name())
                );
    }

    @Nested
    public class GetState {

        @Test
        public void getStateTest() {
            RestAssured.given()
                    .when()
                    .get("/states/{regNum}", "PlateNum")
                    .then()
                    .statusCode(200)
                    .body("changedDate", Matchers.equalTo("2023-01-20T23:58:00"),
                            "orderId", Matchers.equalTo(1),
                            "registrationDate", Matchers.equalTo("2023-01-20T21:59:00"),
                            "status", Matchers.equalTo(Status.ARRIVED.name()));
        }

        @Test
        public void getStateNotFoundTest() {
            Mockito.doReturn(null)
                    .when(stateStorageService)
                    .getState(ArgumentMatchers.anyString());

            RestAssured.given()
                    .when()
                    .get("/states/{regNum}", "PlateNum")
                    .then()
                    .statusCode(404)
                    .body(CoreMatchers.is(""));
        }
    }

    @Test
    public void requestStatesUpdateTest() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .post("/states")
                .then()
                .statusCode(204)
                .body(CoreMatchers.is(""));
    }
}