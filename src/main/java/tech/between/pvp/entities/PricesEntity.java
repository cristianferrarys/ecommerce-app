package tech.between.pvp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PRICES")
public class PricesEntity {

    @Id
    @Column(name = "PRICE_LIST")
    private Integer priceList;

    @Column(name = "BRAND_ID")
    private Integer brandId;

    @Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;

    @Column(name = "END_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURR")
    private String curr;

}
