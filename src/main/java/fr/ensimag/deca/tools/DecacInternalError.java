package fr.ensimag.deca.tools;

/**
 * Internal error of the compiler. Should never happen.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class DecacInternalError extends RuntimeException {
    private static final long serialVersionUID = -7489681854632778463L;

    public DecacInternalError(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DecacInternalError(String message) {
        super(message);
    }

}
