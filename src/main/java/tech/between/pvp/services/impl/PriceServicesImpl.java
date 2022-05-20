package tech.between.pvp.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.between.pvp.DTOs.PriceRequestDTO;
import tech.between.pvp.DTOs.PriceResponseDTO;
import tech.between.pvp.exceptions.PricesNotFoundException;
import tech.between.pvp.repositories.PricesRepository;
import tech.between.pvp.services.PriceServices;

@Slf4j
@Service
public class PriceServicesImpl implements PriceServices {
    @Autowired
    private PricesRepository pricesRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public PriceResponseDTO findByProductFecha(final PriceRequestDTO priceRequest) throws PricesNotFoundException {
        return pricesRepository.findAlgo(
                        priceRequest.getProductId(),
                        priceRequest.getBrandId(),
                        priceRequest.getDateApp(),
                        PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .map(response -> PriceResponseDTO.builder()
                        .brandId(response.getBrandId())
                        .productId(response.getProductId())
                        .dateApp(priceRequest.getDateApp())
                        .priceList(response.getPriceList())
                        .price(response.getPrice())
                        .build()
                )
                .orElseThrow(() -> new PricesNotFoundException("price not found on date "+ priceRequest.getDateApp()));
    }
}
