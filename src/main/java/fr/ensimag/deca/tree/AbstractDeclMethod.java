package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.codegen.TableMethode;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.SymbolTable.Symbol;

abstract public class AbstractDeclMethod extends Tree {

    public void codeGenMethode(DecacCompiler compiler, TableMethode vTable, String classe, String superr) {

        throw new UnsupportedOperationException("Unimplemented method 'codeGenMethode'");
    }

    public void codeGenMethodeBody(DecacCompiler compiler, AbstractIdentifier identifier) {

        throw new UnsupportedOperationException("Unimplemented method 'codeGenMethodeBody'");
    }

    public void verifyDeclMethodBody(DecacCompiler compiler, Symbol nameofclass) throws ContextualError {

        throw new UnsupportedOperationException("Unimplemented method 'verifyDeclMethodBody'");
    }

    public void verifyDeclMethod(DecacCompiler compiler, Symbol superr, EnvironmentExp environmentExp,
            TableMethode tableMethode) throws ContextualError {

        throw new UnsupportedOperationException("Unimplemented method 'verifyDeclMethod'");
    }

}
