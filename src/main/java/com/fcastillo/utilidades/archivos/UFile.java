package com.fcastillo.utilidades.archivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author fcastillo
 */
public class UFile {

    //<editor-fold defaultstate="collapsed" desc="saveFile()">
    public static void saveFile(InputStream uploadedInputStream, String uploadedFileLocation, String fileName) {
        String path = uploadedFileLocation.concat(fileName);
        File file = new File(path);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            // read the submitted file as chunks, and write to the server's file
            byte[] buff = new byte[5 * 1024];
            int len;
            while ((len = uploadedInputStream.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
            fos.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="isFileExists()">
    /**
     *
     * @param filePath ruta completa en donde se encuentra el archivo
     * @return
     */
    public static boolean isFileExists(String filePath) {
        File tempFile = new File(filePath);
        return tempFile.exists();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getFileExtension()">
    /**
     * Metodo que retorna la extensión de un archivo
     *
     * @param nombrearchivo
     * @return
     */
    public static String getFileExtension(String nombrearchivo) {
        if (nombrearchivo.lastIndexOf(".") != -1 && nombrearchivo.lastIndexOf(".") != 0) {
            return nombrearchivo.substring(nombrearchivo.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
    //</editor-fold>

}
