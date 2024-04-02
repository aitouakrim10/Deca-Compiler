package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.*;
import fr.ensimag.deca.context.EnvironmentExp.DoubleDefException;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.DAddr;

import java.io.PrintStream;
import org.apache.commons.lang.Validate;

/**
 * @author gl31
 * @date 01/01/2024
 */
public class DeclVar extends AbstractDeclVar {

    final private AbstractIdentifier type;
    final private AbstractIdentifier varName;
    final private AbstractInitialization initialization;

    public DeclVar(AbstractIdentifier type, AbstractIdentifier varName, AbstractInitialization initialization) {
        Validate.notNull(type);
        Validate.notNull(varName);
        Validate.notNull(initialization);
        this.type = type;
        this.varName = varName;
        this.initialization = initialization;
    }

    @Override
    protected void verifyDeclVar(DecacCompiler compiler,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {

        Type tpe = this.type.verifyType(compiler);
        if (tpe.isVoid() || tpe.isString()) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">" + "Invalid type to declare variable",
                    getLocation());
        }
        VariableDefinition vardef = new VariableDefinition(tpe, this.getLocation());
        this.varName.setDefinition(vardef);
       /*if (compiler.environmentType.defOfType(compiler.symbolTable.getSymbol(varName.getName().getName())) != null) {
            throw new ContextualError(
                    "<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                            + getLocation().getPositionInLine() + ">" + ":" + "variable name can not be type name \""
                            + varName.getName().getName() + "\"",
                    getLocation());
        }*/
        this.initialization.verifyInitialization(compiler, tpe, localEnv, currentClass);
        SymbolTable.Symbol symbol = varName.getName();
        try {
            localEnv.declareVar(symbol, vardef);
        } catch (DoubleDefException e) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">" + ":" + "double definition of variable : "
                    + varName.getName(), this.getLocation());
        }

    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        varName.decompile(s);
        initialization.decompile(s);
        s.print(";");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iter(f);
        varName.iter(f);
        initialization.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        varName.prettyPrint(s, prefix, false);
        initialization.prettyPrint(s, prefix, true);
    }

    @Override
    public void codeGenInst(DecacCompiler compiler) {
        DAddr addr = compiler.processor.malloc();
        varName.getExpDefinition().setOperand(addr);
        initialization.codeGenInst(compiler, type.getType(), addr);
    }

}
