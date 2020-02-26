/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author fcastillo
 */
public class Reportes {

    public byte[] generarJasperReport(String servletRequest, String jasperReportName, ByteArrayOutputStream outputStream, Map parametros, Connection con) {
        JRPdfExporter exporter = new JRPdfExporter();

        try {
            String realPath = servletRequest + "/resources/jasper/" + jasperReportName + ".jrxml";

            InputStream is = new FileInputStream(new File(realPath));

            JasperDesign jasperDesign = JRXmlLoader.load(is);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con);

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (FileNotFoundException | JRException e) {
            e.getLocalizedMessage();
        }
        return outputStream.toByteArray();
    }

}
