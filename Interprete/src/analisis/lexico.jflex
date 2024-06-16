package analisis;

//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;

%%

// Codigo de usuario
%{
    public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

// Caracteristicas de jflex
%cup
%class scanner
%public
%line
%char
%column
%full
//%debug
%ignorecase

// Simbolos del sistema
PAR1 = "("
PAR2 = ")"
MODULO = "%"
POTENCIA = "**"
DIVISION = "/"
MULTIPLICACION = "*"
MAS = "+"
MENOS = "-"
IGUAL = "="
DIFERENTE = "!="
FINCADENA = ";"
DOSPUNTOS = ":"
LLAVE1 = "{"
LLAVE2 = "}"
MENOR = "<"
MAYOR = ">"
MENORIGUAL = "<="
MAYORIGUAL = ">="
OR = "||"
AND = "&&"
XOR = "^"
NOT = "!"
BLANCOS = [\ \t\n\r\f]+
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
ID = [a-zA-Z][a-zA-Z0-9_]*
CADENA = [\"]([^\"])*[\"]
CARACTER = \'[^\']\'
COMSIM = "//"([^\r\n]*)
COMMULT = [/][*][^*]*[*]+([^/*][^*]*[*]+)*[/]

// Palabras reservadas
IMPRIMIR = "println"
INT = "int"
DOUBLE = "double"
STRING = "string"
CONST = "const"
VAR = "var"
IF = "if"
TRUE = "true"
FALSE = "false"
BOOL = "bool"
FOR = "for"
BREAK = "break"


%%
<YYINITIAL> {IMPRIMIR} { return new Symbol(sym.IMPRIMIR, yyline, yycolumn, yytext()); }
<YYINITIAL> {INT} { return new Symbol(sym.INT, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOUBLE} { return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext()); }
<YYINITIAL> {STRING} { return new Symbol(sym.STRING, yyline, yycolumn, yytext()); }
<YYINITIAL> {TRUE} {return new Symbol(sym.TRUE, yyline, yycolumn,yytext());}
<YYINITIAL> {FALSE} {return new Symbol(sym.FALSE, yyline, yycolumn,yytext());}
<YYINITIAL> {IF} {return new Symbol(sym.IF, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOL} {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> {FOR} {return new Symbol(sym.FOR, yyline, yycolumn,yytext());}
<YYINITIAL> {BREAK} {return new Symbol(sym.BREAK, yyline, yycolumn,yytext());}

//PALABRAS RESERVADAS
<YYINITIAL> {CONST} { return new Symbol(sym.CONST, yyline, yycolumn, yytext()); }
<YYINITIAL> {VAR} { return new Symbol(sym.VAR, yyline, yycolumn, yytext()); }
<YYINITIAL> {ID} { return new Symbol(sym.ID, yyline, yycolumn, yytext()); }

<YYINITIAL> {DECIMAL} { return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {ENTERO} { return new Symbol(sym.ENTERO, yyline, yycolumn, yytext()); }

<YYINITIAL> {CADENA} { 
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn, cadena); }
<YYINITIAL> {CARACTER} {
    String caracter = yytext();
    caracter = caracter.substring(1, caracter.length()-1);
    return new Symbol(sym.CARACTER, yyline, yycolumn, caracter); }

<YYINITIAL> {FINCADENA} { return new Symbol(sym.FINCADENA, yyline, yycolumn, yytext()); }
<YYINITIAL> {PAR1} { return new Symbol(sym.PAR1, yyline, yycolumn, yytext()); }
<YYINITIAL> {PAR2} { return new Symbol(sym.PAR2, yyline, yycolumn, yytext()); }
<YYINITIAL> {LLAVE1} {return new Symbol(sym.LLAVE1, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE2} {return new Symbol(sym.LLAVE2, yyline, yycolumn,yytext());}

<YYINITIAL> {MODULO} { return new Symbol(sym.MODULO, yyline, yycolumn, yytext()); }
<YYINITIAL> {POTENCIA} { return new Symbol(sym.POTENCIA, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIVISION} { return new Symbol(sym.DIVISION, yyline, yycolumn, yytext()); }
<YYINITIAL> {MULTIPLICACION} { return new Symbol(sym.MULTIPLICACION, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAS} { return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOS} { return new Symbol(sym.MENOS, yyline, yycolumn, yytext()); }
<YYINITIAL> {IGUAL} { return new Symbol(sym.IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENORIGUAL} { return new Symbol(sym.MENORIGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYORIGUAL} { return new Symbol(sym.MAYORIGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {DIFERENTE} { return new Symbol(sym.DIFERENTE, yyline, yycolumn, yytext()); }
<YYINITIAL> {MAYOR} { return new Symbol(sym.MAYOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOR} { return new Symbol(sym.MENOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {OR} { return new Symbol(sym.OR, yyline, yycolumn, yytext()); }
<YYINITIAL> {AND} { return new Symbol(sym.AND, yyline, yycolumn, yytext()); }
<YYINITIAL> {XOR} { return new Symbol(sym.XOR, yyline, yycolumn, yytext()); }
<YYINITIAL> {NOT} { return new Symbol(sym.NOT, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOSPUNTOS} { return new Symbol(sym.DOSPUNTOS, yyline, yycolumn, yytext()); }

<YYINITIAL> {BLANCOS} { }
<YYINITIAL> {COMSIM} { }
<YYINITIAL> {COMMULT} { }

<YYINITIAL> . {
                listaErrores.add(new Errores("LEXICO", "El caracter " + yytext() + " NO pertenece al lenguaje", yyline, yycolumn));
}