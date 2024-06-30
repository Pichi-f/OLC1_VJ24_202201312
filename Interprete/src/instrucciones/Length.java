/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.List;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author pichi
 */
public class Length extends Instruccion {
    private Instruccion parametro;

    public Length(Instruccion parametro, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.parametro = parametro;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object valor = parametro.interpretar(arbol, tabla);
        
        if (valor == null) {
            return new Errores("SEMANTICO", "El parámetro es nulo", this.linea, this.col);
        }
        
        if (valor instanceof String) {
            return ((String) valor).length();
        } else if (valor instanceof List) {
            return ((List<?>) valor).size();
        } else if (valor.getClass().isArray()) {
            return java.lang.reflect.Array.getLength(valor);
        } else {
            return new Errores("SEMANTICO", "No se puede obtener su tamaño", this.linea, this.col);
        }
    }

    
}
