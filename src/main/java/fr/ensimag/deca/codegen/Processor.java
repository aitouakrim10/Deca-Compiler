package fr.ensimag.deca.codegen;

import fr.ensimag.deca.DecacCompiler;
import fr.ensimag.ima.pseudocode.GPRegister;
import fr.ensimag.ima.pseudocode.Label;
import fr.ensimag.ima.pseudocode.Register;
import fr.ensimag.ima.pseudocode.RegisterOffset;
import fr.ensimag.ima.pseudocode.instructions.BOV;
import fr.ensimag.ima.pseudocode.instructions.ERROR;
import fr.ensimag.ima.pseudocode.instructions.POP;
import fr.ensimag.ima.pseudocode.instructions.PUSH;
import fr.ensimag.ima.pseudocode.instructions.WNL;
import fr.ensimag.ima.pseudocode.instructions.WSTR;

public class Processor {

    public int Rmax = 15;
    public final int start = 2;
    private int offset = 0;
    private int offsetLB = 0;
    

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private int nbrVariable = 0;
    private int nbrParam = 0;
    private boolean methode = false;
    private int nbrVarLoc = 0;

    public void addLocVar() {
        this.nbrVarLoc++;
    }

    public int getNbrVarLoc() {
        return this.nbrVarLoc;
    }
    public int getNbrParam() {
        return nbrParam;
    }

    public void setBool() {
        methode = !methode;
    }

    public void setNbrParam(int nbrParam) {
        this.nbrParam = nbrParam;
    }

    public int addNbrParam() {
        nbrParam++;
        return nbrParam;
    }

    public void addNbrVariable() {
        this.nbrVariable++;
    }

    public int getOffset() {
        return offset;
    }

    public void addOffset() {
        this.offset++;
    }

    public void setRmax(int r) {
        this.Rmax = r - 1;
    }

    public int getNbrVariable() {
        return nbrVariable;
    }

    public GPRegister getR0() {
        return Register.R0;
    }

    public GPRegister getR1() {
        return Register.R1;
    }

    public GPRegister getReg(int i) {
        return Register.getR(i);
    }

    public RegisterOffset malloc() {
        if (!methode) {
            offset += 1;
            this.addNbrVariable();
            return new RegisterOffset(offset, Register.GB);
        } else {
            offsetLB += 1;
            this.addLocVar();
            return new RegisterOffset(offsetLB, Register.LB);
        }
    }

    public void initLb() {
        this.offsetLB = 0;
        this.nbrVarLoc = 0;
    }

    private int  maxR = 0;

    public void setMaxR(int r ) {
        if(methode && (r > maxR) ) {
            maxR = r;
        }
    }

    public void pushAll(DecacCompiler compiler) {
        for(int i = maxR ; i >= 2; i--) {
            compiler.addFirst(new PUSH(getReg(i)));
        }
    }

    public void popAll(DecacCompiler compiler) {
        for(int i = maxR ; i >= 2; i--) {
            compiler.addInstruction(new POP(getReg(i)));
        }
    }

    public void initmaxR(){
        this.maxR = 0;
    }  

    public int nbrRegistreSauver(){
        if(maxR == 0){
            return maxR;
        } else {
            return maxR -1;
        }
    }

    private int nombreMaxParMeth = 0;

    public int getNombreMaxParMeth(){
        return this.nombreMaxParMeth;
    }

    public void setNbrParMeth(int i){
        this.nombreMaxParMeth = i;
    }

    public void setMaxnombParam(int i ){
        if(i > this.nombreMaxParMeth){
            this.nombreMaxParMeth = i;
        }
    }

    public int finalNbrPramBSR(){
        if(nombreMaxParMeth == 0){
            return 0;
        }else {
            return nombreMaxParMeth + 2;
        }
    }

    public void initMaxPar(){
        this.nombreMaxParMeth = 0;
    }

    // BOV I/O
    Label errorIoLabel = new Label("error_io");
    public Label getIoLabel(){
        return errorIoLabel;
    }
    public void addBlockIo(DecacCompiler compiler) {
        compiler.addLabel(errorIoLabel);
        compiler.addInstruction(new WSTR("Error: Input/Output error"));
        compiler.addInstruction(new WNL());
        compiler.addInstruction(new ERROR());
    }

    private String nomMethod;

    public String getNomMethod(){
        return nomMethod;
    }

    public void setNomMethod(String str){
        this.nomMethod = str;
    }


    private int tsto = 0;
    private int  tstoMax = 0;

    public int getTsto(){
        return tsto;
    }

    public int getTstoMax(){
        return tstoMax;
    }

    public void setTsto(int i){
        this.tsto = i;
    }

    public void addTsto(){
        this.tsto++;
    }

    public void minusTsto(){
        this.tsto--;
    }   
    
    public void setTstoMax(){
        if(tsto > tstoMax){
            tstoMax = tsto;
        }
    }

    public int finalTsto(){
        if(methode){
            return nbrVarLoc + nbrRegistreSauver() + this.finalNbrPramBSR()+tstoMax;
        } else {
            return nbrVariable+finalNbrPramBSR()+tstoMax;
        }
    }

    public void initall(){
        nbrVarLoc = 0;
        nombreMaxParMeth = 0;
        nbrParam = 0;
        maxR = 1;
        tsto = 0;
        tstoMax = 0;
    }

    // BOV overflow arith operations
    Label errorAriBovLab = new Label("overflow__error");
    public Label getAriBovLab(){
        return errorAriBovLab;
    }

    public void addBlockArithOverFlow(DecacCompiler compiler) {
        compiler.addLabel(errorAriBovLab);
        compiler.addInstruction(new WSTR("Error: Overflow during arithmetic operation"));
        compiler.addInstruction(new WNL());
        compiler.addInstruction(new ERROR());
    }

    public void addBovToArithLabel(DecacCompiler compiler) {
        compiler.addInstruction(new BOV(errorAriBovLab));
    }



    ///     bov  pile pleine
    public Label pilePleine = new Label("stack_overflow_error");
    public void addBovPleinLabel(DecacCompiler compiler){
        compiler.addInstruction(new BOV(pilePleine));
    }

    public void addBlockStackoverFlow(DecacCompiler compiler) {
        compiler.addLabel(pilePleine);
        compiler.addInstruction(new WSTR("Error: Stack Overflow"));
        compiler.addInstruction(new WNL());
        compiler.addInstruction(new ERROR());
    } 
}
