/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentsControllers;

import com.mysql.jdbc.PreparedStatement;
import dataBaseController.Queries;
import static gestionmoyennes.GestionMoyennes.con;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zed
 */
public class SqlStatements {

    Queries queries = new Queries();

    PreparedStatement insertSecialityStatment;
    PreparedStatement selectAllSecialityStatment;
    PreparedStatement selectAllIdSecialityByNameStatment;
    PreparedStatement selectSecialityByNameStatment;
    PreparedStatement selectNameSecialityById;
    PreparedStatement selectIdSecialityByNameStatment;
    PreparedStatement updateSeciality;
    PreparedStatement deleteSeciality;

    PreparedStatement insertModuleStatment;
    PreparedStatement selectAllModuleStatment;
    PreparedStatement selectModuleByNameStatment;
    PreparedStatement getModuleByNameStatment;
    PreparedStatement updateModule;
    PreparedStatement deleteModule;

    PreparedStatement insertTeacherStatment;
    PreparedStatement desplayTeacherStatment;
    PreparedStatement fillterTeacherByMatStatment;
    PreparedStatement updateTeacherStatment;
    PreparedStatement deleteTeacherStatment;
    PreparedStatement selectTeacherByNameStatment;

    PreparedStatement insertStudentStatment;
    PreparedStatement desplayStudentStatment;
    PreparedStatement fillterStudentByMatStatment;
    PreparedStatement updateStudentStatment;
    PreparedStatement deleteStudentStatment;
    PreparedStatement selectStudentByNameStatment;

    PreparedStatement insertFollowStatment;
    PreparedStatement desplayFollowStatment;
    PreparedStatement searchFollowStatment;
    PreparedStatement deleteFollowsStatment;
    PreparedStatement updateFollowsStatment;
    
    PreparedStatement getStudentTableNotesStatment;

    public SqlStatements() {
        try {

            this.insertSecialityStatment = (PreparedStatement) con.prepareStatement(queries.insertSeciality);
            this.selectAllSecialityStatment = (PreparedStatement) con.prepareStatement(queries.selectAllSeciality);
            this.selectSecialityByNameStatment = (PreparedStatement) con.prepareStatement(queries.selectSecialityByName);
            this.selectNameSecialityById = (PreparedStatement) con.prepareStatement(queries.selectNameSecialityId);
            this.selectAllIdSecialityByNameStatment = (PreparedStatement) con.prepareStatement(queries.selectIdSecialityByName);
            this.updateSeciality = (PreparedStatement) con.prepareStatement(queries.updateSeciality);
            this.deleteSeciality = (PreparedStatement) con.prepareStatement(queries.deleteSeciality);
            this.selectIdSecialityByNameStatment = (PreparedStatement) con.prepareStatement(queries.selectIdSecialityByName);

            this.insertModuleStatment = (PreparedStatement) con.prepareStatement(queries.insertMoule);
            this.selectAllModuleStatment = (PreparedStatement) con.prepareStatement(queries.showModuleInTableQuery);
            this.selectModuleByNameStatment = (PreparedStatement) con.prepareStatement(queries.searchModuleByName);
            this.getModuleByNameStatment = (PreparedStatement) con.prepareStatement(queries.selectMouleByName);
            this.updateModule = (PreparedStatement) con.prepareStatement(queries.updateMoule);
            this.deleteModule = (PreparedStatement) con.prepareStatement(queries.deleteMoule);

            this.insertTeacherStatment = (PreparedStatement) con.prepareStatement(queries.insertTeacherQuery);
            this.desplayTeacherStatment = (PreparedStatement) con.prepareStatement(queries.desplayAllTeacherQuery);
            this.updateTeacherStatment = (PreparedStatement) con.prepareStatement(queries.updateTeacherQuery);
            this.deleteTeacherStatment = (PreparedStatement) con.prepareStatement(queries.deleteTeacherQuery);
            this.fillterTeacherByMatStatment = (PreparedStatement) con.prepareStatement(queries.fillterTeacherByMatQuery);
            this.selectTeacherByNameStatment = (PreparedStatement) con.prepareStatement(queries.selectTeacherByNameQuery);

            this.insertStudentStatment = (PreparedStatement) con.prepareStatement(queries.insertStudentQuery);
            this.desplayStudentStatment = (PreparedStatement) con.prepareStatement(queries.desplayAllStudentQuery);
            this.updateStudentStatment = (PreparedStatement) con.prepareStatement(queries.updateStudentQuery);
            this.deleteStudentStatment = (PreparedStatement) con.prepareStatement(queries.deleteStudentQuery);
            this.fillterStudentByMatStatment = (PreparedStatement) con.prepareStatement(queries.fillterStudentByMatQuery);
            this.selectStudentByNameStatment = (PreparedStatement) con.prepareStatement(queries.selectStudentByNameQuery);

            this.insertFollowStatment = (PreparedStatement) con.prepareStatement(queries.insertFollowQuery);
            this.desplayFollowStatment = (PreparedStatement) con.prepareStatement(queries.desplayAllFollowQuery);
            this.searchFollowStatment = (PreparedStatement) con.prepareStatement(queries.searchFollowQuery);
            this.deleteFollowsStatment = (PreparedStatement) con.prepareStatement(queries.deleteFollowsQuery);
            this.updateFollowsStatment = (PreparedStatement) con.prepareStatement(queries.updateFollowsQuery);
            
            this.getStudentTableNotesStatment=(PreparedStatement) con.prepareStatement(queries.getStudentTableNotesQuery);

        } catch (SQLException ex) {
            Logger.getLogger(SqlStatements.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
