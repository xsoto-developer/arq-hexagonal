package com.akkodis.xsoto.hexagonal.application.usecases;

import com.akkodis.xsoto.hexagonal.domain.model.dto.PricesDto;
import com.akkodis.xsoto.hexagonal.domain.model.dto.request.PricesRequest;

import java.util.List;

public interface PricesService {

    PricesDto createNew(PricesRequest request);

    PricesDto getById(Long id);

    List<PricesDto> getAll();

    void deleteById(Long id);

    PricesDto update(PricesRequest request, Long id);

    List<PricesDto> consulta(PricesRequest request);
}
