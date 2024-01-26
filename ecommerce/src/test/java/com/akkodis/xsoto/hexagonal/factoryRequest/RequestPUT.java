package com.akkodis.xsoto.hexagonal.factoryRequest;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestPUT implements IRequest{
    @Override
    public Response send(RequestInfo info) {
        Response response = given()
                .body(info.getBody())
                .log().all()
        .when()
                .put(info.getUrl());

        response.then().log().all();
        return response;
    }
}
