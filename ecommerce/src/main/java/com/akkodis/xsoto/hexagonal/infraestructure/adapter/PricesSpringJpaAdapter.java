package com.akkodis.xsoto.hexagonal.infraestructure.adapter;

import com.akkodis.xsoto.hexagonal.domain.model.Prices;
import com.akkodis.xsoto.hexagonal.domain.port.PricesPersistencePort;
import com.akkodis.xsoto.hexagonal.infraestructure.adapter.entity.PricesEntity;
import com.akkodis.xsoto.hexagonal.infraestructure.adapter.exception.PricesException;
import com.akkodis.xsoto.hexagonal.infraestructure.adapter.mapper.PricesDboMapper;
import com.akkodis.xsoto.hexagonal.infraestructure.adapter.repository.PricesRepository;
import com.akkodis.xsoto.hexagonal.infraestructure.constant.PricesInfraestructureConstant;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PricesSpringJpaAdapter implements PricesPersistencePort {

    private final PricesRepository pricesRepository;
    private final PricesDboMapper pricesDboMapper;

    public PricesSpringJpaAdapter(PricesRepository pricesRepository, PricesDboMapper pricesDboMapper) {
        this.pricesRepository = pricesRepository;
        this.pricesDboMapper = pricesDboMapper;
    }

    @Override
    public Prices create(Prices request) {
        var pricesToSave = pricesDboMapper.toDbo(request);
        var pricesSaved = pricesRepository.save(pricesToSave);
        return pricesDboMapper.toDomain(pricesSaved);
    }

    @Override
    public Prices getById(Long id) {
        var optionalPrice = pricesRepository.findById(id);
        if (optionalPrice.isEmpty()) {
            throw new PricesException(HttpStatus.NOT_FOUND, String.format(PricesInfraestructureConstant.ErrorMessages.PRICES_NOT_FOUND_MESSAGE_ERROR, id));
        }
        return pricesDboMapper.toDomain(optionalPrice.get());
    }

    @Override
    public List<Prices> getAll() {
        return pricesRepository.findAll()
                .stream()
                .map(pricesDboMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        var optionalPrice = pricesRepository.findById(id);
        if (optionalPrice.isEmpty()) {
            throw new PricesException(HttpStatus.NOT_FOUND, String.format(PricesInfraestructureConstant.ErrorMessages.PRICES_NOT_FOUND_MESSAGE_ERROR, id));
        }
        pricesRepository.deleteById(id);
    }

    @Override
    public Prices update(Prices request) {
        var pricesToUpdate = pricesDboMapper.toDbo(request);
        var pricesUpdated = pricesRepository.save(pricesToUpdate);

        return pricesDboMapper.toDomain(pricesUpdated);
    }

    @Override
    public List<Prices> getByIds(List<Long> pricesIds) {
        return pricesRepository.findByIdIn(pricesIds)
                .stream()
                .map(pricesDboMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Prices> consulta(Prices request) {
        return pricesRepository.getByApplicationDate(request.getApplicationDate(),
                        request.getProductId(),
                        request.getBrandId(), PageRequest.of(0, 1)
                )
                .stream()
                .map(result -> mapToPrices(result, request.getApplicationDate()))
                .collect(Collectors.toList());
    }

    private Prices mapToPrices(PricesEntity result, LocalDateTime applicationDate) {
        Prices prices = pricesDboMapper.toDomain(result);
        prices.setApplicationDate(applicationDate);
        return prices;
    }

}
