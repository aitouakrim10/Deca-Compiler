package fr.ensimag.deca.tree;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.deca.context.ContextualError;
import fr.ensimag.deca.tools.IndentPrintStream;
import fr.ensimag.ima.pseudocode.ImmediateInteger;
import fr.ensimag.ima.pseudocode.ImmediateString;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.instructions.*;
import java.io.PrintStream;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;

/**
 * Deca complete program (class definition plus main block)
 *
 * @author gl31
 * @date 01/01/2024
 */
public class Program extends AbstractProgram {
    private static final Logger LOG = Logger.getLogger(Program.class);
    
    public Program(ListDeclClass classes, AbstractMain main) {
        Validate.notNull(classes);
        Validate.notNull(main);
        this.classes = classes;
        this.main = main;
    }
    public ListDeclClass getClasses() {
        return classes;
    }
    public AbstractMain getMain() {
        return main;
    }
    private ListDeclClass classes;
    private AbstractMain main;

    @Override
    public void verifyProgram(DecacCompiler compiler) throws ContextualError {
        LOG.debug("verify program: start");
        classes.verifyListClass(compiler);
        classes.verifyListClassMembers(compiler);
        classes.verifyListClassBody(compiler);
        main.verifyMain(compiler);
        LOG.debug("verify program: end");
    }

    @Override
    public void codeGenProgram(DecacCompiler compiler) {
        // A FAIRE: compléter ce squelette très rudimentaire de code
        //Label pleine = new Label("pleine");
        compiler.startBlock();
        classes.codeGenListClassTabe(compiler);
        compiler.finBlock();


        compiler.addComment("Main program");
        main.codeGenMain(compiler);
        compiler.addInstruction(new HALT());
        //compiler.addLabel(new Label("tas_plein"));
        //compiler.addInstruction(new WSTR(new ImmediateString("débordement de la pile")));

        compiler.addFirst(new ADDSP(new ImmediateInteger(compiler.processor.getNbrVariable())));
        compiler.addFirst(new BOV(compiler.processor.pilePleine));
        compiler.addFirst(new TSTO(compiler.processor.finalTsto()));
        
        //compiler.addFirst(new BOV(pleine));
        //compiler.addFirst(new TSTO(new ImmediateInteger(compiler.gestionPile.getPileGb())));
        classes.codeGenInitList(compiler);

        compiler.addLabel(new Label("dereferencement.null"));
        compiler.addInstruction(new WSTR(new ImmediateString("null pointeur exeption")));
        compiler.addInstruction(new ERROR());

        compiler.addLabel(new Label("erreur.cast"));
        compiler.addInstruction(new WSTR(new ImmediateString("erreur cast")));
        compiler.addInstruction(new ERROR());


        // IO bov label
        compiler.processor.addBlockIo(compiler);
        // BOV operation arith sur les float
        compiler.processor.addBlockArithOverFlow(compiler);
        // bov stackoverflow
        compiler.processor.addBlockStackoverFlow(compiler);
    }

    @Override
    public void decompile(IndentPrintStream s) {
        getClasses().decompile(s);
        getMain().decompile(s);
    }
    
    @Override
    protected void iterChildren(TreeFunction f) {
        classes.iter(f);
        main.iter(f);
    }
    @Override
    protected void prettyPrintChildren(PrintStream s, String prefix) {
        classes.prettyPrint(s, prefix, false);
        main.prettyPrint(s, prefix, true);
    }
}
