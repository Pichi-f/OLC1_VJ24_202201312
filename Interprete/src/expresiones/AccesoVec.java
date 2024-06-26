/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

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
public class AccesoVec extends Instruccion {
    private String id;
    private Instruccion posicion;

    public AccesoVec(String id, Instruccion posicion, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.posicion = posicion;
    }

    @Override
        public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // aqui obtengo el vector 
        var variable = tabla.getVariable(id);
        if (variable == null || !(variable.getValor() instanceof List<?>)) {
            return new Errores("SEMANTICA", "La variable " + id + " no es un vector o no existe.", this.linea, this.col);
        }
        
        // evaluo la expresion de la posicion para obtener el indice
        Object valorPosicion = posicion.interpretar(arbol, tabla);
        if (!(valorPosicion instanceof Integer)) {
            return new Errores("SEMANTICA", "La posición debe ser un valor entero.", this.linea, this.col);
        }
        int indice = (Integer) valorPosicion;
        
        // verifica que indice este dentro del limite del vector
        List<?> vectorList = (List<?>) variable.getValor();
        if (indice < 0 || indice >= vectorList.size()) {
            return new Errores("SEMANTICA", "Índice fuera de los límites del vector.", this.linea, this.col);
        }
        
        // accedemos al elemento del vector con el indice y retornarlo
        Object elemento = vectorList.get(indice);
        this.tipo.setTipo(variable.getTipo().getTipo()); 
        return elemento;
    }
    
}
