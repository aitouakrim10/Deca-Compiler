package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.TableMethode;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.LabelOperand;
import fr.ensimag.ima.pseudocode.instructions.ADDSP;
import fr.ensimag.ima.pseudocode.instructions.ERROR;
import fr.ensimag.ima.pseudocode.instructions.RTS;
import fr.ensimag.ima.pseudocode.instructions.TSTO;
import fr.ensimag.ima.pseudocode.instructions.WSTR;

import java.io.PrintStream;

public class DeclMethod extends AbstractDeclMethod {
    // [ IDENTIFIER IDENTIFIER LIST_DECL_PARAM METHOD_BODY]
    private final AbstractIdentifier type;
    private final AbstractIdentifier abstractIdentifier1;
    private ListDeclParam listDeclParam;
    // private final AbstractMethodBody abstractMethodBody;
    private final ListDeclVar listDeclVar;
    private final ListInst listInst;

    public DeclMethod(AbstractIdentifier type, AbstractIdentifier abstractIdentifier1, ListDeclParam listDeclParam,
            ListDeclVar listDeclVar, ListInst listInst) {
        this.type = type;
        this.abstractIdentifier1 = abstractIdentifier1;
        this.listDeclParam = listDeclParam;
        this.listDeclVar = listDeclVar;
        this.listInst = listInst;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        abstractIdentifier1.decompile(s);
        s.print("(");
        listDeclParam.decompile(s);
        s.println("){");
        s.indent();
        this.listDeclVar.decompile(s);
        listInst.decompile(s);
        s.unindent();
        s.println("}");

    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s, prefix, false);
        abstractIdentifier1.prettyPrint(s, prefix, false);
        listDeclParam.prettyPrintChildren(s, prefix);
        listDeclVar.prettyPrintChildren(s, prefix);
        listInst.prettyPrintChildren(s, prefix);

    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iterChildren(f);
        abstractIdentifier1.iterChildren(f);
        listDeclParam.iterChildren(f);
        listDeclVar.iterChildren(f);
        listInst.iterChildren(f);
    }

    public void verifyDeclMethod(DecacCompiler compiler, SymbolTable.Symbol superr, EnvironmentExp environmentExp,
            TableMethode tableMethode) throws ContextualError {
        Type type1 = type.verifyType(compiler);
        SymbolTable.Symbol name = abstractIdentifier1.getName();
        Signature signature = listDeclParam.verifyListDeclParam(compiler, environmentExp);

        if (compiler.environmentType.defOfTypeClass(superr).getMembers().get(name) != null) {
            // i am in declmethod override n'est pas respecte je suis pas une methode "
            if (!compiler.environmentType.defOfTypeClass(superr).getMembers().get(name).isMethod()) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:"
                        + getLocation().getLine() + "><col:" + getLocation().getPositionInLine() + ">"
                        + " \"" + name.getName() + "\" is already defined in superclasses but not as a method",
                        getLocation());

            }
        }
        MethodDefinition methodDefinition = (MethodDefinition) compiler.environmentType.defOfTypeClass(superr)
                .getMembers().get(name);
        if (methodDefinition != null) {
            if (methodDefinition.isMethod()) {
                if (!methodDefinition.getSignature().equals(signature)) {
                    throw new ContextualError("<file:" + getLocation().getFilename() + "><line:"
                            + getLocation().getLine() + "><col:" + getLocation().getPositionInLine() + ">"
                            + " override of method  \"" + name.getName() + "\" is not respected ", getLocation());
                }
                if (!type1.isSubTypeOf(methodDefinition.getType())) {
                    throw new ContextualError("<file:" + getLocation().getFilename() + "><line:"
                            + getLocation().getLine() + "><col:" + getLocation().getPositionInLine() + ">" + ":"
                            + " override of method  \"" + name.getName() + "\" is not respected ", getLocation());
                }
            }
        } else {
            methodDefinition = new MethodDefinition(type1, getLocation(), signature, tableMethode.addMethode());
        }

        try {
            environmentExp.declareVar(name, methodDefinition);
            abstractIdentifier1.setDefinition(methodDefinition);
        } catch (EnvironmentExp.DoubleDefException e) {
            throw new ContextualError(
                    "<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine() + "><col:"
                            + getLocation().getPositionInLine() + ">" + ":" + "double definition of method  \""
                            + abstractIdentifier1.getName().getName() + "()\"",
                    getLocation());
        }
    }

    public void verifyDeclMethodBody(DecacCompiler compiler, SymbolTable.Symbol nameofclass) throws ContextualError {
        Type retour = type.verifyType(compiler);
        EnvironmentExp environmentExp = listDeclParam.verifyListDeclParamBody(compiler);
        ClassDefinition classDefinition = compiler.environmentType.defOfTypeClass(nameofclass);
        listDeclVar.verifyListDeclVariable(compiler, environmentExp, classDefinition);
        listInst.verifyListInst(compiler, environmentExp, classDefinition, retour);
    }

    public void codeGenMethode(DecacCompiler compiler, TableMethode vTable, String classe, String superr) {

        vTable.setMethode(abstractIdentifier1.getMethodDefinition().getIndex() - 1,
                new LabelOperand(new Label("code." + classe + "." + abstractIdentifier1.getName().getName())));
    }

    public void codeGenMethodeBody(DecacCompiler compiler, AbstractIdentifier identifier) {
        listDeclParam.initList(compiler);
        compiler.processor.setBool();
        compiler.processor.initLb();
        compiler.processor.initall();

        // Corps de la méthode

        compiler.addLabel(
                new Label("code." + identifier.getName().getName() + "." + abstractIdentifier1.getName().getName()));
        
        compiler.processor.setNomMethod("fin." + identifier.getName().getName() + "." + abstractIdentifier1.getName().getName());
        compiler.startBlock();

        compiler.processor.initmaxR();
        listDeclVar.codeCenListVar(compiler);

        listInst.codeGenListInst(compiler);
        if(!type.getType().isVoid() && !compiler.getCompilerOptions().getNoCheck()){
            compiler.addInstruction(new WSTR("<file:" + getLocation().getFilename() + "><line:"
            + getLocation().getLine() + "><col:" + getLocation().getPositionInLine() + ">:" +"Erreur , the method "+identifier.getName().getName()+"."+abstractIdentifier1.getName().getName() +" has no return"));
            compiler.addInstruction(new ERROR());
        }
        compiler.addLabel(
                new Label("fin." + identifier.getName().getName() + "." + abstractIdentifier1.getName().getName()));

        // pull en contre ordre
        compiler.processor.pushAll(compiler);
        compiler.processor.popAll(compiler);

        compiler.addInstruction(new RTS());
        compiler.addFirst(new ADDSP(compiler.processor.getNbrVarLoc()));
        compiler.addFirst(new TSTO(compiler.processor.finalTsto()));
        compiler.finBlock();
        compiler.processor.initall();
        compiler.processor.initmaxR();
        compiler.processor.setBool();
        // fin de la methode

    }

}
