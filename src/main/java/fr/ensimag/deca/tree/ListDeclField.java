package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.TableMethode;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.deca.tools.SymbolTable;

public class ListDeclField extends TreeList<DeclField>{

    public ListDeclField() {
    }

    @Override
    public void decompile(IndentPrintStream s) {
        for(DeclField declField : getList()){
            declField.decompile(s);
            s.println(";");
        }
    }

    public EnvironmentExp verifyListDeclField(DecacCompiler compiler, SymbolTable.Symbol superr,  SymbolTable.Symbol namofclass, TableMethode tableMethode) throws ContextualError {
        EnvironmentExp environmentExp = compiler.environmentType.defOfTypeClass(namofclass).getMembers();
        for(DeclField declField : getList()) {
            declField.verifyDeclField(compiler,superr,environmentExp,namofclass,tableMethode);
        }
        return environmentExp;
    }

    public void verifyListDeclFieldBody(DecacCompiler compiler, SymbolTable.Symbol name) throws ContextualError {
        for(DeclField declField : getList()){
            declField.verifyDeclFieldBody(compiler,name);
        }
    }

    public void codeGenInitFieldList(DecacCompiler compiler){
        for(DeclField declField : getList()) {
            declField.codeGenInitField(compiler);
        }
    }

}
