package com.github.nfers.api.controller;

import com.github.nfers.service.domain.dto.SubscriptionMessageDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PublisherControllerTest {

    @Test
    public void testSuccessfulPostMessage() {
        SubscriptionMessageDTO dto = new SubscriptionMessageDTO();
        dto.setSubscription("1234");
        dto.setNotificationType("UNSUBSCRIPTION");

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/api/v1/publisher")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode());

        assertThat(dto.getSubscription(), equalTo("1234"));
        assertThat(dto.getNotificationType(), equalTo("UNSUBSCRIPTION"));
        assertNotNull(dto);

    }


    @Test
    public void testBadRequest() {
        SubscriptionMessageDTO dto = new SubscriptionMessageDTO();

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/api/v1/publisher")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }
}