/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmoyennes;

import agentsControllers.FlowAgentControllers;
import agentsControllers.ModuleAgentControllers;
import agentsControllers.SpecialityAgentControllers;
import agentsControllers.StudentAgentControllers;
import agentsControllers.TeacherAgentControllers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modules.Follows;
import modules.Module;
import modules.Specialty;
import modules.Student;
import modules.Teacher;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author zed
 */
public class FXMLDocumentController implements Initializable {

    public ObservableList<String> nbrSemestter = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6");

    public ObservableList<String> listSpy = (ObservableList<String>) new SpecialityAgentControllers().SelectAllSpecialtiesNames();
    public ModuleAgentControllers moduleAgent = new ModuleAgentControllers();
    public SpecialityAgentControllers specialityAgent = new SpecialityAgentControllers();
    public TeacherAgentControllers teacherAgent = new TeacherAgentControllers();
    public StudentAgentControllers studentAgent = new StudentAgentControllers();
    public FlowAgentControllers followAgent = new FlowAgentControllers();

    public void fillCombobox(ComboBox<String> cmb, ObservableList<String> list) {
        if (cmb != null) {
            cmb.getItems().clear();
            for (Object item : list) {
                cmb.getItems().add(item.toString());
            }
            cmb.getSelectionModel().selectFirst();
        }
    }

    ObservableList<Student> studentes = (ObservableList<Student>) new StudentAgentControllers().desplayAllStudent();
    ObservableList<Teacher> teachers = (ObservableList<Teacher>) new TeacherAgentControllers().desplayAllTeacher();
    ObservableList<Module> modules = (ObservableList<Module>) new ModuleAgentControllers().SelectAllModules();

    ArrayList<String> studentsList = new ArrayList<>();
    ArrayList<String> teachersList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCombobox((ComboBox<String>) cmbsemestter, nbrSemestter);
        fillCombobox((ComboBox<String>) cmbSpy, listSpy);

        for (Student std : studentes) {
            studentsList.add(std.getFirstName() + " " + std.getLastName());
        }

        for (Teacher tch : teachers) {
            teachersList.add(tch.getFirstName() + " " + tch.getLastName());
        }
        cmbModule.getItems().clear();
        for (Module item : modules) {
            cmbModule.getItems().add(item.getName());
        }
        cmbModule.getSelectionModel().selectFirst();
        TextFields.bindAutoCompletion(studentN, studentsList);
        TextFields.bindAutoCompletion(teacherT, teachersList);

