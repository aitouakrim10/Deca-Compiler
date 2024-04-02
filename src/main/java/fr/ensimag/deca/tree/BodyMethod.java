package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.tools.IndentPrintStream;
import org.apache.commons.lang.Validate;

public class BodyMethod  extends AbstractBodyMethod {

    
    private ListDeclVar  vars;
    private ListInst insts;

    public BodyMethod (ListDeclVar vars, ListInst insts) {
        Validate.notNull(vars);
        Validate.notNull(insts);
        this.vars = vars;
        this.insts = insts;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.println("{");
        s.indent();
        vars.decompile(s);
        insts.decompile(s);
        s.unindent();
        s.println("}");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        vars.prettyPrint(s, prefix, false);  
        insts.prettyPrint( s, prefix, true); 
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        vars.iterChildren(f);
        insts.iter(f);
    }
  
}
