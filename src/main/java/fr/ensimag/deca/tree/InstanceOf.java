package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.NullOperand;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BNE;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LEA;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

import java.io.PrintStream;

public class InstanceOf extends AbstractExpr {
    private AbstractExpr exp;
    private AbstractIdentifier type;

    public InstanceOf(AbstractExpr abstractExpr, AbstractIdentifier type) {
        this.exp = abstractExpr;
        this.type = type;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        Type type1 = exp.verifyExpr(compiler, localEnv, currentClass);
        Type type2 = type.verifyType(compiler);
        if (!(type1.isClass() ||type1.isNull()) || !type2.isClass()) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:" + "Invalid instanceof operation ",
                    getLocation());
        }
        return compiler.environmentType.BOOLEAN;
    }


    private static int  i = 0;
    @Override
    public void codeGenExp(DecacCompiler compiler, int reg){
        i++;
        Label start = new Label("StartLoopInstanceOf_"+i);
        Label end  = new Label("EndLoopInstanceOf_"+i);
        Label yes = new Label("InstanceOfYes_"+i);
        Label block  = new Label("InstanceOfblock_"+i);
        Label no = new Label("InstanceOfNo_"+i);
        DAddr adresseATrouver = type.getClassDefinition().getvTable().getOperand();
        exp.codeGenExp(compiler,reg);

        compiler.addInstruction(new CMP(new NullOperand(), Register.getR(reg)));
        compiler.addInstruction(new BEQ(no));
    
        if(type.getName().getName().equals("Object")){
            compiler.addInstruction(new BRA(yes));
        }
        compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.getR(reg)),Register.getR(reg)));
        compiler.addInstruction(new LEA(adresseATrouver,Register.R0));
    
        compiler.addInstruction(new CMP(Register.getR(reg),Register.R0));
        compiler.addInstruction(new BEQ(yes));
    
        compiler.addLabel(start);
        compiler.addInstruction(new CMP(new NullOperand(),Register.getR(reg)));
        compiler.addInstruction(new BNE(block));
        compiler.addInstruction(new BRA(no));
    
        compiler.addLabel(block);
        compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.getR(reg)),Register.getR(reg)));
        compiler.addInstruction(new LEA(adresseATrouver,Register.R0));
        compiler.addInstruction(new CMP(Register.getR(0),Register.getR(reg)));
        compiler.addInstruction(new BEQ(yes));
        compiler.addInstruction(new BRA(start));
    
        compiler.addLabel(yes);
        compiler.addInstruction(new LOAD(new ImmediateInteger(1),Register.getR(reg)));
        compiler.addInstruction(new BRA(end));
    
        compiler.addLabel(no);
        compiler.addInstruction(new LOAD(new ImmediateInteger(0),Register.getR(reg)));
        compiler.addLabel(end);
    
}





    @Override
    public void decompile(IndentPrintStream s) {

        exp.decompile(s);
        s.print(" instanceof ");
        type.decompile(s);

    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        exp.prettyPrint(s, prefix, false);
        type.prettyPrint(s, prefix, true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        exp.iter(f);
        type.iter(f);
    }
}