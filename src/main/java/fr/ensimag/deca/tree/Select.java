package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.DAddr;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.NullOperand;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

import java.io.PrintStream;

public class Select extends AbstractLValue{
    private final AbstractExpr abstractExpr ;
    private final AbstractIdentifier abstractIdentifier ;

    public Select(AbstractExpr abstractExpr, AbstractIdentifier abstractIdentifier) {
        this.abstractExpr = abstractExpr;
        this.abstractIdentifier = abstractIdentifier;
    }


    @Override
    public Type verifyExpr(DecacCompiler compiler, EnvironmentExp localEnv, ClassDefinition currentClass) throws ContextualError {
        Type type = abstractExpr.verifyExpr(compiler,localEnv,currentClass);
        if(!type.isClass())
        {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
            + getLocation().getPositionInLine() + ">:" + "Selection on something that is not a class ",getLocation());
        }

        ClassType classType = (ClassType) type;
        SymbolTable.Symbol symbol = classType.getName();
        EnvironmentExp environmentExp = compiler.environmentType.defOfTypeClass(symbol).getMembers();
        Type type1=abstractIdentifier.verifyExpr(compiler,environmentExp,currentClass);
        ExpDefinition expDefinition= abstractIdentifier.getExpDefinition();
        if(!expDefinition.isField())
        {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
            + getLocation().getPositionInLine() + ">:" + 
            "Selection on something that is not a field ",getLocation());

        }
        FieldDefinition fieldDefinition = (FieldDefinition) expDefinition;
        Visibility visibility= fieldDefinition.getVisibility();
        if(visibility.equals(Visibility.PROTECTED))
        {       if(currentClass==null)
            {//on est obligatoirement dans la classe main
                    throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                    + getLocation().getPositionInLine() + ">:" + "the field is protected  ",getLocation());

            }
                if(!currentClass.getType().isSubClassOf(classType))
                {
                    throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                    + getLocation().getPositionInLine() + ">:" + "Unable to inherite, the field is protected ",getLocation());
                }
                // TODOverifier que le champ est un sous type de la classe courante
               if(!type.isSubTypeOf(currentClass.getType())) {
                    throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                    + getLocation().getPositionInLine() + ">:" + "Unable to inherite, the field is protected ",getLocation());
               }
        }
        setType(type1);
        return  type1;
   }

    @Override
    public void decompile(IndentPrintStream s) {
        //s.print("(");
        abstractExpr.decompile(s);
        s.print(".");
        abstractIdentifier.decompile(s);
        //s.print(")");
    }
    
    @Override
    public void codeGenExp(DecacCompiler compiler , int reg){
        compiler.processor.setMaxR(reg);
        if(!abstractExpr.isImplicit()){
            //AbstractIdentifier classe =  (AbstractIdentifier)abstractExpr;
           /*  if(!classe.getDefinition().isField()) {
                compiler.addInstruction(new LOAD(classe.getExpDefinition().getOperand(), Register.getR(reg)));
            } else {
                compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB),Register.getR(reg)));
                compiler.addInstruction(new LOAD(new RegisterOffset(classe.getFieldDefinition().getIndex(),Register.getR(reg)),Register.getR(reg)));
                
            }*/
            DAddr adresse = this.dval(compiler, reg);
            compiler.addInstruction(new LOAD(adresse,Register.getR(reg)));
        } else {
            abstractIdentifier.codeGenExp(compiler, reg);
        }
    }

    @Override
    public  void codeAssign(DecacCompiler compiler , int reg){
        compiler.processor.setMaxR(reg);
        if (!abstractExpr.isImplicit()) {
           // AbstractIdentifier classe =  (AbstractIdentifier)abstractExpr;
           /*  if(!classe.getDefinition().isField()) {
                compiler.addInstruction(new LOAD(classe.getExpDefinition().getOperand(), Register.getR(0)));
            } else {
                compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB),Register.getR(0)));
                compiler.addInstruction(new LOAD(new RegisterOffset(classe.getFieldDefinition().getIndex(),Register.getR(0)),Register.getR(0)));
            }*/
            
            DAddr adresse = this.dval(compiler,0);
            compiler.addInstruction(new STORE(Register.getR(reg),adresse));
        } else {
            abstractIdentifier.codeAssign(compiler, reg);
        }
    }
    @Override
    public DAddr dval(DecacCompiler compiler , int reg){
        abstractExpr.codeGenExp(compiler, reg);
        compiler.addInstruction(new CMP(new NullOperand(),Register.getR(reg)));
        compiler.addInstruction(new BEQ(new Label("dereferencement.null")));
        return new RegisterOffset(abstractIdentifier.getFieldDefinition().getIndex(), Register.getR(reg));

    }


    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        abstractExpr.prettyPrint(s,prefix,true);
        abstractIdentifier.prettyPrint(s,prefix,true);
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        abstractExpr.iterChildren(f);
        abstractIdentifier.iter(f);
    }

}
