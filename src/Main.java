import Controlador.ControladorTareas;
import Modelo.ListaTareas;
import Vista.VistaTareas;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Aplicar Nimbus LookAndFeel para una mejor apariencia
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ListaTareas modelo = new ListaTareas();
            VistaTareas vista = new VistaTareas();
            new ControladorTareas(modelo, vista);
            vista.setVisible(true);
        });
    }
}



