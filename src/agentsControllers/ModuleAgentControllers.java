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
import modules.Module;
import modules.Specialty;

/**
 *
 * @author zed
 */
public class ModuleAgentControllers {

    SqlStatements stm = new SqlStatements();

    public boolean insertModule(Module module) {
        try {
            stm.insertModuleStatment.setInt(1, module.getSpecialtyID());
            stm.insertModuleStatment.setInt(2, module.getCoefficient());
            stm.insertModuleStatment.setInt(3, module.getSemestterNumber());
            stm.insertModuleStatment.setString(4, module.getName());
            int i = stm.insertModuleStatment.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ModuleAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Object SelectAllModules() {
        try {
            ObservableList<Module> modules = FXCollections.observableArrayList(new Module());
            modules.remove(0);
            ResultSet rs = stm.selectAllModuleStatment.executeQuery();
            while (rs.next()) {
                modules.add(new Module(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("semestterNumber"),
                        rs.getInt("coefficient"),
                        rs.getString("specialty.name")));
            }
            return modules;
        } catch (SQLException ex) {
            Logger.getLogger(ModuleAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object SelectModulesByName(Module module) {
        try {
            ObservableList<Module> modules = FXCollections.observableArrayList(new Module());
            modules.remove(0);
            stm.selectModuleByNameStatment.setString(1, module.getName());
            ResultSet rs = stm.selectModuleByNameStatment.executeQuery();
            while (rs.next()) {
                modules.add(new Module(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("semestterNumber"),
                        rs.getInt("coefficient"),
                        rs.getString("specialty.name")
                ));
            }
            return modules;
        } catch (SQLException ex) {
            Logger.getLogger(ModuleAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateModule(Module module) {
        try {
            int idSpy = new SpecialityAgentControllers().SelectlIdSpecialtiesByNames(module.getSpecialtyName());
            stm.updateModule.setString(1, module.getName());
            stm.updateModule.setInt(2, idSpy);
            stm.updateModule.setInt(3, module.getCoefficient());
            stm.updateModule.setInt(4, module.getSemestterNumber());
            stm.updateModule.setInt(5, module.getId());
            int i = stm.updateModule.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ModuleAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteAgent(Module module) {
        try {
            stm.deleteModule.setInt(1, module.getId());
            int i = stm.deleteModule.executeUpdate();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ModuleAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getModuleIdByName(String name) {
        try {
            stm.getModuleByNameStatment.setString(1, name);
            ResultSet rs = stm.getModuleByNameStatment.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getModuleCoefficientByName(String name) {
        try {
            stm.getModuleByNameStatment.setString(1, name);
            ResultSet rs = stm.getModuleByNameStatment.executeQuery();
            rs.next();
            return rs.getInt("coefficient");
        } catch (SQLException ex) {
            Logger.getLogger(StudentAgentControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
