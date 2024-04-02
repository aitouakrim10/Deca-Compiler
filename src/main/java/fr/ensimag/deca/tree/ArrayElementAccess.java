package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;

import java.io.PrintStream;

public class ArrayElementAccess extends AbstractLValue{
    private  AbstractIdentifier identifier ;
    private  AbstractExpr index ;

    public ArrayElementAccess(AbstractIdentifier identifier, AbstractExpr index) {
        this.identifier = identifier;
        this.index = index;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError {
        Type type = identifier.verifyExpr(compiler,localEnv,currentClass);
        if(!type.isArray())
        {
            throw new ContextualError("arrayaccess sur qlq chose qui n'est pas de type array ArrayAccess",getLocation());
        }
        Type type1 = index.verifyExpr(compiler,localEnv,currentClass);
        if(!type1.isInt())
        {
            throw new ContextualError("je suis en aarrayacces ttype entre crochet n'est pas entier ",getLocation());
        }
        return ((ArrayType)(type)).getType();
    }

    @Override
    public void decompile(IndentPrintStream s) {

    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {

    }

    @Override
    protected void iterChildren(TreeFunction f) {

    }
}
