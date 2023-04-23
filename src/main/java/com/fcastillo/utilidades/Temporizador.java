package com.fcastillo.utilidades;

//<editor-fold defaultstate="collpased" desc="imports">
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//</editor-fold>

/**
 * <b>Clase que representa un temporizador.</b>
 * <br><br>
 * Si necesita crear un temporizador incremental, debe establecer en 0 la cantidad de segundos, y luego invocar al método incrementarTiempo()
 * especificando la cantidad de segundos que quiere contar. Por ejemplo, contar hasta 10:
 * <br><br>
 * <pre>
 * Temporizador temporizador = new Temporizador(0);
 * temporizador.incrementarTiempo(10);
 * </pre>
 *
 * Si necesita crear una cuenta regresiva, debe especificar la cantidad de segundos a partir de la cual iniciará la cuenta, y luego invocar al método
 * decrementarTiempo(). Por ejemplo, una cuenta regresiva típica de 10, 9, 8, ..., 0.
 * <br><br>
 * <pre>
 * Temporizador temporizador = new Temporizador(10);
 * temporizador.decrementarTiempo();
 * </pre>
 *
 * @author fcastillo
 */
public class Temporizador {

    //<editor-fold defaultstate="collapsed" desc="fields">
    private boolean detenerCronometro;
    private int segundos;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Temporizador()">
    public Temporizador(int segundos) {
        this.segundos = segundos;
        this.detenerCronometro = false;
    }//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="set/get">
    public boolean isDetenerCronometro() {
        return detenerCronometro;
    }

    public void setDetenerCronometro(boolean detenerCronometro) {
        this.detenerCronometro = detenerCronometro;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="decrementarTiempo()">
    /**
     * Método que resta la cantidad de segundos hasta llegar a cero.
     */
    public void decrementarTiempo() {
        segundos--;
        if (segundos == 0) {
            detenerCronometro = true;
            Mensajes.info("Finalizó el tiempo.");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="incrementarTiempo()">
    /**
     * Método que incrementa los segundos hasta llegar a los segundos de finalización.
     *
     * @param segundosFinalizacion cantidad de segundos en los que se dentendrá la cuenta.
     */
    public void incrementarTiempo(int segundosFinalizacion) {
        segundos++;
        if (segundos == segundosFinalizacion) {
            detenerCronometro = true;
            Mensajes.info("Finalizó el tiempo.");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="mostrarTiempo()">
    /**
     * Método que retorna el tiempo transcurrido en formato
     * <pre>hh:mm:ss</pre>.
     *
     * @return tiempo transcurrido.
     */
    public String mostrarTiempo() {
        LocalTime lt = LocalTime.ofSecondOfDay(segundos);
        return lt.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
    //</editor-fold>

}
