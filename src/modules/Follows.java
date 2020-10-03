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
public class Follows {
    
   private int id;    
   private String student;    
   private String teacher; 
   private String module;
   private float examenNote;
   private float tdNote;
   private float avaregeModule;

    public Follows(int id, String student, String teacher, String module, float examenNote, float tdNote, float avaregeModule) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.module = module;
        this.examenNote = examenNote;
        this.tdNote = tdNote;
        this.avaregeModule = avaregeModule;
    }
    
    

    public Follows(String student, String teacher, String module, float examenNote, float tdNote, float avaregeModule) {
        this.student = student;
        this.teacher = teacher;
        this.module = module;
        this.examenNote = examenNote;
        this.tdNote = tdNote;
        this.avaregeModule = avaregeModule;
    }

    public Follows(int id) {
        this.id = id;
    }

    public Follows(String student, String module) {
        this.student = student;
        this.module = module;
    }
    
    

    public Follows() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public float getExamenNote() {
        return examenNote;
    }

    public void setExamenNote(float examenNote) {
        this.examenNote = examenNote;
    }

    public float getTdNote() {
        return tdNote;
    }

    public void setTdNote(float tdNote) {
        this.tdNote = tdNote;
    }

    public float getAvaregeModule() {
        return avaregeModule;
    }

    public void setAvaregeModule(float avaregeModule) {
        this.avaregeModule = avaregeModule;
    }
   
    

    
}
