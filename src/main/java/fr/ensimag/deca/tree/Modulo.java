package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.ERROR;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.REM;
import fr.ensimag.ima.pseudocode.instructions.WSTR;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class Modulo extends AbstractOpArith {

    public Modulo(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {
        Type right = this.getRightOperand().verifyExpr(compiler, localEnv, currentClass);
        Type left = this.getLeftOperand().verifyExpr(compiler, localEnv, currentClass);
        if (!(left.isInt() && right.isInt())) {
            throw new ContextualError(
                    "<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                            + getLocation().getPositionInLine() + ">:" +
                            "Invalid type for modulo operation  \"" + getOperatorName()
                            + "\" , type of the two operands should be int ",
                    getLocation());
        }
        setType(compiler.environmentType.INT);
        return compiler.environmentType.INT;
    }

    @Override
    protected String getOperatorName() {
        return "%";
    }

    private static int i = 0;
    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        i++;
        Label err = new Label("error_modulo" + i);
        Label fin = new Label("fin_modulo" + i);
        if (reg < compiler.processor.Rmax) {
            compiler.processor.setMaxR(reg + 1);
            this.getLeftOperand().codeGenExp(compiler, reg);
            this.getRightOperand().codeGenExp(compiler, reg + 1);
            if (!compiler.getCompilerOptions().getNoCheck()){
                compiler.addInstruction(new CMP(0, compiler.processor.getReg(reg +1)));
                compiler.addInstruction(new BEQ(err));
            }
            compiler.addInstruction(new REM(compiler.processor.getReg(reg + 1), compiler.processor.getReg(reg)));
            compiler.addInstruction(new BRA(fin));
            compiler.addLabel(err);
            compiler.addInstruction(new WSTR("<file:"+getLocation().getFilename()+"><line:"+getLocation().getLine()+"><col:"+getLocation().getPositionInLine() + ">: MATHerror : ZeroModulo %0"));
            compiler.addInstruction(new ERROR());
            compiler.addLabel(fin);
        } else {
            compiler.processor.setMaxR(reg);
            this.getLeftOperand().codeGenExp(compiler, reg);
            compiler.addInstruction(new PUSH(compiler.processor.getReg(reg)));
            compiler.processor.addTsto();//
            compiler.processor.setTstoMax();//
            this.getRightOperand().codeGenExp(compiler, reg);
            if (!compiler.getCompilerOptions().getNoCheck()){
                compiler.addInstruction(new CMP(0, compiler.processor.getReg(reg)));
                compiler.addInstruction(new BEQ(err));
            }
            compiler.addInstruction(new LOAD(compiler.processor.getReg(reg), compiler.processor.getR0()));
            compiler.addInstruction(new POP(compiler.processor.getReg(reg)));
            compiler.processor.minusTsto();///
            compiler.addInstruction(new REM(compiler.processor.getR0(), compiler.processor.getReg(reg)));
            compiler.addInstruction(new BRA(fin));
            compiler.addLabel(err);
            compiler.addInstruction(new WSTR("<file:"+getLocation().getFilename()+"><line:"+getLocation().getLine()+"><col:"+getLocation().getPositionInLine() + ">: MATHerror : ZeroModulo %0 "));
            compiler.addInstruction(new ERROR());
            compiler.addLabel(fin);
        }
    }
}
    
    

