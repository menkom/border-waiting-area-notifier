package info.mastera.border.controller;

import info.mastera.border.declarant.client.DeclarantApi;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.hamcrest.CoreMatchers;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class ProxyControllerTest {

    @InjectMock
    @RestClient
    DeclarantApi declarantApi;

    @Test
    void getCheckpointsTest() {
        Mockito.doReturn("")
                .when(declarantApi).getCheckpoints(any());

        RestAssured.given()
                .when()
                .get("/declarants")
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
                .get("/declarants")
                .then()
                .statusCode(500)
                .body(CoreMatchers.is(""));
    }
}