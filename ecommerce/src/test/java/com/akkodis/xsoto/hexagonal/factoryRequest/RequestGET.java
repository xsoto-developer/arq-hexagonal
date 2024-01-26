package com.akkodis.xsoto.hexagonal.factoryRequest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestGET implements IRequest{
    @Override
    public Response send(RequestInfo info) {
        Response response =given()
                .body(info.getBody())
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(info.getUrl());
        response.then().log().all();
        return response;
    }
}
