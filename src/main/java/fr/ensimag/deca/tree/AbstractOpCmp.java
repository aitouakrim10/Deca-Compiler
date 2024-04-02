package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public abstract class AbstractOpCmp extends AbstractBinaryExpr {

    public AbstractOpCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        Type left = this.getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type right = this.getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        Type ret = compiler.environmentType.defOfType(compiler.symbolTable.getSymbol("boolean")).getType();
        if ((left.isFloat() && right.isFloat()) || (left.isInt() && right.isInt())) {
            setType(ret);
            return ret;

        } else if (left.isFloat() && right.isInt()) {
            AbstractExpr flt = new ConvFloat(this.getRightOperand());
            flt.verifyExpr(compiler, localEnv, currentClass);
            setRightOperand(flt);
            setType(ret);
            setLocation(getLocation());
            return ret;

        } else if (left.isInt() && right.isFloat()) {
            AbstractExpr flt = new ConvFloat(getLeftOperand());
            flt.verifyExpr(compiler, localEnv, currentClass);
            setLeftOperand(flt);
            setType(ret);
            setLocation(getLocation());
            return ret;

        } else if (left.isBoolean() && right.isBoolean()) {
            if (this.getOperatorName().equals("==") || this.getOperatorName().equals("!=")) {
                setType(ret);
                return ret;
            } else {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                        + "><col:" + getLocation().getPositionInLine() + ">" + ":"
                        + "Invalid comparison operation  \"" + getOperatorName() + "\" for booleans operands",
                        getLocation());
            }
        } else {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">" + ":"
                    + "Invalid type for comparison operation  \"" + getOperatorName() + "\" operation", getLocation());
        }

    }

}
