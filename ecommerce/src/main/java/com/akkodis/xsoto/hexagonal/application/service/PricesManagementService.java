package com.akkodis.xsoto.hexagonal.application.service;

import com.akkodis.xsoto.hexagonal.application.mapper.PricesDtoMapper;
import com.akkodis.xsoto.hexagonal.application.mapper.PricesRequestMapper;
import com.akkodis.xsoto.hexagonal.application.usecases.PricesService;
import com.akkodis.xsoto.hexagonal.domain.model.dto.PricesDto;
import com.akkodis.xsoto.hexagonal.domain.model.dto.request.PricesRequest;
import com.akkodis.xsoto.hexagonal.domain.port.PricesPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PricesManagementService implements PricesService {

    private final PricesPersistencePort pricesPersistencePort;
    private final PricesRequestMapper pricesRequestMapper;
    private final PricesDtoMapper pricesDtoMapper;

    @Autowired
    public PricesManagementService(final PricesPersistencePort pricesPersistencePort,
                                   final PricesRequestMapper pricesRequestMapper,
                                   final PricesDtoMapper pricesDtoMapper) {
        this.pricesPersistencePort = pricesPersistencePort;
        this.pricesRequestMapper = pricesRequestMapper;
        this.pricesDtoMapper = pricesDtoMapper;
    }

    @Override
    public PricesDto createNew(PricesRequest request) {
        var pricesToCreate = pricesRequestMapper.toDomain(request);
        var pricesCreated = pricesPersistencePort.create(pricesToCreate);
        return pricesDtoMapper.toDto(pricesCreated);
    }

    @Override
    public PricesDto getById(Long id) {
        var prices = pricesPersistencePort.getById(id);
        return pricesDtoMapper.toDto(prices);
    }

    @Override
    public List<PricesDto> getAll() {
        var lstPrices = pricesPersistencePort.getAll();
        return lstPrices
                .stream()
                .map(pricesDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        pricesPersistencePort.deleteById(id);
    }

    @Override
    public PricesDto update(PricesRequest request, Long id) {
        var pricesToUpdate = pricesRequestMapper.toDomain(request);
        pricesToUpdate.setBrandId(request.getBrandId());
        pricesToUpdate.setProductId(request.getProductId());
        var taskUpdated = pricesPersistencePort.update(pricesToUpdate);
        return pricesDtoMapper.toDto(taskUpdated);
    }

    @Override
    public List<PricesDto> consulta(PricesRequest request) {
        var pricesToConsult = pricesRequestMapper.toDomain(request);
        var lstPrices = pricesPersistencePort.consulta(pricesToConsult);
        return lstPrices
                .stream()
                .map(pricesDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
