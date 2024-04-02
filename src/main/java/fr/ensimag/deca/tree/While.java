package fr.ensimag.deca.tree;

import fr.ensimag.deca.context.Type;
import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ClassDefinition;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.context.EnvironmentExp;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.Label;
import java.io.PrintStream;

import fr.ensimag.ima.pseudocode.instructions.BEQ;
import fr.ensimag.ima.pseudocode.instructions.BRA;
import fr.ensimag.ima.pseudocode.instructions.CMP;
import org.apache.commons.lang.Validate;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public class While extends AbstractInst {
    private AbstractExpr condition;
    private ListInst body;

    public AbstractExpr getCondition() {
        return condition;
    }

    public ListInst getBody() {
        return body;
    }

    public While(AbstractExpr condition, ListInst body) {
        Validate.notNull(condition);
        Validate.notNull(body);
        this.condition = condition;
        this.body = body;
    }

    private static int i = 0; // assures difference between labels names
    @Override
    protected void codeGenInst(DecacCompiler compiler) {
        i++;
        Label loop = new Label("start_loop" + i);
        Label fin = new Label("fin_loop" + i);

        compiler.addLabel(loop); //loop :
        this.condition.codeGenExp(compiler, compiler.processor.start);; // evaluation de condition (0,1)-->r2

        compiler.addInstruction(new CMP(0,compiler.processor.getReg(compiler.processor.start))); // si false jump fin
        compiler.addInstruction(new BEQ(fin));
        
        this.body.codeGenListInst(compiler);

        compiler.addInstruction(new BRA(loop)); // jump loop
        compiler.addLabel(fin); // fin:
       
    }

    @Override
    protected void verifyInst(DecacCompiler compiler, EnvironmentExp localEnv,
            ClassDefinition currentClass, Type returnType)
            throws ContextualError {
                this.condition.verifyCondition(compiler, localEnv, currentClass);
                this.body.verifyListInst(compiler, localEnv, currentClass, returnType);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        s.print("while(");
        getCondition().decompile(s);
        s.println("){");
        s.indent();
        getBody().decompile(s);
        s.unindent();
        s.print("}");
    }

    @Override
    protected void iterChildren(TreeFunction f) {
        condition.iter(f);
        body.iter(f);
    }

    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        condition.prettyPrint(s, prefix, false);
        body.prettyPrint(s, prefix, true);
    }

}
