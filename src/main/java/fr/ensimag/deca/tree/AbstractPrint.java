package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.instructions.FLOAT;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.WFLOAT;
import fr.ensimag.ima.pseudocode.instructions.WFLOATX;
import fr.ensimag.ima.pseudocode.instructions.WINT;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * Print statement (print, println, ...).
 *
 * @author gl31
 * @date 01/01/2024
 */
public abstract class AbstractPrint extends AbstractInst {

    private boolean printHex;
    private ListExpr arguments = new ListExpr();

    abstract String getSuffix();

    public AbstractPrint(boolean printHex, ListExpr arguments) {
        Validate.notNull(arguments);
        this.arguments = arguments;
        this.printHex = printHex;
    }

    public ListExpr getArguments() {
        return arguments;
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
        for (AbstractExpr exp : this.arguments.getList()) {
            Type type = exp.verifyExpr(compiler, localEnv, currentClass);
            if (!(type.isString() || type.isInt() || type.isFloat())) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                        + "><col:" + getLocation().getPositionInLine() + ">" + ": argument with "
                        + exp.getType().getName().getName()
                        + " type (or type of class) is not supported by prints fonctions ",
                        getLocation());
            }
        }
    }

    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        for (AbstractExpr a : getArguments().getList()) {
            if (a.getType().isString()) {
                a.codeGenPrint(compiler);
            } else {
                a.codeGenExp(compiler, compiler.processor.start);
                compiler.addInstruction(new LOAD(compiler.processor.getReg(2), compiler.processor.getR1()));
                if (a.getType().isInt()) {
                    if (this.printHex == true) {
                        compiler.addInstruction(new FLOAT(compiler.processor.getR1(), compiler.processor.getR1()));
                        compiler.addInstruction(new WFLOATX());
                    } else {
                        compiler.addInstruction(new WINT());
                    }
                } else if (a.getType().isFloat()) {
                    if (this.printHex == true) {
                        compiler.addInstruction(new WFLOATX());
                    } else {
                        compiler.addInstruction(new WFLOAT());
                    }
                }
            }
        }
    }

    private boolean getPrintHex() {
        return printHex;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        if (this.getPrintHex()) {
            s.print("print" + getSuffix() + "x");
        } else {
            s.print("print" + getSuffix());
        }
        s.print("(");
        arguments.decompile(s);
        s.print(");");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        arguments.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        arguments.prettyPrint(s, prefix, true);
    }

}
