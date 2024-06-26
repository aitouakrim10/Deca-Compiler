parser grammar DecaParser;

options {
    // Default language but name it anyway
    //
    language  = Java;

    // Use a superclass to implement all helper
    // methods, instance variables and overrides
    // of ANTLR default methods, such as error
    // handling.
    //
    superClass = AbstractDecaParser;

    // Use the vocabulary generated by the accompanying
    // lexer. Maven knows how to work out the relationship
    // between the lexer and parser and will build the
    // lexer before the parser. It will also rebuild the
    // parser if the lexer changes.
    //
    tokenVocab = DecaLexer;

}

// which packages should be imported?
@header {
    import java.util.*;
    import fr.ensimag.deca.tree.*;
    import fr.ensimag.deca.tools.*;
    import java.io.PrintStream;
}

@members {
    @Override
    protected AbstractProgram parseProgram() {
        return prog().tree;
    }
}


prog returns[AbstractProgram tree]
    : list_classes main EOF {
            assert($list_classes.tree != null);
            assert($main.tree != null);
            $tree = new Program($list_classes.tree, $main.tree);
            setLocation($tree, $list_classes.start);

        }
    ;

main returns[AbstractMain tree]
    : /* epsilon */ {
            $tree = new EmptyMain();
        }
    | block {
            assert($block.decls != null);
            assert($block.insts != null);
            $tree = new Main($block.decls, $block.insts);
            setLocation($tree, $block.start);
        }
    ;

block returns[ListDeclVar decls, ListInst insts]
    : OBRACE list_decl list_inst CBRACE {
            assert($list_decl.tree != null);
            assert($list_inst.tree != null);
            $decls = $list_decl.tree;
            $insts = $list_inst.tree;
        }
    ;

list_decl returns[ListDeclVar tree]
@init   {
            $tree = new ListDeclVar();
        
        }
    : decl_var_set[$tree]*
    ;

decl_var_set[ListDeclVar l]
    : type list_decl_var[$l,$type.tree] SEMI
    ;

list_decl_var[ListDeclVar l, AbstractIdentifier t]
    : dv1=decl_var[$t] {
        $l.add($dv1.tree);
        } (COMMA dv2=decl_var[$t] {
            $l.add($dv2.tree);
        }
      )*
    ;

decl_var[AbstractIdentifier t] returns[AbstractDeclVar tree]
@init   {
            AbstractInitialization initialisation = new NoInitialization();
        }
    : i=ident {
        }
      (EQUALS e=expr {
            initialisation = new Initialization($e.tree);
            setLocation(initialisation,$EQUALS);
        }
      )? {  
            $tree = new DeclVar($t,$i.tree,initialisation);
            setLocation($tree,$i.start);
        }
    ;

list_inst returns[ListInst tree]
@init {
    $tree = new ListInst() ;
}
    : (inst {
        $tree.add($inst.tree);
        setLocation($tree, $inst.start);
        }
      )*
    ;

inst returns[AbstractInst tree]
    : e1=expr SEMI {
            assert($e1.tree != null);
            $tree = $e1.tree;
            setLocation($tree, $e1.start);

        }
    | SEMI {
            $tree = new NoOperation();
            setLocation($tree, $SEMI);
        }
    | PRINT OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Print(false,$list_expr.tree);
            setLocation($tree, $PRINT);
        }
    | PRINTLN OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Println(false,$list_expr.tree);
            setLocation($tree, $PRINTLN);

        }
    | PRINTX OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Print(true,$list_expr.tree);
            setLocation($tree, $PRINTX);
        }
    | PRINTLNX OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Println(true,$list_expr.tree);
            setLocation($tree, $PRINTLNX);
        }
    | if_then_else {
            assert($if_then_else.tree != null);
            $tree = $if_then_else.tree;
            
        }
    | WHILE OPARENT condition=expr CPARENT OBRACE body=list_inst CBRACE {
            assert($condition.tree != null);
            assert($body.tree != null);
            $tree = new While($condition.tree, $body.tree);
            setLocation($tree, $WHILE);
    
        }
    | RETURN expr SEMI {
            assert($expr.tree != null);
            $tree = new Return($expr.tree);
            setLocation($tree, $RETURN);
            
        }
    ;

