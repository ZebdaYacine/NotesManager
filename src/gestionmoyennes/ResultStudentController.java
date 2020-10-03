/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmoyennes;

import agentsControllers.SpecialityAgentControllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modules.Module;
import modules.Specialty;
import modules.Student;

/**
 * FXML Controller class
 *
 * @author zed
 */
public class ResultStudentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static Student std = new Student();
    FXMLDocumentController obj = new FXMLDocumentController();

    @FXML
    private TextField studentN,t1,t2;

    @FXML
    private ComboBox<String> cmbsemestter, cmbSpy;

    @FXML
    private TableColumn moduleCol, coafCol, tdCol, examenCol, avCol;

    @FXML
    private TableView tableR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obj.fillCombobox((ComboBox<String>) cmbsemestter, obj.nbrSemestter);
        obj.fillCombobox((ComboBox<String>) cmbSpy, obj.listSpy);

        studentN.setText(std.getFirstName() + " " + std.getLastName());
        cmbsemestter.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
        });
    }

    public void showTable(Event event) {
        ObservableList<String> idSpy = new FXMLDocumentController().specialityAgent.SelectAllIdSpecialtiesByNames(cmbSpy.getSelectionModel().getSelectedItem());
        new FXMLDocument().fillterTables(t1,t2,tableR, moduleCol, coafCol, tdCol, examenCol, avCol,
                Integer.parseInt(cmbsemestter.getSelectionModel().getSelectedItem()),
                std.getId(), Integer.parseInt(idSpy.get(0)));
    }

}
