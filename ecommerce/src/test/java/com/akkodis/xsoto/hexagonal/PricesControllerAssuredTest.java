package com.akkodis.xsoto.hexagonal;

import com.akkodis.xsoto.hexagonal.factoryRequest.FactoryRequest;
import com.akkodis.xsoto.hexagonal.factoryRequest.RequestInfo;
import com.akkodis.xsoto.hexagonal.util.ApiConfiguration;
import com.akkodis.xsoto.hexagonal.util.TestConfiguration;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.hamcrest.Matchers.equalTo;

public class PricesControllerAssuredTest {
    Response response;
    org.json.JSONObject body = new org.json.JSONObject();
    RequestInfo requestInfo = new RequestInfo();
    private final int productId = 35455;
    private final int brandId = 1;

    private void genericTest(LocalDateTime applicationDate, int productId, int brandId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TestConfiguration.FORMAT_LOCAL_DATE_TIME);
        body.put("applicationDate", applicationDate.format(formatter));
        body.put("productId", productId);
        body.put("brandId", brandId);
        requestInfo.setUrl(ApiConfiguration.READ_PROJECT);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("get").send(requestInfo);

    }

    @Test
    public void test_1() {
        // Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-14 10:00:00"), productId, brandId);
        response.then()
                .body("productId[0]", equalTo(body.get("productId")))
                .body("brandId[0]", equalTo(body.get("brandId")))
                .body("applicationDate[0]", equalTo(body.get("applicationDate")))
                .body("price[0]", equalTo(35.5F))
                .statusCode(200);
    }

    @Test
    public void test_2() {
        // Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-14 16:00:00"), productId, brandId);
        response.then()
                .body("productId[0]", equalTo(body.get("productId")))
                .body("brandId[0]", equalTo(body.get("brandId")))
                .body("applicationDate[0]", equalTo(body.get("applicationDate")))
                .body("price[0]", equalTo(25.45F))
                .statusCode(200);
    }

    @Test
    public void test_3() {
        // Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-14 21:00:00"), productId, brandId);
        response.then()
                .body("productId[0]", equalTo(body.get("productId")))
                .body("brandId[0]", equalTo(body.get("brandId")))
                .body("applicationDate[0]", equalTo(body.get("applicationDate")))
                .body("price[0]", equalTo(35.5F))
                .statusCode(200);
    }

    @Test
    public void test_4() {
        // Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-15 10:00:00"), productId, brandId);
        response.then()
                .body("productId[0]", equalTo(body.get("productId")))
                .body("brandId[0]", equalTo(body.get("brandId")))
                .body("applicationDate[0]", equalTo(body.get("applicationDate")))
                .body("price[0]", equalTo(30.5F))
                .statusCode(200);
    }

    @Test
    public void test_5() {
        // Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-16 21:00:00"), productId, brandId);
        response.then()
                .body("productId[0]", equalTo(body.get("productId")))
                .body("brandId[0]", equalTo(body.get("brandId")))
                .body("applicationDate[0]", equalTo(body.get("applicationDate")))
                .body("price[0]", equalTo(38.95F))
                .statusCode(200);
    }

    private LocalDateTime getDateTime(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TestConfiguration.FORMAT_DATE_TIME);
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error al analizar la fecha y hora: " + e.getMessage());
            throw e;
        }
    }
}
