package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.*;

/**
 * Operator "x >= y"
 * 
 * @author gl31
 * @date 01/01/2024
 */
public class GreaterOrEqual extends AbstractOpIneq {

    public GreaterOrEqual(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return ">=";
    }

    private static int i = 0; // variable assures different labels names

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        Label fin = new Label("fin_greater_or_equal" + i);
        Label seq = new Label("seq_greater_or_equal" + i);
        compiler.processor.setMaxR(reg + 1);

        this.getLeftOperand().codeGenExp(compiler, reg);
        this.getRightOperand().codeGenExp(compiler, reg + 1);
        compiler.addInstruction(new CMP(compiler.processor.getReg(reg + 1),compiler.processor.getReg(reg)),"comparaison"); 
        compiler.addInstruction(new BGE(seq),"if true jump seq"); 

        compiler.addInstruction(new LOAD(0,compiler.processor.getReg(reg)),"set 0 in return reg"); // load 0 , reg
        compiler.addInstruction(new BRA(fin),"jump fin");
        compiler.addLabel(seq); // seq
        compiler.addInstruction(new LOAD(1,compiler.processor.getReg(reg)),"set 1 in return reg"); // load 1 , reg
        compiler.addLabel(fin); // fin
      
    }


}
