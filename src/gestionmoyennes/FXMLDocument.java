/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmoyennes;

import agentsControllers.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modules.Follows;
import modules.Module;
import modules.ResultsTable;
import modules.Specialty;
import modules.Student;
import modules.Teacher;

/**
 *
 * @author zed
 */
public class FXMLDocument implements Initializable {

    ModuleAgentControllers moduleAgent = new ModuleAgentControllers();
    SpecialityAgentControllers specialityAgent = new SpecialityAgentControllers();
    TeacherAgentControllers teacherAgent = new TeacherAgentControllers();
    StudentAgentControllers studentAgent = new StudentAgentControllers();
    FlowAgentControllers followAgent = new FlowAgentControllers();
    SuperAgentControllers superAgent = new SuperAgentControllers();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void refrechSpecialty(TableView tableSp, TableColumn nameColumn, TableColumn IDColumn) throws SQLException {
        ObservableList<Specialty> Specialties = (ObservableList<Specialty>) specialityAgent.SelectAllSpecialties();
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        IDColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableSp.setItems(Specialties);
    }

    public void fillterSpecialtyByName(TableView tableSp, TableColumn nameColumn, TableColumn IDColumn, Specialty specialty) throws SQLException {
        ObservableList<Specialty> Specialties = (ObservableList<Specialty>) specialityAgent.SelectSpecialtiesByName(specialty);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        IDColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableSp.setItems(Specialties);
    }

    public void refrechModule(TableView tableM, TableColumn cofColumn, TableColumn nameModuleColumn,
            TableColumn semmesterColumn, TableColumn SpyColumn, TableColumn IDModuleColumn) throws SQLException {
        ObservableList<Module> modules = (ObservableList<Module>) moduleAgent.SelectAllModules();
        cofColumn.setCellValueFactory(
                new PropertyValueFactory<>("coefficient")
        );
        nameModuleColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        semmesterColumn.setCellValueFactory(
                new PropertyValueFactory<>("semestterNumber")
        );
        SpyColumn.setCellValueFactory(
                new PropertyValueFactory<>("specialtyName")
        );
        IDModuleColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableM.setItems(modules);
    }

    public void fillterModuleByName(TableView tableM, TableColumn cofColumn, TableColumn nameModuleColumn,
            TableColumn semmesterColumn, TableColumn SpyColumn, TableColumn IDModuleColumn, Module module) throws SQLException {
        ObservableList<Specialty> Specialties = (ObservableList<Specialty>) moduleAgent.SelectModulesByName(module);
        cofColumn.setCellValueFactory(
                new PropertyValueFactory<>("coefficient")
        );
        nameModuleColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        semmesterColumn.setCellValueFactory(
                new PropertyValueFactory<>("semestterNumber")
        );
        SpyColumn.setCellValueFactory(
                new PropertyValueFactory<>("specialtyName")
        );
        IDModuleColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableM.setItems(Specialties);
    }

    public void refrechTeachers(TableView tableU, TableColumn firstnameColumn, TableColumn lastnameColumn,
            TableColumn matColumn, TableColumn IDUColumn) throws SQLException {
        ObservableList<Module> modules = (ObservableList<Module>) teacherAgent.desplayAllTeacher();
        firstnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        lastnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        matColumn.setCellValueFactory(
                new PropertyValueFactory<>("matrucula")
        );
        IDUColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableU.setItems(modules);
    }

    public void fillterUserByMat(TableView tableU, TableColumn firstnameColumn, TableColumn lastnameColumn,
            TableColumn matColumn, TableColumn IDUColumn, Teacher teacher) throws SQLException {
        ObservableList<Module> modules = (ObservableList<Module>) teacherAgent.fillteUser(teacher);
        firstnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        lastnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        matColumn.setCellValueFactory(
                new PropertyValueFactory<>("matrucula")
        );
        IDUColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableU.setItems(modules);
    }

    public void refrechStudents(TableView tableU, TableColumn firstnameColumn, TableColumn lastnameColumn,
            TableColumn matColumn, TableColumn IDUColumn) throws SQLException {
        ObservableList<Student> modules = (ObservableList<Student>) studentAgent.desplayAllUser();
        firstnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        lastnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        matColumn.setCellValueFactory(
                new PropertyValueFactory<>("matrucula")
        );
        IDUColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableU.setItems(modules);
    }

