package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.RTS;
import fr.ensimag.ima.pseudocode.instructions.SEQ;

import org.apache.log4j.Logger;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class ListDeclClass extends TreeList<AbstractDeclClass> {
    private static final Logger LOG = Logger.getLogger(ListDeclClass.class);
    
    @Override
    public void decompile(IndentPrintStream s) {
        for (AbstractDeclClass c : getList()) {
            c.decompile(s);
            s.println();
        }
    }

    /**
     * Pass 1 of [SyntaxeContextuelle]
     */
    void verifyListClass(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify listClass: start");
        for(AbstractDeclClass declClass : getList()) {
            declClass.verifyClass(compiler);
        }
        LOG.debug("verify listClass: end");
    }

    /**
     * Pass 2 of [SyntaxeContextuelle]
     */
    public void verifyListClassMembers(DecacCompiler compiler) throws ContextualError {
        for(AbstractDeclClass declClass : getList()) {
                declClass.verifyClassMembers(compiler);
        }
    }
    
    /**
     * Pass 3 of [SyntaxeContextuelle]
     */
    public void verifyListClassBody(DecacCompiler compiler) throws ContextualError {
        for(AbstractDeclClass declClass : getList()) {
            declClass.verifyClassBody(compiler);
        }
    }

    public void codeGenListClassTabe(DecacCompiler compiler){
        for(AbstractDeclClass declClass : getList()) {
            declClass.codeGenTable(compiler);
        }
    }
    
    public void codeGenInitList(DecacCompiler compiler){
        compiler.startBlock();
        Label ok  = new Label("equals_Object_ok_________");
        
        Label fin =  new Label("equals_end_Object____________");
        compiler.addLabel(new Label("code.Object.equals"));
        compiler.addInstruction(new LOAD(new RegisterOffset(-2,Register.LB), Register.R0));
        compiler.addInstruction(new CMP(new RegisterOffset(-3, Register.LB),Register.R0));
        compiler.addInstruction(new BEQ(ok));
        compiler.addInstruction(new LOAD(new ImmediateInteger(0),Register.R0));
        compiler.addInstruction(new BRA(fin));
        compiler.addLabel(ok);
        compiler.addInstruction(new LOAD(new ImmediateInteger(1), Register.R0));
        compiler.addLabel(fin);
        compiler.addLabel(new Label("fin.Object.equals"));
        compiler.addInstruction(new RTS());
        compiler.finBlock();
        for(AbstractDeclClass declClass : getList()) {
            declClass.codeGenInit(compiler);
        }
    }




}
