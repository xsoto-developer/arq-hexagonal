package com.akkodis.xsoto.hexagonal;

import com.akkodis.xsoto.hexagonal.domain.model.dto.request.PricesRequest;
import com.akkodis.xsoto.hexagonal.util.ApiConfiguration;
import com.akkodis.xsoto.hexagonal.util.TestConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final long productId = 35455;
    private final long brandId = 1;

    private void genericTest(LocalDateTime applicationDate, long productId, long brandId) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TestConfiguration.FORMAT_LOCAL_DATE_TIME);
        PricesRequest requestBody = new PricesRequest();
        requestBody.setApplicationDate(applicationDate);
        requestBody.setProductId(productId);
        requestBody.setBrandId(brandId);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ApiConfiguration.READ_PROJECT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(requestBody.getProductId())) // Verifica un campo específico en el primer elemento del array
                .andExpect(jsonPath("$[0].brandId").value(requestBody.getBrandId())) // Verifica un campo específico en el segundo elemento del array
                .andExpect(jsonPath("$[0].applicationDate").value(requestBody.getApplicationDate().format(formatter))) // Verifica un campo específico en el segundo elemento del array
                .andExpect(jsonPath("$[0].price").value(notNullValue())) // Verifica un campo específico en el segundo elemento del array
                .andReturn();
    }

    @Test
    public void test_1() throws Exception {
        // Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-14 10:00:00"), productId, brandId);
    }

    @Test
    public void test_2() throws Exception {
        // Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
            genericTest(getDateTime("2020-06-14 16:00:00"), productId, brandId);
    }

    @Test
    public void test_3() throws Exception {
        // Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-14 21:00:00"), productId, brandId);
    }

    @Test
    public void test_4() throws Exception {
        // Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-15 10:00:00"), productId, brandId);
    }

    @Test
    public void test_5() throws Exception {
        // Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
        genericTest(getDateTime("2020-06-16 21:00:00"), productId, brandId);
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
