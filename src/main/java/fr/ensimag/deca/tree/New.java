package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.*;

import java.io.PrintStream;

public class New extends AbstractExpr {
    private AbstractIdentifier identifier;

    public New(AbstractIdentifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        Type type1 = identifier.verifyType(compiler);
        if (!type1.isClass()) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
            + "><col:" + getLocation().getPositionInLine() + ">: Invalid new" + identifier.getName().getName() + " is not a class,  ", getLocation());
        }
        setType(type1);
        return type1;
    }

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        if(!identifier.getName().getName().equals("Object")) {
            compiler.processor.setMaxR(reg);
            int nbrChamp = identifier.getClassDefinition().getNumberOfFields();
            compiler.addInstruction(new NEW(new ImmediateInteger(nbrChamp + 1), Register.getR(reg)));
            compiler.processor.addBovPleinLabel(compiler);
            compiler.addInstruction(new LEA(identifier.getClassDefinition().getvTable().getOperand(), Register.R0));
            compiler.addInstruction(new STORE(Register.R0, new RegisterOffset(0, Register.getR(reg))));
            compiler.addInstruction(new PUSH(Register.getR(reg)));
            compiler.processor.addTsto();//
            compiler.processor.setTstoMax();//
            compiler.addInstruction(new BSR(new Label("init." + identifier.getName().getName())));
            compiler.addInstruction(new POP(Register.getR(reg)));
            compiler.processor.minusTsto();
        }
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("new ");
        identifier.decompile(s);
        s.print("()");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        identifier.prettyPrint(s, prefix, false, false);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        identifier.iter(f);
    }

}
