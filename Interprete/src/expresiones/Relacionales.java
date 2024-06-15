/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

/**
 *
 * @author pichi
 */
public class Relacionales extends Instruccion {
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;

    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if(condIzq instanceof Errores){
            return condIzq;
        }
        
        var condDer = this.cond2.interpretar(arbol, tabla);
        if(condDer instanceof Errores){
            return condDer;
        }
        
        return switch (relacional){
            case EQUALS -> this.equals(condIzq, condDer);
            case MENOR -> this.menor(condIzq, condDer);
            default -> new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
    public Object equals(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch(comparando1){
            case tipoDato.ENTERO ->
                switch (comparando2){
                    case tipoDato.ENTERO ->
                        (int) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 == (double) comp2;
                    case tipoDato.CADENA ->
                        comp1.toString().equals(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 == (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CADENA ->
                switch (comparando2) {
                    case tipoDato.CADENA ->
                        comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object menor(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) comp1 < (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 < (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional invaldo",
                        this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 < (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 < (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional invaldo",
                        this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional invaldo",
                this.linea, this.col);
        };
    }
}
