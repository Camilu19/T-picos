package Modelo;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable {
    private String descripcion;
    private boolean completada;
    private Date fechaVencimiento;

    public Tarea(String descripcion, Date fechaVencimiento) {
        this.descripcion = descripcion;
        this.completada = false;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean estaCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return descripcion + (completada ? " (Completada)" : " (Pendiente)") + " - Vence el: " + fechaVencimiento;
    }
}
