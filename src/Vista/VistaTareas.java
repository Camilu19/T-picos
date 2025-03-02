package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Date;
import java.util.GregorianCalendar;
import Modelo.Tarea;
import Modelo.ListaTareas;

// Importar librerías externas
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.CellConstraints;
import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

public class VistaTareas extends JFrame {
    private JTextField campoTextoTarea;
    private JButton botonAgregar, botonCompletar, botonEliminar;
    private JList<String> listaTareas;
    private DefaultListModel<String> modeloLista;
    private JDatePicker selectorFecha;

    public VistaTareas() {
        setTitle("Lista de Tareas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Usar FormLayout de JGoodies
        JPanel panelSuperior = new JPanel(new FormLayout("right:pref, 10px, fill:pref:grow", "p, p"));
        CellConstraints cc = new CellConstraints();

        campoTextoTarea = new JTextField(20);
        botonAgregar = new JButton("Agregar Tarea");

        panelSuperior.add(new JLabel("Tarea:"), cc.xy(1, 1));
        panelSuperior.add(campoTextoTarea, cc.xy(3, 1));
        panelSuperior.add(botonAgregar, cc.xy(3, 2));

        // Configurar JDatePicker para la selección de fechas
        selectorFecha = new JDateComponentFactory().createJDatePicker();
        selectorFecha.setTextEditable(false);
        selectorFecha.setShowYearButtons(false);
        
        /*
        panelSuperior.add(new JLabel("Fecha de Vencimiento:"), cc.xy(1, 2));
        panelSuperior.add((Component) selectorFecha, cc.xy(3, 2));
        */

        modeloLista = new DefaultListModel<>();
        listaTareas = new JList<>(modeloLista);
        JScrollPane panelLista = new JScrollPane(listaTareas);

        JPanel panelBotones = new JPanel();
        botonCompletar = new JButton("Marcar como Completada");
        botonEliminar = new JButton("Eliminar Tarea");
        panelBotones.add(botonCompletar);
        panelBotones.add(botonEliminar);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelLista, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        pack();
    }

    public String obtenerTextoTarea() {
        return campoTextoTarea.getText();
    }

    public Date obtenerFechaVencimiento() {
        Object valor = selectorFecha.getModel().getValue();
        if (valor instanceof GregorianCalendar) {
            return ((GregorianCalendar) valor).getTime();
        }
        return null;
    }

    public void limpiarEntrada() {
        campoTextoTarea.setText("");
    }

    public void actualizarListaTareas(List<Tarea> tareas) {
        modeloLista.clear();
        for (Tarea tarea : tareas) {
            modeloLista.addElement(tarea.toString());
        }
    }

    public void agregarEventoAgregar(ActionListener listener) {
        botonAgregar.addActionListener(listener);
    }

    public void agregarEventoCompletar(ActionListener listener) {
        botonCompletar.addActionListener(listener);
    }

    public void agregarEventoEliminar(ActionListener listener) {
        botonEliminar.addActionListener(listener);
    }

    public Tarea obtenerTareaSeleccionada(ListaTareas listaTareas) {
        int indice = this.listaTareas.getSelectedIndex();
        if (indice != -1) {
            return listaTareas.obtenerTareas().get(indice);
        }
        return null;
    }
}

