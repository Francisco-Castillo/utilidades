/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.utilidades.rest;

import com.fcastillo.utilidades.interfaces.IRespuestaJSON;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;

/**
 *
 * @author fcastillo
 * @version Agosto 2020
 */
public class Respuesta implements IRespuestaJSON {

    @Override
    public Response jsonResponse(int statusCode, List<String> lstMensajes, JsonArrayBuilder arregloElementos, int totalRows) {

        JsonObjectBuilder job = Json.createObjectBuilder();

        Response.Status status = getStatus(statusCode);

        job.add("status", Json.createObjectBuilder()
                .add("statusCode", status.getStatusCode())
                .add("statusMessage", status.getReasonPhrase())
                .add("listMessages", Json.createArrayBuilder(lstMensajes)))
                .add("totalRows", totalRows)
                .add("objeto", arregloElementos);

        return Response.status(status).entity(job.build()).build();
    }

    @Override
    public Response jsonSimpleResponse(int statusCode, String mensaje) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();

        Response.Status status = getStatus(statusCode);

        job.add("status", Json.createObjectBuilder()
                .add("statusCode", status.getStatusCode())
                .add("statusMessage", status.getReasonPhrase()))
                .add("message", mensaje);

        return Response.status(status).entity(job.build()).build();
    }

    //<editor-fold defaultstate="collapsed" desc="getStatus()">
    /**
     * Método que retorna un objeto de tipo status
     *
     * @param statuscode codigo de estado
     * @return
     */
    private Response.Status getStatus(int statuscode) {
        Response.Status[] arrStatus = Response.Status.values();
        Response.Status status = Arrays.stream(arrStatus)
                .filter(x -> x.getStatusCode() == statuscode)
                .findFirst().orElse(null);
        return status;
    }//</editor-fold>

    @Override
    public Response jsonResponse(int statusCode, JsonArrayBuilder arregloElementos, int totalRows) {
        JsonObjectBuilder job = Json.createObjectBuilder();

        Response.Status status = getStatus(statusCode);

        job.add("status", Json.createObjectBuilder()
                .add("statusCode", status.getStatusCode())
                .add("statusMessage", status.getReasonPhrase()))
                .add("totalRows", totalRows)
                .add("objeto", arregloElementos);

        return Response.status(status).entity(job.build()).build();
    }

}
