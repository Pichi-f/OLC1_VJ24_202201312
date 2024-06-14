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
        try {
            String texto = "println(1.2+\" Mi cadena \" );"
                    + "println(2+2-1+5); println(-1.33+3.33);"
                    + "println(2+1+(2+3));"
                    + "println(-2-1);"
                    + "println(1);"
                    + "println(\"True\"+\" Mi cadena \");"
                    + "println(2+1.0==2.0+1);"
                    + "println(2+\"a\");"
                    + "println((\"hola\"==\"HoaLa\")+\"cadana\");";
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
