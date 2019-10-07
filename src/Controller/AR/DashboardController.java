/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AR;

import Classe.Employe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.EmployeService;

/**
 * FXML Controller class
 *
 * @author mst
 */
public class DashboardController implements Initializable {

    EmployeService es = new EmployeService();
    Employe employe = new Employe();
    @FXML
    private VBox vbEmploye;
    @FXML
    private BorderPane bpEmploye;
    @FXML
    private Label titile;
    @FXML
    private Button btnChart;
    @FXML
    private Button signOut;
    @FXML
    private Button btnEmployeClick;
    @FXML
    private Button btnProfilClick;
    @FXML
    private Button btnEtudiantClick;
    @FXML
    private Button btnEtablissementClick;
    @FXML
    private Button btnRechercheEtudiantClick;
    @FXML
    private Button btnChartClick;

    public int getEmployeConnected() {
        Preferences userPreferences = Preferences.userRoot();

        int idEmp = userPreferences.getInt("currentUserId", -1);

        return idEmp;

    }

    public void getEmployeConnectedRemove() {
        Preferences userPreferences = Preferences.userRoot();

        userPreferences.remove("currentUserId");

    }

    @FXML
    public void signOut() throws IOException {
//        Stage stage = new Stage();

//        vbEmploye.getChildren().setAll(scene.getRoot());
        getEmployeConnectedRemove();
        // get a handle to the stage
        Stage stage = (Stage) signOut.getScene().getWindow();
//        // do what you have to do
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/AuthentificationView.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
//        stage.setScene(scene);
//        stage.show();

    }

    @FXML
    public void btnEmploye() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/EmployeVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
        titile.setText("employe");
    }

    @FXML
    public void btnEtablissement() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/EtablissementVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
        titile.setText("المؤسسة");
    }

    @FXML
    public void btnRechercheEtudiant() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/RechercheEtudiantVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
        titile.setText("بحث على الطالب");
    }

    @FXML
    public void btnChart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/ChartVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
        titile.setText("الرسومات المبيانية");
    }

    @FXML
    public void btnEtudiant() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/EtudiantVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
        titile.setText("التلاميذ");
    }

    @FXML
    public void btnProfil() throws IOException {
        VBox root = FXMLLoader.load(getClass().getResource("/view/AR/ProfilVue.fxml"));

        Scene scene = new Scene(root);

        vbEmploye.getChildren().setAll(scene.getRoot());
        titile.setText("الوظاءف");
//        VBox v = FXMLLoader.load(getClass().getResource("/vue/ProfilVue.fxml"));
//        vbEmploye.setCenter(v);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int mID = getEmployeConnected();
                employe = es.findById(mID);
                System.out.println("eee :"+mID);
          
                
                if (!employe.getProfil().getLibelle().equals("مدير")) {
                    btnEmployeClick.setVisible(false);
                    btnEtablissementClick.setVisible(false);
                    btnEtudiantClick.setVisible(false);
                    btnProfilClick.setVisible(false);
                }
            }
        });
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                btnChart.setOnAction(e -> {
//                    title.setText("Hello, World.");
//                });
//            }
//        });
    }

}
