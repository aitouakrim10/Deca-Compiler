package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.SymbolTable;
import fr.ensimag.deca.tree.Location;

public class ArrayType extends ClassType{
    private final Type type ;

    public ArrayType(SymbolTable.Symbol className, Location location, ClassDefinition superClass,Type type) {
        super(className, location, superClass);
        this.type=type;
    }



    @Override
    public boolean isArray() {
        return true;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean sameType(Type otherType) {
        if(!otherType.isArray())
        {
            return false;
        }
        ArrayType arrayType =(ArrayType) otherType;

        return arrayType.type.sameType(this.type);
    }
}
