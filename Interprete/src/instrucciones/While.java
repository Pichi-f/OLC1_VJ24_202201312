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
public class While extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public While(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // se crea nuevo entorno
        var newTabla = new tablaSimbolos(tabla);

        // se valida condicion
        var cond = this.condicion.interpretar(arbol, newTabla);
        if (cond instanceof Errores) {
            return cond;
        }
        if (this.condicion.tipo.getTipo() != tipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La condición debe ser booleana",
                    this.linea, this.col);
        }
        while ((boolean) this.condicion.interpretar(arbol, newTabla)) {
            var newTabla2 = new tablaSimbolos(newTabla);
            for (var instruccion : this.instrucciones) {
                // Verificar la condición antes de ejecutar instrucciones que podrían contener bucles anidados
                if (!(boolean) this.condicion.interpretar(arbol, newTabla2)) {
                    break;
                }
                var resultadoInstruccion = instruccion.interpretar(arbol, newTabla2);
                if (resultadoInstruccion instanceof Break) {
                    return null; // Manejar 'break' adecuadamente
                }
                if (resultadoInstruccion instanceof Continue) {
                    // Saltar a la siguiente iteración del ciclo
                    break;
                }
                if (resultadoInstruccion instanceof Errores) {
                    return resultadoInstruccion; // Manejar errores adecuadamente
                }
            }
        }
        return null;
    }
}