    public void fillterStudentByMat(TableView tableU, TableColumn firstnameColumn, TableColumn lastnameColumn,
            TableColumn matColumn, TableColumn IDUColumn, Teacher teacher) throws SQLException {
        ObservableList<Module> modules = (ObservableList<Module>) studentAgent.fillteUser(teacher);
        firstnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        lastnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        matColumn.setCellValueFactory(
                new PropertyValueFactory<>("matrucula")
        );
        IDUColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableU.setItems(modules);
    }

    public void refrechFollows(TableView tableU, TableColumn firstnameColumn, TableColumn lastnameColumn,
            TableColumn matColumn, TableColumn IDUColumn) throws SQLException {
        ObservableList<Module> modules = (ObservableList<Module>) studentAgent.desplayAllUser();
        firstnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        lastnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName")
        );
        matColumn.setCellValueFactory(
                new PropertyValueFactory<>("matrucula")
        );
        IDUColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableU.setItems(modules);
    }

    void refrechFollows(TableView tableF, TableColumn studentNColumn, TableColumn teacherTColumn,
            TableColumn cmbModuleColumn, TableColumn TDColumn, TableColumn examenColumn, TableColumn avaregeColumn, TableColumn IDFColumn) {

        ObservableList<Follows> follows = (ObservableList<Follows>) followAgent.desplayAllFollows();

        studentNColumn.setCellValueFactory(
                new PropertyValueFactory<>("student")
        );
        teacherTColumn.setCellValueFactory(
                new PropertyValueFactory<>("teacher")
        );
        cmbModuleColumn.setCellValueFactory(
                new PropertyValueFactory<>("module")
        );

        examenColumn.setCellValueFactory(
                new PropertyValueFactory<>("examenNote")
        );
        TDColumn.setCellValueFactory(
                new PropertyValueFactory<>("tdNote")
        );
        avaregeColumn.setCellValueFactory(
                new PropertyValueFactory<>("avaregeModule")
        );
        IDFColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableF.setItems(follows);
    }

    void fillterFollowsByStudentName(TableView tableF, TableColumn studentNColumn, TableColumn teacherTColumn,
            TableColumn cmbModuleColumn, TableColumn TDColumn,
            TableColumn examenColumn, TableColumn avaregeColumn, TableColumn IDFColumn,
            Follows fl) {

        ObservableList<Follows> follows = (ObservableList<Follows>) followAgent.searchFollows(fl);

        studentNColumn.setCellValueFactory(
                new PropertyValueFactory<>("student")
        );
        teacherTColumn.setCellValueFactory(
                new PropertyValueFactory<>("teacher")
        );
        cmbModuleColumn.setCellValueFactory(
                new PropertyValueFactory<>("module")
        );

        examenColumn.setCellValueFactory(
                new PropertyValueFactory<>("examenNote")
        );
        TDColumn.setCellValueFactory(
                new PropertyValueFactory<>("tdNote")
        );
        avaregeColumn.setCellValueFactory(
                new PropertyValueFactory<>("avaregeModule")
        );
        IDFColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        tableF.setItems(follows);
    }

    void fillterTables(TextField t1,TextField t2,TableView tableR, TableColumn moduleCol, TableColumn coafCol,
            TableColumn tdCol, TableColumn examenCol, TableColumn avCol, int idSmtr, int idstd, int idSpy) {

        ObservableList<ResultsTable> table = (ObservableList<ResultsTable>) superAgent.desplayTableResult(idSmtr, idstd, idSpy);

        int nbrCoaf = 0;
        Float nbrAvrg = 0.f;

        for (ResultsTable value : table) {
            nbrCoaf += value.getCoefficient();
            nbrAvrg+=  value.getAvrege();
        }
        nbrAvrg=nbrAvrg/nbrCoaf;
        
        t1.setText(nbrCoaf+"");
        t2.setText(nbrAvrg+"");

        moduleCol.setCellValueFactory(
                new PropertyValueFactory<>("nameModule")
        );
        coafCol.setCellValueFactory(
                new PropertyValueFactory<>("coefficient")
        );
        tdCol.setCellValueFactory(
                new PropertyValueFactory<>("td")
        );
        examenCol.setCellValueFactory(
                new PropertyValueFactory<>("examen")
        );
        avCol.setCellValueFactory(
                new PropertyValueFactory<>("avrege")
        );

        tableR.setItems(table);
    }

}
