/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.FR;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author mst
 */
public class DashbordController implements Initializable {

    @FXML
    private VBox vbEmploye;
    @FXML
    private BorderPane bpEmploye;

    @FXML
    public void btnEmploye() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/../view/EmployeVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
    }
    @FXML
    public void btnProfil() throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("/../view/ProfilVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
//        VBox v = FXMLLoader.load(getClass().getResource("/vue/ProfilVue.fxml"));
//        vbEmploye.setCenter(v);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
