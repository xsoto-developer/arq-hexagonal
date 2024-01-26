package com.akkodis.xsoto.hexagonal.application.mapper;

import com.akkodis.xsoto.hexagonal.domain.model.Prices;
import com.akkodis.xsoto.hexagonal.domain.model.dto.PricesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricesDtoMapper {
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "applicationDate", target = "applicationDate")
    PricesDto toDto(Prices domain);
}
