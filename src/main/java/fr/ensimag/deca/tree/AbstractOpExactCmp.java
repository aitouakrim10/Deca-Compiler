package fr.ensimag.deca.tree;

import fr.ensimag.deca.tools.IndentPrintStream;

/**
 *
 * @author gl31
 * @date 01/01/2024
 */
public abstract class AbstractOpExactCmp extends AbstractOpCmp {

    public AbstractOpExactCmp(AbstractExpr leftOperand, AbstractExpr rightOperand) {
        super(leftOperand, rightOperand);
    }

    public void decompile(IndentPrintStream s) {
        this.getLeftOperand().decompile(s);
        s.print(this.getOperatorName());
        this.getRightOperand().decompile(s);

    }
}
