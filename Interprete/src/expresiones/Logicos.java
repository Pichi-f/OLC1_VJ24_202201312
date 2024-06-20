/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author pichi
 */
public class Logicos extends Instruccion {
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresLogicos operador;
    private Instruccion negacion;

    public Logicos(Instruccion cond1, Instruccion cond2,OperadoresLogicos operador,  int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.operador = operador;
        this.cond1 = cond1;
        this.cond2 = cond2;
    }

    public Logicos(Instruccion negacion, OperadoresLogicos operador, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.operador = operador;
        this.negacion = negacion;
    }
    
    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object resultado1 = null;
        
        if (operador == OperadoresLogicos.NOT && negacion != null) {
            resultado1 = negacion.interpretar(arbol, tabla);
            if (resultado1 instanceof Errores) {
                return resultado1;
            }
            return not(resultado1);
        } else {
            // Manejo de las demás operaciones lógicas
            resultado1 = cond1.interpretar(arbol, tabla);
            if (resultado1 instanceof Errores) {
                return resultado1;
            }
        }

        switch (operador) {
            case OR:
                return or(resultado1, cond2.interpretar(arbol, tabla));
            case AND:
                return and(resultado1, cond2.interpretar(arbol, tabla));
            case XOR:
                return xor(resultado1, cond2.interpretar(arbol, tabla));
            // El caso NOT se maneja antes para el caso de una sola condición
            default:
                return new Errores("SEMANTICO", "Operador lógico inválido", this.linea, this.col);
        }
    }

    public Object or(Object comp1, Object comp2) {
        if (comp1 instanceof Boolean && comp2 instanceof Boolean) {
            return (boolean) comp1 || (boolean) comp2;
        } else {
            return new Errores("SEMANTICO", "Operación OR no válida para tipos no booleanos", this.linea, this.col);
        }
    }

    public Object and(Object comp1, Object comp2) {
        if (comp1 instanceof Boolean && comp2 instanceof Boolean) {
            return (boolean) comp1 && (boolean) comp2;
        } else {
            return new Errores("SEMANTICO", "Operación AND no válida para tipos no booleanos", this.linea, this.col);
        }
    }

    public Object xor(Object comp1, Object comp2) {
        if (comp1 instanceof Boolean && comp2 instanceof Boolean) {
            return (boolean) comp1 ^ (boolean) comp2;
        } else {
            return new Errores("SEMANTICO", "Operación XOR no válida para tipos no booleanos", this.linea, this.col);
        }
    }

    public Object not(Object comp1) {
        if (comp1 instanceof Boolean) {
            return !(boolean) comp1;
        } else {
            return new Errores("SEMANTICO", "Operación NOT no válida para tipos no booleanos", this.linea, this.col);
        }
    }
}
