package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.TableMethode;
import fr.ensimag.deca.context.*;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;

import java.io.PrintStream;

public class DeclField extends Tree {

    private final AbstractIdentifier identifier;
    private final AbstractInitialization abstractInitialization;
    private final AbstractIdentifier type;
    private final Visibility visibility;

    public DeclField(AbstractIdentifier identifier, AbstractInitialization abstractInitialization,
            AbstractIdentifier type, Visibility visibility) {
        this.identifier = identifier;
        this.abstractInitialization = abstractInitialization;
        this.type = type;
        this.visibility = visibility;
    }

    @Override
    public void decompile(IndentPrintStream s) {
        if (visibility == Visibility.PROTECTED) {
            s.print(visibility.name().toLowerCase() + " ");
        }
        type.decompile(s);
        s.print(" ");
        identifier.decompile(s);
        abstractInitialization.decompile(s);
    }

    public void verifyDeclField(DecacCompiler compiler, SymbolTable.Symbol superr, EnvironmentExp environmentExp,
            SymbolTable.Symbol namofclass, TableMethode tableMethode) throws ContextualError {
        if (type.verifyType(compiler).isVoid()) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:"
                    + "void can not be a field type", getLocation());
        }
        FieldDefinition fieldDefinition = null;
        identifier.setType(type.getType());
        SymbolTable.Symbol name = identifier.getName();
        if (compiler.environmentType.defOfTypeClass(superr).getMembers().get(name) != null) {
            if (!compiler.environmentType.defOfTypeClass(superr).getMembers().get(name).isField()) {
                throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                        + "><col:" + getLocation().getPositionInLine() + ">:"
                        + "Invalid field ", getLocation());
            }

            fieldDefinition = (FieldDefinition) compiler.environmentType.defOfTypeClass(superr).getMembers().get(name);
        }

        if (fieldDefinition == null) {
            ClassDefinition classDefinition = compiler.environmentType.defOfTypeClass(namofclass);
            fieldDefinition = new FieldDefinition(type.getType(), getLocation(), visibility, classDefinition,
                    tableMethode.addChamp());
        }

        try {
            environmentExp.declareVar(name, fieldDefinition);
            identifier.setDefinition(fieldDefinition);
        } catch (EnvironmentExp.DoubleDefException e) {
            throw new ContextualError("<file:" + getLocation().getFilename() + "><line:" + getLocation().getLine()
                    + "><col:" + getLocation().getPositionInLine() + ">:"
                    + "double definition of field  \"" + identifier.getName().getName() + "\"", getLocation());
        }
    }

    public void verifyDeclFieldBody(DecacCompiler compiler, SymbolTable.Symbol name) throws ContextualError {
        ClassDefinition classDefinition = compiler.environmentType.defOfTypeClass(name);
        abstractInitialization.verifyInitialization(compiler, type.verifyType(compiler), classDefinition.getMembers(),
                classDefinition);
    }

    public void codeGenInitField(DecacCompiler compiler) {
        abstractInitialization.codeGenClassInit(compiler, this.identifier.getFieldDefinition().getIndex(),
                this.identifier.getType());
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        identifier.iter(f);
        abstractInitialization.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        s.println(visibility);
        identifier.prettyPrint(s, prefix, false, true);
        abstractInitialization.prettyPrint(s, prefix, true);
    }
}
