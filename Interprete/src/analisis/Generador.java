/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;

/**
 *
 * @author pichi
 */
public class Generador {
    public static void main(String[] args) {
        generarCompilador();
    }

    public static void generarCompilador() {
        try{
            String ruta = "src/analisis/";
            /*
                ruta -> ruta del los archivos
                -d -> ruta donde se genera la salida
                ruta salida
            */ 

           String Flex[] = {ruta + "lexico.jflex", "-d", ruta};
           jflex.Main.generate(Flex);

           /*
            -destdir indica la ruta donde se generara la salida
            ruta de salida
            -parser indican el nombre del archivo
            parser
            ruta del archivo cup
           */

          String Cup[] = { "-destdir", ruta, "-parser", "parser", ruta + "sintactico.cup" };

            java_cup.Main.main(Cup);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
