package OLC1_VJ24_202201312;

import javax.swing.*;
import java.awt.*;

/**
 * main
 */
public class main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new JMenu("Archivo"));
        menuBar.add(new JMenu("Pestañas"));
        menuBar.add(new JMenu("Ejecutar"));
        menuBar.add(new JMenu("Reportes"));

        // Crear las pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Crear las pestañas con los JLabel y los JSplitPane
        for (int i = 1; i <= 3; i++) {
            // Crear los editores de texto
            JTextArea textArea1 = new JTextArea();
            JTextArea textArea2 = new JTextArea();

            // Agregar barras de desplazamiento a los editores de texto
            JScrollPane scrollPane1 = new JScrollPane(textArea1);
            JScrollPane scrollPane2 = new JScrollPane(textArea2);

            // Crear el JLabel para el primer editor de texto
            JLabel label1 = new JLabel("Entrada");
            label1.setHorizontalAlignment(JLabel.CENTER);

            // Crear el JPanel para el primer editor de texto y añadir el JLabel y el JScrollPane
            JPanel panel1 = new JPanel(new BorderLayout());
            panel1.add(label1, BorderLayout.NORTH);
            panel1.add(scrollPane1, BorderLayout.CENTER);

            // Crear el JLabel para el segundo editor de texto
            JLabel label2 = new JLabel("Salida");
            label2.setHorizontalAlignment(JLabel.CENTER);

            // Crear el JPanel para el segundo editor de texto y añadir el JLabel y el JScrollPane
            JPanel panel2 = new JPanel(new BorderLayout());
            panel2.add(label2, BorderLayout.NORTH);
            panel2.add(scrollPane2, BorderLayout.CENTER);

            // Crear el JSplitPane y añadir los JPanel
            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel1, panel2);
            splitPane.setResizeWeight(0.5); // Esto hará que ambos JPanel tengan el mismo tamaño

            // Añadir la pestaña al JTabbedPane
            tabbedPane.addTab("Tab " + i, splitPane);
        }

        // Añadir el menú y las pestañas al frame
        frame.setJMenuBar(menuBar);
        frame.add(tabbedPane);

        //Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}