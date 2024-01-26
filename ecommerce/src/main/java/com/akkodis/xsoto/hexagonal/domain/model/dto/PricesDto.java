package com.akkodis.xsoto.hexagonal.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PricesDto {
    private Long productId;
    private Long brandId;
    private int priceList;
    private LocalDateTime applicationDate;
    private Double price;
}
