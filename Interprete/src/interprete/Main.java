package interprete;

import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import excepciones.Errores;
import instrucciones.AsignacionVar;
import instrucciones.AsignacionVec;
import instrucciones.Declaracion;
import instrucciones.DeclaracionList;
import instrucciones.DeclaracionVec;
import instrucciones.Metodo;
import instrucciones.StartWith;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu archivoMenu = new JMenu("Archivo");
        menuBar.add(archivoMenu);
        menuBar.add(new JMenu("Pestañas"));
        JMenu ejecutarMenu = new JMenu("Ejecutar");
        menuBar.add(ejecutarMenu);
        JMenu reportesMenu = new JMenu("Reportes");
        menuBar.add(reportesMenu);

        JMenuItem ejecutarItem = new JMenuItem("Ejecutar");
        ejecutarMenu.add(ejecutarItem);

        JMenuItem nuevoArchivoItem = new JMenuItem("Nuevo Archivo");
        archivoMenu.add(nuevoArchivoItem);

        JMenuItem abrirArchivoItem = new JMenuItem("Abrir Archivo");
        archivoMenu.add(abrirArchivoItem);

        JMenuItem generarReporteItem = new JMenuItem("Generar Reporte");
        reportesMenu.add(generarReporteItem);

        JTabbedPane tabbedPane = new JTabbedPane();
        ArrayList<JTextArea> textAreas1 = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            JTextArea textArea1 = new JTextArea();
            JTextArea textArea2 = new JTextArea();
            textAreas1.add(textArea1);

            JScrollPane scrollPane1 = new JScrollPane(textArea1);
            JScrollPane scrollPane2 = new JScrollPane(textArea2);

            JLabel label1 = new JLabel("Entrada");
            label1.setHorizontalAlignment(JLabel.CENTER);

            JPanel panel1 = new JPanel(new BorderLayout());
            panel1.add(label1, BorderLayout.NORTH);
            panel1.add(scrollPane1, BorderLayout.CENTER);

            JLabel label2 = new JLabel("Salida");
            label2.setHorizontalAlignment(JLabel.CENTER);

            JPanel panel2 = new JPanel(new BorderLayout());
            panel2.add(label2, BorderLayout.NORTH);
            panel2.add(scrollPane2, BorderLayout.CENTER);

            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel1, panel2);
            splitPane.setResizeWeight(0.5); 

            tabbedPane.addTab("Tab " + i, splitPane);
        }

        abrirArchivoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showOpenDialog(frame);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();
                    try {
                        String contenido = new String(Files.readAllBytes(archivoSeleccionado.toPath()));
                        int selectedIndex = tabbedPane.getSelectedIndex();
                        if (selectedIndex != -1) {
                            JTextArea textArea1 = textAreas1.get(selectedIndex);
                            textArea1.setText(contenido);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error al abrir el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        ejecutarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                        ast.setTablaGlobal(tabla);

                        LinkedList<Errores> lista = new LinkedList<>();
                        lista.addAll(s.listaErrores);
                        lista.addAll(p.listaErrores);
                        
                        //almacenar funciones, metodos o structs
                        for (var a : ast.getInstrucciones()) {
                            if (a == null) {
                                continue;
                            }

                            if (a instanceof Metodo) {
                                ast.addFunciones(a);
                            }
                        }

                        //declaraciones o asignaciones globales
                        for (var a : ast.getInstrucciones()) {
                            if (a == null) {
                                continue;
                            }

                            if (a instanceof Declaracion || a instanceof AsignacionVar
                                    || a instanceof AsignacionVec || a instanceof DeclaracionVec
                                    || a instanceof DeclaracionList) {
                                var res = a.interpretar(ast, tabla);
                                if (res instanceof Errores errores) {
                                    lista.add(errores);
                                }
                            }

                        }

                        //execute -> start_with
                        StartWith b = null;
                        for (var a : ast.getInstrucciones()) {
                            if (a == null) {
                                continue;
                            }
                            if (a instanceof StartWith startWith) {
                                b = startWith;
                                break;
                            }
                        }

                        var resultadoExecute = b.interpretar(ast, tabla);
                        if (resultadoExecute instanceof Errores) {
                            System.out.println("Ya no sale compi1");
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

        generarReporteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex != -1) {
                    JSplitPane splitPane = (JSplitPane) tabbedPane.getComponentAt(selectedIndex);
                    JPanel panel2 = (JPanel) splitPane.getBottomComponent();
                    JScrollPane scrollPane2 = (JScrollPane) panel2.getComponent(1);
                    JTextArea textArea2 = (JTextArea) scrollPane2.getViewport().getView();

                    try {
                        String errores = textArea2.getText();
                        if (!errores.isEmpty()) {
                            String[] erroresArray = errores.split("\n");

                            StringBuilder htmlContent = new StringBuilder("<html><head><title>Reporte de Errores</title></head><body>");
                            htmlContent.append("<h1>Reporte de Errores</h1>");
                            htmlContent.append("<table border='1'><tr><th>#</th><th>Tipo</th><th>Descripción</th><th>Línea</th><th>Columna</th></tr>");

                            int contador = 1;

                            for (String error : erroresArray) {
                                if (error.startsWith("Errores")) {
                                    int startIndex = error.indexOf("{");
                                    int endIndex = error.indexOf("}");
                                    if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                                        String contenido = error.substring(startIndex + 1, endIndex);
                                        String[] partes = contenido.split(",\\s*");
                                        if (partes.length == 4) {
                                            String tipo = partes[0].replace("tipo=", "").trim();
                                            String descripcion = partes[1].replace("desc=", "").trim();
                                            String linea = partes[2].replace("linea=", "").trim();
                                            String columna = partes[3].replace("col=", "").trim();
                                            htmlContent.append("<tr><td>").append(contador).append("</td>")
                                                        .append("<td>").append(tipo).append("</td>")
                                                        .append("<td>").append(descripcion).append("</td>")
                                                        .append("<td>").append(linea).append("</td>")
                                                        .append("<td>").append(columna).append("</td></tr>");
                                            contador++;
                                        }
                                    }
                                }
                            }

                            htmlContent.append("</table></body></html>");

                            Files.write(Paths.get("reporteErrores.html"), htmlContent.toString().getBytes());

                            JOptionPane.showMessageDialog(frame, "Reporte generado exitosamente.", "Reporte", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "No hay errores para reportar.", "Reporte", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frame.setJMenuBar(menuBar);
        frame.add(tabbedPane);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}

