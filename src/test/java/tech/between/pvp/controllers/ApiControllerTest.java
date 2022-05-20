package tech.between.pvp.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import io.restassured.RestAssured;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tech.between.pvp.DTOs.PriceRequestDTO;
import tech.between.pvp.DTOs.PriceResponseDTO;
import tech.between.pvp.exceptions.PricesNotFoundException;
import tech.between.pvp.services.PriceServices;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {

    @LocalServerPort
    private Integer port;

    @MockBean
    PriceServices priceServices;
    String baseUrlRoot;

    @BeforeEach
    void setUp() {
        baseUrlRoot = "http://localhost:" + port+"/api/v1/price/product";
    }

    @Test
    void priceByProductController_OK() throws PricesNotFoundException {

        BigDecimal priceExpected = new BigDecimal("25.45");
        final PriceRequestDTO PriceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(LocalDateTime.now())
                .build();

        final PriceResponseDTO pricesEntityMock = PriceResponseDTO.builder()
                .price(priceExpected)
                .priceList(2)
                .build();


        when(priceServices.findByProductFecha(any())).thenReturn(pricesEntityMock);
        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .body(PriceRequest)
                .when()
                .post(baseUrlRoot)
                .then()
                .statusCode(200)
                .body("price", Matchers.equalTo(25.45F))
                .body("price_list", Matchers.equalTo(2));
        verify(priceServices, times(1)).findByProductFecha(any());

    }

    @Test
    void priceByProductController_NotFoundException() throws PricesNotFoundException {

        final String message = "is required";
        final PriceRequestDTO PriceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(LocalDateTime.now())
                .build();

        when(priceServices.findByProductFecha(any())).thenThrow(new PricesNotFoundException(message));

        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .body(PriceRequest)
                .when()
                .post(baseUrlRoot)
                .then()
                .statusCode(404)
                .body("message", Matchers.equalTo(message));
        verify(priceServices, times(1)).findByProductFecha(any());
    }

    @Test
    void priceByProductController_ValidationException() throws PricesNotFoundException {

        final String message = "is required";
        final PriceRequestDTO PriceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .dateApp(LocalDateTime.now())
                .build();
        RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json")
                .body(PriceRequest)
                .when()
                .post(baseUrlRoot)
                .then()
                .statusCode(400)
                .body("message", Matchers.containsString(message));
        verify(priceServices, times(0)).findByProductFecha(any());
    }
}
