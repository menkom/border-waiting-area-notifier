package info.mastera.border.controller;

import info.mastera.border.model.Checkpoint;
import info.mastera.border.service.CheckpointsStorageService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
public class CheckpointControllerTest {

    @InjectMock
    private CheckpointsStorageService checkpointsStorageService;

    @BeforeEach
    protected void init() {
        Mockito.doReturn(List.of(
                new Checkpoint()
                        .setId("1")
                        .setName("x")
                ))
                .when(checkpointsStorageService)
                .get();
    }

    @Test
    void getCheckpointsTest() {
        RestAssured.given()
                .when()
                .get("/checkpoints")
                .then()
                .statusCode(200)
                .body(CoreMatchers.is("[{\"id\":\"1\",\"name\":\"x\"}]"));
    }
}