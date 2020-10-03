/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

/**
 *
 * @author zed
 */
public class ResultsTable {

    private String nameModule;
    private int coefficient;
    private Float td;
    private Float examen;
    private Float avrege;
    
    
    public ResultsTable(String nameModule, int coefficient, Float td, Float examen, Float avrege) {
        this.nameModule = nameModule;
        this.coefficient = coefficient;
        this.td = td;
        this.examen = examen;
        this.avrege = avrege;
    }

    public ResultsTable() {
    }

    public String getNameModule() {
        return nameModule;
    }

    public void setNameModule(String nameModule) {
        this.nameModule = nameModule;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public Float getTd() {
        return td;
    }

    public void setTd(Float td) {
        this.td = td;
    }

    public Float getExamen() {
        return examen;
    }

    public void setExamen(Float examen) {
        this.examen = examen;
    }

    public Float getAvrege() {
        return avrege;
    }

    public void setAvrege(Float avrege) {
        this.avrege = avrege;
    }

    

}
