package OLC1_VJ24_202201312;

import javax.swing.*;

/**
 * main
 */
public class main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Crear los editores de texto
        JTextArea textArea1 = new JTextArea();
        JTextArea textArea2 = new JTextArea();

        // Agregar barras de desplazamiento a los editores de texto
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem = new JMenuItem("Item");
        menu.add(menuItem);
        menuBar.add(menu);

        // Crear el JSplitPane y añadir los editores de texto
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane1, scrollPane2);
        splitPane.setResizeWeight(0.5); // Esto hará que ambos editores de texto tengan el mismo tamaño

        // Crear las pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Pestaña 1", splitPane);

        // Crear un nuevo JSplitPane para la segunda pestaña
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(new JTextArea()), new JScrollPane(new JTextArea()));
        splitPane2.setResizeWeight(0.5);

        tabbedPane.addTab("Pestaña 2", splitPane2);

        // Añadir el menú y las pestañas al frame
        frame.setJMenuBar(menuBar);
        frame.add(tabbedPane);

        frame.setVisible(true);
    }
}