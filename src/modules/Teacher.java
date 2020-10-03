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
public class Teacher extends User {

    public Teacher(int id, String firstName, String lastName, int matrucula) {
        super(id, firstName, lastName, matrucula);
    }

    public Teacher() {
    }

    public Teacher(int id) {
        super(id);
    }
    
    public Teacher(String firstName, String lastName, int matrucula) {
        super(firstName, lastName, matrucula);
    }

   
    
}
