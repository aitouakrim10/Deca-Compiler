package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * @author gl31
 * @date 01/01/2024
 */
public class Initialization extends AbstractInitialization {

    public AbstractExpr getExpression() {
        return expression;
    }

    private AbstractExpr expression;

    public void setExpression(AbstractExpr expression) {
        Validate.notNull(expression);
        this.expression = expression;
    }

    public Initialization(AbstractExpr expression) {
        Validate.notNull(expression);
        this.expression = expression;
    }

    @Override
    protected void verifyInitialization(DecacCompiler compiler, Type t,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        try {
            AbstractExpr expr = expression.verifyRValue(compiler, localEnv, currentClass, t);
            setExpression(expr);
        } catch (ContextualError e) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:" + "Invalid initialization for "
                    + t.getName().getName() + " field/varible"
                    , getLocation());
        }

    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(" = ");
        this.expression.decompile(s);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        expression.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        expression.prettyPrint(s, prefix, true);
    }

    @Override
    public void codeGenInst(DecacCompiler compiler, Type type, DAddr addr) {
        this.expression.codeGenExp(compiler, compiler.processor.start);
        compiler.addInstruction(new STORE(compiler.processor.getReg(compiler.processor.start), addr));
    }

    @Override
    public void codeGenClassInit(DecacCompiler compiler, int indice, Type type) {
        compiler.processor.setMaxR(compiler.processor.start);
        expression.codeGenExp(compiler, compiler.processor.start);
        compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.R0));
        compiler.addInstruction(new STORE(Register.getR(compiler.processor.start), new RegisterOffset(indice, Register.getR(0))));
    }

}
