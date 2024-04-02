package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.SymbolTable;

public class CharType extends Type{
    public CharType(SymbolTable.Symbol name) {
        super(name);
    }

    @Override
    public boolean sameType(Type otherType) {
        return otherType.isChar();
    }

    @Override
    public boolean isChar() {
        return true;
    }
}