if_then_else returns[IfThenElse tree]
@init { 
        Deque<Token> tokens = new LinkedList<Token>();
        Deque<AbstractExpr> condition = new LinkedList<AbstractExpr>();
        Deque<ListInst> insts = new LinkedList<ListInst>();
        
}
    : if1=IF OPARENT condition=expr CPARENT OBRACE li_if=list_inst CBRACE {
            tokens.add($if1);
            condition.add($condition.tree);
            insts.add($li_if.tree);
        }
      (ELSE elsif=IF OPARENT elsif_cond=expr CPARENT OBRACE elsif_li=list_inst CBRACE {
           tokens.add($elsif);
           condition.add($elsif_cond.tree);
           insts.add($elsif_li.tree);
        }
      )*
      (ELSE OBRACE li_else=list_inst CBRACE {
            insts.add($li_else.tree);
            
        }
      )?
      {
            if (insts.size() == condition.size()) {
                  insts.add(new ListInst());
            }

            ListInst elseInst = insts.removeLast();
            for(int i = 0 ; i < condition.size()+1; i++) {
                  AbstractExpr condExpr = condition.removeLast();
                  ListInst ifInst = insts.removeLast();
                  $tree = new IfThenElse(condExpr, ifInst , elseInst);
                  setLocation($tree,tokens.removeLast());
                  elseInst= new ListInst();
                  elseInst.add($tree);
            }
      }
    ;

list_expr returns[ListExpr tree]
@init   {
        $tree = new ListExpr();
        }
    : (e1=expr {
        $tree.add($e1.tree) ;
        setLocation($tree, $e1.start);
        }
       (COMMA e2=expr {
            $tree.add($e2.tree) ;
            //setLocation($tree, $e2.start);
        }
       )* )?
    ;

expr returns[AbstractExpr tree]

    : assign_expr {
            assert($assign_expr.tree != null);
            $tree = $assign_expr.tree;
            setLocation($tree, $assign_expr.start);
        }
    ;

assign_expr returns[AbstractExpr tree]
    : e=or_expr (
        /* condition: expression e must be a "LVALUE" */ {
            if (! ($e.tree instanceof AbstractLValue)) {
                throw new InvalidLValue(this, $ctx);
            }
        }
        EQUALS e2=assign_expr {
            assert($e.tree != null);
            assert($e2.tree != null);
            $tree = new Assign((AbstractLValue)$e.tree , $e2.tree);
            setLocation($tree, $EQUALS);

        }
      | /* epsilon */ {
            assert($e.tree != null);
            $tree = $or_expr.tree ;
            setLocation($tree, $e.start);
        }
      )
    ;

or_expr returns[AbstractExpr tree]
    : e=and_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree, $e.start);
        }
    | e1=or_expr OR e2=and_expr {
            assert($e2.tree != null);
            assert($e1.tree != null);
            $tree =new Or($e1.tree,$e2.tree);
            setLocation($tree, $OR);

       }
    ;

and_expr returns[AbstractExpr tree]
    : e=eq_neq_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree, $e.start);
        }
    |  e1=and_expr AND e2=eq_neq_expr {
            assert($e1.tree != null);                         
            assert($e2.tree != null);
            $tree = new And($e1.tree,$e2.tree);
           setLocation($tree, $AND);
        }
    ;

eq_neq_expr returns[AbstractExpr tree]
    : e=inequality_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree, $e.start);
        }
    | e1=eq_neq_expr EQEQ e2=inequality_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Equals($e1.tree, $e2.tree);
            setLocation($tree, $EQEQ);

        }
    | e1=eq_neq_expr NEQ e2=inequality_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new NotEquals($e1.tree, $e2.tree);
            setLocation($tree, $NEQ);

        }
    ;

inequality_expr returns[AbstractExpr tree]
    : e=sum_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree, $e.start);
        }
    | e1=inequality_expr LEQ e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new LowerOrEqual($e1.tree, $e2.tree);
            setLocation($tree, $LEQ);
            
        }
    | e1=inequality_expr GEQ e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree=new GreaterOrEqual($e1.tree , $e2.tree);
            setLocation($tree, $GEQ);

        }
    | e1=inequality_expr GT e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree=new Greater($e1.tree,$e2.tree);
            setLocation($tree, $GT);

        }
    | e1=inequality_expr LT e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree=new Lower($e1.tree,$e2.tree);
            setLocation($tree, $LT);

        }
    
    | e1=inequality_expr INSTANCEOF type {
        assert($e1.tree != null);
        assert($type.tree != null);
        $tree= new InstanceOf($e1.tree,$type.tree);
    }
    ;


sum_expr returns[AbstractExpr tree]
    : e=mult_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree, $e.start);
        }
    | e1=sum_expr PLUS e2=mult_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree  = new Plus($e1.tree , $e2.tree);
            setLocation($tree,  $PLUS);
        }
    | e1=sum_expr MINUS e2=mult_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree= new Minus($e1.tree , $e2.tree);
            setLocation($tree, $MINUS);
        }
    ;

