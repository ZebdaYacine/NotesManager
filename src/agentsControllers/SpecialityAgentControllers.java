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
import modules.Specialty;

/**
 *
 * @author zed
 */
public class SpecialityAgentControllers {

    SqlStatements stm = new SqlStatements();

    public boolean insertSpeciality(Specialty specialty) {
        try {
            stm.insertSecialityStatment.setInt(1, specialty.getId());
            stm.insertSecialityStatment.setString(2, specialty.getName());
            int i = stm.insertSecialityStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object SelectAllSpecialties() {
        try {
            ObservableList<Specialty> Specialties = FXCollections.observableArrayList(new Specialty());
            Specialties.remove(0);
            ResultSet rs = stm.selectAllSecialityStatment.executeQuery();
            while (rs.next()) {
                Specialties.add(new Specialty(rs.getInt("id"), rs.getString("name")));
            }
            return Specialties;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<String> SelectAllSpecialtiesNames() {
        try {
            ObservableList<String> Specialties = FXCollections.observableArrayList(new String());
            Specialties.remove(0);
            ResultSet rs = stm.selectAllSecialityStatment.executeQuery();
            while (rs.next()) {
                Specialties.add(rs.getString("name"));
            }
            return Specialties;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ObservableList<String> SelectAllIdSpecialtiesByNames(String names) {
        try {
            ObservableList<String> Specialties = FXCollections.observableArrayList(new String());
            Specialties.remove(0);
            stm.selectAllIdSecialityByNameStatment.setString(1, names);
            ResultSet rs = stm.selectAllIdSecialityByNameStatment.executeQuery();
            while (rs.next()) {
                Specialties.add(rs.getInt("id") + "");
            }
            return Specialties;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int SelectlIdSpecialtiesByNames(String name) {
        try {
            stm.selectIdSecialityByNameStatment.setString(1,name);
            ResultSet rs = stm.selectIdSecialityByNameStatment.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public String SelectNameSpecialtiesById(int id) {
        try {
            stm.selectNameSecialityById.setInt(1, id);
            ResultSet rs = stm.selectNameSecialityById.executeQuery();
            rs.next();
            return rs.getString("name");
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "No Spy";
    }

    public Object SelectSpecialtiesByName(Specialty specialty) {
        try {
            ObservableList<Specialty> Specialties = FXCollections.observableArrayList(new Specialty());
            Specialties.remove(0);
            stm.selectSecialityByNameStatment.setString(1, specialty.getName());
            ResultSet rs = stm.selectSecialityByNameStatment.executeQuery();
            while (rs.next()) {
                Specialties.add(new Specialty(rs.getInt("id"), rs.getString("name")));
            }
            return Specialties;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateSpecialty(Specialty specialty) {
        try {
            stm.updateSeciality.setString(1, specialty.getName());
            stm.updateSeciality.setInt(2, specialty.getId());
            int i = stm.updateSeciality.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteSpecialty(Specialty specialty) {
        try {
            stm.deleteSeciality.setInt(1, specialty.getId());
            int i = stm.deleteSeciality.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SpecialityAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
