package com.waes.techassigment.main.com.waes.techassigment.datadiff.controller;

import com.waes.techassigment.datadiff.WaesTechAssigmentApplication;
import com.waes.techassigment.datadiff.model.DiffDataSaveBodyV1;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WaesTechAssigmentApplication.class}, webEnvironment
        = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiffDataControllerTest {

    private static final String API_ROOT = "/v1/diff";

    @Test
    @Order(1)
    void whenGetDiffData_with_id_no_records_thenNotFound() {
        Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(1));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    @Order(2)
    void whenGetDiffData_with_id_with_one_data_thenPreconditionFailed() {
        //Given
        final int id = 1;
        createLeftDiffDataByIdWithApi(id);
        Response response = RestAssured.get(API_ROOT + "/" + id);
        assertEquals(HttpStatus.PRECONDITION_FAILED.value(), response.getStatusCode());
    }

    @Test
    @Order(3)
    void createLeftDiffData_with_id_thenCreated() {
        DiffDataSaveBodyV1 data = generateRandonBodyWithValidBase64String();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(data)
                .post(API_ROOT + "/" + randomNumeric(1) + "/left");

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    @Order(4)
    void createRightDiffData_with_id_thenCreated() {
        DiffDataSaveBodyV1 data = generateRandonBodyWithValidBase64String();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(data)
                .post(API_ROOT + "/" + randomNumeric(1) + "/right");

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    @Order(5)
    void whenGetDiffData_with_id_with_all_data_thenOk() {
        //Given
        final int id = 1;
        createLeftDiffDataByIdWithApi(id);
        createRigthDiffDataByIdWithApi(id);
        Response response = RestAssured.get(API_ROOT + "/" + id);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }

    @Test
    @Order(6)
    void createLeftDiffData_with_id_and_invalid_data_thenBadRequest() {
        DiffDataSaveBodyV1 data = generateRandonBodyWithInvalidBase64String();
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(data)
                .post(API_ROOT + "/" + randomNumeric(1) + "/left");

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    private DiffDataSaveBodyV1 generateRandonBodyWithValidBase64String() {
        DiffDataSaveBodyV1 body = new DiffDataSaveBodyV1();
        body.setData("ewogICAgImV4YW1wbGUiIDogIkhlbGxvIFdvcmxkISIKfQ==");
        return body;
    }

    private DiffDataSaveBodyV1 generateRandonBodyWithInvalidBase64String() {
        DiffDataSaveBodyV1 body = new DiffDataSaveBodyV1();
        body.setData("hello");
        return body;
    }

    private void createLeftDiffDataByIdWithApi(final int id) {
        DiffDataSaveBodyV1 data = generateRandonBodyWithValidBase64String();
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(data)
                .post(API_ROOT + "/" + id + "/left");
    }

    private void createRigthDiffDataByIdWithApi(final int id) {
        DiffDataSaveBodyV1 data = generateRandonBodyWithValidBase64String();
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(data)
                .post(API_ROOT + "/" + id + "/right");
    }
}