package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;

/**
 * Initialization (of variable, field, ...)
 *
 * @author gl31
 * @date 01/01/2024
 */
public abstract class AbstractInitialization extends Tree {

    /**
     * Implements non-terminal "initialization" of [SyntaxeContextuelle] in pass 3
     * 
     * @param compiler     contains "env_types" attribute
     * @param t            corresponds to the "type" attribute
     * @param localEnv     corresponds to the "env_exp" attribute
     * @param currentClass
     *                     corresponds to the "class" attribute (null in the main
     *                     bloc).
     */
    protected abstract void verifyInitialization(DecacCompiler compiler,
            Type t, EnvironmentExp localEnv, ClassDefinition currentClass)
            throws ContextualError;

    /**
     * Implements non-terminal "initialization" of [SyntaxeContextuelle] in pass 3
     * 
     * @param compiler contains "env_types" attribute
     * @param type     corresponds to the "type" of variable
     * @param addr     corresponds to the "memory adress" of variable
     */
    public abstract void codeGenInst(DecacCompiler compiler, Type type, DAddr addr);

    public void codeGenClassInit(DecacCompiler compiler, int indice, Type type) {
        // rien
    }

}
