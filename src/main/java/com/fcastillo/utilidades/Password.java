package com.fcastillo.utilidades;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//</editor-fold>

/**
 * Clase utilitaria para el manejo de passwords
 *
 * @author fcastillo
 * @version 0.1
 * @since 22/02/2020
 */
public class Password {

    //<editor-fold defaultstate="collapsed" desc="fields">
    public static String NUMEROS = "0123456789";
    public static String MAYUSCULAS = "ABCDEFGHIJLMNOPQRSTUVWXYZ";
    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static String ESPECIALES = "!@#*ñÑ";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="encriptar()">
    public static String encriptar(String password) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            byte[] mb = md.digest();

            for (int i = 0; i < mb.length; i++) {
                sb.append(Integer.toString((mb[i] & 0xff) + 0x100, 16).substring(1));

            }
        } catch (NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPin()">
    /**
     * Metodo que genera un pin aleatorio de 4 digitos
     *
     * @return
     */
    public static String getPin() {
        return generarPassword(NUMEROS, 4);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getPassword()">
    /**
     * Metodo para generar un password alfanumerico aleatorio
     *
     * @param cadena caracteres a incluir
     * @param longitud cantidad de caracteres
     * @return
     */
    public static String getPassword(String cadena, int longitud) {
        return generarPassword(cadena, longitud);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getStandardPassword()">
    /**
     * Metodo para obtener un password generico
     *
     * @param length cantidad de caracteres con las que se generará el password
     * @return
     */
    public static String getStandardPassword(int length) {
        return generarPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="generarPassword()">
    private static String generarPassword(String key, int length) {
        String pswd = "";
        for (int i = 0; i < length; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;
    }//</editor-fold>
}
