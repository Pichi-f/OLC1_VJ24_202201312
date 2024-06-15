
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package analisis;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import abstracto.Instruccion;
import simbolo.Tipo;
import simbolo.tipoDato;
import excepciones.Errores;
import instrucciones.Print;
import instrucciones.Declaracion;
import instrucciones.AsignacionVar;
import expresiones.Nativo;
import expresiones.Aritmeticas;
import expresiones.OperadoresAritmeticos;
import expresiones.Relacionales;
import expresiones.OperadoresRelacionales;
import expresiones.AccesoVar;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\036\000\002\002\004\000\002\002\003\000\002\003" +
    "\004\000\002\003\003\000\002\004\003\000\002\004\003" +
    "\000\002\004\003\000\002\004\004\000\002\005\007\000" +
    "\002\006\011\000\002\012\003\000\002\012\003\000\002" +
    "\007\006\000\002\010\003\000\002\010\003\000\002\010" +
    "\003\000\002\011\004\000\002\011\005\000\002\011\005" +
    "\000\002\011\005\000\002\011\005\000\002\011\005\000" +
    "\002\011\005\000\002\011\005\000\002\011\006\000\002" +
    "\011\003\000\002\011\003\000\002\011\003\000\002\011" +
    "\003\000\002\011\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\071\000\014\003\015\007\011\012\016\032\017\033" +
    "\010\001\002\000\016\002\ufffe\003\ufffe\007\ufffe\012\ufffe" +
    "\032\ufffe\033\ufffe\001\002\000\004\007\063\001\002\000" +
    "\016\002\ufffc\003\ufffc\007\ufffc\012\ufffc\032\ufffc\033\ufffc" +
    "\001\002\000\004\002\062\001\002\000\004\007\ufff6\001" +
    "\002\000\004\030\057\001\002\000\016\002\ufffb\003\ufffb" +
    "\007\ufffb\012\ufffb\032\ufffb\033\ufffb\001\002\000\016\002" +
    "\ufffd\003\ufffd\007\ufffd\012\ufffd\032\ufffd\033\ufffd\001\002" +
    "\000\016\002\000\003\015\007\011\012\016\032\017\033" +
    "\010\001\002\000\004\011\055\001\002\000\004\016\020" +
    "\001\002\000\004\007\ufff7\001\002\000\020\004\026\005" +
    "\027\006\025\007\021\010\024\016\030\021\022\001\002" +
    "\000\024\011\uffe4\017\uffe4\020\uffe4\021\uffe4\023\uffe4\024" +
    "\uffe4\025\uffe4\026\uffe4\030\uffe4\001\002\000\020\004\026" +
    "\005\027\006\025\007\021\010\024\016\030\021\022\001" +
    "\002\000\022\017\052\020\033\021\040\023\032\024\037" +
    "\025\034\026\035\030\036\001\002\000\024\011\uffe5\017" +
    "\uffe5\020\uffe5\021\uffe5\023\uffe5\024\uffe5\025\uffe5\026\uffe5" +
    "\030\uffe5\001\002\000\024\011\uffe7\017\uffe7\020\uffe7\021" +
    "\uffe7\023\uffe7\024\uffe7\025\uffe7\026\uffe7\030\uffe7\001\002" +
    "\000\024\011\uffe6\017\uffe6\020\uffe6\021\uffe6\023\uffe6\024" +
    "\uffe6\025\uffe6\026\uffe6\030\uffe6\001\002\000\024\011\uffe8" +
    "\017\uffe8\020\uffe8\021\uffe8\023\uffe8\024\uffe8\025\uffe8\026" +
    "\uffe8\030\uffe8\001\002\000\020\004\026\005\027\006\025" +
    "\007\021\010\024\016\030\021\022\001\002\000\022\017" +
    "\041\020\033\021\040\023\032\024\037\025\034\026\035" +
    "\030\036\001\002\000\020\004\026\005\027\006\025\007" +
    "\021\010\024\016\030\021\022\001\002\000\020\004\026" +
    "\005\027\006\025\007\021\010\024\016\030\021\022\001" +
    "\002\000\020\004\026\005\027\006\025\007\021\010\024" +
    "\016\030\021\022\001\002\000\020\004\026\005\027\006" +
    "\025\007\021\010\024\016\030\021\022\001\002\000\004" +
    "\030\044\001\002\000\020\004\026\005\027\006\025\007" +
    "\021\010\024\016\030\021\022\001\002\000\020\004\026" +
    "\005\027\006\025\007\021\010\024\016\030\021\022\001" +
    "\002\000\024\011\ufff0\017\ufff0\020\ufff0\021\ufff0\023\ufff0" +
    "\024\ufff0\025\ufff0\026\ufff0\030\ufff0\001\002\000\024\011" +
    "\uffea\017\uffea\020\uffea\021\uffea\023\032\024\037\025\034" +
    "\026\035\030\uffea\001\002\000\024\011\uffed\017\uffed\020" +
    "\uffed\021\uffed\023\uffed\024\uffed\025\034\026\035\030\uffed" +
    "\001\002\000\020\004\026\005\027\006\025\007\021\010" +
    "\024\016\030\021\022\001\002\000\024\011\uffe9\017\uffe9" +
    "\020\033\021\040\023\032\024\037\025\034\026\035\030" +
    "\uffe9\001\002\000\024\011\uffef\017\uffef\020\uffef\021\uffef" +
    "\023\uffef\024\uffef\025\uffef\026\uffef\030\uffef\001\002\000" +
    "\022\011\uffee\017\uffee\020\uffee\021\uffee\023\uffee\024\uffee" +
    "\026\035\030\uffee\001\002\000\024\011\uffeb\017\uffeb\020" +
    "\uffeb\021\040\023\032\024\037\025\034\026\035\030\uffeb" +
    "\001\002\000\024\011\uffec\017\uffec\020\uffec\021\uffec\023" +
    "\uffec\024\037\025\034\026\035\030\uffec\001\002\000\004" +
    "\011\053\001\002\000\016\002\ufff9\003\ufff9\007\ufff9\012" +
    "\ufff9\032\ufff9\033\ufff9\001\002\000\024\011\ufff1\017\ufff1" +
    "\020\ufff1\021\ufff1\023\032\024\037\025\034\026\035\030" +
    "\ufff1\001\002\000\016\002\ufffa\003\ufffa\007\ufffa\012\ufffa" +
    "\032\ufffa\033\ufffa\001\002\000\016\002\uffff\003\uffff\007" +
    "\uffff\012\uffff\032\uffff\033\uffff\001\002\000\020\004\026" +
    "\005\027\006\025\007\021\010\024\016\030\021\022\001" +
    "\002\000\022\011\061\020\033\021\040\023\032\024\037" +
    "\025\034\026\035\030\036\001\002\000\016\002\ufff5\003" +
    "\ufff5\007\ufff5\012\ufff5\032\ufff5\033\ufff5\001\002\000\004" +
    "\002\001\001\002\000\004\027\064\001\002\000\010\013" +
    "\070\014\065\015\066\001\002\000\004\030\ufff3\001\002" +
    "\000\004\030\ufff2\001\002\000\004\030\071\001\002\000" +
    "\004\030\ufff4\001\002\000\020\004\026\005\027\006\025" +
    "\007\021\010\024\016\030\021\022\001\002\000\022\011" +
    "\073\020\033\021\040\023\032\024\037\025\034\026\035" +
    "\030\036\001\002\000\016\002\ufff8\003\ufff8\007\ufff8\012" +
    "\ufff8\032\ufff8\033\ufff8\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\071\000\020\002\006\003\013\004\003\005\012\006" +
    "\005\007\011\012\004\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\014\004\055\005\012\006\005\007\011\012\004\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\004\011\022\001\001\000\002\001\001\000\004\011" +
    "\053\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\011" +
    "\030\001\001\000\002\001\001\000\004\011\050\001\001" +
    "\000\004\011\047\001\001\000\004\011\046\001\001\000" +
    "\004\011\045\001\001\000\002\001\001\000\004\011\042" +
    "\001\001\000\004\011\041\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\011\044\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\004\011\057\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\010" +
    "\066\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\011\071\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



    scanner s;
    parser(scanner s){this.s = s;}

    public LinkedList<Errores> listaErrores = new LinkedList<>();
    
    public void syntax_error(Symbol s){
        listaErrores.add(new Errores("SINTACTICO RECUPERABLE", "No se esperaba el componente " + s.value, s.left, s.right));
    }

    public void unrecovered_syntax_error (Symbol s){
        listaErrores.add(new Errores("SINTACTICO NO RECUPERABLE", "No se esperaba el componente " + s.value, s.left, s.right));
    }



