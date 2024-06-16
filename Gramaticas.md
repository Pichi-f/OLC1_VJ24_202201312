# GRAMATICAS

INICIO ::= INSTRUCCIONES:a    {:  RESULT = a;  :}
;


INSTRUCCIONES ::= INSTRUCCIONES:a INSTRUCCION:b     {:  RESULT = a; RESULT.add(b);  :}

                | INSTRUCCION:a                     {:  RESULT = new LinkedList<>(); RESULT.add(a);  :}
;


INSTRUCCION ::= IMPRESION:a         {:  RESULT = a;  :}

                | DECLARACION:a     {:  RESULT = a;  :}

                | ASIGNACION:a      {:  RESULT = a;  :}

                | SIF:a             {:   RESULT=a;   :}

                | CFOR:a            {:   RESULT=a;   :}

                | BREAKK:a          {:   RESULT=a;   :}

                | SWHILE:a          {:   RESULT=a;   :}

                | error FINCADENA  
;


IMPRESION ::= IMPRIMIR PAR1 EXPRESION:a PAR2 FINCADENA      {:  RESULT = new Print(a, aleft, aright);  :}
;


DECLARACION ::= MUTABILIDAD:m ID:b DOSPUNTOS TIPOS:a IGUAL EXPRESION:c FINCADENA    {:  RESULT = new Declaracion(m, b, c, a, aleft, aright);  :}
;


MUTABILIDAD ::= CONST     {:  RESULT = "CONST";  :}
                | VAR      {:  RESULT = "VAR";  :}
;


ASIGNACION ::= ID:a IGUAL EXPRESION:b FINCADENA  {:   RESULT=new AsignacionVar(a, b, aleft, aright);  :}
;


SIF ::= IF PAR1 EXPRESION:a PAR2 LLAVE1 INSTRUCCIONES:b LLAVE2  {:   RESULT = new If(a, b, aleft, aright);   :}
;


CFOR ::= FOR PAR1 ASIGNACION:a EXPRESION:b FINCADENA ACTUALIZA_FOR:c PAR2 LLAVE1 INSTRUCCIONES:d LLAVE2     {:   RESULT = new For(a, b, c, d, aleft, aright);   :}
;


ACTUALIZA_FOR ::= ID:a IGUAL EXPRESION:b {:   RESULT=new AsignacionVar(a, b, aleft, aright);  :}
;


BREAKK ::= BREAK:a FINCADENA      {:    RESULT = new Break(aleft, aright);     :}
;


SWHILE ::= WHILE PAR1 EXPRESION:a PAR2 LLAVE1 INSTRUCCIONES:b LLAVE2     {:   RESULT = new While(a, b, aleft, aright);   :}
;


TIPOS ::= INT     {:  RESULT = new Tipo(tipoDato.ENTERO);  :}

        | DOUBLE  {:  RESULT = new Tipo(tipoDato.DECIMAL);  :}

        | STRING  {:  RESULT = new Tipo(tipoDato.CADENA);  :}

        | BOOL    {:  RESULT = new Tipo(tipoDato.BOOLEANO);  :}
;

EXPRESION ::= MENOS EXPRESION:a                 {:  RESULT = new Aritmeticas(a, OperadoresAritmeticos.NEGACION, aleft, aright);  :} %prec UMENOS

            | EXPRESION:a MODULO EXPRESION:b    {:  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.MODULO, aleft, aright);  :}%prec MODULO

            | EXPRESION:a POTENCIA EXPRESION:b  {:  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.POTENCIA, aleft, aright);  :}

            | EXPRESION:a DIVISION EXPRESION:b  {:  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.DIVISION, aleft, aright);  :}

            | EXPRESION:a MULTIPLICACION EXPRESION:b  {:  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.MULTIPLICACION, aleft, aright);  :}

            | EXPRESION:a MAS EXPRESION:b       {:  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.SUMA, aleft, aright);  :}

            | EXPRESION:a MENOS EXPRESION:b     {:  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.RESTA, aleft, aright);  :}

            | EXPRESION:a IGUAL IGUAL EXPRESION:b     {:  RESULT = new Relacionales(a, b, OperadoresRelacionales.EQUALS, aleft, aright);  :}%prec EQUALS

            | EXPRESION:a DIFERENTE EXPRESION:b     {:  RESULT = new Relacionales(a, b, OperadoresRelacionales.DIFERENTE, aleft, aright);  :}%prec DIFERENTE

            | EXPRESION:a MENORIGUAL EXPRESION:b     {:  RESULT = new Relacionales(a, b, OperadoresRelacionales.MENORIGUAL, aleft, aright);  :}

            | EXPRESION:a MAYORIGUAL EXPRESION:b     {:  RESULT = new Relacionales(a, b, OperadoresRelacionales.MAYORIGUAL, aleft, aright);  :}

            | EXPRESION:a MENOR EXPRESION:b     {:  RESULT = new Relacionales(a, b, OperadoresRelacionales.MENOR, aleft, aright);  :}

            | EXPRESION:a MAYOR EXPRESION:b     {:  RESULT = new Relacionales(a, b, OperadoresRelacionales.MAYOR, aleft, aright);  :}

            | EXPRESION:a OR EXPRESION:b        {:  RESULT = new Logicos(a, b, OperadoresLogicos.OR, aleft, aright);  :}%prec OR

            | EXPRESION:a AND EXPRESION:b       {:  RESULT = new Logicos(a, b, OperadoresLogicos.AND, aleft, aright);  :}%prec AND

            | EXPRESION:a XOR EXPRESION:b       {:  RESULT = new Logicos(a, b, OperadoresLogicos.XOR, aleft, aright);  :}%prec XOR

            | NOT EXPRESION:a                    {:  RESULT = new Logicos(a, OperadoresLogicos.NOT, aleft, aright);  :}%prec NOT

            | ENTERO:a      {:  RESULT = new Nativo(Integer.parseInt(a), new Tipo(tipoDato.ENTERO), aleft, aright);  :} 

            | DECIMAL:a     {:  RESULT = new Nativo(new Double(a), new Tipo(tipoDato.DECIMAL), aleft, aright);  :}

            | CADENA:a      {:  RESULT = new Nativo(a, new Tipo(tipoDato.CADENA), aleft, aright);  :}

            | CARACTER:a    {:  RESULT = new Nativo(a, new Tipo(tipoDato.CARACTER), aleft, aright);  :}

            | TRUE:a        {:   RESULT = new Nativo(true, new Tipo(tipoDato.BOOLEANO), aleft, aright );   :}

            | FALSE:a       {:   RESULT = new Nativo(false, new Tipo(tipoDato.BOOLEANO), aleft, aright );    :}

            | ID:a          {:  RESULT = new AccesoVar(a, aleft, aright);  :}

            | PAR1 EXPRESION:a PAR2             {:  RESULT = a;  :}
;
