package interprete;

import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import excepciones.Errores;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;

/**
 * main
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new JMenu("Archivo"));
        menuBar.add(new JMenu("Pestañas"));
        JMenu ejecutarMenu = new JMenu("Ejecutar");
        menuBar.add(ejecutarMenu);
        menuBar.add(new JMenu("Reportes"));

        // Crear el elemento de menú Ejecutar
        JMenuItem ejecutarItem = new JMenuItem("Ejecutar");
        ejecutarMenu.add(ejecutarItem);

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

        // Agregar el ActionListener al menú Ejecutar
        ejecutarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la pestaña seleccionada
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex != -1) {
                    JSplitPane splitPane = (JSplitPane) tabbedPane.getComponentAt(selectedIndex);
                    JPanel panel1 = (JPanel) splitPane.getTopComponent();
                    JScrollPane scrollPane1 = (JScrollPane) panel1.getComponent(1);
                    JTextArea textArea1 = (JTextArea) scrollPane1.getViewport().getView();
                    
                    JPanel panel2 = (JPanel) splitPane.getBottomComponent();
                    JScrollPane scrollPane2 = (JScrollPane) panel2.getComponent(1);
                    JTextArea textArea2 = (JTextArea) scrollPane2.getViewport().getView();

                    try {
                        String texto = textArea1.getText();
                        scanner s = new scanner(new BufferedReader(new StringReader(texto)));
                        parser p = new parser(s);
                        var resultado = p.parse();
                        var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
                        var tabla = new tablaSimbolos();
                        tabla.setNombre("GLOBAL");
                        ast.setConsola("");

                        LinkedList<Errores> lista = new LinkedList<>();
                        lista.addAll(s.listaErrores);
                        lista.addAll(p.listaErrores);
                        for (var a : ast.getInstrucciones()) {
                            if (a == null) {
                                continue;
                            }
                            var res = a.interpretar(ast, tabla);
                            if (res instanceof Errores) {
                                lista.add((Errores) res);
                            }
                        }
                        textArea2.setText(ast.getConsola());
                        for (var i : lista) {
                            textArea2.append(i.toString() + "\n");
                        }
                    } catch (Exception ex) {
                        textArea2.setText("Algo salió mal\n" + ex.toString());
                    }
                }
            }
        });

        // Añadir el menú y las pestañas al frame
        frame.setJMenuBar(menuBar);
        frame.add(tabbedPane);

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}