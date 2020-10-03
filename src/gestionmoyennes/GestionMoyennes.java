/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmoyennes;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zed
 */
public class GestionMoyennes extends Application {

    public static Connection con;

    public GestionMoyennes() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/NotesManagres";
        String user = "BladiUser";
        String Password = "root";
        Class.forName("com.mysql.jdbc.Driver");
        this.con = (Connection) DriverManager.getConnection(url, user, Password);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
