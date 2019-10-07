/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AR;

import Classe.Employe;
import java.io.IOException;
import util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.EmployeService;

/**
 *
 * @author mst
 */
public class AuthentificationِController implements Initializable {

    EmployeService es = new EmployeService();
    Util util = new Util();
    private static Employe emp = null;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    @FXML
    private Label usernameError;
    @FXML
    private Label passwordError;

    @FXML
    private void connectionProfil(ActionEvent e) throws IOException {
        String mUsername = username.getText();
        String mPassword = password.getText();

//      Start  TODO REMOVE  Next LoadingTransition();
//        LoadingTransition();
//        Ends TODO

        emp = es.CheckLogin(mUsername);
        System.out.println("user : " + mUsername + " pass : " + mPassword);
        System.out.println("emp : " + emp);
        if (emp == null) {
            usernameError.setVisible(true);
        } else {
            usernameError.setVisible(false);
            if (util.MD5(mPassword).equals(emp.getPassword())) {
                LoadingTransition();
                passwordError.setVisible(false);
            } else {
                passwordError.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usernameError.setVisible(false);

        passwordError.setVisible(false);
    }

    private void LoadingTransition() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/DashboardView.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
//        stage.getIcons().add(new Image(this.getClass().getResource("/images/user2.png").toString()));
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Preferences userPreferences = Preferences.userRoot();
        userPreferences.putInt("currentUserId", emp.getId());

        ((Stage) btnLogin.getScene().getWindow()).close();

    }
}
