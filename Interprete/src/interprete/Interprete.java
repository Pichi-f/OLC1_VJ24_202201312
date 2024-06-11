/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interprete;

import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author pichi
 */
public class Interprete {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
        try {
            String texto = "imprimir(1.2+\" Mi cadena \" );"
                    + "imprimir(2+2-1+5); imprimir(-1.33+3.33);"
                    + "imprimir(2+\"cadena\");"
                    + "imprimir(-2-1);";
            scanner s = new scanner(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            for(var a : ast.getInstrucciones()){
                var res = a.interpretar(ast, tabla);
            }
            System.out.println(ast.getConsola());
        } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
    }
    
}
