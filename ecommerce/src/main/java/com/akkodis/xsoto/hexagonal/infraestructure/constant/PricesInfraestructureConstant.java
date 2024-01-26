package com.akkodis.xsoto.hexagonal.infraestructure.constant;

public class PricesInfraestructureConstant {
    public static class Endpoints {
        public static final String BASE = "/api/v1/prices";
        public static final String GET_ID = "/{id}";
        public static final String GET_PRICES = "/consulta";
    }

    public static class ErrorMessages {
        public static final String PRICES_NOT_FOUND_MESSAGE_ERROR = "Registro no encontrado";
        public static final String PRICES_DELETE_MESSAGE_ERROR = "Error al eliminar el registro";
        public static final String PRICE_NOT_FOUND_MESSAGE = "No se encontró el registro con ID: %s";
        public static final String ERROR_DETECTED = "Excepción detectada: {}";
    }

    public static class Messages {
        public static final String MESSAGE_SUCCESSFUL = "Registro Creado Correctamente";
    }
}
