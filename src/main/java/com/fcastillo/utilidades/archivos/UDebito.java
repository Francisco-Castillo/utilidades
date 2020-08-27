/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.utilidades.archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author fcastillo
 */
public class UDebito {

    //--------------------------------------Header---------------------------------------------
    public String getFillerInicial(String linea) {
        return linea.substring(0, 1).trim();
    }

    public String getNroConvenio(String linea) {
        return linea.substring(1, 6).trim();
    }

    public String getNroServicio(String linea) {
        return linea.substring(6, 16).trim();
    }

    public String getNroEmpresa(String linea) {
        return linea.substring(16, 21).trim();
    }

    public String getFechaGeneracion(String linea) {
        return linea.substring(21, 29).trim();
    }

    public String getImporteTotal(String linea) {
        return linea.substring(29, 47).trim();
    }

    public String getMoneda(String linea) {
        return linea.substring(47, 50).trim();
    }

    public String getTipoMovimiento(String linea) {
        return linea.substring(50, 52).trim();
    }

    public String getInfoMonetaria(String linea) {
        return linea.substring(52, 150).trim();
    }

    public String getFillerFinal(String linea) {
        return linea.substring(linea.length() - 1, linea.length()).trim();
    }

    //--------------------------------------Detalle---------------------------------------------
    public String getCodigoBanco(String linea) {
        return linea.substring(21, 24).trim();
    }

    public String getCodigoSucursalCuenta(String linea) {
        return linea.substring(24, 28).trim();
    }

    public String getTipoCuenta(String linea) {
        String valor = linea.substring(28, 29).trim();
        return valor.equals("") ? "-1" : valor;
    }

    public String getCuentaBancaria(String linea) {
        return linea.substring(29, 44).trim();
    }

    public String getIdCliente(String linea) {
        return linea.substring(44, 66).trim();
    }

    public String getIdDebito(String linea) {
        return linea.substring(66, 81).trim();
    }

    public String getFuncionMov(String linea) {
        return linea.substring(81, 83).trim();
    }

    public String getMotivoRechazo(String linea) {
        return linea.substring(83, 87).trim();
    }

    public String getFechaVencimiento(String linea) {
        return linea.substring(87, 95).trim();
    }

    public String getMonedaConv(String linea) {
        return linea.substring(95, 98).trim();
    }

    public String getImporteAdebitar(String linea) {
        return linea.substring(98, 111).trim();
    }

    public String getFechaReintento(String linea) {
        return linea.substring(111, 119).trim();
    }

    public String getImporteDebitado(String linea) {
        return linea.substring(119, 132).trim();
    }

    public String getNuevaSucBanco(String linea) {
        return linea.substring(132, 136).trim();
    }

    public String getNuevaTipoCuenta(String linea) {
        String valor = linea.substring(136, 137).trim();
        return valor.equals("") ? "-1" : valor;
    }

    public String getNuevaCuentaBco(String linea) {
        return linea.substring(137, 152).trim();
    }

    public String getNuevaIDCliente(String linea) {
        return linea.substring(152, 174).trim();
    }

    public String getDatosRetorno(String linea) {
        return linea.substring(174, 214).trim();
    }

    //<editor-fold defaultstate="collapsed" desc="readDebitoFile()"> 
    public void readDebitoFile(String pathFile) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String linea;

            int posicion = 0;

            while ((linea = br.readLine()) != null) {

                if (posicion == 0) {
                    System.out.println("Header");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Filler : " + getFillerInicial(linea));
                    System.out.println("Numero de convenio : " + getNroConvenio(linea));
                    System.out.println("Numero de Servicio : " + getNroServicio(linea));
                    System.out.println("Numero de Empresa : " + getNroEmpresa(linea));
                    System.out.println("Fecha : " + getFechaGeneracion(linea));
                    System.out.println("Importe Total : " + getImporteTotal(linea));
                    System.out.println("Moneda : " + getMoneda(linea));
                    System.out.println("Tipo Movimiento : " + getTipoMovimiento(linea));
                    System.out.println("Info Monetaria : " + getInfoMonetaria(linea));
                    System.out.println("Filler Final : " + getFillerFinal(linea));
                }

                if (posicion != 0) {
                    System.out.println("\nDETALLE");
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Filler: " + getFillerFinal(linea));
                    System.out.println("Nro convenio: " + getNroConvenio(linea));
                    System.out.println("Nro Servicio: " + getNroServicio(linea));
                    System.out.println("Nro Empresa: " + getNroEmpresa(linea));

                    System.out.println("Codigo Banco: " + getCodigoBanco(linea));
                    System.out.println("Codigo Sucursal cuenta: " + getCodigoSucursalCuenta(linea));
                    System.out.println("Tipo cuenta: " + getTipoCuenta(linea));
                    System.out.println("Cuenta Bancaria: " + getCuentaBancaria(linea));
                    System.out.println("Id actual cliente: " + getIdCliente(linea));
                    System.out.println("ID DEBITO: " + getIdDebito(linea));
                    System.out.println("Funcion mov: " + getFuncionMov(linea));
                    System.out.println("MOtivo Rechazo: " + getMotivoRechazo(linea));
                    System.out.println("Fecha Vencimiento: " + getFechaVencimiento(linea));
                    System.out.println("Importe a debitar: " + getImporteAdebitar(linea));
                    System.out.println("Fecha Reintento: " + getFechaReintento(linea));
                    System.out.println("Importe debitado: " + getImporteDebitado(linea));
                    System.out.println("NUeva sucursal bancaria: " + getNuevaSucBanco(linea));
                    System.out.println("Nueva tipo cuenta: " + getNuevaTipoCuenta(linea));
                    System.out.println("Nueva cuenta bancaria: " + getNuevaCuentaBco(linea));
                    System.out.println("Nueva id cliente: " + getNuevaIDCliente(linea));
                    System.out.println("Datos retorno: " + getDatosRetorno(linea));
                    System.out.println("-----------------------------------------------------\n ");
                }
                posicion++;
            }
        } catch (IOException e) {
            throw e;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getCodigoRechazo()">
    public int getCodigoRechazo(String cadena) {
        int codigo = 0;
        switch (cadena) {
            case "R02":
                codigo = 1;
                break;
            case "R03":
                codigo = 2;
                break;
            case "R04":
                codigo = 3;
                break;
            case "R08":
                codigo = 4;
                break;
            case "R10":
                codigo = 5;
                break;
            case "R14":
                codigo = 6;
                break;
            case "R15":
                codigo = 7;
                break;
            case "R17":
                codigo = 8;
                break;
            case "R19":
                codigo = 9;
                break;
            case "R20":
                codigo = 10;
                break;
            case "R23":
                codigo = 11;
                break;
            case "R24":
                codigo = 12;
                break;
            case "R26":
                codigo = 13;
                break;
            default:
                codigo = 14;
        }
        return codigo;
    }//</editor-fold>
}
