package fr.ensimag.deca.tree;


import fr.ensimag.deca.tools.IndentPrintStream;

/**
 * List of expressions (eg list of parameters).
 *
 * @author gl31
 * @date 01/01/2024
 */
public class ListExpr extends TreeList<AbstractExpr> {


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
}
