package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;

import java.io.PrintStream;


public class ArrayIdentifier extends  AbstractIdentifier {
    private final SymbolTable.Symbol name;
    private  ArrayDefinition arrayDefinition ;

    public ArrayIdentifier(SymbolTable.Symbol name) {
        this.name = name;
    }


    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError {
       return verifyType(compiler);
    }

    @Override
    public ClassDefinition getClassDefinition() {
        return null;
    }

    @Override
    public Definition getDefinition() {
        return arrayDefinition;
    }

    @Override
    public FieldDefinition getFieldDefinition() {
        return null;
    }

    @Override
    public MethodDefinition getMethodDefinition() {
        return null;
    }

    @Override
    public SymbolTable.Symbol getName() {
        return null;
    }

    @Override
    public ExpDefinition getExpDefinition() {
        return null;
    }

    @Override
    public VariableDefinition getVariableDefinition() {
        return null;
    }

    @Override
    public void setDefinition(Definition definition) {

    }

    @Override
    public Type verifyType(DecacCompiler compiler) throws ContextualError {

        Type type1 = compiler.environmentType.defOfType(name).getType();
        SymbolTable.Symbol symbol = compiler.createSymbol(type1.getName().toString()+"ArrayClass");
        ArrayType arrayType = new ArrayType(symbol,getLocation(),compiler.environmentType.getClasseDefinition0(), type1);
        arrayDefinition = new ArrayDefinition(arrayType,getLocation(),compiler.environmentType.getClasseDefinition0());
        compiler.environmentType.putType(symbol,arrayDefinition);
        setType(arrayType);
        setDefinition(arrayDefinition);
        setType(arrayType);
        return getType();

    }

    @Override
    public void decompile(IndentPrintStream s) {

    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {

    }

    @Override
    protected void iterChildren(TreeFunction f) {

    }
}


