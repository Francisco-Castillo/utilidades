/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.utilidades.interfaces;

import java.util.List;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.core.Response;

/**
 *
 * @author fcastillo
 * @version Agosto 2020
 */
public interface IRespuestaJSON {

    /**
     *
     * @param statusCode - Codigo de estado HTTP
     * @param lstMensajes - Lista de mensajes a mostrar al cliente
     * @param arregloElementos - Listado de elementos a mostrar en consulta, en caso de no retornar listado puede ser null
     * @param totalRows - Cantidad total de elementos retornados, si no devuelve elementos enviar 0.
     * @return Respuesta JSON
     */
    public Response jsonResponse(int statusCode, List<String> lstMensajes, JsonArrayBuilder arregloElementos, int totalRows);

    /**
     * *
     *
     * @param statusCode - Codigo de estado HTTP
     * @param arregloElementos - Listado de elementos a retonar
     * @param totalRows - Cantidad de elementos retornados
     * @return - Respuesta JSON
     */
    public Response jsonResponse(int statusCode, JsonArrayBuilder arregloElementos, int totalRows);

    /**
     *
     * @param statusCode - Representa el codigo de estado a enviar al cliente
     * @param mensaje - Mensaje a mostrar
     * @return Respuesta JSON
     */
    public Response jsonSimpleResponse(int statusCode, String mensaje);

}
