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
public class DeclaracionList extends Instruccion {
    private String indentificador;

    public DeclaracionList(String indentificador, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.indentificador = indentificador;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        LinkedList<Object> listaVacia = new LinkedList<>();
        Simbolo s = new Simbolo(tipo, indentificador, listaVacia);
        
        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Lista ya existente", this.linea, this.col);
        }
        return null;
    }
    
    
}
