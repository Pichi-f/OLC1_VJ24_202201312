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
            case MAYOR -> this.mayor(condIzq, condDer);
            case DIFERENTE -> this.diferente(condIzq, condDer);
            case MENORIGUAL -> this.menorigual(condIzq, condDer);
            case MAYORIGUAL -> this.mayorigual(condIzq, condDer);
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
                    case tipoDato.CARACTER ->
                        (int) comp1 == (int)((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 == (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 == (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 == (int)((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.BOOLEANO ->
                switch (comparando2) {
                    case tipoDato.BOOLEANO ->
                        (boolean) comp1 == (boolean) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char)((String) comp1).charAt(0) == (int) comp2;
                    case tipoDato.DECIMAL ->
                        ((String) comp1).charAt(0) == (char) ((double) comp2);
                    case tipoDato.CARACTER ->
                        ((String) comp1).charAt(0) == ((String) comp2).charAt(0);
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
    
    public Object mayor(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch (comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) comp1 > (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 > (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional invaldo",
                        this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 > (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 > (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional invaldo",
                        this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional invaldo",
                this.linea, this.col);
        };
    }
    
    public Object diferente(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

        return switch(comparando1) {
            case tipoDato.ENTERO ->
                switch (comparando2){
                    case tipoDato.ENTERO ->
                        (int) comp1 != (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 != (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 != (int)((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (int) comp1 != (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 != (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) ((String) comp1).charAt(0) != (double) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido decimal", this.linea, this.col);
                };
            case tipoDato.BOOLEANO ->
                switch (comparando2) {
                    case tipoDato.BOOLEANO ->
                        (boolean) comp1 != (boolean) comp2;
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido boolenao", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        ((String) comp1).charAt(0) != (char) (int) comp2;
                    case tipoDato.DECIMAL ->
                        ((String) comp1).charAt(0) != (char) ((double) comp2);
                    case tipoDato.CARACTER ->
                        ((String) comp1).charAt(0) != ((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido caracter", this.linea, this.col);
                };
            case tipoDato.CADENA ->
                switch (comparando2) {
                    case tipoDato.CADENA ->
                        !comp1.toString().equalsIgnoreCase(comp2.toString());
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido cadena", this.linea, this.col);
                };
            default ->
                new Errores("SEMANTICO", "Relacional Invalido otro", this.linea, this.col);
        };
    }
    
    public Object menorigual(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch(comparando1){
            case tipoDato.ENTERO ->
                switch (comparando2){
                    case tipoDato.ENTERO ->
                        (int) comp1 <= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 <= (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 <= (int)((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 <= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 <= (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 <= (int)((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char)((String) comp1).charAt(0) <= (int) comp2;
                    case tipoDato.DECIMAL ->
                        ((String) comp1).charAt(0) <= (char) ((double) comp2);
                    case tipoDato.CARACTER ->
                        ((String) comp1).charAt(0) <= ((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
        
        
    }
    
    public Object mayorigual(Object comp1, Object comp2){
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();
        
        return switch(comparando1){
            case tipoDato.ENTERO ->
                switch (comparando2){
                    case tipoDato.ENTERO ->
                        (int) comp1 >= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (int) comp1 >= (double) comp2;
                    case tipoDato.CARACTER ->
                        (int) comp1 >= (int)((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional invalido", this.linea, this.col);
                };
            case tipoDato.DECIMAL ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (double) comp1 >= (int) comp2;
                    case tipoDato.DECIMAL ->
                        (double) comp1 >= (double) comp2;
                    case tipoDato.CARACTER ->
                        (double) comp1 >= (int)((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
            case tipoDato.CARACTER ->
                switch (comparando2) {
                    case tipoDato.ENTERO ->
                        (char)((String) comp1).charAt(0) >= (int) comp2;
                    case tipoDato.DECIMAL ->
                        ((String) comp1).charAt(0) >= (char) ((double) comp2);
                    case tipoDato.CARACTER ->
                        ((String) comp1).charAt(0) >= ((String) comp2).charAt(0);
                    default ->
                        new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                };
                default ->
                    new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
        
        
    }
}
