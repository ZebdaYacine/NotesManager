/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentsControllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.Student;
import modules.Teacher;

/**
 *
 * @author zed
 */
public class StudentAgentControllers {

    SqlStatements stm = new SqlStatements();

    public boolean insertUser(Teacher teacher) {
        try {
            stm.insertStudentStatment.setString(1, teacher.getFirstName());
            stm.insertStudentStatment.setString(2, teacher.getLastName());
            stm.insertStudentStatment.setInt(3, teacher.getMatrucula());
            int i = stm.insertStudentStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object desplayAllUser() {
        try {
            ObservableList<Teacher> Teacher = FXCollections.observableArrayList(new Teacher());
            Teacher.remove(0);
            ResultSet rs = stm.desplayStudentStatment.executeQuery();
            while (rs.next()) {
                Teacher.add(new Teacher(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("matrucula")));
            }
            return Teacher;
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object desplayAllStudent() {
        try {
            ObservableList<Student> student = FXCollections.observableArrayList(new Student());
            student.remove(0);
            ResultSet rs = stm.desplayStudentStatment.executeQuery();
            while (rs.next()) {
                student.add(new Student(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("matrucula")));
            }
            return student;
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object fillteUser(Teacher teacher) {
        try {
            ObservableList<Teacher> Teacher = FXCollections.observableArrayList(new Teacher());
            Teacher.remove(0);
            stm.fillterStudentByMatStatment.setInt(1, teacher.getId());
            ResultSet rs = stm.fillterStudentByMatStatment.executeQuery();
            while (rs.next()) {
                Teacher.add(new Teacher(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("matrucula")));
            }
            return Teacher;
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateUser(Teacher teacher) {
        try {
            stm.updateStudentStatment.setString(1, teacher.getFirstName());
            stm.updateStudentStatment.setString(2, teacher.getLastName());
            stm.updateStudentStatment.setInt(3, teacher.getMatrucula());
            stm.updateStudentStatment.setInt(4, teacher.getId());
            int i = stm.updateStudentStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteAgent(Teacher teacher) {
        try {
            stm.deleteStudentStatment.setInt(1, teacher.getId());
            int i = stm.deleteStudentStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getStudentIdByName(String[] student) {
        try {
            stm.selectStudentByNameStatment.setString(1, student[0]);
            stm.selectStudentByNameStatment.setString(2, student[1]);
            ResultSet rs = stm.selectStudentByNameStatment.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
