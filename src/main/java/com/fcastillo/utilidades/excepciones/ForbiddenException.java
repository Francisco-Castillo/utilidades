/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.utilidades.excepciones;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author fcastillo
 */
public class ForbiddenException extends WebApplicationException {

    /**
     * HTTP Status Code 403 -  Forbidden Exception
     *
     * @param message
     */
    public ForbiddenException(ErrorMessage message) {
        super(Response.status(Response.Status.FORBIDDEN).entity(message).type("application/json").build());
    }
}
