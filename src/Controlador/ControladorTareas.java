package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import Modelo.Tarea;
import Modelo.ListaTareas;
import Vista.VistaTareas;

public class ControladorTareas {
    private ListaTareas modelo;
    private VistaTareas vista;

    public ControladorTareas(ListaTareas modelo, VistaTareas vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.agregarEventoAgregar(new EventoAgregarTarea());
        this.vista.agregarEventoCompletar(new EventoMarcarCompletada());
        this.vista.agregarEventoEliminar(new EventoEliminarTarea());
    }

    private class EventoAgregarTarea implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String descripcionTarea = vista.obtenerTextoTarea();
            Date fechaVencimiento = vista.obtenerFechaVencimiento();

            if (descripcionTarea.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "La descripción de la tarea no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            /*
            if (fechaVencimiento == null) {
                JOptionPane.showMessageDialog(vista, "Seleccione una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            */
            modelo.agregarTarea(descripcionTarea, fechaVencimiento);
            vista.limpiarEntrada();
            vista.actualizarListaTareas(modelo.obtenerTareas());
        }
    }

    private class EventoMarcarCompletada implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Tarea tareaSeleccionada = vista.obtenerTareaSeleccionada(modelo);
            if (tareaSeleccionada != null) {
                modelo.cambiarEstadoTarea(tareaSeleccionada);
                vista.actualizarListaTareas(modelo.obtenerTareas());
            }
        }
    }

    private class EventoEliminarTarea implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Tarea tareaSeleccionada = vista.obtenerTareaSeleccionada(modelo);
            if (tareaSeleccionada != null) {
                int respuesta = JOptionPane.showConfirmDialog(vista, "¿Seguro que deseas eliminar la tarea?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    modelo.eliminarTarea(tareaSeleccionada);
                    vista.actualizarListaTareas(modelo.obtenerTareas());
                }
            }
        }
    }
}
