package info.mastera.border.controller;

import info.mastera.border.declarant.client.DeclarantApi;
import info.mastera.border.declarant.client.model.CheckpointsResponse;
import info.mastera.border.declarant.client.model.StateResponse;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hamcrest.CoreMatchers;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class ProxyControllerTest {

    @InjectMock
    @RestClient
    DeclarantApi declarantApi;

    @Nested
    public class GetCheckpoints {

        @Test
        void getCheckpointsTest() {
            var response = new CheckpointsResponse();
            response.setResult(new ArrayList<>());
            Mockito.doReturn(response)
                    .when(declarantApi).getCheckpoints(any());

            RestAssured.given()
                    .when()
                    .get("/declarants/checkpoints")
                    .then()
                    .statusCode(200)
                    .body(CoreMatchers.is("{\"result\":[]}"));
        }

        @Test
        void getCheckpointsNullTest() {
            Mockito.doReturn(null)
                    .when(declarantApi).getCheckpoints(any());

            RestAssured.given()
                    .when()
                    .get("/declarants/checkpoints")
                    .then()
                    .statusCode(200)
                    .body(CoreMatchers.is(""));
        }

        @Test
        void getCheckpointsExceptionTest() {
            Mockito.doThrow(new ResteasyWebApplicationException(new WebApplicationException()))
                    .when(declarantApi).getCheckpoints(any());

            RestAssured.given()
                    .when()
                    .get("/declarants/checkpoints")
                    .then()
                    .statusCode(500)
                    .body(CoreMatchers.is(""));
        }
    }

    @Nested
    public class GetState {

        @Test
        void getStateTest() {
            var response = new StateResponse();
            Mockito.doReturn(response)
                    .when(declarantApi).getState(any(), any());

            RestAssured.given()
                    .when()
                    .get("/declarants/state")
                    .then()
                    .statusCode(200)
                    .body(CoreMatchers.is("{}"));
        }

        @Test
        void getStateNullTest() {
            Mockito.doReturn(null)
                    .when(declarantApi).getState(any(), any());

            RestAssured.given()
                    .when()
                    .get("/declarants/state")
                    .then()
                    .statusCode(200)
                    .body(CoreMatchers.is(""));
        }

        @Test
        void getStateExceptionTest() {
            Mockito.doThrow(new ResteasyWebApplicationException(new WebApplicationException()))
                    .when(declarantApi).getState(any(), any());

            RestAssured.given()
                    .when()
                    .get("/declarants/state")
                    .then()
                    .statusCode(500)
                    .body(CoreMatchers.is(""));
        }
    }
}