mult_expr returns[AbstractExpr tree]
    : e=unary_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree, $e.start);
        }
    | e1=mult_expr TIMES e2=unary_expr {
            assert($e1.tree != null);                                         
            assert($e2.tree != null);
            $tree = new Multiply($e1.tree, $e2.tree);
            setLocation($tree, $TIMES);

        }
    | e1=mult_expr SLASH e2=unary_expr {
            assert($e1.tree != null);                                         
            assert($e2.tree != null);
            $tree=new Divide($e1.tree,$e2.tree);
            setLocation($tree, $SLASH);
        }
    | e1=mult_expr PERCENT e2=unary_expr {
            assert($e1.tree != null);                                                                          
            assert($e2.tree != null);
            $tree=new Modulo($e1.tree , $e2.tree);
            setLocation($tree, $PERCENT );
        }
    ;

unary_expr returns[AbstractExpr tree]
    : op=MINUS e=unary_expr {
            assert($e.tree != null);
            $tree=new UnaryMinus($e.tree);
            setLocation($tree, $op);
        }

    | op=EXCLAM e=unary_expr {
            assert($e.tree != null);
            $tree=new Not($e.tree);
            setLocation($tree, $op);
        }
    |array_element_access {
            $tree = $array_element_access.tree;
           
            }
    | select_expr {
            assert($select_expr.tree != null);
            $tree=$select_expr.tree;
            setLocation($tree, $select_expr.start);
        }
    ;

select_expr returns[AbstractExpr tree]
    : e=primary_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree, $e.start);
        }
    | e1=select_expr DOT i=ident {
            assert($e1.tree != null);
            assert($i.tree != null);
        }
        (o=OPARENT args=list_expr CPARENT {
            // we matched "e1.i(args)"
            assert($args.tree != null);
            $tree = new MethodCall($e1.tree, $i.tree, $args.tree);
            setLocation($tree, $e1.start);
            }
        | /* epsilon */ {
            // we matched "e.i"
            $tree=new Select($e1.tree, $i.tree);
            setLocation($tree, $e1.start);
           
        }
        )
    ;
    

primary_expr returns[AbstractExpr tree]
    : ident {
            assert($ident.tree != null);
            $tree = $ident.tree;
            setLocation($tree, $ident.start);
            }
    | m=ident OPARENT args=list_expr CPARENT {
            assert($args.tree != null);
            assert($m.tree != null);
            $tree = new MethodCall(new This(), $m.tree, $args.tree);
            setLocation($tree, $ident.start);
        }

    | OPARENT expr CPARENT {
            assert($expr.tree != null);
            $tree = $expr.tree;
            setLocation($tree,$expr.start);
        }
    | READINT OPARENT CPARENT {
        $tree = new ReadInt();
        setLocation($tree , $READINT);
        }

    | READFLOAT OPARENT CPARENT {
        $tree=new ReadFloat();
        setLocation($tree , $READFLOAT);
        }
    
    | NEW IDENT(LBRACKET)(expr)(RBRACKET)  {
        $tree= new Array1(new ArrayIdentifier(new SymbolTable().create($IDENT.text)),$expr.tree);
        //setLocation($tree, $NEW);
        }

    | NEW ident OPARENT CPARENT {
            assert($ident.tree != null);
            $tree=new New($ident.tree);
            setLocation($tree, $NEW );


        }
    | cast=OPARENT type CPARENT OPARENT expr CPARENT {
            assert($type.tree != null);
            assert($expr.tree != null);
            $tree = new Cast($type.tree, $expr.tree);
            setLocation($tree,$cast);

        }
    | literal {
            assert($literal.tree != null);
            $tree = $literal.tree ;
            setLocation($tree, $literal.start);
        }
    ;

type returns[AbstractIdentifier tree]
    : ident {
            assert($ident.tree != null);
            $tree=$ident.tree;
            setLocation($tree, $ident.start);
        }
    ;

literal returns[AbstractExpr tree]
    : INT {
        try {
            $tree = new IntLiteral(Integer.parseInt($INT.text));
            setLocation($tree , $INT);
            } catch(NumberFormatException e) {
                throw new InvalidIntValue(this, $ctx);
            }
        }
    | fd=FLOAT {
            try {
                
                Double d = Double.parseDouble($fd.text);
                float f = Float.parseFloat($fd.text);
                if(f == 0 && 0 != d){
                    throw new InvalideSmallFloat(this, $ctx);
                }
                $tree = new FloatLiteral(Float.parseFloat($fd.text));
                setLocation($tree, $fd);
            }catch(IllegalArgumentException e){
                throw new InvalidFloatValue(this, $ctx);
            }
           
        }
    | CHARR {
                $tree = new CharLiteral($CHARR.text);
                setLocation($tree , $CHARR);

    }
    | STRING {
            $tree = new StringLiteral($STRING.text.replace("\"",""));
            setLocation($tree, $STRING);
        }
    | TRUE {
            $tree = new BooleanLiteral(true);
            setLocation($tree, $TRUE);
        }
    | FALSE {
            $tree = new BooleanLiteral(false);
            setLocation($tree, $FALSE);
        }
    | THIS {
            $tree = new This();
            setLocation($tree, $THIS);
        }
    | NULL {
            $tree = new Null();
            setLocation($tree, $NULL);
        }
    ;

