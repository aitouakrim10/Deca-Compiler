package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import java.io.PrintStream;

import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import org.apache.commons.lang.Validate;

/**
 * Full if/else if/else statement.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class IfThenElse extends AbstractInst {
    
    private final AbstractExpr condition; 
    private final ListInst thenBranch;
    private ListInst elseBranch;

    public IfThenElse(AbstractExpr condition, ListInst thenBranch, ListInst elseBranch) {
        Validate.notNull(condition);
        Validate.notNull(thenBranch);
        Validate.notNull(elseBranch);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }
    
    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
            this.condition.verifyCondition(compiler, localEnv, currentClass);
            this.thenBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
            this.elseBranch.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    private static int i = 0; // assure diference between labels names
    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        i++;
        Label els = new Label("else" + i);
        Label fin = new Label("fin" + i);

        this.condition.codeGenExp(compiler, compiler.processor.start);; // r0 <--- (0,1)
        compiler.addInstruction(new CMP(0,compiler.processor.getReg(compiler.processor.start))); // si false : jamp else
        compiler.addInstruction(new BEQ(els));

        this.thenBranch.codeGenListInst(compiler); // if body
        compiler.addInstruction(new BRA(fin)); // jamp fin

        compiler.addLabel(els); //else:
        this.elseBranch.codeGenListInst(compiler); // else body

        compiler.addLabel(fin); //fin:
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("if(");
        this.condition.decompile(s);
        s.println("){");
        s.indent();
        this.thenBranch.decompile(s);
        s.unindent();
        s.println("} else {");
        s.indent();
        this.elseBranch.decompile(s);
        s.unindent();
        s.print("}");
    }

    @Override
    protected
    void iterChildren(TreeFunction f) {
        condition.iter(f);
        thenBranch.iter(f);
        elseBranch.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        thenBranch.prettyPrint(s, prefix, false);
        elseBranch.prettyPrint(s, prefix, true);
    }
}
