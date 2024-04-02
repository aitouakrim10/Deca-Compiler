package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.ImmediateFloat;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.DIV;
import fr.ensimag.ima.pseudocode.instructions.ERROR;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.QUO;
import fr.ensimag.ima.pseudocode.instructions.WSTR;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */

public class Divide extends AbstractOpArith {
    public Divide(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorName() {
        return "/";
    }

    private static int i = 0;

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        
        i++;
        Label err = new Label("error_div" + i);
        Label fin = new Label("fin_div" + i);

        if (this.getLeftOperand().getType().isInt() && this.getRightOperand().getType().isInt()) {
            if (reg < compiler.processor.Rmax) {

                compiler.processor.setMaxR(reg + 1);
                this.getLeftOperand().codeGenExp(compiler, reg);
                this.getRightOperand().codeGenExp(compiler, reg + 1);

                if (!compiler.getCompilerOptions().getNoCheck()){
                    compiler.addInstruction(new CMP(0, compiler.processor.getReg(reg + 1)));
                    compiler.addInstruction(new BEQ(err));
                }

                compiler.addInstruction(new QUO(compiler.processor.getReg(reg + 1), compiler.processor.getReg(reg)));
                compiler.addInstruction(new BRA(fin));
                compiler.addLabel(err);
                compiler.addInstruction(new WSTR( "<file:"+getLocation().getFilename()+"><line:"+getLocation().getLine()+"><col:"+getLocation().getPositionInLine() + ">: MATHerror : ZeroDivision /0 "));
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
                compiler.addInstruction(new QUO(compiler.processor.getR0(), compiler.processor.getReg(reg)));
                compiler.addInstruction(new BRA(fin));
                compiler.addLabel(err);
                compiler.addInstruction(new WSTR( "<file:"+getLocation().getFilename()+"><line:"+getLocation().getLine()+"><col:"+getLocation().getPositionInLine() + ">: MATHerror : ZeroDivision /0"));
                compiler.addInstruction(new ERROR());
                compiler.addLabel(fin);
            }

        } else {
            if (reg < compiler.processor.Rmax) {

                compiler.processor.setMaxR(reg + 1);
                this.getLeftOperand().codeGenExp(compiler, reg);
                this.getRightOperand().codeGenExp(compiler, reg + 1);

                if (!compiler.getCompilerOptions().getNoCheck()){
                    if (this.getRightOperand().getType().isInt()) {
                        compiler.addInstruction(new CMP(0, compiler.processor.getReg(reg + 1)));
                    } else {
                        compiler.addInstruction(new CMP(new ImmediateFloat(0), compiler.processor.getReg(reg + 1)));
                    }
                    compiler.addInstruction(new BEQ(err));
                }

                compiler.addInstruction(new DIV(compiler.processor.getReg(reg + 1), compiler.processor.getReg(reg)));
                if((!compiler.getCompilerOptions().getNoCheck()) && (getLeftOperand().getType().isFloat() || getRightOperand().getType().isFloat())){
                    compiler.processor.addBovToArithLabel(compiler);
                }
                compiler.addInstruction(new BRA(fin));
                compiler.addLabel(err);
                compiler.addInstruction(new WSTR( "<file:"+getLocation().getFilename()+"><line:"+getLocation().getLine()+"><col:"+getLocation().getPositionInLine() + ">: MATHerror : ZeroDivision /0"));
                compiler.addInstruction(new ERROR());
                compiler.addLabel(fin);

            } else {
                
                compiler.processor.setMaxR(reg);
                this.getLeftOperand().codeGenExp(compiler, reg);
                compiler.addInstruction(new PUSH(compiler.processor.getReg(reg)));
                compiler.processor.addTsto();//
                compiler.processor.setTstoMax();//
                this.getRightOperand().codeGenExp(compiler, reg);

                if(!compiler.getCompilerOptions().getNoCheck()) {
                    if (this.getRightOperand().getType().isInt()) {
                        compiler.addInstruction(new CMP(0, compiler.processor.getReg(reg)));
                    } else {
                        compiler.addInstruction(new CMP(new ImmediateFloat(0), compiler.processor.getReg(reg)));
                    }
                    compiler.addInstruction(new BEQ(err));
                }

                compiler.addInstruction(new LOAD(compiler.processor.getReg(reg), compiler.processor.getR0()));
                compiler.addInstruction(new POP(compiler.processor.getReg(reg)));
                compiler.processor.minusTsto();///
                compiler.addInstruction(new DIV(compiler.processor.getR0(), compiler.processor.getReg(reg)));
                if((!compiler.getCompilerOptions().getNoCheck()) && (getLeftOperand().getType().isFloat() || getRightOperand().getType().isFloat())){
                    compiler.processor.addBovToArithLabel(compiler);
                }
                compiler.addInstruction(new BRA(fin));
                compiler.addLabel(err);
                compiler.addInstruction(new WSTR( "<file:"+getLocation().getFilename()+"><line:"+getLocation().getLine()+"><col"+getLocation().getPositionInLine() + ">: MATHerror : ZeroDivision /0"));
                compiler.addInstruction(new ERROR());
                compiler.addLabel(fin);
            }
        }
    }

}
