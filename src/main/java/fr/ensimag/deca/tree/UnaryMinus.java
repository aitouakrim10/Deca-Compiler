package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.instructions.OPP;

/**
 * @author gl31
 * @date 01/01/2024
 */
public class UnaryMinus extends AbstractUnaryExpr {

    public UnaryMinus(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        Type type = this.getOperand().verifyExpr(compiler, localEnv, currentClass);
        if(!(type.isFloat() || type.isInt())){
            throw new   ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
            + getLocation().getPositionInLine() + ">:" + " invalide typr for operation " + getOperatorName(), getLocation());
        }
        setType(type);
        return type;
    }

    @Override
    protected String getOperatorName() {
        return "-";
    }

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        this.getOperand().codeGenExp(compiler, reg);
        compiler.addInstruction(new OPP(compiler.processor.getReg(reg), compiler.processor.getReg(reg)));
    }
}
