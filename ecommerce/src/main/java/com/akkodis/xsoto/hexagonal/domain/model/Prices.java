package com.akkodis.xsoto.hexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prices {
    private Long id;
    private Long brandId;
    private Date startDate;
    private Date endDate;
    private int priceList;
    private Long productId;
    private int priority;
    private Double price;
    private String curr;
    private LocalDateTime applicationDate;
}
