package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.*;
import fr.ensimag.ima.pseudocode.instructions.LOAD;
import fr.ensimag.ima.pseudocode.instructions.STORE;

import java.util.ArrayList;
import java.util.Arrays;

public class TableMethode {
    TableMethode superClasse;

    public TableMethode getSuperClasse() {
        return superClasse;
    }

    public void setSuperClasse(TableMethode superClasse) {
        this.superClasse = superClasse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DAddr getOperand() {
        return operand;
    }

    public void setOperand(DAddr operand) {
        this.operand = operand;
    }

    int nbrMethode=0;
    int indiceChamp=0;
    String name = "s";
    DAddr operand;
    LabelOperand[] table;

    public int getIndiceChamp() {
        return indiceChamp;
    }

    public void setIndiceChamp(int indiceChamp) {
        this.indiceChamp = indiceChamp;
    }

    public int getNbrMethode() {
        return nbrMethode;
    }

    public LabelOperand[] getTable() {
        return table;
    }

    public void setTable(LabelOperand[] table) {
        this.table = table;
    }

    public void setNbrMethode(int nbrMethode) {
        this.nbrMethode = nbrMethode;
    }

    public TableMethode(TableMethode superClasse) {
        this.superClasse = superClasse;
    }
    public  void createTable(){
        table = new LabelOperand[nbrMethode];
    }
    public void copySuper(TableMethode superClasse){
        ArrayList<LabelOperand> tableau = new ArrayList<>(Arrays.asList(superClasse.table).subList(0, superClasse.nbrMethode));
        for(int i =0;i<superClasse.getNbrMethode();i++){
            table[i]=tableau.get(i);
        }
    }
    public void codeGen(DecacCompiler compiler){
        for(int i =0;i<nbrMethode;i++){
            compiler.addInstruction(new LOAD(table[i], Register.R0));
            compiler.addInstruction(new STORE(Register.R0,compiler.processor.malloc()));
        }
    }
    public TableMethode createTableObject(){
        LabelOperand[] tableau = new LabelOperand[1];
        tableau[0]=new LabelOperand(new Label("code.Object.equals"));
        TableMethode vtable = new TableMethode(null);
        vtable.setTable(tableau);
        vtable.setName("Object");
        vtable.setNbrMethode(1);
        return vtable;
    }
    public int addMethode(){
        nbrMethode++;
        return nbrMethode;
    }
    public int addChamp(){
        indiceChamp++;
        return indiceChamp;
    }
    public void setMethode(int index,LabelOperand label){
        table[index]=label;
    }


}

