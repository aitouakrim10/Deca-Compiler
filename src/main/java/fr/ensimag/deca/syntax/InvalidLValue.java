package fr.ensimag.deca.syntax;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Syntax error for an expression that should be an lvalue (ie that can be
 * assigned), but is not.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class InvalidLValue extends DecaRecognitionException {

    private static final long serialVersionUID = 4670163376041273741L;

    public InvalidLValue(DecaParser recognizer, ParserRuleContext ctx) {
        super(recognizer, ctx);
    }

    @Override
    public String getMessage() {
        return "left-hand side of assignment is not an lvalue";
    }
}
