package com.akkodis.xsoto.hexagonal.infraestructure.rest.controller;

import com.akkodis.xsoto.hexagonal.application.usecases.PricesService;
import com.akkodis.xsoto.hexagonal.domain.model.dto.PricesDto;
import com.akkodis.xsoto.hexagonal.domain.model.dto.request.PricesRequest;
import com.akkodis.xsoto.hexagonal.infraestructure.constant.PricesInfraestructureConstant;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(PricesInfraestructureConstant.Endpoints.BASE)

public class PricesController {
    private final PricesService pricesService;

    public PricesController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping(PricesInfraestructureConstant.Endpoints.GET_ID)
    public ResponseEntity<PricesDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pricesService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<PricesDto>> getAll() {
        return ResponseEntity.ok(pricesService.getAll());
    }

    @GetMapping(PricesInfraestructureConstant.Endpoints.GET_PRICES)
    public ResponseEntity<?> obtenerConsulta(@Valid @RequestBody PricesRequest pricesRequest,
                                             BindingResult result) {
        return result.hasErrors() ? validar(result) : ResponseEntity.ok(pricesService.consulta(pricesRequest));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody PricesRequest taskRequest, BindingResult result) {
        pricesService.createNew(taskRequest);
        return result.hasErrors() ? validar(result) : new ResponseEntity<>(pricesService.createNew(taskRequest), HttpStatus.CREATED);
    }

    @PutMapping(PricesInfraestructureConstant.Endpoints.GET_ID)
    public ResponseEntity<?> edit(@Valid @RequestBody PricesRequest taskRequest, BindingResult result,
                                  @PathVariable Long id) {
        return result.hasErrors() ? validar(result) : ResponseEntity.ok(pricesService.update(taskRequest, id));
    }

    @DeleteMapping(PricesInfraestructureConstant.Endpoints.GET_ID)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            pricesService.deleteById(id);
            return ResponseEntity.ok(PricesInfraestructureConstant.Messages.MESSAGE_SUCCESSFUL);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(PricesInfraestructureConstant.ErrorMessages.PRICE_NOT_FOUND_MESSAGE, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(PricesInfraestructureConstant.ErrorMessages.PRICES_DELETE_MESSAGE_ERROR);
        }
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
