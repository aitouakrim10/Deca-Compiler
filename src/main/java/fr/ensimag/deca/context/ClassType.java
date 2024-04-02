package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.deca.tree.Location;

/**
 * Type defined by a class.
 *
 * @author gl31
 * @date 01/01/2024
 */
public class ClassType extends Type {

    protected ClassDefinition definition;

    public ClassDefinition getDefinition() {
        return this.definition;
    }

    @Override
    public ClassType asClassType(String errorMessage, Location l) {
        return this;
    }

    @Override
    public boolean isClass() {
        return true;
    }

    @Override
    public boolean isClassOrNull() {
        return true;
    }

    /**
     * Standard creation of a type class.
     */
    public ClassType(Symbol className, Location location, ClassDefinition superClass) {
        super(className);
        this.definition = new ClassDefinition(this, location, superClass);
    }

    /**
     * Creates a type representing a class className.
     * (To be used by subclasses only)
     */
    protected ClassType(Symbol className) {
        super(className);
    }


    @Override
    public boolean sameType(Type otherType) {
        if(!otherType.isClass())
        {
            return false;
        }
        ClassType classType = (ClassType) otherType;
        return isSubClassOf(classType);
    }

    /**
     * Return true if potentialSuperClass is a superclass of this class.
     */
    public boolean isSubClassOf(ClassType potentialSuperClass) {
        if(this.getName().equals(potentialSuperClass.getName()))
        {
            return true;
        }
        if(this.definition==null)
        {
            return false;
        }
        if(potentialSuperClass.equals(this))
        {
            return true;
        }
        else
        {
            return this.definition.getSuperClass().getType().isSubClassOf(potentialSuperClass);
        }
 
    }


}
