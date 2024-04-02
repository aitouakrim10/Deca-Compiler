package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.ImmediateString;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.WSTR;

import java.io.PrintStream;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class BooleanLiteral extends AbstractExpr {

    private boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        Type bool = new BooleanType(compiler.createSymbol("Boolean"));
        setType(bool);
        return bool;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(Boolean.toString(value));
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    String prettyPrintNode() {
        return "BooleanLiteral (" + value + ")";
    }

    // added by abdel
    @Override
    public void codeGenInst(DecacCompiler compiler) {

    }

    @Override
    protected void codeGenPrint(DecacCompiler compiler) {
        if (this.value = true) {
            compiler.addInstruction(new WSTR(new ImmediateString("true")));
        } else {
            compiler.addInstruction(new WSTR(new ImmediateString("false")));
        }
    }

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        compiler.processor.setMaxR(reg);
        if (this.value) {
            compiler.addInstruction(new LOAD(1, compiler.processor.getReg(reg)));
        } else {
            compiler.addInstruction(new LOAD(0, compiler.processor.getReg(reg)));
        }
    }
}
