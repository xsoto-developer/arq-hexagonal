package com.akkodis.xsoto.hexagonal.factoryRequest;

import io.restassured.response.Response;

public interface IRequest {
    Response send(RequestInfo info);
}
