package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.*;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class Equals extends AbstractOpExactCmp {

    public Equals(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "==";
    }

    private static int i = 0; // assures difference between labels names
    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        Label fin = new Label("fin_equals" + i);
        Label eq = new Label("eq_equals" + i);
        compiler.processor.setMaxR(reg + 1);

        this.getLeftOperand().codeGenExp(compiler, reg);
        this.getRightOperand().codeGenExp(compiler, reg + 1);
        compiler.addInstruction(new CMP(compiler.processor.getReg(reg),compiler.processor.getReg(reg + 1)),"comparaison"); //comparaison 
        compiler.addInstruction(new BEQ(eq),"if equals , jump eq"); // si vrai jamp eq
        compiler.addInstruction(new LOAD(0,compiler.processor.getReg(reg)),"if not ,set 0 and jump "); // load 0 , reg
        compiler.addInstruction(new BRA(fin)); // jump fin
        compiler.addLabel(eq); // eq
        compiler.addInstruction(new LOAD(1,compiler.processor.getReg(reg)),"they are equals"); // load 1 , reg
        compiler.addLabel(fin); // fin
        
    }
}
