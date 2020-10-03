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
import modules.ResultsTable;

/**
 *
 * @author zed
 */
public class SuperAgentControllers {

    SqlStatements stm = new SqlStatements();

    public Object desplayTableResult(int idSmrt, int idSpy, int idStdt) {
        try {
            ObservableList<ResultsTable> table = FXCollections.observableArrayList(new ResultsTable());
            table.remove(0);
            
            stm.getStudentTableNotesStatment.setInt(1, idSmrt);
            stm.getStudentTableNotesStatment.setInt(2, idStdt);
            stm.getStudentTableNotesStatment.setInt(3, idSpy);
            
            ResultSet rs = stm.getStudentTableNotesStatment.executeQuery();
            while (rs.next()) {
                table.add(new ResultsTable(
                        rs.getString("module.name"),
                        rs.getInt("module.coefficient"),
                        rs.getFloat("follows.tdNote"),
                        rs.getFloat("follows.examenNote"),
                        rs.getFloat("follows.avaregeModule")
                ));
            }
            stm.desplayFollowStatment.close();
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(SuperAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
