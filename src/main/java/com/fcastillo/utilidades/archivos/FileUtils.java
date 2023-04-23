package com.fcastillo.utilidades.archivos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author fcastillo
 */
public class FileUtils {

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

    public static void saveMultipleFiles(List<UploadedFile> files, String uploadDirectory) throws Exception {

	try {
	    if (CollectionUtils.isNotEmpty(files)) {

		for (UploadedFile file : files) {

		    byte[] bytes = file.getContent();

		    String fileName = file.getFileName();

		    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadDirectory + fileName)));

		    stream.write(bytes);
		    stream.close();
		}

	    }
	} catch (Exception e) {
	    throw e;
	}
    }

    //<editor-fold defaultstate="collapsed" desc="isFileExists()">
    /**
     *
     * @param filePath ruta completa en donde se encuentra el archivo
     * @return
     */
    public static boolean isFileExists(String filePath) {
	Path path = Paths.get(filePath);
	return Files.exists(path);
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

    /**
     * Metodo encargado de empaquetar un conjunto de archivos en un .zip
     *
     * @param outputName Nombre de archivo zip
     * @param files Arreglo de Strings que representan las rutas de los archivos
     * a incluir en el archivo .zip
     * @throws IOException
     */
    public static void downloadFilesAsZip(String outputName, String... files) throws IOException {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	ExternalContext externalContext = facesContext.getExternalContext();

	externalContext.responseReset();
	externalContext.setResponseContentType("application/zip");
	externalContext.setResponseHeader("Content-Disposition", "attachment; filename=" + outputName + ".zip");

	List<File> listOfFiles = Arrays.stream(files).map(item -> {
	    File file = new File(item);
	    return file;
	}).collect(Collectors.toList());

	OutputStream outputStream = externalContext.getResponseOutputStream();
	ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));

	for (File file : listOfFiles) {
	    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
	    FileInputStream fileInputStream;
	    try {
		fileInputStream = new FileInputStream(file);
	    } catch (FileNotFoundException e) {
		zipOutputStream.write(("No se encontro archivo." + file.getName()).getBytes());
		zipOutputStream.closeEntry();
		continue;
	    }

	    BufferedInputStream bufferedInputStream;
	    bufferedInputStream = new BufferedInputStream(fileInputStream);

	    int data = 0;

	    while ((data = bufferedInputStream.read()) != -1) {
		zipOutputStream.write(data);
	    }
	    bufferedInputStream.close();

	    zipOutputStream.closeEntry();
	}

	zipOutputStream.close();

	facesContext.responseComplete();
    }

    /**
     *
     * @param rootPath Ubicacion del directorio raiz
     * @param directoryName Nombre del nuevo directorio a crear
     * @throws IOException excepcion del tipo NoSuchFileException en caso de que
     * el archivo no exista
     */
    public static void createDirectory(String rootPath, String directoryName) throws IOException {

	String fullPath = rootPath.concat(directoryName);

	Path path = Paths.get(fullPath);

	Files.createDirectory(path);

    }

    public static File uploadedFileToFileConverter(UploadedFile uf) {
	InputStream inputStream = null;
	OutputStream outputStream = null;
	//Add you expected file encoding here:
	System.setProperty("file.encoding", "UTF-8");
	File newFile = new File(uf.getFileName());
	try {
	    inputStream = uf.getInputStream();
	    outputStream = new FileOutputStream(newFile);
	    int read = 0;
	    byte[] bytes = new byte[1024];
	    while ((read = inputStream.read(bytes)) != -1) {
		outputStream.write(bytes, 0, read);
	    }
	} catch (IOException e) {
	    //Do something with the Exception (logging, etc.)
	}
	return newFile;
    }

}
