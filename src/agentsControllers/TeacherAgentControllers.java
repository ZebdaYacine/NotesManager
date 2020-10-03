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
import modules.Teacher;

/**
 *
 * @author zed
 */
public class TeacherAgentControllers {

    SqlStatements stm = new SqlStatements();

    public boolean insertTeacher(Teacher teacher) {
        try {
            stm.insertTeacherStatment.setString(1, teacher.getFirstName());
            stm.insertTeacherStatment.setString(2, teacher.getLastName());
            stm.insertTeacherStatment.setInt(3, teacher.getMatrucula());
            int i = stm.insertTeacherStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object desplayAllTeacher() {
        try {
            ObservableList<Teacher> Teacher = FXCollections.observableArrayList(new Teacher());
            Teacher.remove(0);
            ResultSet rs = stm.desplayTeacherStatment.executeQuery();
            while (rs.next()) {
                Teacher.add(new Teacher(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("matrucula")));
            }
            return Teacher;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object fillteUser(Teacher teacher) {
        try {
            ObservableList<Teacher> Teacher = FXCollections.observableArrayList(new Teacher());
            Teacher.remove(0);
            stm.fillterTeacherByMatStatment.setInt(1, teacher.getId());
            ResultSet rs = stm.fillterTeacherByMatStatment.executeQuery();
            while (rs.next()) {
                Teacher.add(new Teacher(rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("matrucula")));
            }
            return Teacher;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateTeacher(Teacher teacher) {
        try {
            stm.updateTeacherStatment.setString(1, teacher.getFirstName());
            stm.updateTeacherStatment.setString(2, teacher.getLastName());
            stm.updateTeacherStatment.setInt(3, teacher.getMatrucula());
            stm.updateTeacherStatment.setInt(4, teacher.getId());
            int i = stm.updateTeacherStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteAgent(Teacher teacher) {
        try {
            stm.deleteTeacherStatment.setInt(1, teacher.getId());
            int i = stm.deleteTeacherStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(TeacherAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getTecherIdByName(String[] teacher) {
        try {
            stm.selectTeacherByNameStatment.setString(1, teacher[0]);
            stm.selectTeacherByNameStatment.setString(2, teacher[1]);
            ResultSet rs = stm.selectTeacherByNameStatment.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
