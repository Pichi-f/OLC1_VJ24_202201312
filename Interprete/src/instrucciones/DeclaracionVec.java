/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.*;

/**
 *
 * @author pichi
 */
public class DeclaracionVec extends Instruccion {
    public String mutabilidad;
    public String identificador;
    public LinkedList<Instruccion> valores;
    public int dimensiones;

    public DeclaracionVec(String mutabilidad, String identificador, LinkedList<Instruccion> valores, int dimensiones, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.valores = valores;
        this.dimensiones = dimensiones;
    }
    
    @Override
public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
    LinkedList<Object> valoresInterpretados = new LinkedList<>();
    
    for (Instruccion valor : valores) {
        var valorInterpretado = valor.interpretar(arbol, tabla);
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }
        if (this.dimensiones == 1) {
            // Manejo de vectores de 1 dimensión
            if (valor.tipo.getTipo() != this.tipo.getTipo()) {
                return new Errores("SEMANTICO", "Tipos erroneos en vector de 1 dimensión", this.linea, this.col);
            }
            valoresInterpretados.add(valorInterpretado);
        } else if (this.dimensiones == 2) {
            // Manejo de vectores de 2 dimensiones
            if (!(valorInterpretado instanceof LinkedList)) {
                return new Errores("SEMANTICO", "Se esperaba un vector de 2 dimensiones", this.linea, this.col);
            }
            LinkedList<Object> subListaInterpretada = new LinkedList<>();
            for (Object elem : (LinkedList<?>) valorInterpretado) {
                if (!(elem instanceof Instruccion)) {
                    return new Errores("SEMANTICO", "Tipos Erroneos en vector de 2 dimensiones", this.linea, this.col);
                }
                var elemInterpretado = ((Instruccion) elem).interpretar(arbol, tabla);
                if (elemInterpretado instanceof Errores) {
                    return elemInterpretado;
                }
                if (((Instruccion) elem).tipo.getTipo() != this.tipo.getTipo()) {
                    return new Errores("SEMANTICO", "Tipos Erroneos en vector de 2 dimensiones", this.linea, this.col);
                }
                subListaInterpretada.add(elemInterpretado);
            }
            valoresInterpretados.add(subListaInterpretada);
        }
    }
    Simbolo s = new Simbolo(this.tipo, this.identificador, valoresInterpretados);
    if (mutabilidad.toLowerCase().equals("const")) {
        s.setMutabilidad(true);
    }
    
    boolean creacion = tabla.setVariable(s);
    if (!creacion) {
        return new Errores("SEMANTICO", "Vector ya existente", this.linea, this.col);
    }
    
    return null;
}
    
}
