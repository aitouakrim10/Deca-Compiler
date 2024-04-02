package fr.ensimag.deca.tree;


import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.*;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class LowerOrEqual extends AbstractOpIneq {
    public LowerOrEqual(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "<=";
    }
   

    private static int i = 0; // assures difference between labels names
    
    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        Label fin = new Label("fin_lower_or_equal" + i);
        Label inf_eq = new Label("inf_eq_lower_or_equals" + i);
        compiler.processor.setMaxR(reg + 1);

        this.getLeftOperand().codeGenExp(compiler, reg);
        this.getRightOperand().codeGenExp(compiler, reg + 1);
        compiler.addInstruction(new CMP(compiler.processor.getReg(reg + 1),compiler.processor.getReg(reg)),"comparaison"); 
        compiler.addInstruction(new BLE(inf_eq),"if true jump inf"); 

        compiler.addInstruction(new LOAD(0,compiler.processor.getReg(reg)),"set 0 in return reg"); // load 0 , reg
        compiler.addInstruction(new BRA(fin),"jump fin");

        compiler.addLabel(inf_eq); // inf
        compiler.addInstruction(new LOAD(1,compiler.processor.getReg(reg)),"set 1 in return reg"); // load 1 , reg
        compiler.addLabel(fin); // fin
       

    }


}
