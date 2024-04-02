package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.SymbolTable;

/**
 *
 * @author Ensimag
 * @date 01/01/2024
 */
public class NullType extends Type {

    public NullType(SymbolTable.Symbol name) {
        super(name);
    }

    @Override
    public boolean sameType(Type otherType) {
        return otherType.isClass();
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean isClassOrNull() {
        return true;
    }
    @Override
    public boolean isSubTypeOf(Type otherType) {
        return otherType.isClass() || otherType.isNull();
    }
// TODOredefinir isSubClassOf pour NullType car null est une sous classe de toutes les classes


    public boolean isSubClassOf(ClassType potentialSuperClass) {
        return true;
    }
}
