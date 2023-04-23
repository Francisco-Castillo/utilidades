package com.fcastillo.utilidades;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase utilitaria para el manejo de Tokens
 *
 * @author Francisco Castillo
 * @version 0.1
 * @since 26/02/2020
 */
public class Token {

    /**
     * Metodo para generar un token a partir de un nombre de usuario
     *
     * @param username
     * @return
     */
    public static String generar(String username) {
        long minutosExpiracion = 60L;
        LocalDateTime periodoExpiracion = LocalDateTime.now().plusMinutes(minutosExpiracion);
        Date horaExpiracion = Date.from(periodoExpiracion.atZone(ZoneId.systemDefault()).toInstant());
        Key key = new SecretKeySpec("secret".getBytes(), "DES");
        String JWSCompact = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, key)
                .setIssuedAt(new Date())
                .setExpiration(horaExpiracion)
                .compact();
        return JWSCompact;
    }

}
