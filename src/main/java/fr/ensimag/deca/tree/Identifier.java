package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.Definition;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.FieldDefinition;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.ExpDefinition;
import fr.ensimag.deca.context.VariableDefinition;
import fr.ensimag.deca.tools.DecacInternalError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.LOAD;

import java.io.PrintStream;

import fr.ensimag.ima.pseudocode.instructions.STORE;
import org.apache.commons.lang.Validate;

/**
 * Deca Identifier
 *
 * @author gl31
 * @date 01/01/2024
 */
public class Identifier extends AbstractIdentifier {

    private Symbol name;
    private Definition definition;

    @Override
    protected void checkDecoration() {
        if (getDefinition() == null) {
            throw new DecacInternalError("Identifier " + this.getName() + " has no attached Definition");
        }
    }

    @Override
    public Definition getDefinition() {
        return definition;
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * ClassDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *                            if the definition is not a class definition.
     */
    @Override
    public ClassDefinition getClassDefinition() {
        try {
            return (ClassDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a class identifier, you can't call getClassDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * MethodDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *                            if the definition is not a method definition.
     */
    @Override
    public MethodDefinition getMethodDefinition() {
        try {
            return (MethodDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a method identifier, you can't call getMethodDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * FieldDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *                            if the definition is not a field definition.
     */
    @Override
    public FieldDefinition getFieldDefinition() {
        try {
            return (FieldDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a field identifier, you can't call getFieldDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * VariableDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *                            if the definition is not a field definition.
     */
    @Override
    public VariableDefinition getVariableDefinition() {
        try {
            return (VariableDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a variable identifier, you can't call getVariableDefinition on it");
        }
    }

    /**
     * Like {@link #getDefinition()}, but works only if the definition is a
     * ExpDefinition.
     * 
     * This method essentially performs a cast, but throws an explicit exception
     * when the cast fails.
     * 
     * @throws DecacInternalError
     *                            if the definition is not a field definition.
     */
    @Override
    public ExpDefinition getExpDefinition() {
        try {
            return (ExpDefinition) definition;
        } catch (ClassCastException e) {
            throw new DecacInternalError(
                    "Identifier "
                            + getName()
                            + " is not a Exp identifier, you can't call getExpDefinition on it");
        }
    }

    @Override
    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    @Override
    public Symbol getName() {
        return name;
    }

    public Identifier(Symbol name) {
        Validate.notNull(name);
        this.name = name;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass) throws ContextualError {

        ExpDefinition expDefinition = localEnv.get(name);
        if (expDefinition == null) {
            if (currentClass != null) {
                expDefinition = currentClass.getExpDefRec(getName(), compiler);

            }
            if (expDefinition == null) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                        + "><col:" + getLocation().getPositionInLine() + ">" + ":"
                        + " \"" + name + "\" is not declared ", getLocation());
            }
            // throw new ContextualError("variable is not declared ", getLocation());
        }
        setType(expDefinition.getType());
        setDefinition(expDefinition);
        return getType();
    }

    /**
     * Implements non-terminal "type" of [SyntaxeContextuelle] in the 3 passes
     * 
     * @param compiler contains "env_types" attribute
     */
    @Override
    public Type verifyType(DecacCompiler compiler) throws ContextualError {
        if (compiler.environmentType.defOfTypeClass(this.getName()) != null) {
            ClassDefinition classDefinition = compiler.environmentType.defOfTypeClass(this.getName());
            setDefinition(classDefinition);
            setType(classDefinition.getType());
            return getType();
        } else {
            Symbol symbol = compiler.getSymbolTable().getSymbol(this.name.getName().toString());
            if (symbol == null) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                        + "><col:" + getLocation().getPositionInLine() + ">:" + "cannot find symbol \"" + name.getName()
                        + "\" type is inexistant",
                        getLocation());
            }
            Type tpe = compiler.environmentType.defOfType(symbol).getType();
            setType(tpe);
            Definition def = compiler.environmentType
                    .defOfType(compiler.getSymbolTable().getSymbol(this.name.getName()));
            if (def == null) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                        + "><col:" + getLocation().getPositionInLine() + ">:" + "type definition of " + name.getName()
                        + " is inexistant", getLocation());
            }
            setDefinition(def);
            return tpe;
        }
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        // leaf node => nothing to do
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        // leaf node => nothing to do
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print(name.toString());
    }

    @Override
    String prettyPrintNode() {
        return "Identifier (" + getName() + ")";
    }

    @Override
    protected void prettyPrintType(PrintStream s, String prefix) {
        Definition d = getDefinition();
        if (d != null) {
            s.print(prefix);
            s.print("definition: ");
            s.print(d);
            s.println();
        }
    }

    @Override
    public void codeGenExp(DecacCompiler compiler, int reg) {
        compiler.processor.setMaxR(reg);
        if (!this.getDefinition().isField()) {
            DAddr add = this.getExpDefinition().getOperand();
            compiler.addInstruction(new LOAD(add, compiler.processor.getReg(reg)));
        } else {
            compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.getR(reg)));
            compiler.addInstruction(new LOAD(
                    new RegisterOffset(this.getFieldDefinition().getIndex(), Register.getR(reg)), Register.getR(reg)));
        }
    }

    @Override
    public void codeAssign(DecacCompiler compiler, int reg) {
        compiler.processor.setMaxR(reg);
        if (!this.getDefinition().isField()) {
            compiler.addInstruction(new STORE(Register.getR(reg), this.getExpDefinition().getOperand()));
        } else {
            compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.getR(0)));
            compiler.addInstruction(new STORE(Register.getR(reg),
                    new RegisterOffset(this.getFieldDefinition().getIndex(), Register.getR(0))));
        }
    }

    @Override
    public DAddr dval(DecacCompiler compiler, int reg) {
        if (!this.getDefinition().isField()) {
            DAddr add = this.getExpDefinition().getOperand();
            return add;
        } else {
            compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.getR(reg)));
            return new RegisterOffset(this.getFieldDefinition().getIndex(), Register.getR(reg));
        }
    }

}
