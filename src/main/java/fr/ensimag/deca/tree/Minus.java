package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.ImmediateFloat;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.OPP;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.SUB;

/**
 * @author gl31
 * @date 01/01/2024
 */
public class Minus extends AbstractOpArith {
    public Minus(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }


    @Override
    protected String getOperatorName() {
        return "-";
    }

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        
        if(this.getLeftOperand() instanceof FloatLiteral ){
            compiler.processor.setMaxR(reg);
            FloatLiteral fl = (FloatLiteral) this.getLeftOperand();
            this.getRightOperand().codeGenExp(compiler, reg);
            compiler.addInstruction(new SUB(new ImmediateFloat(fl.getValue()),compiler.processor.getReg(reg)));
            if(!compiler.getCompilerOptions().getNoCheck()){
                compiler.processor.addBovToArithLabel(compiler);
            }
            compiler.addInstruction(new OPP(compiler.processor.getReg(reg), compiler.processor.getReg(reg)));
            if(!compiler.getCompilerOptions().getNoCheck()){
                compiler.processor.addBovToArithLabel(compiler);
            }
        }else if (this.getLeftOperand() instanceof IntLiteral) {
            compiler.processor.setMaxR(reg);
            IntLiteral in = (IntLiteral) this.getLeftOperand();
            this.getRightOperand().codeGenExp(compiler, reg);
            compiler.addInstruction(new SUB(new ImmediateInteger(in.getValue()),compiler.processor.getReg(reg)));

            if((!compiler.getCompilerOptions().getNoCheck()) && (getLeftOperand().getType().isFloat() || getRightOperand().getType().isFloat())){
                compiler.processor.addBovToArithLabel(compiler);
            }
            compiler.addInstruction(new OPP(compiler.processor.getReg(reg), compiler.processor.getReg(reg)));
            if((!compiler.getCompilerOptions().getNoCheck()) && (getLeftOperand().getType().isFloat() || getRightOperand().getType().isFloat())){
                compiler.processor.addBovToArithLabel(compiler);
            }

        } else {
            if(reg < compiler.processor.Rmax ) {
                compiler.processor.setMaxR(reg + 1);
                this.getLeftOperand().codeGenExp(compiler, reg);
                this.getRightOperand().codeGenExp(compiler, reg + 1);
                compiler.addInstruction(new SUB(compiler.processor.getReg(reg + 1),compiler.processor.getReg(reg)));
                if((!compiler.getCompilerOptions().getNoCheck()) && (getLeftOperand().getType().isFloat() || getRightOperand().getType().isFloat())){
                    compiler.processor.addBovToArithLabel(compiler);
                } 
            } else {
                compiler.processor.setMaxR(reg + 1);
                this.getLeftOperand().codeGenExp(compiler, reg);
                compiler.addInstruction(new PUSH(compiler.processor.getReg(reg)));
                compiler.processor.addTsto();//
                compiler.processor.setTstoMax();//
                this.getRightOperand().codeGenExp(compiler, reg);
                compiler.addInstruction(new LOAD(compiler.processor.getReg(reg), compiler.processor.getR0()));
                compiler.addInstruction(new POP(compiler.processor.getReg(reg)));
                compiler.processor.minusTsto();//
                compiler.addInstruction(new SUB(compiler.processor.getR0(),compiler.processor.getReg(reg)));
                if((!compiler.getCompilerOptions().getNoCheck()) && (getLeftOperand().getType().isFloat() || getRightOperand().getType().isFloat())){
                    compiler.processor.addBovToArithLabel(compiler);
                }
            }
        }
    }

}
