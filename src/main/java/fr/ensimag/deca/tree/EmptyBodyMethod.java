package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.tools.IndentPrintStream;

public  class EmptyBodyMethod  extends AbstractBodyMethod{

   

    @Override
    public void decompile(IndentPrintStream s) {
        //rien
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
       // rien
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        //rien
    }
    
}
