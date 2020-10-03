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
public class User {
    
    private int id;
    private String firstName;
    private String lastName;
    private int matrucula;

    public User(int id, String firstName, String lastName, int matrucula) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.matrucula = matrucula;
    }

    public User() {
    }
    
    

    public User(int id) {
        this.id = id;
    }
    
    

    public User(String firstName, String lastName, int matrucula) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.matrucula = matrucula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMatrucula() {
        return matrucula;
    }

    public void setMatrucula(int matrucula) {
        this.matrucula = matrucula;
    }
   
}
