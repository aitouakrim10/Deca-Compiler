package fr.ensimag.deca.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * Manage unique symbols.
 * 
 * A Symbol contains the same information as a String, but the SymbolTable
 * ensures the uniqueness of a Symbol for a given String value. Therefore,
 * Symbol comparison can be done by comparing references, and the hashCode()
 * method of Symbols can be used to define efficient HashMap (no string
 * comparison or hashing required).
 * 
 * @author gl31
 * @date 01/01/2024
 */
public class SymbolTable {
    private Map<String, Symbol> map = new HashMap<String, Symbol>();
    
    /**
     * Create or reuse a symbol.
     * 
     * If a symbol already exists with the same name in this table, then return
     * this Symbol. Otherwise, create a new Symbol and add it to the table.
     */
    
     public Symbol getSymbol(String str) {
        return map.get(str);
     }

    public Symbol create(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        } else {
            Symbol symbole = new Symbol(name);
            map.put(name,symbole);
            return symbole;
        }
    }

    public static class Symbol implements Comparable<Symbol>{
        // Constructor is private, so that Symbol instances can only be created
        // through SymbolTable.create factory (which thus ensures uniqueness
        // of symbols).
        private Symbol(String name) {
            super();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof Symbol) {
                Symbol sym = (Symbol) o ;
                return this.name.equals(sym.name);
            }
            return false;
        }
        @Override
        public int compareTo(Symbol o) {
            if(o.toString().equals(this.toString())) {
                return 0;
            }
            return -1;
        }

   
        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }

        private String name;
    }
}
