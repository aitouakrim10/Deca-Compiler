package fr.ensimag.deca.tree;

import fr.ensimag.deca.codegen.TableMethode;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.*;

import java.io.PrintStream;

/**
 * Declaration of a class (<code>class name extends superClass {members}<code>).
 * 
 * @author gl31
 * @date 01/01/2024
 */
public class DeclClass extends AbstractDeclClass {
    final private AbstractIdentifier classIdent;
    private AbstractIdentifier extendsIdent;
    final private ListDeclField declField;
    final private ListDeclMethod listDeclMethod;

    public DeclClass(AbstractIdentifier classIdent, AbstractIdentifier extendsIden, ListDeclField declField,
            ListDeclMethod listDeclMethod) {
        this.classIdent = classIdent;
        this.declField = declField;
        this.listDeclMethod = listDeclMethod;
        this.extendsIdent = extendsIden;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("class ");
        classIdent.decompile(s);
        if (!extendsIdent.getName().getName().equals("Object")) {
            s.print(" extends ");
            extendsIdent.decompile(s);
        }
        s.println("{");
        s.indent();
        declField.decompile(s);
        listDeclMethod.decompile(s);
        s.unindent();
        s.println("}");
    }

    @Override
    protected void verifyClass(DecacCompiler compiler) throws ContextualError {
        Symbol name = classIdent.getName();
        if(name.toString().equals("int") || name.toString().equals("float") || name.toString().equals("boolean") || name.toString().equals("void") || name.toString().equals("null")) {
            throw new ContextualError(" la classe ne peut avoir ce nom", getLocation());
        }
        ClassDefinition classDefinitionsuper;
        Symbol su = extendsIdent.getName();
        if (su.equals(compiler.environmentType.OBJECT.getName())) {
            classDefinitionsuper = compiler.environmentType.getClasseDefinition0();
            extendsIdent.setDefinition(classDefinitionsuper);

            TableMethode nono = new TableMethode(null);
            extendsIdent.getClassDefinition().setvTable(nono.createTableObject());

        } else {
            SymbolTable.Symbol superr = extendsIdent.getName();
            classDefinitionsuper = compiler.environmentType.defOfTypeClass(superr);
            extendsIdent.setDefinition(classDefinitionsuper);

        }

        if (classDefinitionsuper == null) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:"
                    + "the class " + extendsIdent.getName() + " is not defined", getLocation());
        }

        ClassType classType = new ClassType(name, getLocation(), classDefinitionsuper);
        ClassDefinition classDefinition = new ClassDefinition(classType, getLocation(), classDefinitionsuper);
        classIdent.setDefinition(classDefinition);
        compiler.environmentType.declare(name, classDefinition);
    }

    @Override
    protected void verifyClassMembers(DecacCompiler compiler)
            throws ContextualError {
        TableMethode tableMethodeSuperClasse = extendsIdent.getClassDefinition().getvTable();
        TableMethode tableMethodeClasse = new TableMethode(tableMethodeSuperClasse);
        classIdent.getClassDefinition().setvTable(tableMethodeClasse);

        tableMethodeClasse.setNbrMethode(tableMethodeSuperClasse.getNbrMethode());

        tableMethodeClasse.setIndiceChamp(tableMethodeSuperClasse.getIndiceChamp());
        listDeclMethod.verifyListDeclMethod(compiler, extendsIdent.getName(), classIdent.getName(), tableMethodeClasse);
        if (declField != null) {
            declField.verifyListDeclField(compiler, extendsIdent.getName(), classIdent.getName(), tableMethodeClasse);
            classIdent.getClassDefinition().setNumberOfFields(tableMethodeClasse.getIndiceChamp());
        }

    }

    @Override
    protected void verifyClassBody(DecacCompiler compiler) throws ContextualError {
        if (!listDeclMethod.isEmpty()) {
            listDeclMethod.verifyListDeclMethodBody(compiler, classIdent.getName());
        }
        if (declField != null) {
            declField.verifyListDeclFieldBody(compiler, classIdent.getName());
        }
    }

    @Override
    public void codeGenTable(DecacCompiler compiler) {
        TableMethode tableMethodeSuperClasse = extendsIdent.getClassDefinition().getvTable();
        TableMethode tableMethodeClasse = classIdent.getClassDefinition().getvTable();
        if (tableMethodeSuperClasse.getName().equals("Object")) {
            compiler.addComment("Construction de la table Object");
            DAddr adresse = compiler.processor.malloc();
            compiler.addInstruction(new LOAD(new NullOperand(), Register.R0));
            compiler.addInstruction(new STORE(Register.R0, adresse));
            tableMethodeSuperClasse.codeGen(compiler);
            tableMethodeSuperClasse.setOperand(adresse);
        }
        tableMethodeClasse.createTable();
        tableMethodeClasse.copySuper(tableMethodeSuperClasse);
        listDeclMethod.codeGenListMethode(compiler, tableMethodeClasse, classIdent.getName().getName(),
                extendsIdent.getName().getName());
        DAddr adresse = compiler.processor.malloc();
        compiler.addComment("Construction de la table " + classIdent.getName().getName());
        compiler.addInstruction(new LEA(tableMethodeSuperClasse.getOperand(), Register.R0));
        compiler.addInstruction(new STORE(Register.R0, adresse));
        tableMethodeClasse.setOperand(adresse);

        tableMethodeClasse.codeGen(compiler);

    }

    @Override
    public void codeGenInit(DecacCompiler compiler) {
        compiler.addLabel(new Label("init." + classIdent.getName().getName()));
        TableMethode tableMethodeSuperClasse = extendsIdent.getClassDefinition().getvTable();
        // TableMethode tableMethode = classIdent.getClassDefinition().getvTable();
        if (!tableMethodeSuperClasse.getName().equals("Object")) {
            compiler.addInstruction(new LOAD(new RegisterOffset(-2, Register.LB), Register.R0));
            compiler.addInstruction(new PUSH(Register.R0));
            compiler.addInstruction(new BSR(new Label("init." + extendsIdent.getName().getName())));
            compiler.addInstruction(new SUBSP(new ImmediateInteger(1)));
            // compiler.addInstruction(new POP(Register.getR(0)));
        }
        compiler.startBlock();
        compiler.processor.initmaxR();
        compiler.processor.initall();
        declField.codeGenInitFieldList(compiler);
        compiler.addInstruction(new RTS());
        compiler.processor.pushAll(compiler);
        compiler.processor.popAll(compiler);
        compiler.addFirst(new BOV(compiler.processor.pilePleine));
        compiler.addFirst(new TSTO(compiler.processor.finalTsto()));
        compiler.processor.initmaxR();
        compiler.finBlock();
        if (!listDeclMethod.isEmpty()) {
            listDeclMethod.codeGenListMethodeBody(compiler, classIdent);
        }
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        classIdent.prettyPrint(s, prefix, false);
        if (extendsIdent != null) {
            extendsIdent.prettyPrint(s, prefix, false);
        }
        if (declField != null) {
            declField.prettyPrintChildren(s, prefix);
        }
        if (listDeclMethod != null) {
            listDeclMethod.prettyPrint(s, prefix, true);
        }
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        classIdent.iter(f);
        declField.iter(f);
        listDeclMethod.iter(f);
    }

}
