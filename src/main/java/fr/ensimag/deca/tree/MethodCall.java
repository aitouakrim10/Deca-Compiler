package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;

import java.io.PrintStream;


public class MethodCall extends AbstractExpr {
    private final AbstractExpr expr;
    private final AbstractIdentifier identifier ;
    private  ListExpr listExpr;
    ClassDefinition classDefinition ;

    public MethodCall(AbstractExpr expr, AbstractIdentifier identifier, ListExpr listExpr) {
        this.expr = expr;
        this.identifier = identifier;
        this.listExpr = listExpr;
    }

    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError {
        ClassDefinition classDefinition1;
        if(expr.isImplicit()) {
            if(currentClass==null) {
                throw new ContextualError("methodcall : appel implicite de methode dans le main",getLocation());
            }
            classDefinition1=currentClass;

        } else {
            Type type = expr.verifyExpr(compiler,localEnv,currentClass);
            if(!type.isClass()) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                + getLocation().getPositionInLine() + ">:" + "it is not a class",getLocation());
            }
            SymbolTable.Symbol name = ((ClassType)(type)).getName();
            classDefinition1 = compiler.environmentType.defOfTypeClass(name);
            if(classDefinition1==null) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                + getLocation().getPositionInLine() + ">:" + "the" + name + " class is not declared ",getLocation());
            }
        }
        Type type = classDefinition1.getType();

        if(!type.isClass()) {
            throw  new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
            + getLocation().getPositionInLine() + ">:" + "calling a method on a non class",getLocation());
        }
        EnvironmentExp environmentExp=classDefinition1.getMembers();
        Type type1=identifier.verifyExpr(compiler,environmentExp,classDefinition1);
        if(!identifier.getDefinition().isMethod()) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
            + getLocation().getPositionInLine() + ">:" + identifier.getName().getName() +" is not a method ",getLocation());
        }
        MethodDefinition methodDefinition = identifier.getMethodDefinition();
        Signature signature = methodDefinition.getSignature();
        int i =0;
        if(listExpr.getList().size()!= signature.size()) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
            + getLocation().getPositionInLine() + ">:" + "Incorrect method call signature" ,getLocation());
        }
        ListExpr  list =  new ListExpr();
        for(AbstractExpr expr1 : listExpr.getList()) {
            AbstractExpr exp =  expr1.verifyRValue(compiler,localEnv,currentClass, signature.paramNumber(i));
            list.add(exp);
            i++;
        }
        this.listExpr = list;
        setType(type1);
        return type1;
    }

    @Override
    public void codeGenInst(DecacCompiler compiler){
        int reg = compiler.processor.start;
        int nbrParam = listExpr.size();
        compiler.processor.setMaxR(reg);
        compiler.processor.setMaxnombParam(nbrParam+1);
        if(!expr.isImplicit()) {

            dvale(compiler,reg);
        } else {
            compiler.addInstruction(new ADDSP(new ImmediateInteger(nbrParam+1)));
            compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.getR(reg)));
            compiler.addInstruction(new STORE(Register.getR(reg),new RegisterOffset(0,Register.SP)));
            for(int i =0;i<nbrParam;i++){
                listExpr.getList().get(i).codeGenExp(compiler,reg);
                compiler.addInstruction(new STORE(Register.getR(reg),new RegisterOffset(-1-i,Register.SP)));
            }
            compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.SP),Register.getR(reg)));
            compiler.addInstruction(new CMP(new NullOperand(),Register.getR(reg)));
            compiler.addInstruction(new BEQ(new Label("dereferencement.null")));
            compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.getR(reg)),Register.getR(reg)));
            compiler.addInstruction(new BSR(new RegisterOffset(identifier.getMethodDefinition().getIndex(),Register.getR(reg))));
            compiler.addInstruction(new SUBSP(new ImmediateInteger(nbrParam+1)));
        }
    }


    @Override
    public void codeGenExp(DecacCompiler compiler , int reg){
        int nbrParam = listExpr.size();
        compiler.processor.setMaxR(reg);
        compiler.processor.setMaxnombParam(nbrParam+1);
        if(!expr.isImplicit()){

            GPRegister gpRegister = dvale(compiler,reg);
            compiler.addInstruction(new LOAD(gpRegister,Register.getR(reg)));


        } else {
            compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.getR(reg)));
            compiler.addInstruction(new STORE(Register.getR(reg),new RegisterOffset(0,Register.SP)));
            for(int i =0;i<nbrParam;i++){
                listExpr.getList().get(i).codeGenExp(compiler,reg);
                compiler.addInstruction(new STORE(Register.getR(reg),new RegisterOffset(-1-i,Register.SP)));
            }
            compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.SP),Register.getR(reg)));
            compiler.addInstruction(new CMP(new NullOperand(),Register.getR(reg)));
            compiler.addInstruction(new BEQ(new Label("dereferencement.null")));
            compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.getR(reg)),Register.getR(reg)));
            compiler.addInstruction(new BSR(new RegisterOffset(identifier.getMethodDefinition().getIndex(),Register.getR(reg))));
            compiler.addInstruction(new SUBSP(new ImmediateInteger(nbrParam+1)));
            compiler.addInstruction(new LOAD(Register.getR(0),Register.getR(reg)));
        }

    }
    public GPRegister dvale(DecacCompiler compiler , int reg){
        int nbrParam = listExpr.size();
        expr.codeGenExp(compiler, reg);
        compiler.addInstruction(new ADDSP(new ImmediateInteger(nbrParam+1)));
        compiler.addInstruction(new STORE(Register.getR(reg),new RegisterOffset(0,Register.SP)));
        for(int i =0;i<nbrParam;i++){
            listExpr.getList().get(i).codeGenExp(compiler,reg);
            compiler.addInstruction(new STORE(Register.getR(reg),new RegisterOffset(-1-i,Register.SP)));
        }
        compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.SP),Register.getR(reg)));
        compiler.addInstruction(new CMP(new NullOperand(),Register.getR(reg)));
        compiler.addInstruction(new BEQ(new Label("dereferencement.null")));
        compiler.addInstruction(new LOAD(new RegisterOffset(0,Register.getR(reg)),Register.getR(reg)));
        compiler.addInstruction(new BSR(new RegisterOffset(identifier.getMethodDefinition().getIndex(),Register.getR(reg))));
        compiler.addInstruction(new SUBSP(new ImmediateInteger(nbrParam+1)));
        return Register.R0;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        if(expr!=null) {
            expr.decompile(s);
            s.print(".");
        }
        identifier.decompile(s);
        s.print("(");
        listExpr.decompile(s);
        s.print(")");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        if(expr!=null) {
            expr.prettyPrintChildren(s,prefix);
        }
        identifier.prettyPrint(s,prefix,false,true);
        listExpr.prettyPrint(s,prefix,false,true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        expr.iterChildren(f);
        identifier.iterChildren(f);
        listExpr.iterChildren(f);

    }
}