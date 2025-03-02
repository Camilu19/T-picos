package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaTareas {
    private List<Tarea> tareas;

    public ListaTareas() {
        tareas = new ArrayList<>();
    }

    public void agregarTarea(String descripcion, Date fechaVencimiento) {
        tareas.add(new Tarea(descripcion, fechaVencimiento));
    }

    public void eliminarTarea(Tarea tarea) {
        tareas.remove(tarea);
    }

    public void cambiarEstadoTarea(Tarea tarea) {
        for (Tarea t : tareas) {
            if (t.equals(tarea)) {
                t.setCompletada(!t.estaCompletada());
                break;
            }
        }
    }

    public List<Tarea> obtenerTareas() {
        return tareas;
    }

    public void agregarTarea(String descripcionTarea) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
