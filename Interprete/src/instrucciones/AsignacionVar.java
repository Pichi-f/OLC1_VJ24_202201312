/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

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
public class AsignacionVar extends Instruccion {

    private String id;
    private Instruccion exp;

    public AsignacionVar(String id, Instruccion exp, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        //variable exista
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no exitente",
                    this.linea, this.col);
        }

        // interpretar el nuevo valor a asignar
        var newValor = this.exp.interpretar(arbol, tabla);
        if (newValor instanceof Errores) {
            return newValor;
        }

        //validar tipos
        if (variable.getTipo().getTipo() != this.exp.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipos erroneos en asignacion",
                    this.linea, this.col);
        }
        
        if(tabla.getVariable(id).isMutabilidad()){
            return new Errores("SEMANTICO", "No se puede reasignar una variable", this.linea, this.col);
        }
        //this.tipo.setTipo(variable.getTipo().getTipo());
        variable.setValor(newValor);
        return null;
    }

}
