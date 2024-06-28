/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import java.util.List;

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
public class AsignacionVec extends Instruccion{
    private String id;
    private Instruccion posicion;
    private Instruccion valor;

    public AsignacionVec(String id, Instruccion posicion, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.posicion = posicion;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // obtiene el valor
        var variable = tabla.getVariable(id);
        if (variable == null || !(variable.getValor() instanceof List<?>)) {
            return new Errores("SEMANTICA", "La variable " + id + " no es un vector o no existe.", this.linea, this.col);
        }

        // evaluar la posicion
        Object valorPosicion = posicion.interpretar(arbol, tabla);
        if (!(valorPosicion instanceof Integer)) {
            return new Errores("SEMANTICA", "La posición debe ser un valor entero.", this.linea, this.col);
        }
        int indice = (Integer) valorPosicion;

        // evalua el nuevo valor
        Object nuevoValor = valor.interpretar(arbol, tabla);
        
        // valida la posicion
        List<Object> vectorList = (List<Object>) variable.getValor();
        if (indice < 0 || indice >= vectorList.size()) {
            return new Errores("SEMANTICA", "Índice fuera de los límites del vector.", this.linea, this.col);
        }
        if (!vectorList.isEmpty() && vectorList.get(0).getClass() != nuevoValor.getClass()) {
            return new Errores("SEMANTICA", "El tipo del valor no coincide con el tipo de los elementos del vector", this.linea, this.col);
        }

        vectorList.set(indice, nuevoValor);

        return null;
    }
    
}
