package com.guptaji.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guptaji.entity.StudentEntity;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


import org.crac.Core;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("Integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentResourceTest {

    @Inject
    private ObjectMapper objectMapper;

    @Order(2)
    @Test
    void getAllStudenDetails() {

        // for my API db testing
//        RestAssured
//                .given()
//                .when()
//                .get("/student")
//                .then()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("size()", CoreMatchers.equalTo(6))
//                .body("collegeName", CoreMatchers.hasItems("NIT RKL", "IIT Roorkee"));

        // for h2 db testing
        RestAssured
                .given()
                .when()
                .get("/student")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .body("size()", CoreMatchers.equalTo(1))
                .body("collegeName", CoreMatchers.hasItems("NIT RKL"));
    }

    @Order(1)
    @Test
    void insertOneNewStudent() throws JsonProcessingException {

        // first prepare the data
        StudentEntity studentEntity = new StudentEntity(10, "Aadarsh", "CSE", "NIT RKL");

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(objectMapper.writeValueAsString(studentEntity))
                .when()
                .post("/student")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .contentType(MediaType.TEXT_PLAIN);
//                .body("Done dana done done");
    }

    @Order(3)
    @Test
    void getAllStudenDetailsById() {

        RestAssured.given()
                .when()
                .get("/student/10")
                .then()
                .contentType(MediaType.APPLICATION_JSON)
                .body("branch", CoreMatchers.equalTo("CSE"))
                .body("name", CoreMatchers.equalTo("Aadarsh"))
                .body("collegeName", CoreMatchers.equalTo("NIT RKL"))
                .body("id", CoreMatchers.equalTo(10))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Order(4)
    @Test
    void deleteById() {
        // To check in DB
//        try {
//            Thread.sleep(10000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        RestAssured.given()
                .when()
                .delete("/student/10")
                .then()
                .contentType(MediaType.TEXT_PLAIN)
                .statusCode(Response.Status.OK.getStatusCode());
    }
}