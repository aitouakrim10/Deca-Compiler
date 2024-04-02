package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

import java.io.PrintStream;

/**
 * Absence of initialization (e.g. "int x;" as opposed to "int x =
 * 42;").
 *
 * @author gl31
 * @date 01/01/2024
 */
public class NoInitialization extends AbstractInitialization {

    @Override
    protected void verifyInitialization(DecacCompiler compiler, Type t,
            EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError {
        //throw new UnsupportedOperationException("not yet implemented");
        // ask teacher
        
    }


    /**
     * Node contains no real information, nothing to check.
     */
    @Override
    protected void checkLocation() {
        // nothing
    }

    @Override
    public void decompile(IndentPrintStream s) {
        // nothing
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }
    
    public void codeGenInst(DecacCompiler compiler, Type type , DAddr addr ) {
        if (type.isInt() || type.isBoolean()) {
            compiler.addInstruction(new LOAD(new ImmediateInteger(0),compiler.processor.getReg(compiler.processor.start)));
            compiler.addInstruction(new STORE(compiler.processor.getReg(compiler.processor.start), addr));
        } else if (type.isFloat()) {
            compiler.addInstruction(new LOAD(new ImmediateFloat(0),compiler.processor.getReg(compiler.processor.start)));
            compiler.addInstruction(new STORE(compiler.processor.getReg(compiler.processor.start), addr)); 
        }
    }

    @Override
    public  void codeGenClassInit(DecacCompiler compiler,int indice,Type type){
        compiler.processor.setMaxR(compiler.processor.start);
       if (type.isInt() || type.isBoolean()) {
            compiler.addInstruction(new LOAD(new ImmediateInteger(0), Register.getR(compiler.processor.start)));

        } else if (type.isFloat()) {
            compiler.addInstruction(new LOAD(new ImmediateFloat(0), Register.getR(compiler.processor.start)));
        } else {

            compiler.addInstruction(new LOAD(new NullOperand(),Register.getR(compiler.processor.start)));
        }
        compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB),Register.R0 ));
        compiler.addInstruction(new STORE(Register.getR(compiler.processor.start),new RegisterOffset(indice,Register.getR(0))));


    }
    }



