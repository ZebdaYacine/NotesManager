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
public class Module {
    
    private int id;
    private String name;
    private int semestterNumber;
    private int coefficient;
    private int specialtyID;
    private String specialtyName;

    public Module() {
    }

    public Module(int id) {
        this.id = id;
    }
    
    public Module(String name) {
        this.name = name;
    }
    
    public Module(int id, String name, int semestterNumber, int coefficient, int specialtyID, String specialtyName) {
        this.id = id;
        this.name = name;
        this.semestterNumber = semestterNumber;
        this.coefficient = coefficient;
        this.specialtyID = specialtyID;
        this.specialtyName = specialtyName;
    }
    
    public Module(int id, String name, int semestterNumber, int coefficient, String specialtyName) {
        this.id = id;
        this.name = name;
        this.semestterNumber = semestterNumber;
        this.coefficient = coefficient;
        this.specialtyID = specialtyID;
        this.specialtyName = specialtyName;
    }

    public Module(String name, int semestterNumber, int coefficient, int specialtyID, String specialtyName) {
        this.name = name;
        this.semestterNumber = semestterNumber;
        this.coefficient = coefficient;
        this.specialtyID = specialtyID;
        this.specialtyName = specialtyName;
    }
    
    public Module(String name, int semestterNumber, int coefficient, int specialtyID) {
        this.name = name;
        this.semestterNumber = semestterNumber;
        this.coefficient = coefficient;
        this.specialtyID = specialtyID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemestterNumber() {
        return semestterNumber;
    }

    public void setSemestterNumber(int semestterNumber) {
        this.semestterNumber = semestterNumber;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getSpecialtyID() {
        return specialtyID;
    }

    public void setSpecialtyID(int specialtyID) {
        this.specialtyID = specialtyID;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

   
}
