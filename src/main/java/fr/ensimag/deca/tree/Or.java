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
public class Or extends AbstractOpBool {

    public Or(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "||";
    }

    private static int i = 0; // assures difference between labels names

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        Label fin = new Label("fin_or" + i);
        compiler.processor.setMaxR(reg);
        if (reg < compiler.processor.Rmax) {
            this.getLeftOperand().codeGenExp(compiler, reg);
            compiler.addInstruction(new CMP(new ImmediateInteger(1), compiler.processor.getReg(reg)));
            compiler.addInstruction(new BEQ(fin)); 
            this.getRightOperand().codeGenExp(compiler, reg); 
            compiler.addLabel(fin);
        }
    }

}
