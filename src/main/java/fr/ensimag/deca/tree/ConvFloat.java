package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Conversion of an int into a float. Used for implicit conversions.
 * 
 * @author gl31
 * @date 01/01/2024
 */
public class ConvFloat extends AbstractUnaryExpr {
    public ConvFloat(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) {
        Type tpe = compiler.environmentType.defOfType(compiler.symbolTable.getSymbol("float")).getType();
        setType(tpe);
        return tpe;
    }

    @Override
    protected String getOperatorName() {
        return "/* conv float */";
    }

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        compiler.processor.setMaxR(reg);
        super.getOperand().codeGenExp(compiler, reg);
        compiler.addInstruction(new FLOAT(compiler.processor.getReg(reg), compiler.processor.getReg(reg)));
    }

}
