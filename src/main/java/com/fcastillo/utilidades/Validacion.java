package com.fcastillo.utilidades;

/**
 *
 * @author fcastillo
 */
public class Validacion {

    /**
     * Metodo estatico que comprueba si un valor es nulo y retorna un valor por defecto.
     *
     * @param <T>
     * @param value objecto a comprobar
     * @param defaultValue valor a retornar por defecto
     * @return
     */
    public static <T> T defaultValue(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

}
