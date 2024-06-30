/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

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
public class Declaracion extends Instruccion {

    public String mutabilidad;
    public String identificador;
    public Instruccion valor;

    //aqui se veran los valores por defecto, tomarlo en cuenta
    public Declaracion(String mutabilidad, String identificador, Instruccion valor, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.valor = valor;
    }

    public Declaracion(String mutabilidad, String identificador, Tipo tipo, int linea, int col) {
        super(tipo, linea, col);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.valor = null;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        // Interpretar la expresión
        var valorInterpretado = (this.valor == null) ? this.valoresDefault() : this.valor.interpretar(arbol, tabla);
        // Se valida si es error
        if (valorInterpretado instanceof Errores) {
            return valorInterpretado;
        }
        // Se valida el tipo
        if (this.valor != null) {
            if (this.valor.tipo.getTipo() != this.tipo.getTipo()) {
                return new Errores("SEMANTICO", "Tipos Erroneos", this.linea, this.col);
            }
        }

        //Se crea el simbolo
        Simbolo s = new Simbolo(this.tipo, this.identificador, valorInterpretado);
        if (mutabilidad.toLowerCase().equals("const")) {
            s.setMutabilidad(true);
            System.out.println("Valor constante");
        }

        boolean creacion = tabla.setVariable(s);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.col);
        }

        return null;
    }

    public Object valoresDefault() {
        return switch (this.tipo.getTipo()) {
            case tipoDato.BOOLEANO ->
                true;
            case tipoDato.CADENA ->
                "";
            case tipoDato.CARACTER ->
                '\u0000';
            case tipoDato.ENTERO ->
                0;
            case tipoDato.DECIMAL ->
                0.0;
            default ->
                null;
        };
    }
}
