package tech.between.pvp.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PriceRequestDTO implements Serializable {
    @ApiModelProperty(notes = "date_app", example = "2020-06-14 16:00:00", required = true)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "date_app is required.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateApp;

    @ApiModelProperty(notes = "product_id", example = "35455", required = true)
    @NotNull(message = "product_id is required.")
    @Min(value = 1, message = "product_id min 1 is required.")
    private Long productId;
    @ApiModelProperty(notes = "brand_id", example = "1 (ZARA)", required = true)
    @NotNull(message = "brand_id is required.")
    @Min(value = 1, message = "brand_id min 1 is required.")
    private Integer brandId;
}
