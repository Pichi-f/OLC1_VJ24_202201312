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
        // Paso 1: Buscar el símbolo en la tabla de símbolos
        Simbolo simboloLista = tabla.getVariable(identificador);
        if (simboloLista == null || !(simboloLista.getValor() instanceof LinkedList)) {
            return new Errores("SEMANTICO", "La variable '" + identificador + "' no es una lista o no existe.", linea, col);
        }
        
        // Paso 2: Verificar que el símbolo es de tipo lista (esto ya se verifica al comprobar que el valor es una instancia de LinkedList)
        
        // Paso 3: Evaluar la expresión para obtener el valor a agregar
        Object valorAgregar = valor.interpretar(arbol, tabla);
        if (valorAgregar instanceof Errores) {
            return valorAgregar; // Propagar el error si la evaluación de la expresión falla
        }
        
        // Paso 4: Agregar el valor al final de la lista
        @SuppressWarnings("unchecked")
        LinkedList<Object> lista = (LinkedList<Object>) simboloLista.getValor();
        lista.add(valorAgregar);
        
        // Paso 5: Retornar null ya que la operación es de tipo VOID
        return null;
    }
    
    
}
