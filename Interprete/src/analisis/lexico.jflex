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
MAS = "+"
MENOS = "-"
IGUAL = "="
FINCADENA = ";"
DOSPUNTOS = ":"
BLANCOS = [\ \t\n\r\f]+
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
ID = [a-zA-Z][a-zA-Z0-9_]*
CADENA = [\"]([^\"])*[\"]

// Palabras reservadas
IMPRIMIR = "println"
INT = "int"
DOUBLE = "double"
STRING = "string"
CONST = "const"
VAR = "var"


%%
<YYINITIAL> {IMPRIMIR} { return new Symbol(sym.IMPRIMIR, yyline, yycolumn, yytext()); }
<YYINITIAL> {INT} { return new Symbol(sym.INT, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOUBLE} { return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext()); }
<YYINITIAL> {STRING} { return new Symbol(sym.STRING, yyline, yycolumn, yytext()); }


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

<YYINITIAL> {FINCADENA} { return new Symbol(sym.FINCADENA, yyline, yycolumn, yytext()); }
<YYINITIAL> {PAR1} { return new Symbol(sym.PAR1, yyline, yycolumn, yytext()); }
<YYINITIAL> {PAR2} { return new Symbol(sym.PAR2, yyline, yycolumn, yytext()); }

<YYINITIAL> {MAS} { return new Symbol(sym.MAS, yyline, yycolumn, yytext()); }
<YYINITIAL> {MENOS} { return new Symbol(sym.MENOS, yyline, yycolumn, yytext()); }
<YYINITIAL> {IGUAL} { return new Symbol(sym.IGUAL, yyline, yycolumn, yytext()); }
<YYINITIAL> {DOSPUNTOS} { return new Symbol(sym.DOSPUNTOS, yyline, yycolumn, yytext()); }

<YYINITIAL> {BLANCOS} { }

<YYINITIAL> . {
                listaErrores.add(new Errores("LEXICO", "El caracter " + yytext() + " NO pertenece al lenguaje", yyline, yycolumn));
}