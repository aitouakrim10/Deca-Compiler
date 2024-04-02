package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Arithmetic binary operations (+, -, /, ...)
 * 
 * @author gl31
 * @date 01/01/2024
 */
public abstract class AbstractOpArith extends AbstractBinaryExpr {

    public AbstractOpArith(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        Type left = this.getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        Type right = this.getRightOperand().verifyExpr(compiler, localEnv, currentClass);

        if (left.sameType(right) && (left.isInt() || right.isFloat())) {
            setType(right);
            return left;
        } else if (left.isInt() && right.isFloat()) {
            AbstractExpr flt = new ConvFloat(getLeftOperand());
            Type tpe = flt.verifyExpr(compiler, localEnv, currentClass);
            flt.setLocation(getLeftOperand().getLocation());
            setLeftOperand(flt);
            setType(tpe);
            return tpe;
        } else if (right.isInt() && left.isFloat()) {
            AbstractExpr flt = new ConvFloat(getRightOperand());
            Type tp = flt.verifyExpr(compiler, localEnv, currentClass);
            flt.setLocation(getRightOperand().getLocation());
            setRightOperand(flt);
            setType(tp);
            return tp;
        } else {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">" + ":"
                    + "Invalids operands type for arithmetic operation  \"" + getOperatorName()
                    + "\" operation ,both operands should have int or float type", getLocation());

        }
    }

    public void decompile(IndentPrintStream s) {
        this.getLeftOperand().decompile(s);
        s.print(this.getOperatorName());
        this.getRightOperand().decompile(s);
    }
}
