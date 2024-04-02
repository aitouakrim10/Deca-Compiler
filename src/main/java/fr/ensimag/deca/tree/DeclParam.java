package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;

import java.io.PrintStream;

public class DeclParam extends Tree {
    private final AbstractIdentifier type;
    private final AbstractIdentifier identifier2;

    public DeclParam(AbstractIdentifier identifier, AbstractIdentifier identifier2) {
        this.type = identifier;
        this.identifier2 = identifier2;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        identifier2.decompile(s);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, true);
        identifier2.prettyPrint(s, prefix, true);

    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        identifier2.iter(f);

    }

    public Type verifyDeclParam(DecacCompiler compiler) throws ContextualError {
        Type type1 = type.verifyType(compiler);
        if (type1.isVoid()) {
            throw new ContextualError(
                    "<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                            + getLocation().getPositionInLine() + ">:" + "parametre type can not be void ",
                    getLocation());
        }
        return type1;
    }

    public void verifyDeclParamBody(DecacCompiler compiler, EnvironmentExp environmentExp) throws ContextualError {
        Type type1 = type.verifyType(compiler);
        SymbolTable.Symbol name = identifier2.getName();
        ParamDefinition paramDefinition = new ParamDefinition(type1, getLocation());
        try {
            environmentExp.declareVar(name, paramDefinition);
            identifier2.setDefinition(paramDefinition);
        } catch (EnvironmentExp.DoubleDefException e) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:"
                    + "error : double definition of parametre \"" + identifier2.getName().getName() + "\"",
                    getLocation());
        }
    }

    public void init(DecacCompiler compiler) {
        identifier2.getExpDefinition()
                .setOperand(new RegisterOffset(-compiler.processor.addNbrParam() - 2, Register.LB));
    }

}
