package fr.ensimag.ima.pseudocode.instructions;

import fr.ensimag.ima.pseudocode.ImmediateString;
import fr.ensimag.ima.pseudocode.UnaryInstruction;

/**
 * @author Ensimag
 * @date 01/01/2024
 */
public class WSTR extends UnaryInstruction {
    public WSTR(ImmediateString op) {
        super(op);
    }
    
    public WSTR(String message) {
        super(new ImmediateString(message));
    }
    
}
