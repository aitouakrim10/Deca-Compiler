package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.context.Signature;
import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.tools.IndentPrintStream;

public class ListDeclParam extends TreeList<DeclParam>{

    @Override
    public void decompile(IndentPrintStream s) {
        int n = this.getList().size();
        if(n != 0){
            for(int i = 0; i < n - 1; i++) {
                this.getList().get(i).decompile(s);
                s.print(", ");
            }
            this.getList().get(n-1).decompile(s);
        }
    }

    protected Signature verifyListDeclParam(DecacCompiler compiler, EnvironmentExp environmentExp) throws ContextualError {
        Signature signature = new Signature();
        for (DeclParam declParam:getList()) {
            Type type = declParam.verifyDeclParam(compiler);
            signature.add(type);
        }
        return signature;
    }

    public EnvironmentExp verifyListDeclParamBody(DecacCompiler compiler) throws ContextualError {
        EnvironmentExp environmentExp = new EnvironmentExp(null);
        for (DeclParam declParam : getList()) {
            declParam.verifyDeclParamBody(compiler,environmentExp);
        }
        return environmentExp;
    }

    public void initList(DecacCompiler compiler){
        for (DeclParam declParam : getList())
        {
            declParam.init(compiler);
        }
    }


}
