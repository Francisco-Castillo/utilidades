package com.fcastillo.utilidades;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
//</editor-fold>

/**
 * Clase utilitaria para el manejo de fechas
 *
 * @author Francisco Castillo
 * @version 0.1
 * @since 23/02/2020
 */
public class Fecha {

    //<editor-fold defaultstate="collapsed" desc="getOnlyDate()">
    public static String getOnlyDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getOnlyHour()">
    public static String getOnlyHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDateHour()">
    public static String getDateHour(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        return dateFormat.format(date);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDayOfWeek()">
    public static int getDayOfWeek(LocalDate date) {
        return date.getDayOfWeek().getValue();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDayOfMonth()">
    public static int getDayOfMonth(LocalDate date) {
        return date.getDayOfMonth();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getMonth()">
    public static int getMonth(LocalDate date) {
        return date.getMonthValue();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getYear()">
    public static int getYear(LocalDate date) {
        return date.getYear();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getStringDate()">
    public static String getFormattedDate(LocalDate date) {
        StringBuilder sb = new StringBuilder();
        sb.append(nombreDia(getDayOfWeek(date)));
        sb.append(getDayOfMonth(date));
        sb.append(" de ");
        sb.append(nombreMes(getMonth(date))).append(" de ").append(getYear(date));

        return sb.toString();

    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="asLocalDate()">
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }//</editor-fold>

    //<editor-fold defaultstate="collpased" desc="addHoursToDate()">
    public static Date addHoursToDate(Date fecha, int horas) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.HOUR, horas);
        return c.getTime();
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="mostrarFechaActual()">
    public String mostrarFechaActual() {
        LocalDate localDate = LocalDate.now();
        return getFormattedDate(localDate);
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="nombreMes()">
    public static String nombreMes(int numeroDeMes) {
        String mes;
        switch (numeroDeMes) {
            case 1:
                mes = "Enero";
                break;
            case 2:
                mes = "Febrero";
                break;
            case 3:
                mes = "Marzo";
                break;
            case 4:
                mes = "Abril";
                break;
            case 5:
                mes = "Mayo";
                break;
            case 6:
                mes = "Junio";
                break;
            case 7:
                mes = "Julio";
                break;
            case 8:
                mes = "Agosto";
                break;
            case 9:
                mes = "Septiembre";
                break;
            case 10:
                mes = "octubre";
                break;
            case 11:
                mes = "noviembre";
                break;
            case 12:
                mes = "diciembre";
                break;
            default:
                mes = "Mes no válido";
                break;
        }
        return mes;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="nombreDia()">
    public static String nombreDia(int numeroDelDia) {
        String dia;
        switch (numeroDelDia) {
            case 1:
                dia = "Lunes ";
                break;
            case 2:
                dia = "Martes ";
                break;
            case 3:
                dia = "Miércoles ";
                break;
            case 4:
                dia = "Jueves ";
                break;
            case 5:
                dia = "Viernes ";
                break;
            case 6:
                dia = "Sábado ";
                break;
            case 7:
                dia = "Domingo ";
                break;
            default:
                dia = "No valido ";
                break;
        }
        return dia;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="fechaMenor()">
    public static boolean fechaMenor(Date startDate, Date endDate) {
        boolean esValida = true;

        if (startDate.after(endDate)) {
            esValida = false;
        }
        return esValida;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="calcularEdad()">
    public String calcularEdad(Date fnacimiento) {
        if (fnacimiento != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate localDate = Instant.ofEpochMilli(fnacimiento.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between(localDate, ahora);
            return "" + periodo.getYears() + " años y " + periodo.getMonths() + " meses";
        }
        return "";

    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ISO8601()">
    public static String ISO8601(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="stringToLocalDate()">
    /**
     *
     * @param date String con formato yyyMMdd que representa una fecha
     * @return LocalDate con formato yyyy-MM-dd
     */
    public static LocalDate stringToLocalDate(String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = 0;
        int day = 0;
        String sMes = date.substring(4, 6);
        String sDia = date.substring(6, date.length());

        if (sMes.charAt(0) == '0') {
            month = Integer.parseInt(Character.toString(sMes.charAt(1)));
        } else {
            month = Integer.parseInt(sMes);
        }

        if (sDia.charAt(0) == '0') {
            day = Integer.parseInt(Character.toString(sDia.charAt(1)));
        } else {
            day = Integer.parseInt(sDia);
        }

        LocalDate localDate = LocalDate.of(year, month, day);

        return localDate;
    }//</editor-fold>

}
