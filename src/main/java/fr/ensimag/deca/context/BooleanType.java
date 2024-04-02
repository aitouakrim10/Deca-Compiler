package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.SymbolTable;


/**
 *
 * @author Ensimag
 * @date 01/01/2024
 */
public class BooleanType extends Type {

    public BooleanType(SymbolTable.Symbol name) {
        super(name);
        //String c =  "hhh";
        //c.
    }

    @Override
    public boolean isBoolean() {
        return true;
    }

    @Override
    public boolean sameType(Type otherType) {
        return otherType.isBoolean();
    }


}