        cmbModule.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            calculAvrg(TD, examen, cmbModule);
        });
    }

    /*------------------------------------------------------ Speciality Part ------------------------------------------------------*/
    @FXML
    private TextField name, ID;

    @FXML
    private TableView tableSp;

    @FXML
    private TableColumn nameColumn, IDColumn;

    public void addSpeciality(Event event) throws SQLException {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Specialty specialty = new Specialty(name.getText());
        if (name.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (specialityAgent.insertSpeciality(specialty)) {
            new Alert(Alert.AlertType.INFORMATION, " specialty is inserted ", button).show();
            new FXMLDocument().refrechSpecialty(tableSp, nameColumn, IDColumn);
        } else {
            Options.field("there is an error try again");
        }
    }

    public void loadSpeciality(Event event) throws SQLException {
        new FXMLDocument().refrechSpecialty(tableSp, nameColumn, IDColumn);
    }

    public void refrechSp(Event event) {
        try {
            new FXMLDocument().refrechSpecialty(tableSp, nameColumn, IDColumn);
            name.setText("");
            ID.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSpeciality(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (name.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (specialityAgent.updateSpecialty(new Specialty(Integer.parseInt(ID.getText()), name.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, " specialty is updated ", button).show();
            try {
                new FXMLDocument().refrechSpecialty(tableSp, nameColumn, IDColumn);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    public void showSpeciality(Event event) {
        Specialty specialty = (Specialty) tableSp.getSelectionModel().getSelectedItem();
        name.setText(specialty.getName());
        ID.setText(specialty.getId() + "");
    }

    public void deleteSpeciality(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Specialty specialty = new Specialty(Integer.parseInt(ID.getText()), name.getText());
        if (name.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (specialityAgent.deleteSpecialty(specialty)) {
            new Alert(Alert.AlertType.INFORMATION, " specialty is deleted ", button).show();
            try {
                new FXMLDocument().refrechSpecialty(tableSp, nameColumn, IDColumn);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    @FXML
    public void searchSpeciality(KeyEvent ky) throws SQLException {
        if (!name.getText().isEmpty()) {
            new FXMLDocument().fillterSpecialtyByName(tableSp, nameColumn, IDColumn, new Specialty(name.getText()));
        } else {
            Options.field("Empty field");
        }
    }

    /*------------------------------------------------------ Module Part ------------------------------------------------------*/
    @FXML
    private TextField coefficient, nameModule, IDModule;

    @FXML
    private TableView tableM;

    @FXML
    private ComboBox<String> cmbsemestter, cmbSpy;

    @FXML
    private TableColumn cofColumn, nameModuleColumn, semmesterColumn, SpyColumn, IDModuleColumn;

    public void addModule(Event event) throws SQLException {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ObservableList<String> idSpy = specialityAgent.SelectAllIdSpecialtiesByNames(cmbSpy.getSelectionModel().getSelectedItem());
        Module module = new Module(nameModule.getText(), Integer.parseInt(cmbsemestter.getSelectionModel().getSelectedItem()),
                Integer.parseInt(coefficient.getText()), Integer.parseInt(idSpy.get(0)));
        if (coefficient.getText().isEmpty() || nameModule.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (moduleAgent.insertModule(module)) {
            new Alert(Alert.AlertType.INFORMATION, " module  was inserted ", button).show();
            new FXMLDocument().refrechModule(tableM, cofColumn,
                    nameModuleColumn, semmesterColumn, SpyColumn, IDModuleColumn);
        } else {
            Options.field("there is an error try again");
        }
    }

    public void updateModule(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (coefficient.getText().isEmpty() || nameModule.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (moduleAgent.updateModule(new Module(Integer.parseInt(IDModule.getText()),
                nameModule.getText(), Integer.parseInt(cmbsemestter.getSelectionModel().getSelectedItem()),
                Integer.parseInt(coefficient.getText()), cmbSpy.getSelectionModel().getSelectedItem()))) {
            new Alert(Alert.AlertType.INFORMATION, " module was updated ", button).show();
            try {
                new FXMLDocument().refrechModule(tableM, cofColumn,
                        nameModuleColumn, semmesterColumn, SpyColumn, IDModuleColumn);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    public void showModule(Event event) {
        Module module = (Module) tableM.getSelectionModel().getSelectedItem();
        nameModule.setText(module.getName());
        coefficient.setText(module.getCoefficient() + "");
        cmbsemestter.getSelectionModel().select(module.getSemestterNumber() - 1);
        cmbSpy.getSelectionModel().select(module.getSpecialtyID());
        IDModule.setText(module.getId() + "");
    }

    public void loadModule(Event event) throws SQLException {
        new FXMLDocument().refrechModule(tableM, cofColumn,
                nameModuleColumn, semmesterColumn, SpyColumn, IDModuleColumn);
    }

    public void refrechModule(Event event) {
        try {
            new FXMLDocument().refrechModule(tableM, cofColumn,
                    nameModuleColumn, semmesterColumn, SpyColumn, IDModuleColumn);
            nameModule.setText("");
            coefficient.setText("");
            IDModule.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteModule(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (coefficient.getText().isEmpty() || nameModule.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (moduleAgent.deleteAgent(new Module(Integer.parseInt(IDModule.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " module was updated ", button).show();
            try {
                new FXMLDocument().refrechModule(tableM, cofColumn,
                        nameModuleColumn, semmesterColumn, SpyColumn, IDModuleColumn);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    @FXML
    public void searchModule(KeyEvent ky) throws SQLException {
        if (!nameModule.getText().isEmpty()) {
            new FXMLDocument().fillterModuleByName(tableM, cofColumn, nameModuleColumn,
                    semmesterColumn, SpyColumn, IDModuleColumn, new Module(nameModule.getText()));
        } else {
            Options.field("Empty field");
        }
    }

    /*------------------------------------------------------ Teacher Part ------------------------------------------------------*/
    @FXML
    private TextField firstname, lastname, mat, IdUser;

    @FXML
    private TableView tableU;

    @FXML
    private TableColumn firstnameColumn, lastnameColumn, matColumn, IDUColumn;

    public void addUser(Event event) throws SQLException {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (mat.getText().isEmpty() || mat.getText().isEmpty() || firstname.getText().isEmpty() || lastname.getText().isEmpty()) {
            Options.field("Empty fields");
        } else if (teacherAgent.insertTeacher(new Teacher(firstname.getText(), lastname.getText(), Integer.parseInt(mat.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " Teacher  was inserted ", button).show();
            new FXMLDocument().refrechTeachers(tableU, firstnameColumn,
                    lastnameColumn, matColumn, IDUColumn);
        } else {
            Options.field("there is an error try again");
        }
    }

    public void updteUser(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (firstname.getText().isEmpty() || lastname.getText().isEmpty() || IdUser.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (teacherAgent.updateTeacher(new Teacher(Integer.parseInt(IdUser.getText()), firstname.getText(), lastname.getText(), Integer.parseInt(mat.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " Teacher was updated ", button).show();
            try {
                new FXMLDocument().refrechTeachers(tableU, firstnameColumn,
                        lastnameColumn, matColumn, IDUColumn);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    public void deleteUser(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (mat.getText().isEmpty() || mat.getText().isEmpty() || firstname.getText().isEmpty() || lastname.getText().isEmpty()) {
            Options.field("Empty fields");
        } else if (teacherAgent.deleteAgent(new Teacher(Integer.parseInt(IdUser.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " Teacher was deleted ", button).show();
            try {
                new FXMLDocument().refrechTeachers(tableU, firstnameColumn,
                        lastnameColumn, matColumn, IDUColumn);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    public void refrechUser(Event event) {
        try {
            new FXMLDocument().refrechTeachers(tableU, firstnameColumn,
                    lastnameColumn, matColumn, IDUColumn);
            firstname.setText("");
            lastname.setText("");
            mat.setText("");
            IdUser.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTeacher(Event event) throws SQLException {
        new FXMLDocument().refrechTeachers(tableU, firstnameColumn,
                lastnameColumn, matColumn, IDUColumn);
    }

    public void showTeacher(Event event) {
        Teacher teacher = (Teacher) tableU.getSelectionModel().getSelectedItem();
        firstname.setText(teacher.getFirstName());
        lastname.setText(teacher.getLastName());
        mat.setText(teacher.getMatrucula() + "");
        IdUser.setText(teacher.getId() + "");
    }

    @FXML
    public void searchUser(KeyEvent ky) throws SQLException {
        if (!mat.getText().isEmpty()) {
            new FXMLDocument().fillterUserByMat(tableU, firstnameColumn,
                    lastnameColumn, matColumn, IDUColumn, new Teacher(Integer.parseInt(mat.getText())));
        } else {
            Options.field("Empty field");
        }
    }

    /*------------------------------------------------------ Studnet Part ------------------------------------------------------*/
    @FXML
    private TextField firstname1, lastname1, mat1, IdUser1;

    @FXML
    private TableView tableU1;

    @FXML
    private TableColumn firstnameColumn1, lastnameColumn1, matColumn1, IDUColumn1;

    public void addUser1(Event event) throws SQLException {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (mat1.getText().isEmpty() || firstname1.getText().isEmpty() || lastname1.getText().isEmpty()) {
            Options.field("Empty fields");
        } else if (studentAgent.insertUser(new Teacher(firstname1.getText(), lastname1.getText(), Integer.parseInt(mat1.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " User  was inserted ", button).show();
            new FXMLDocument().refrechStudents(tableU1, firstnameColumn1,
                    lastnameColumn1, matColumn1, IDUColumn1);
        } else {
            Options.field("there is an error try again");
        }
    }

    public void updteUser1(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (firstname1.getText().isEmpty() || lastname1.getText().isEmpty() || IdUser1.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (studentAgent.updateUser(new Teacher(Integer.parseInt(IdUser1.getText()), firstname1.getText(), lastname1.getText(),
                Integer.parseInt(mat1.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " User was updated ", button).show();
            try {
                new FXMLDocument().refrechStudents(tableU1, firstnameColumn1,
                        lastnameColumn1, matColumn1, IDUColumn1);

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    public void deleteUser1(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (mat1.getText().isEmpty() || mat1.getText().isEmpty() || firstname1.getText().isEmpty() || lastname1.getText().isEmpty()) {
            Options.field("Empty fields");
        } else if (studentAgent.deleteAgent(new Teacher(Integer.parseInt(IdUser1.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " Student was deleted ", button).show();
            try {
                new FXMLDocument().refrechStudents(tableU1, firstnameColumn1,
                        lastnameColumn1, matColumn1, IDUColumn1);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Options.field("there is an error try again");
        }
    }

    public void refrechUser1(Event event) {
        try {
            new FXMLDocument().refrechStudents(tableU1, firstnameColumn1,
                    lastnameColumn1, matColumn1, IDUColumn1);
            firstname1.setText("");
            lastname1.setText("");
            mat1.setText("");
            IdUser1.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadStudent(Event event) throws SQLException {
        new FXMLDocument().refrechStudents(tableU1, firstnameColumn1,
                lastnameColumn1, matColumn1, IDUColumn1);
    }

    @FXML
    private Button btnshowRes;

    public void showTeacher1(Event event) {
        Teacher teacher = (Teacher) tableU1.getSelectionModel().getSelectedItem();
        firstname1.setText(teacher.getFirstName());
        lastname1.setText(teacher.getLastName());
        mat1.setText(teacher.getMatrucula() + "");
        IdUser1.setText(teacher.getId() + "");
        btnshowRes.setDisable(false);
    }

    @FXML
    public void searchUser1(KeyEvent ky) throws SQLException {
        if (!mat1.getText().isEmpty()) {
            btnshowRes.setDisable(true);
            new FXMLDocument().fillterStudentByMat(tableU1, firstnameColumn1,
                    lastnameColumn1, matColumn1, IDUColumn1, new Teacher(Integer.parseInt(mat1.getText())));
        } else {
            Options.field("Empty field");
        }
    }

    /*------------------------------------------------------ follow Part ------------------------------------------------------*/
    @FXML
    private TextField studentN, teacherT, TD, IdF, examen, avarege;

    @FXML
    private TableView tableF;

    @FXML
    private ComboBox<String> cmbModule;

    @FXML
    private TableColumn studentNColumn, teacherTColumn, cmbModuleColumn, TDColumn, examenColumn, avaregeColumn, IDFColumn;

    public void btnaddF(Event event) throws SQLException {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (TD.getText().isEmpty() || avarege.getText().isEmpty() || studentN.getText().isEmpty() || teacherT.getText().isEmpty() || examen.getText().isEmpty()) {
            Options.field("Empty fields");
        } else if ((Float.parseFloat(TD.getText()) > 0 && Float.parseFloat(TD.getText()) < 20)
                && (Float.parseFloat(examen.getText()) > 0 && Float.parseFloat(examen.getText()) < 20)) {
            if (followAgent.insertFollow(
                    new Follows(studentN.getText(),
                            teacherT.getText(),
                            cmbModule.getSelectionModel().getSelectedItem().toString(),
                            Float.parseFloat(examen.getText()),
                            Float.parseFloat(TD.getText()),
                            Float.parseFloat(avarege.getText())))) {
                new Alert(Alert.AlertType.INFORMATION, " follos  was inserted ", button).show();
                new FXMLDocument().refrechFollows(tableF, studentNColumn,
                        teacherTColumn, cmbModuleColumn, TDColumn, examenColumn, avaregeColumn, IDFColumn);
            } else {
                Options.field("there is an error try again");
            }
        } else {
            Options.field("the notes must between 0-20");
        }
    }

    @FXML
    public void calculAvrege0(KeyEvent ky) throws SQLException {
        if (!examen.getText().isEmpty()) {
            calculAvrg(TD, examen, cmbModule);
        }
    }

    @FXML
    public void calculAvrege1(KeyEvent ky) throws SQLException {
        if (!TD.getText().isEmpty()) {
            calculAvrg(TD, examen, cmbModule);
        }
    }

    private void calculAvrg(TextField TD, TextField examen, ComboBox<String> cmbModule) {
        int coaf = moduleAgent.getModuleCoefficientByName(cmbModule.getSelectionModel().getSelectedItem());
        float tdNot = Float.parseFloat(TD.getText());
        float examenNote = Float.parseFloat(examen.getText());
        float avrg = (float) (Math.round(((tdNot * 0.4 + examenNote * 0.6) * coaf) * 100.0) / 100.0);
        avarege.setText(avrg + "");
    }

    public void loadFollows(Event event) throws SQLException {
        new FXMLDocument().refrechFollows(tableF, studentNColumn,
                teacherTColumn, cmbModuleColumn, TDColumn, examenColumn, avaregeColumn, IDFColumn);
    }

    public void showFollows(Event event) {
        Follows follows = (Follows) tableF.getSelectionModel().getSelectedItem();
        studentN.setText(follows.getStudent());
        teacherT.setText(follows.getTeacher());
        TD.setText(follows.getTdNote() + "");
        IdF.setText(follows.getId() + "");
        examen.setText(follows.getExamenNote() + "");
        avarege.setText(follows.getAvaregeModule() + "");
        cmbModule.getSelectionModel().select(follows.getModule());

    }

    public void refrechFollow(Event event) {
        new FXMLDocument().refrechFollows(tableF, studentNColumn,
                teacherTColumn, cmbModuleColumn, TDColumn, examenColumn, avaregeColumn, IDFColumn);
        studentN.setText("");
        teacherT.setText("");
        TD.setText("");
        IdF.setText("");
        examen.setText("");
        avarege.setText("");
    }

    public void deleteFollow(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (IdF.getText().isEmpty()) {
            Options.field("Empty fields");
        } else if (followAgent.deleteAgent(new Follows(Integer.parseInt(IdF.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " Follows was deleted ", button).show();
            new FXMLDocument().refrechFollows(tableF, studentNColumn,
                    teacherTColumn, cmbModuleColumn, TDColumn, examenColumn, avaregeColumn, IDFColumn);
        } else {
            Options.field("there is an error try again");
        }
    }

    public void searchFollow(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (studentN.getText().isEmpty()) {
            Options.field("Empty student fields");
        } else {
            new FXMLDocument().fillterFollowsByStudentName(tableF, studentNColumn,
                    teacherTColumn, cmbModuleColumn, TDColumn, examenColumn,
                    avaregeColumn, IDFColumn, new Follows(studentN.getText(),
                            cmbModule.getSelectionModel().getSelectedItem()));
        }
    }

    public void updateFollow(Event event) {
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if (IdF.getText().isEmpty()) {
            Options.field("Empty field");
        } else if (followAgent.updateFollow(new Follows(Integer.parseInt(IdF.getText()),
                studentN.getText(), teacherT.getText(), cmbModule.getSelectionModel().getSelectedItem(),
                Float.parseFloat(examen.getText()), Float.parseFloat(TD.getText()),
                Float.parseFloat(avarege.getText())))) {
            new Alert(Alert.AlertType.INFORMATION, " Follows was updated ", button).show();
            new FXMLDocument().refrechFollows(tableF, studentNColumn, teacherTColumn,
                    cmbModuleColumn, TDColumn, examenColumn, avaregeColumn, IDFColumn);
        } else {
            Options.field("there is an error try again");
        }
    }

    public void showResult(Event event) {
        try {
            if(IdUser1.getText().isEmpty()){
            Options.field("no student Selected");
            }
            else{
            ResultStudentController.std.setId(Integer.parseInt(IdUser1.getText()));
            ResultStudentController.std.setFirstName(firstname1.getText());
            ResultStudentController.std.setLastName(lastname1.getText());
            Parent root = FXMLLoader.load(getClass().getResource("ResultStudent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
