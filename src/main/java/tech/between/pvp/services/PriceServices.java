package tech.between.pvp.services;


import tech.between.pvp.DTOs.PriceRequestDTO;
import tech.between.pvp.DTOs.PriceResponseDTO;
import tech.between.pvp.exceptions.PricesNotFoundException;

public interface PriceServices {
    PriceResponseDTO findByProductFecha(PriceRequestDTO priceRequest) throws PricesNotFoundException;
}
