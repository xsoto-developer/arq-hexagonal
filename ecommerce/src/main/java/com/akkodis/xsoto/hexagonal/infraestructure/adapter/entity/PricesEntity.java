package com.akkodis.xsoto.hexagonal.infraestructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "prices")
public class PricesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private int priceList;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "priority")
    private int priority;
    @Column(name = "price")
    private Double price;
    @Column(name = "curr")
    private String curr;
    @Transient
    private LocalDateTime applicationDate;
}
