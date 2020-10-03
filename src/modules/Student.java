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
public class Student extends User {

    public Student(int id, String firstName, String lastName, int matrucula) {
        super(id, firstName, lastName, matrucula);
    }

    public Student() {
    }

    public Student(int id) {
        super(id);
    }

    public Student(String firstName, String lastName, int matrucula) {
        super(firstName, lastName, matrucula);
    }

    
}
