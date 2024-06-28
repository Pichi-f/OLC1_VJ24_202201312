/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.*;

/**
 *
 * @author pichi
 */
public class Remove extends Instruccion {
    private String identificador;
    private Instruccion valor;

    public Remove(String identificador, Instruccion valor, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.identificador = identificador;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        
        Simbolo simboloLista = tabla.getVariable(identificador);
        if (simboloLista == null || !(simboloLista.getValor() instanceof LinkedList)) {
            return new Errores("SEMANTICO", "La variable '" + identificador + "' no es una lista o no existe.", this.linea, this.col);
        }

        Object valorIndice = valor.interpretar(arbol, tabla);
        if (valorIndice instanceof Errores) {
            return valorIndice;
        }
        if (!(valorIndice instanceof Integer)) {
            return new Errores("SEMANTICO", "El índice debe ser un valor entero.", this.linea, this.col);
        }
        int indice = (Integer) valorIndice;
        LinkedList<Object> lista = (LinkedList<Object>) simboloLista.getValor();
        if (indice < 0 || indice >= lista.size()) {
            return new Errores("SEMANTICO", "Índice fuera de los límites de la lista.", this.linea, this.col);
        }

        Object elementoEliminado = lista.remove(indice);
        return elementoEliminado;
    }
}
