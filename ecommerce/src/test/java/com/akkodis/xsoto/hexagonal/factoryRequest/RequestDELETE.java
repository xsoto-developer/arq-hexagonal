package com.akkodis.xsoto.hexagonal.factoryRequest;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements IRequest {
    @Override
    public Response send(RequestInfo info) {
        Response response = given()
                .log().all()
                .when()
                .delete(info.getUrl());
        response.then().log().all();
        return response;
    }
}
