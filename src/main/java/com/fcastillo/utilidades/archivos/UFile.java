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
    public void saveFile(InputStream uploadedInputStream, String uploadedFileLocation) {
        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
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
    public boolean isFileExists(String filePath) {
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
    public String getFileExtension(String nombrearchivo) {
        if (nombrearchivo.lastIndexOf(".") != -1 && nombrearchivo.lastIndexOf(".") != 0) {
            return nombrearchivo.substring(nombrearchivo.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
    //</editor-fold>

}
