package com.fcastillo.utilidades;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.util.Arrays;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
//</editor-fold>

/**
 * Clase utilitaria para el manejo de respuestas JSON
 *
 * @author Francisco Castillo
 * @version 0.1
 * @since 22/02/2020
 */
public class Respuesta {

    //<editor-fold defaultstate="collpased" desc="jsonResponse()">
    /**
     * Metodo
     *
     * @param statuscode
     * @param totalrows
     * @param lstMessages
     * @return
     */
    public Response jsonResponse(int statuscode, int totalrows, List<String> lstMessages) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        Response.Status status = getStatus(statuscode);

        job.add("status", Json.createObjectBuilder()
                .add("statusCode", status.getStatusCode())
                .add("statusMessage", status.getReasonPhrase())
                .add("listMessages", Json.createArrayBuilder(lstMessages)))
                .add("totalRows", totalrows);

        return Response.ok(job.build()).build();
    }//</editor-fold>

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
    
    

}
