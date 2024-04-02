package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Assignment, i.e. lvalue = expr.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class Assign extends AbstractBinaryExpr {

    @Override
    public AbstractLValue getLeftOperand() {
        // The cast succeeds by construction, as the leftOperand has been set
        // as an AbstractLValue by the constructor.
        return (AbstractLValue) super.getLeftOperand();
    }

    public Assign(AbstractLValue leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {

        AbstractExpr left = this.getLeftOperand();
        Type leftType = left.verifyExpr(compiler, localEnv, currentClass);
        try {
            AbstractExpr right = this.getRightOperand().verifyRValue(compiler, localEnv, currentClass, leftType);
            setRightOperand(right);
            setType(leftType);
            return leftType;
        } catch (ContextualError e) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:"
                    + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:"
                    + "Invalid assign : the two expressions has not the same type",
                    getLocation());

        }

    }

    @Override
    protected String getOperatorName() {
        return "=";
    }

    public void decompile(IndentPrintStream s) {
        this.getLeftOperand().decompile(s);
        s.print(" = ");
        this.getRightOperand().decompile(s);
    }

    @Override
    public void codeGenInst(DecacCompiler compiler) {
        this.getRightOperand().codeGenExp(compiler, compiler.processor.start);
        this.getLeftOperand().codeAssign(compiler, compiler.processor.start);
    }

}
