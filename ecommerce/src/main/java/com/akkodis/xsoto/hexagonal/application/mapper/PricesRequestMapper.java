package com.akkodis.xsoto.hexagonal.application.mapper;

import com.akkodis.xsoto.hexagonal.domain.model.Prices;
import com.akkodis.xsoto.hexagonal.domain.model.dto.request.PricesRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricesRequestMapper {
    @Mapping(source = "applicationDate", target = "applicationDate")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brandId")
    Prices toDomain(PricesRequest request);
}