ident returns[AbstractIdentifier tree]
    : IDENT {
            $tree = new Identifier(new SymbolTable().create($IDENT.text));
            setLocation($tree, $IDENT);
        }
    |  IDENT(LBRACKET)(RBRACKET) {
            $tree = new ArrayIdentifier(new SymbolTable().create($IDENT.text));
            setLocation($tree, $IDENT);
         }

    ;

/****     Class related rules     ****/

list_classes returns[ListDeclClass tree]
    @init {
        $tree = new ListDeclClass();
    }
     : (c1=class_decl {
            $tree.add($c1.tree);
            setLocation($tree, $c1.start);
        }
      )*
    ;

class_decl returns[DeclClass tree]
    : CLASS name=ident superclass=class_extension OBRACE d=class_body CBRACE {
            assert($name.tree != null);
            $tree = new DeclClass($name.tree,$superclass.tree, $d.declfield, $d.list_decl_method);
            setLocation($tree, $CLASS);
        }
    ;

class_extension returns[AbstractIdentifier tree]
    : EXTENDS ident {
            $tree = $ident.tree;
            setLocation($tree, $ident.start);
        }
    | /* epsilon */ {
            $tree =  new Identifier(new SymbolTable().create("Object"));
            //setLocation($tree , Location.BUILTIN);
        }
    ;

class_body returns[ ListDeclField declfield, ListDeclMethod list_decl_method]
    @init {
        $list_decl_method = new ListDeclMethod();
        $declfield = new ListDeclField();
    }
    : (m=decl_method {
            $list_decl_method.add($m.tree);
        }
      |d=decl_field_set [ $declfield ]

      )*
    ;

decl_field_set [ListDeclField tree ]
    : v=visibility t=type l=list_decl_field[$tree,$t.tree,$v.visibilite]
      SEMI
    ;

visibility returns [Visibility visibilite]

    : /* epsilon */ {
        $visibilite =Visibility.PUBLIC;
        }
    | PROTECTED {
        $visibilite= Visibility.PROTECTED;
        }
    ;

list_decl_field [ListDeclField tree,AbstractIdentifier typee,Visibility visibilityy]
    : dv1=decl_field[$typee,$visibilityy] {
        $tree.add($dv1.tree);
    }
        (COMMA dv2=decl_field[$typee,$visibilityy] {
            $tree.add($dv2.tree);
        }
      )*
    ;

decl_field [AbstractIdentifier typee,Visibility visibilityy]returns [DeclField tree]
    : i=ident {
        AbstractInitialization init = new NoInitialization();
        }
      (EQUALS e=expr {
            init = new Initialization($e.tree);
            setLocation(init,  $e.start);
        }
      )? {
            $tree= new DeclField($ident.tree,init,$typee,$visibilityy);
            setLocation( $tree, $i.start);
        }
    ;

decl_method returns[AbstractDeclMethod tree]
    : type ident OPARENT params=list_params CPARENT (
        block {
            $tree = new DeclMethod($type.tree,$ident.tree,$params.tree,$block.decls,$block.insts);
            setLocation($tree , $block.start);
        }
      | ASM OPARENT code=multi_line_string CPARENT SEMI {

                StringLiteral  stringLiteral= new StringLiteral($code.text);
                $tree = new AsmMethod($type.tree,$ident.tree,$params.tree,stringLiteral);
                setLocation($tree, $ASM);

        }
      ) {
        }
    ;

list_params returns[ListDeclParam tree]
    @init {
        $tree=new ListDeclParam();
    }
        : (p1=param {
            $tree.add($p1.tree);
        } (COMMA p2=param {
            $tree.add($p2.tree);
        }
      )*)?
    ;

multi_line_string returns[String text, Location location]
    : s=STRING {
            $text = $s.text;
            $location = tokenLocation($s);
        }
    | s=MULTI_LINE_STRING {
            $text = $s.text;
            $location = tokenLocation($s);
        }
    ;

param returns [DeclParam tree]
    : type ident {
        $tree=new DeclParam($type.tree, $ident.tree) ;
        setLocation($tree, $type.start);
        }
    ;

array_element_access returns [AbstractLValue tree]
    : ident LBRACKET index=expr RBRACKET {
        $tree = new ArrayElementAccess($ident.tree, $index.tree);
        //setLocation($tree, $ident.start);
    }
    ;
