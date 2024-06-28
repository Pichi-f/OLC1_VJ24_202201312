/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import java.util.LinkedList;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author pichi
 */
public class Append extends Instruccion{
    private String identificador;
    private Instruccion valor;

    public Append(String identificador, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.identificador = identificador;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // buscamos el simbolo en la tabla de símbolos
        Simbolo simboloLista = tabla.getVariable(identificador);
        if (simboloLista == null || !(simboloLista.getValor() instanceof LinkedList)) {
            return new Errores("SEMANTICO", "La variable '" + identificador + "' no es una lista o no existe.", this.linea, this.col);
        }
        
        Object valorAgregar = valor.interpretar(arbol, tabla);
        if (valorAgregar instanceof Errores) {
            return valorAgregar;
        }
        
        // se verifica el tipo
        LinkedList<Object> lista = (LinkedList<Object>) simboloLista.getValor();
        if (!lista.isEmpty() && valorAgregar.getClass() != lista.getFirst().getClass()) {
            return new Errores("SEMANTICO", "El tipo de dato del valor a agregar no coincide con el tipo de dato de los elementos de la lista.", this.linea, this.col);
        }
        lista.add(valorAgregar);
        
        return null;
    }
    
    
}
