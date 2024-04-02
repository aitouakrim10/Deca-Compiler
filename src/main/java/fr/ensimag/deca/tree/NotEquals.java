package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class NotEquals extends AbstractOpExactCmp {

    public NotEquals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "!=";
    }

    private static int i = 0; // assures difference between labels names

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        compiler.processor.setMaxR(reg + 1);
        Label fin = new Label("fin_not_equals" + i);
        Label eq = new Label("eq_not_equals" + i);
        this.getLeftOperand().codeGenExp(compiler, reg);
        this.getRightOperand().codeGenExp(compiler, reg + 1);
        compiler.addInstruction(new CMP(compiler.processor.getReg(reg),compiler.processor.getReg(reg + 1)),"comparaison");
        compiler.addInstruction(new BEQ(eq),"if equals , jump eq"); 
        compiler.addInstruction(new LOAD(1,compiler.processor.getReg(reg)),"if not ,set 1 and jump "); 
        compiler.addInstruction(new BRA(fin),"jump fin");
        compiler.addLabel(eq); // eq
        compiler.addInstruction(new LOAD(0,compiler.processor.getReg(reg)),"they are equals");
        compiler.addLabel(fin); // fin
    }
}
