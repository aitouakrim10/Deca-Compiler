package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.TableMethode;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;


public class ListDeclMethod extends TreeList<AbstractDeclMethod>{
    public ListDeclMethod() {
    }

    @Override
    public void decompile(IndentPrintStream s) {
        for(AbstractDeclMethod declMethod : getList()) {
            declMethod.decompile(s);
        }
    }

    protected EnvironmentExp verifyListDeclMethod(DecacCompiler compiler, SymbolTable.Symbol superr, SymbolTable.Symbol name,TableMethode tableMethode) throws ContextualError {
        EnvironmentExp environmentExp = compiler.environmentType.defOfTypeClass(name).getMembers();
        for(AbstractDeclMethod declMethod : getList())
        {
            declMethod.verifyDeclMethod(compiler,superr,environmentExp,tableMethode);
        }
        return environmentExp;

    }

    public void verifyListDeclMethodBody(DecacCompiler compiler, SymbolTable.Symbol nameofclass) throws ContextualError {
        for(AbstractDeclMethod declMethod : getList())
        {
            declMethod.verifyDeclMethodBody(compiler,nameofclass);
        }
    }

    public void codeGenListMethode(DecacCompiler compiler, TableMethode vTable, String classe, String superr) {
        for (AbstractDeclMethod declMethod : getList()) {
            declMethod.codeGenMethode(compiler, vTable, classe, superr);
        }
    }

    public void codeGenListMethodeBody(DecacCompiler compiler,AbstractIdentifier identifier) {

        for (AbstractDeclMethod declMethod : getList()) {
            declMethod.codeGenMethodeBody(compiler, identifier);
        }

    }

}