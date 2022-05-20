package tech.between.pvp.DTOs;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"productId", "brandId", "priceList","dateApp", "price"})
public class PriceResponseDTO {
    @ApiModelProperty(notes = "product_id", example = "35455", required = false)
    private Long productId;
    @ApiModelProperty(notes = "brandId", example = "1", required = false)
    private Integer brandId;
    @ApiModelProperty(notes = "price_list", example = "2 (id Price)", required = false)
    private Integer priceList;
    @ApiModelProperty(notes = "date_app", example = "2020-06-14T16:00:00", required = false)
    private LocalDateTime dateApp;
    @ApiModelProperty(notes = "price", example = "34.40", required = false)
    private BigDecimal price;
}
