package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public abstract class AbstractOpBool extends AbstractBinaryExpr {

    public AbstractOpBool(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        try {
            this.getLeftOperand().verifyCondition(compiler, localEnv, currentClass);
            this.getRightOperand().verifyCondition(compiler, localEnv, currentClass);
            setLeftOperand(getLeftOperand());
            setRightOperand(getRightOperand());
            Type type = compiler.environmentType.defOfType(compiler.symbolTable.getSymbol("boolean")).getType();
            setType(type);
            return type;
        } catch (ContextualError e) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">" + ":"
                    + "Invalids operands type for  \"" + getOperatorName()
                    + "\" operation ,both operands should have boolean type", getLocation());

        }
    }

    public void decompile(IndentPrintStream s) {
        this.getLeftOperand().decompile(s);
        s.print(this.getOperatorName());
        this.getRightOperand().decompile(s);
    }

}
