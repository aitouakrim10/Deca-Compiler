package fr.ensimag.deca.context;

import fr.ensimag.deca.DecacCompiler;
import java.util.HashMap;
import java.util.Map;
import fr.ensimag.deca.tools.SymbolTable.Symbol;
import fr.ensimag.deca.tree.AbstractIdentifier;
import fr.ensimag.deca.tree.Identifier;
import fr.ensimag.deca.tree.Location;

// A FAIRE: étendre cette classe pour traiter la partie "avec objet" de Déca
/**
 * Environment containing types. Initially contains predefined identifiers, more
 * classes can be added with declareClass().
 *
 * @author gl31
 * @date 01/01/2024
 */
public class EnvironmentType {

    private final Map<Symbol, TypeDefinition> envTypes;
    private  Map<Symbol, ClassDefinition> envClassTypes;
    
    public Map<Symbol, TypeDefinition> getEnv(){
        return envTypes;
    }



    public EnvironmentType(DecacCompiler compiler) {
        envTypes = new HashMap<Symbol, TypeDefinition>();
        envClassTypes = new HashMap<Symbol,ClassDefinition>();

        Symbol intSymb = compiler.createSymbol("int");
        INT = new IntType(intSymb);
        envTypes.put(intSymb, new TypeDefinition(INT, Location.BUILTIN));





        Symbol charSymb = compiler.createSymbol("char");
        CHAR = new CharType(charSymb);
        envTypes.put(charSymb, new TypeDefinition(CHAR, Location.BUILTIN));

        Symbol floatSymb = compiler.createSymbol("float");
        FLOAT = new FloatType(floatSymb);
        envTypes.put(floatSymb, new TypeDefinition(FLOAT, Location.BUILTIN));

        Symbol voidSymb = compiler.createSymbol("void");
        VOID = new VoidType(voidSymb);
        envTypes.put(voidSymb, new TypeDefinition(VOID, Location.BUILTIN));

        Symbol booleanSymb = compiler.createSymbol("boolean");
        BOOLEAN = new BooleanType(booleanSymb);
        envTypes.put(booleanSymb, new TypeDefinition(BOOLEAN, Location.BUILTIN));

        Symbol stringSymb = compiler.createSymbol("string");
        STRING = new StringType(stringSymb);
        envTypes.put(stringSymb, new TypeDefinition(STRING, Location.BUILTIN));

        Symbol nullSymb = compiler.createSymbol("null");
        NULL = new NullType(nullSymb);
        envTypes.put(nullSymb, new TypeDefinition(NULL, Location.BUILTIN));
        
        Symbol object = compiler.createSymbol("Object");
        CLASS0 = new Identifier(object);
        OBJECT = new ClassType(object);
        ClasseDefinition0 = new ClassDefinition(OBJECT,Location.BUILTIN,null);
        Signature signature = new Signature();
        signature.add(OBJECT);
        try {
            ClasseDefinition0.getMembers().declareVar(compiler.createSymbol("equals"),new MethodDefinition(BOOLEAN,Location.BUILTIN,signature,1));
        } catch (EnvironmentExp.DoubleDefException e) {
            throw new RuntimeException(e);
        }
        envClassTypes.put(object,ClasseDefinition0);
        
    }

    public ClassDefinition getClasseDefinition0() {
        return ClasseDefinition0;
    }

    public final AbstractIdentifier getIdentifierClass0() {
        return CLASS0;
    }
    

    public ClassDefinition defOfTypeClass(Symbol s) {
            return envClassTypes.get(s);
    }


    public TypeDefinition defOfType(Symbol s) {
        return envTypes.get(s);
    }

    public void putType(Symbol sym,ClassDefinition def ) {
        this.envClassTypes.put(sym, def);
    }

    public void declare(Symbol name, ClassDefinition classDefinition) throws ContextualError{
        if(envClassTypes.containsKey(name)) {
            throw new ContextualError("la classe " + name.getName() + " est deja definie ",classDefinition.getLocation());
        } else {
            envClassTypes.put(name,classDefinition);
        }
    }

    private final ClassDefinition ClasseDefinition0;
    private final AbstractIdentifier CLASS0 ;
    public final ClassType  OBJECT;

    public final VoidType    VOID;
    public final IntType     INT;
    public final FloatType   FLOAT;
    public final StringType  STRING;
    public final BooleanType BOOLEAN;
    public final NullType NULL;
    public final CharType CHAR;

}