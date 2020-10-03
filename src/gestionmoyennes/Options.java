/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmoyennes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 *
 * @author zed
 */
public class Options implements Initializable{
    
    
    public static void field(String str) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information Dialog");
        a.setHeaderText(null);
        a.setContentText(str);
        a.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
