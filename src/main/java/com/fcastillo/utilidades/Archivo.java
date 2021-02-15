package com.fcastillo.utilidades;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.io.File;
//</editor-fold>

/**
 * Clase utilitaria para el manejo de archivos
 *
 * @author Francisco Castillo
 * @version 0.1
 * @since 23/02/2020
 */
public class Archivo {

    //<editor-fold defaultstate="collapsed()" desc="listarArchivos()">
    /**
     * Metodo que retorna un listado de archivos de un directorio
     *
     * @param nombreDirectorio
     * @return
     */
    public static File[] listarArchivos(String nombreDirectorio) {
        File directorio = new File(nombreDirectorio);
        File[] lstFiles = directorio.listFiles();
        return lstFiles;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="obtenerExtension()">
    /**
     * Metodo que retorna el nombre de la extensión de un archivo
     *
     * @param nombrearchivo
     * @return
     */
    public String obtenerExtension(String nombrearchivo) {
        if (nombrearchivo.lastIndexOf(".") != -1 && nombrearchivo.lastIndexOf(".") != 0) {
            return nombrearchivo.substring(nombrearchivo.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
    //</editor-fold>
}
