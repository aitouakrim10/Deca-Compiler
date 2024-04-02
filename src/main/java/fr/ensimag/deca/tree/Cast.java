package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;

import java.io.PrintStream;

public class Cast extends AbstractExpr {
    private AbstractIdentifier type;
    private AbstractExpr expr;
    static  int  i =0;

    public Cast(AbstractIdentifier type, AbstractExpr expr) {
        this.type = type;
        this.expr = expr;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        Type dest = type.verifyType(compiler);
        Type exp = expr.verifyExpr(compiler, localEnv, currentClass);
        if (exp.isVoid()) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:"
                    + "Invalid cast to void", getLocation());
        } else if (dest.isInt() && exp.isInt() || dest.isInt() && exp.isFloat()) {
            setType(dest);
            return dest;
        } else if (dest.isFloat() && exp.isFloat() || dest.isFloat() && exp.isInt()) {
            setType(dest);
            return dest;
        } else if (dest.isSubTypeOf(exp) || exp.isSubTypeOf(dest)) {
            setType(dest);
            return dest;
        } else {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:"
                    + "Invalid cast", getLocation());
        }

    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("(");
        this.type.decompile(s);
        s.print(") (");
        expr.decompile(s);
        s.print(")");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        expr.prettyPrint(s, prefix, true);

    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        expr.iter(f);

    }

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        compiler.processor.setMaxR(reg);
        Type t = type.getType();
        if ((t.isInt() && expr.getType().isInt() )||(expr.getType().isFloat() && t.isFloat())) {
            this.expr.codeGenExp(compiler, reg);
        } else if (t.isFloat() && expr.getType().isInt()) {
            this.expr.codeGenExp(compiler, reg);
            compiler.addInstruction(new FLOAT(compiler.processor.getReg(reg), compiler.processor.getReg(reg)));
        } else if (t.isInt() && expr.getType().isFloat()) {
            this.expr.codeGenExp(compiler, reg);
            compiler.addInstruction(new INT(compiler.processor.getReg(reg), compiler.processor.getReg(reg)));
        } else if (t.sameType(expr.getType())){
            i++;
            DAddr adresseATrouver = type.getClassDefinition().getvTable().getOperand();
            expr.codeGenExp(compiler, reg);

            compiler.addInstruction(new CMP(new NullOperand(), Register.getR(reg)));
            compiler.addInstruction(new BEQ(new Label("EndLoop" + i)));

            if (type.getName().getName().equals("Object")) {
                compiler.addInstruction(new BRA(new Label("Yes" + i)));
            }

            compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.getR(reg)), Register.getR(reg)));
            compiler.addInstruction(new LEA(adresseATrouver, Register.R0));

            compiler.addInstruction(new CMP(Register.getR(reg), Register.R0));
            compiler.addInstruction(new BEQ(new Label("Yes" + i)));


            compiler.addLabel(new Label("StartLoop" + i));
            compiler.addInstruction(new CMP(new NullOperand(), Register.getR(reg)));
            compiler.addInstruction(new BNE(new Label("block" + i)));
            compiler.addInstruction(new BRA(new Label("No" + i)));

            compiler.addLabel(new Label("block" + i));
            compiler.addInstruction(new LOAD(new RegisterOffset(0, Register.getR(reg)), Register.getR(reg)));
            compiler.addInstruction(new LEA(adresseATrouver, Register.R0));
            compiler.addInstruction(new CMP(Register.getR(0), Register.getR(reg)));
            compiler.addInstruction(new BEQ(new Label("Yes" + i)));
            compiler.addInstruction(new BRA(new Label("StartLoop" + i)));


            compiler.addLabel(new Label("Yes" + i));
            compiler.addInstruction(new LOAD(new ImmediateInteger(1), Register.getR(reg)));
            compiler.addInstruction(new BRA(new Label("EndLoop" + i)));

            compiler.addLabel(new Label("No" + i));
            compiler.addInstruction(new LOAD(new ImmediateInteger(0), Register.getR(reg)));
            compiler.addLabel(new Label("EndLoop" + i));


            compiler.addInstruction(new CMP(new ImmediateInteger(0), Register.getR(reg)));
            compiler.addInstruction(new BEQ(new Label("erreur.cast")));
            expr.codeGenExp(compiler, reg);


        }
    }
}
