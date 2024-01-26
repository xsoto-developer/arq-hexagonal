package com.akkodis.xsoto.hexagonal.domain.port;

import com.akkodis.xsoto.hexagonal.domain.model.Prices;

import java.util.List;

public interface PricesPersistencePort {
    Prices create(Prices request);

    Prices getById(Long id);

    List<Prices> getAll();

    void deleteById(Long id);

    Prices update(Prices request);

    List<Prices> getByIds(List<Long> tasksIds);

    List<Prices> consulta(Prices request);
}
