package com.akkodis.xsoto.hexagonal.domain.model.dto.request;

import com.akkodis.xsoto.hexagonal.domain.model.constant.PricesDomainConstant;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PricesRequest {
    @NotNull(message = PricesDomainConstant.ErrorMessages.NOT_NULL)
    private LocalDateTime applicationDate;
    @NotNull(message = PricesDomainConstant.ErrorMessages.NOT_NULL)
    private Long productId;
    @NotNull(message = PricesDomainConstant.ErrorMessages.NOT_NULL)
    private Long brandId;
}
