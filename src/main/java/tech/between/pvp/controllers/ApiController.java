package tech.between.pvp.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.between.pvp.DTOs.PriceRequestDTO;
import tech.between.pvp.DTOs.PriceResponseDTO;
import tech.between.pvp.entities.PricesEntity;
import tech.between.pvp.exceptions.PricesNotFoundException;
import tech.between.pvp.services.PriceServices;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private PriceServices priceServices;

    @PostMapping("/price/product")
    @ApiOperation(value = "Obtiene el precio del producto en una fecha determinada.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The price was not found"),
            @ApiResponse(code = 400, message = "Bad Request - Valid is required")
    })
    public PriceResponseDTO priceByProduct(@RequestBody @Valid PriceRequestDTO priceRequest)
            throws PricesNotFoundException {
        log.info("PriceResponseDTO :{}", priceRequest);
        return priceServices.findByProductFecha(priceRequest);
    }
}
