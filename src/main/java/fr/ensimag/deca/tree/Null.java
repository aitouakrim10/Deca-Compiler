package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.NullOperand;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.instructions.LOAD;


import java.io.PrintStream;

public class Null extends AbstractExpr{



    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError {
        Type type = compiler.environmentType.NULL;
        setType(type);
        return type;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("null");
        
    }

    @Override
    public void codeGenExp(DecacCompiler compiler,int reg){
        compiler.addInstruction(new LOAD(new NullOperand(), Register.getR(reg)));
    }

    @Override
    String prettyPrintNode() {
        return "NULL";
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
         // leaf node => nothing to do
    }

    @Override
    protected void iterChildren(TreeFunction f) {
         // leaf node => nothing to do
    }
}
