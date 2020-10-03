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
import modules.Follows;

/**
 *
 * @author zed
 */
public class FlowAgentControllers {

    SqlStatements stm = new SqlStatements();
    StudentAgentControllers stdAgent = new StudentAgentControllers();
    TeacherAgentControllers tchAgent = new TeacherAgentControllers();
    ModuleAgentControllers mdlAgent = new ModuleAgentControllers();

    public boolean insertFollow(Follows follow) {
        try {
            String[] student = follow.getStudent().split(" ", 2);
            String[] teacher = follow.getTeacher().split(" ", 2);
            stm.insertFollowStatment.setInt(1, stdAgent.getStudentIdByName(student));
            stm.insertFollowStatment.setInt(2, tchAgent.getTecherIdByName(teacher));
            stm.insertFollowStatment.setInt(3, mdlAgent.getModuleIdByName(follow.getModule()));
            stm.insertFollowStatment.setFloat(4, follow.getExamenNote());
            stm.insertFollowStatment.setFloat(5, follow.getTdNote());
            stm.insertFollowStatment.setFloat(6, follow.getAvaregeModule());
            int i = stm.insertFollowStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(FlowAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object desplayAllFollows() {
        try {
            ObservableList<Follows> follows = FXCollections.observableArrayList(new Follows());
            follows.remove(0);
            ResultSet rs = stm.desplayFollowStatment.executeQuery();
            while (rs.next()) {
                follows.add(new Follows(
                        rs.getInt("follows.id"),
                        rs.getString("student.firstname") + " " + rs.getString("student.lastname"),
                        rs.getString("teacher.firstname") + " " + rs.getString("teacher.lastname"),
                        rs.getString("module.name"),
                        rs.getFloat("follows.examenNote"),
                        rs.getFloat("follows.tdNote"),
                        rs.getFloat("follows.avaregeModule")
                ));
            }
            stm.desplayFollowStatment.close();
            return follows;
        } catch (SQLException ex) {
            Logger.getLogger(FlowAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateFollow(Follows follow) {
        try {
            String[] student = follow.getStudent().split(" ", 2);
            String[] teacher = follow.getTeacher().split(" ", 2);
            stm.updateFollowsStatment.setInt(1, stdAgent.getStudentIdByName(student));
            stm.updateFollowsStatment.setInt(2, tchAgent.getTecherIdByName(teacher));
            stm.updateFollowsStatment.setInt(3, mdlAgent.getModuleIdByName(follow.getModule()));
            stm.updateFollowsStatment.setFloat(4, follow.getExamenNote());
            stm.updateFollowsStatment.setFloat(5, follow.getTdNote());
            stm.updateFollowsStatment.setFloat(6, follow.getAvaregeModule());
            stm.updateFollowsStatment.setInt(7, follow.getId());
            int i = stm.updateFollowsStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(FlowAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteAgent(Follows follows) {
        try {
            stm.deleteFollowsStatment.setInt(1, follows.getId());
            int i = stm.deleteFollowsStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(FlowAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object searchFollows(Follows fl) {
        try {
            ObservableList<Follows> follows = FXCollections.observableArrayList(new Follows());
            follows.remove(0);
            String[] str = fl.getStudent().split(" ", 2);
            stm.searchFollowStatment.setString(1, fl.getModule());
            stm.searchFollowStatment.setString(2, str[0]);
            stm.searchFollowStatment.setString(3, str[1]);
            ResultSet rs = stm.searchFollowStatment.executeQuery();
            while (rs.next()) {
                follows.add(new Follows(
                        rs.getInt("follows.id"),
                        rs.getString("student.firstname") + " " + rs.getString("student.lastname"),
                        rs.getString("teacher.firstname") + " " + rs.getString("teacher.lastname"),
                        rs.getString("module.name"),
                        rs.getFloat("follows.examenNote"),
                        rs.getFloat("follows.tdNote"),
                        rs.getFloat("follows.avaregeModule")
                ));
            }
            stm.desplayFollowStatment.close();
            return follows;
        } catch (SQLException ex) {
            Logger.getLogger(FlowAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
