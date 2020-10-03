/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBaseController;

/**
 *
 * @author zed
 */
public class Queries {

    public String insertSeciality = "insert into specialty(id,name) values (?,?)";
    public String selectAllSeciality = "select * from specialty ";
    public String selectSecialityByName = "select * from specialty where name=? ";
    public String selectNameSecialityId = "select name from specialty where id=? ";
    public String selectIdSecialityByName = "select id from specialty where name=? ";
    public String updateSeciality = "update specialty set name=? where id =? ";
    public String deleteSeciality = "delete from specialty where id = ?";

    public String insertMoule = "insert into module(specialtyID,coefficient,semestterNumber,name) values (?,?,?,?)";
    public String showModuleInTableQuery = "select module.id,module.coefficient,module.name,module.semestterNumber,specialty.name  from module ,specialty where specialty.id=module.specialtyID";
    public String searchModuleByName = "select module.id,module.coefficient,module.name,module.semestterNumber,specialty.name  "
            + "from module ,specialty where "
            + "specialty.id=module.specialtyID and module.name=? ";
    public String selectMouleByName = "select * from module where name=? ";
    public String updateMoule = "update module set "
            + "name=? , "
            + "specialtyID=?, "
            + "coefficient=? "
            + ", semestterNumber=?  "
            + " where id =? ";

    public String deleteMoule = "delete from module where id = ?";
    public String insertTeacherQuery = "insert  into teacher(firstname,lastname,matrucula) values(?,?,?)";
    public String desplayAllTeacherQuery = "select * from teacher";
    public String updateTeacherQuery = "update teacher set "
            + " firstname=? , "
            + " lastname=? , "
            + " matrucula=? "
            + " where id =? ";
    public String selectTeacherByNameQuery = "select * from teacher where firstname=? && lastname=?";

    public String deleteTeacherQuery = "delete from student where id = ?";
    public String fillterTeacherByMatQuery = "select * from student where matrucula=?";
    public String insertStudentQuery = "insert  into student(firstname,lastname,matrucula) values(?,?,?)";
    public String desplayAllStudentQuery = "select * from student";
    public String updateStudentQuery = "update student set "
            + " firstname=? , "
            + " lastname=? , "
            + " matrucula=? "
            + " where id =? ";
    public String deleteStudentQuery = "delete from student where id = ?";
    public String fillterStudentByMatQuery = "select * from student where matrucula=?";
    public String selectStudentByNameQuery = "select * from student where firstname=? && lastname=?";

    public String insertFollowQuery = "insert  into follows(studentID,teacherID,moduleID,examenNote,tdNote,avaregeModule) "
            + "values(?,?,?,?,?,?)";
    public String desplayAllFollowQuery = "select follows.id,follows.examenNote,follows.tdNote"
            + ", follows.avaregeModule,teacher.firstname,teacher.lastname " +
        ",student.firstname,student.lastname,module.name" +
        " from follows ,teacher,student,module where" +
        " follows.studentID=student.id " +
        " and follows.teacherID=teacher.id" +
        " and follows.moduleID=module.id;";
    public String searchFollowQuery = "select follows.id,follows.examenNote,follows.tdNote"
            + ", follows.avaregeModule,teacher.firstname,teacher.lastname " +
        ",student.firstname,student.lastname,module.name" +
        " from follows ,teacher,student,module where" +
        " follows.studentID=student.id " +
        " and follows.teacherID=teacher.id" +
        " and follows.moduleID=module.id and module.name=? and student.firstname=? and student.lastname=? ;";
    public String deleteFollowsQuery="delete from follows where id = ?";
    public String updateFollowsQuery = "update follows set "
            + " studentID=? , "
            + " teacherID=? , "
            + " moduleID=?,"
            + " examenNote=?,"
            + " tdNote=?,"
            + " avaregeModule=? "
            + " where id =? ";
    
    public String getStudentTableNotesQuery ="select module.name,module.coefficient,follows.tdNote,"
            + "follows.examenNote,follows.avaregeModule " 
            +" from module,follows where " 
            +" follows.moduleID=module.id"
            +" and  semestterNumber=? and follows.studentID=? and module.specialtyID=?";
    
}
