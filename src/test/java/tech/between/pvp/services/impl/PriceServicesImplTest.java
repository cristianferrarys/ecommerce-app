package tech.between.pvp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.between.pvp.DTOs.PriceRequestDTO;
import tech.between.pvp.DTOs.PriceResponseDTO;
import tech.between.pvp.entities.PricesEntity;
import tech.between.pvp.exceptions.PricesNotFoundException;
import tech.between.pvp.repositories.PricesRepository;
import tech.between.pvp.services.PriceServices;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
class PriceServicesImplTest {
    @MockBean
    PricesRepository pricesRepository;
    @Autowired
    PriceServices priceServices;
    @Autowired
    private ObjectMapper objectMapper;


    //-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    //-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    //-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    //-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    //-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void test1() throws IOException, PricesNotFoundException {
        // when
        LocalDateTime test1 = LocalDateTime.of(2020, 6, 14, 10, 00, 00);
        PriceRequestDTO priceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(test1).build();
        //give
        serviceExecute("test1");
        // then
        PriceResponseDTO pricesServicesExpected = priceServices.findByProductFecha(priceRequest);
        Assertions.assertEquals(pricesServicesExpected.getPrice(), new BigDecimal("35.50"));
        Assertions.assertEquals(pricesServicesExpected.getPriceList(), 1);
        Assertions.assertEquals(pricesServicesExpected.getProductId(), 35455);
        verify(pricesRepository, times(1)).findAlgo(any(), any(), any(), any());
    }

    @Test
    void test2() throws IOException, PricesNotFoundException {
        // when
        LocalDateTime test2 = LocalDateTime.of(2020, 6, 14, 16, 00, 00);
        PriceRequestDTO priceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(test2).build();
        //give
        serviceExecute("test2");
        // then
        PriceResponseDTO pricesServicesExpected = priceServices.findByProductFecha(priceRequest);
        Assertions.assertEquals(pricesServicesExpected.getPrice(), new BigDecimal("25.45"));
        Assertions.assertEquals(pricesServicesExpected.getPriceList(), 2);
        Assertions.assertEquals(pricesServicesExpected.getProductId(), 35455);
        verify(pricesRepository, times(1)).findAlgo(any(), any(), any(), any());
    }

    @Test
    void test3() throws IOException, PricesNotFoundException {
        // when
        LocalDateTime test3 = LocalDateTime.of(2020, 6, 14, 21, 00, 00);
        PriceRequestDTO priceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(test3).build();
        //give
        serviceExecute("test3");
        // then
        PriceResponseDTO pricesServicesExpected = priceServices.findByProductFecha(priceRequest);
        Assertions.assertEquals(pricesServicesExpected.getPrice(), new BigDecimal("35.50"));
        Assertions.assertEquals(pricesServicesExpected.getPriceList(), 1);
        Assertions.assertEquals(pricesServicesExpected.getProductId(), 35455);
        verify(pricesRepository, times(1)).findAlgo(any(), any(), any(), any());
    }

    @Test
    void test4() throws IOException, PricesNotFoundException {
        // when
        LocalDateTime test4 = LocalDateTime.of(2020, 6, 15, 10, 00, 00);
        PriceRequestDTO priceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(test4).build();
        //give
        serviceExecute("test4");
        // then
        PriceResponseDTO pricesServicesExpected = priceServices.findByProductFecha(priceRequest);
        Assertions.assertEquals(pricesServicesExpected.getPrice(), new BigDecimal("30.50"));
        Assertions.assertEquals(pricesServicesExpected.getPriceList(), 3);
        Assertions.assertEquals(pricesServicesExpected.getProductId(), 35455);
        verify(pricesRepository, times(1)).findAlgo(any(), any(), any(), any());
    }

    @Test
    void test5() throws IOException, PricesNotFoundException {
        // when
        final LocalDateTime test5 = LocalDateTime.of(2020, 6, 16, 21, 00, 00);
        final PriceRequestDTO priceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(test5).build();
        //give
        serviceExecute("test5");
        // then
        PriceResponseDTO pricesServicesExpected = priceServices.findByProductFecha(priceRequest);
        Assertions.assertEquals(pricesServicesExpected.getPrice(), new BigDecimal("38.95"));
        Assertions.assertEquals(pricesServicesExpected.getPriceList(), 4);
        Assertions.assertEquals(pricesServicesExpected.getProductId(), 35455);
        verify(pricesRepository, times(1)).findAlgo(any(), any(), any(), any());
    }

    @Test
    void exceptionDB_test() {
        // when
        LocalDateTime testException = LocalDateTime.of(2021, 6, 14, 10, 00, 00);
        PriceRequestDTO priceRequest = PriceRequestDTO.builder()
                .brandId(1)
                .productId(35455L)
                .dateApp(testException).build();
        //give
        when(pricesRepository.findAlgo(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        Assertions.assertThrows(PricesNotFoundException.class, () -> {
            priceServices.findByProductFecha(priceRequest);
        });
        verify(pricesRepository, times(1)).findAlgo(any(), any(), any(), any());
    }

    private void serviceExecute(String test) throws IOException {
        List<PricesEntity> pricesListMock = List.of(objectMapper.readValue(new File("src/test/resources/prices/" + test + ".json"), PricesEntity[].class));
        when(pricesRepository.findAlgo(any(), any(), any(), any())).thenReturn(pricesListMock);
    }
}
