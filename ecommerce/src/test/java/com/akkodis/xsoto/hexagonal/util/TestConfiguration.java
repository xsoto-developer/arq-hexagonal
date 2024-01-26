package com.akkodis.xsoto.hexagonal.util;

public class TestConfiguration {
    public static final String FORMAT_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private TestConfiguration() {
        throw new AssertionError("No se permite instanciar la clase de Test     configuración");
    }
}