/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parser$actions {



  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parser$do_action_part00000000(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INICIO EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		LinkedList<Instruccion> start_val = (LinkedList<Instruccion>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INICIO ::= INSTRUCCIONES 
            {
              LinkedList<Instruccion> RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		LinkedList<Instruccion> a = (LinkedList<Instruccion>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = a;  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INICIO",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // INSTRUCCIONES ::= INSTRUCCIONES INSTRUCCION 
            {
              LinkedList<Instruccion> RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		LinkedList<Instruccion> a = (LinkedList<Instruccion>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = a; RESULT.add(b);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INSTRUCCIONES",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // INSTRUCCIONES ::= INSTRUCCION 
            {
              LinkedList<Instruccion> RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new LinkedList<>(); RESULT.add(a);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INSTRUCCIONES",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // INSTRUCCION ::= IMPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = a;  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INSTRUCCION",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // INSTRUCCION ::= DECLARACION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = a;  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INSTRUCCION",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // INSTRUCCION ::= ASIGNACION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = a;  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INSTRUCCION",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // INSTRUCCION ::= error FINCADENA 
            {
              Instruccion RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("INSTRUCCION",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // IMPRESION ::= IMPRIMIR PAR1 EXPRESION PAR2 FINCADENA 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		  RESULT = new Print(a, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("IMPRESION",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // DECLARACION ::= MUTABILIDAD ID DOSPUNTOS TIPOS IGUAL EXPRESION FINCADENA 
            {
              Instruccion RESULT =null;
		int mleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).left;
		int mright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)).right;
		String m = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-6)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-5)).value;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Tipo a = (Tipo)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int cleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int cright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Instruccion c = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		  RESULT = new Declaracion(m, b, c, a, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("DECLARACION",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // MUTABILIDAD ::= CONST 
            {
              String RESULT =null;
		  RESULT = "CONST";  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("MUTABILIDAD",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // MUTABILIDAD ::= VAR 
            {
              String RESULT =null;
		  RESULT = "VAR";  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("MUTABILIDAD",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // ASIGNACION ::= ID IGUAL EXPRESION FINCADENA 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		   RESULT=new AsignacionVar(a, b, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("ASIGNACION",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // TIPOS ::= INT 
            {
              Tipo RESULT =null;
		  RESULT = new Tipo(tipoDato.ENTERO);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("TIPOS",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // TIPOS ::= DOUBLE 
            {
              Tipo RESULT =null;
		  RESULT = new Tipo(tipoDato.DECIMAL);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("TIPOS",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // TIPOS ::= STRING 
            {
              Tipo RESULT =null;
		  RESULT = new Tipo(tipoDato.CADENA);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("TIPOS",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // EXPRESION ::= MENOS EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Aritmeticas(a, OperadoresAritmeticos.NEGACION, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // EXPRESION ::= PAR1 EXPRESION PAR2 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		  RESULT = a;  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // EXPRESION ::= EXPRESION MODULO EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.MODULO, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // EXPRESION ::= EXPRESION POTENCIA EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.POTENCIA, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // EXPRESION ::= EXPRESION DIVISION EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.DIVISION, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // EXPRESION ::= EXPRESION MULTIPLICACION EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.MULTIPLICACION, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // EXPRESION ::= EXPRESION MAS EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.SUMA, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // EXPRESION ::= EXPRESION MENOS EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Aritmeticas(a, b, OperadoresAritmeticos.RESTA, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // EXPRESION ::= EXPRESION IGUAL IGUAL EXPRESION 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		Instruccion a = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Instruccion b = (Instruccion)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Relacionales(a, b, OperadoresRelacionales.EQUALS, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // EXPRESION ::= ENTERO 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Nativo(Integer.parseInt(a), new Tipo(tipoDato.ENTERO), aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // EXPRESION ::= DECIMAL 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Nativo(new Double(a), new Tipo(tipoDato.DECIMAL), aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // EXPRESION ::= CADENA 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Nativo(a, new Tipo(tipoDato.CADENA), aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // EXPRESION ::= CARACTER 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new Nativo(a, new Tipo(tipoDato.CARACTER), aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // EXPRESION ::= ID 
            {
              Instruccion RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  RESULT = new AccesoVar(a, aleft, aright);  
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
              return CUP$parser$do_action_part00000000(
                               CUP$parser$act_num,
                               CUP$parser$parser,
                               CUP$parser$stack,
                               CUP$parser$top);
    }
}

}
