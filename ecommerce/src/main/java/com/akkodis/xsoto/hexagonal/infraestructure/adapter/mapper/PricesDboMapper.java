package com.akkodis.xsoto.hexagonal.infraestructure.adapter.mapper;

import com.akkodis.xsoto.hexagonal.domain.model.Prices;
import com.akkodis.xsoto.hexagonal.infraestructure.adapter.entity.PricesEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricesDboMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "brandId", target = "brandId")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "priority", target = "priority")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "curr", target = "curr")
    @Mapping(source = "applicationDate", target = "applicationDate")
    PricesEntity toDbo(Prices domain);

    @InheritInverseConfiguration
    Prices toDomain(PricesEntity entity);
}
