package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class Not extends AbstractUnaryExpr {

    public Not(AbstractExpr operand) {
        super(operand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {

        this.getOperand().verifyCondition(compiler, localEnv, currentClass);
        Type type = compiler.environmentType.defOfType(compiler.getSymbolTable().getSymbol("boolean")).getType();
        setType(type);
        return type;
        
    }

    @Override
    protected String getOperatorName() {
        return "!";
    }

    private static int i = 0; // assure difference between labels names
    
    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        Label alter0 = new Label("alter0_not" + i);
        Label fin = new Label("fin_not" + i);
        compiler.processor.setMaxR(reg);
        this.getOperand().codeGenExp(compiler, reg);
        compiler.addInstruction(new CMP(0, compiler.processor.getReg(reg)));
        compiler.addInstruction(new BEQ(alter0)," // si == 0 , jump");
        compiler.addInstruction(new LOAD(0,compiler.processor.getReg(reg)),"si non reg <-- 0");
        compiler.addInstruction(new BRA(fin),"jump fin");
        compiler.addLabel(alter0);
        compiler.addInstruction(new LOAD(1,compiler.processor.getReg(reg))); //reg <-- 1
        compiler.addLabel(fin); // fin :
    }

}
