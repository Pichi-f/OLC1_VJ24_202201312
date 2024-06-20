/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;

/**
 *
 * @author pichi
 */
public class Aritmeticas extends Instruccion {

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operandoUnico;

    // Negacion
    public Aritmeticas(Instruccion operandoUnico, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    // Cualquier operacion
    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        } else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }
        }

        return switch (operacion) {
            case SUMA ->
                this.suma(opIzq, opDer);
            case NEGACION ->
                this.negacion(Unico);
            case RESTA ->
                this.resta(opIzq, opDer);
            case MULTIPLICACION ->
                this.multiplicacion(opIzq, opDer);
            case DIVISION ->
                this.division(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer);
            case MODULO ->
                this.modulo(opIzq, opDer);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.col);
        };
    }

    public Object suma(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case tipoDato.BOOLEANO -> {
                        return new Errores("SEMANTICO", "No se puede sumar un entero con un booleano", this.linea, this.col);
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) ((String) op2).charAt(0);
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case tipoDato.BOOLEANO -> {
                        return new Errores("SEMANTICO", "No se puede sumar un decimal con un booleano", this.linea, this.col);
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 + (double) ((String) op2).charAt(0);
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        return new Errores("SEMANTICO", "No se puede suma un boolenao con un entero", this.linea, this.col);
                    }
                    case tipoDato.DECIMAL -> {
                        return new Errores("SEMANTICO", "No se puede suma un boolenao con un decimal", this.linea, this.col);
                    }
                    case tipoDato.BOOLEANO -> {
                        return new Errores("SEMANTICO", "No se puede suma un booleano con un booleano", this.linea, this.col);
                    }
                    case tipoDato.CARACTER -> {
                        return new Errores("SEMANTICO", "No se puede suma un booleano con un caracter", this.linea, this.col);
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return ((String) op1).charAt(0) + (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case tipoDato.BOOLEANO -> {
                        return new Errores("SEMANTICO", "No se puede sumar un entero con un booleano", this.linea, this.col);
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) ((String) op2).charAt(0);
                    }
                    case tipoDato.CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.CADENA -> {
                this.tipo.setTipo(tipoDato.CADENA);
                return op1.toString() + op2.toString();
            }
            default -> {
                return new Errores("SEMANTICO", "AFUERA", this.linea, this.col);
            }
        }
    }

    public Object resta(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 - (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 - ((String) op2).charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - ((String) op2).charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return ((String) op1).charAt(0) - (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return ((String) op1).charAt(0) - (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        return new Errores("SEMANTICO", "No se puede restar un caracter entre otro caracter", this.linea, this.col);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "AFUERA", this.linea, this.col);
            }
        }
    }

    public Object multiplicacion(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 * (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 * (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 * ((String) op2).charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicación errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * ((String) op2).charAt(0);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicación errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return ((String) op1).charAt(0) * (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return ((String) op1).charAt(0) * (double) op2;
                    }
                    case tipoDato.CARACTER -> {
                        return new Errores("SEMANTICO", "No se puede multiplicar un caracter entre otro caracter", this.linea, this.col);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicación erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "AFUERA", this.linea, this.col);
            }
        }
    }
    
    public Object division(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((int) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double)((int) op1 / (int) op2);
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((double) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) ((int) op1 / (double) op2);
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if (((String) op2).charAt(0) == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return ((double) (int) op1 / ((String) op2).charAt(0));
                    }
                    default -> {
                        return new Errores("SEMANTICO", "División errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((int) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) ((double) op1 / (int) op2);
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((double) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) ((double) op1 / (double) op2);
                    }
                    case tipoDato.CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if (((String) op2).charAt(0) == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) ((double) op1 / ((String) op2).charAt(0));
                    }
                    default -> {
                        return new Errores("SEMANTICO", "División errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.CARACTER -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((int) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) (((String) op1).charAt(0) / (int) op2);
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((double) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) (((String) op1).charAt(0) / (double) op2);
                    }
                    case tipoDato.CARACTER -> {
                        return new Errores("SEMANTICO", "No se puede dividir un caracter entre otro caracter", this.linea, this.col);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "División erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "AFUERA", this.linea, this.col);
            }
        }
    }
    
    public Object potencia(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        if ((int) op2 == 0) {
                            return 1;
                        }
                        return (int) Math.pow((int) op1, (int) op2);
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((double) op2 == 0) {
                            return 1;
                        }
                        return (double) Math.pow((int) op1, (double) op2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Potencia errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((int) op2 == 0) {
                            return 1;
                        }
                        return (double) Math.pow((double) op1, (int) op2);
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((double) op2 == 0) {
                            return 1;
                        }
                        return (double) Math.pow((double) op1, (double) op2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Potencia errorea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "AFUERA", this.linea, this.col);
            }
        }
    }
    
    public Object modulo(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1) {
            case tipoDato.ENTERO -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((int) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (int) op1 % (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((int) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (int) op1 % (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Modulo errorea", this.linea, this.col);
                    }
                }
            }
            case tipoDato.DECIMAL -> {
                switch (tipo2) {
                    case tipoDato.ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((int) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) op1 % (int) op2;
                    }
                    case tipoDato.DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        if ((double) op2 == 0) {
                            return new Errores("SEMANTICO", "No se puede dividir entre 0", this.linea, this.col);
                        }
                        return (double) op1 % (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Modulo errorea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Potencia errorea", this.linea, this.col);
            }
        }
    }
    
    public Object negacion(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case tipoDato.ENTERO -> {
                this.tipo.setTipo(tipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case tipoDato.DECIMAL -> {
                this.tipo.setTipo(tipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion errores", this.linea, this.col);
            }
        }
    }

}
