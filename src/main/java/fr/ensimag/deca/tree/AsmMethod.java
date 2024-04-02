package fr.ensimag.deca.tree;

import java.io.PrintStream;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.TableMethode;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.MethodDefinition;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.ima.pseudocode.InlinePortion;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.LabelOperand;
import fr.ensimag.ima.pseudocode.instructions.ADDSP;
import fr.ensimag.ima.pseudocode.instructions.RTS;

public class AsmMethod extends AbstractDeclMethod{
    private final AbstractIdentifier type ;
    private final AbstractIdentifier abstractIdentifier1 ;
    private final ListDeclParam listDeclParam;
    private final StringLiteral stringLiteral;

    public AsmMethod(AbstractIdentifier type, AbstractIdentifier abstractIdentifier1, ListDeclParam listDeclParam, StringLiteral stringLiteral) {
        this.type = type;
        this.abstractIdentifier1 = abstractIdentifier1;
        this.listDeclParam = listDeclParam;
        String str =  stringLiteral.getValue().substring(1, stringLiteral.getValue().length()-1);
        str = str.replace("\\\"", "\"");
        this.stringLiteral = new StringLiteral(str);

    }


    @Override
    public void verifyDeclMethodBody(DecacCompiler compiler, SymbolTable.Symbol nameofclass) throws ContextualError {
        stringLiteral.verifyExpr(compiler,null,compiler.environmentType.defOfTypeClass(nameofclass));
    }


    public void verifyDeclMethod(DecacCompiler compiler, SymbolTable.Symbol superr, EnvironmentExp environmentExp, TableMethode tableMethode) throws ContextualError {
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

    @Override
    public void codeGenMethode(DecacCompiler compiler, TableMethode vTable, String classe, String superr) {

        vTable.setMethode(abstractIdentifier1.getMethodDefinition().getIndex() - 1,
        new LabelOperand(new Label("code." + classe + "." + abstractIdentifier1.getName().getName())));
    }


     public void codeGenMethodeBody(DecacCompiler compiler, AbstractIdentifier identifier) {
        listDeclParam.initList(compiler);
        compiler.processor.setBool();
        compiler.processor.initLb();

        // Corps de la méthode

        compiler.addLabel(
                new Label("code." + identifier.getName().getName() + "." + abstractIdentifier1.getName().getName()));
        compiler.startBlock();

        compiler.processor.initmaxR();
        compiler.add(new InlinePortion(stringLiteral.getValue()));
        compiler.addLabel(
                new Label("fin." + identifier.getName().getName() + "." + abstractIdentifier1.getName().getName()));

        // pull en contre ordre
        compiler.processor.pushAll(compiler);
        compiler.processor.popAll(compiler);
        compiler.processor.initmaxR();

        compiler.addInstruction(new RTS());
        compiler.addFirst(new ADDSP(compiler.processor.getNbrVarLoc()));
        compiler.finBlock();
        compiler.processor.setBool();
        // fin de la methode

    }



    @Override
    public void decompile(IndentPrintStream s) {
        type.decompile(s);
        s.print(" ");
        abstractIdentifier1.decompile(s);
        s.println("()");
        s.indent();
        s.print("asm (");
        stringLiteral.decompile(s);
        s.unindent();
        s.println(");");
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        type.prettyPrint(s,prefix,false);
        abstractIdentifier1.prettyPrint(s,prefix,false);
        listDeclParam.prettyPrint(s,prefix,true);
        stringLiteral.prettyPrint(s, prefix,true);
       
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        type.iterChildren(f);
        abstractIdentifier1.iterChildren(f);
        listDeclParam.iterChildren(f);
        stringLiteral.iterChildren(f);
    }
}