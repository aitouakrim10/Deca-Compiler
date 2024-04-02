package fr.ensimag.deca.syntax;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Syntax error for an expression that should be an lvalue (ie that can be
 * assigned), but is not.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class InvalidIntValue extends DecaRecognitionException {

    private static final long serialVersionUID = 4670163376041273741L;

    public InvalidIntValue(DecaParser recognizer, ParserRuleContext ctx) {
        super(recognizer, ctx);
    }

    @Override
    public String getMessage() {
        return "<" + getLocation().getFilename() +"><line:"+ getLocation().getLine()+"><col:"+getLocation().getPositionInLine() +">:OverFlow : int number is too long" ;
    }
}
