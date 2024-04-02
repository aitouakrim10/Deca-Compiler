package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.CMP;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class And extends AbstractOpBool {

    public And(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "&&";
    }


    private static int i = 0; // assurer des titres des labels differents

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        Label fin = new Label("fin_and" + i);
        compiler.processor.setMaxR(reg);

        this.getLeftOperand().codeGenExp(compiler, reg);
        compiler.addInstruction(new CMP(new ImmediateInteger(0), compiler.processor.getReg(reg)));
        compiler.addInstruction(new BEQ(fin)); 
        this.getRightOperand().codeGenExp(compiler, reg);
        compiler.addLabel(fin);
    }

} 
