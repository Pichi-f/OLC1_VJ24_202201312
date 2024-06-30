/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author pichi
 */
public class Llamada extends Instruccion {
    private String id;
    private LinkedList<Instruccion> parametros;

    public Llamada(String id, LinkedList<Instruccion> parametros, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var busqueda = arbol.getFuncion(this.id);
        if (busqueda == null) {
            return new Errores("SEMANTICO", "Funcion no existente",
                    this.linea, this.col);
        }

        if (busqueda instanceof Metodo metodo) {
            this.tipo.setTipo(metodo.tipo.getTipo());
            var newTabla = new tablaSimbolos(arbol.getTablaGlobal());
            newTabla.setNombre("LLAMADA METODO " + this.id);
            if (metodo.parametros.size() != this.parametros.size()) {
                return new Errores("SEMANTICO", "Parametros Erroneos",
                        this.linea, this.col);
            }

            for (int i = 0; i < this.parametros.size(); i++) {
                var identificador = (String) metodo.parametros.
                        get(i).get("id");

                var valor = this.parametros.get(i);

                var tipo2 = (Tipo) metodo.parametros.
                        get(i).get("tipo");

                var declaracionParametro = new Declaracion("const", identificador, tipo2, this.linea, this.col);

                var resultado = declaracionParametro.interpretar(arbol, newTabla);

                if (resultado instanceof Errores) {
                    return resultado;
                }

                var valorInterpretado = valor.interpretar(arbol, tabla);
                if (valorInterpretado instanceof Errores) {
                    return valorInterpretado;
                }

                var variable = newTabla.getVariable(identificador);
                if (variable == null) {
                    return new Errores("SEMANTICO", "Error declaracion parametros",
                            this.linea, this.col);
                }
                if (variable.getTipo().getTipo() != valor.tipo.getTipo()) {
                    return new Errores("SEMANTICO", "Error en tipo de parametro",
                            this.linea, this.col);
                }

                variable.setValor(valorInterpretado);
            }

            var resultadoFuncion = metodo.interpretar(arbol, newTabla);
            if (resultadoFuncion instanceof Errores) {
                return resultadoFuncion;
            }
            if (resultadoFuncion != null){
                return resultadoFuncion;
            }

        }
        return null;

    
    }
    
}
