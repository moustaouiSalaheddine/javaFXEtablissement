package Controller.FR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Classe.Employe;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.EmployeService;
import service.ProfilService;

/**
 *
 * @author Sinponzakra
 */
public class MainController implements Initializable {

    EmployeService es = new EmployeService();
    ProfilService ps = new ProfilService();

    @FXML
    private Label headerText;
    @FXML
    private BorderPane mBorder;
    @FXML
    private Text profilCount;
    @FXML
    private Text employeCount;
    @FXML
    private VBox mainCenter;
    @FXML
    private VBox profilTicket;
    @FXML
    private VBox employeTicket;
    @FXML
    private ImageView logOutBtn;
    @FXML
    private Label employeNameLabel;

    @FXML
    private void switchtoProfil(ActionEvent e) throws IOException {
        headerText.setText("Profils");
        VBox v = FXMLLoader.load(getClass().getResource("/vue/ProfilVue.fxml"));
        mBorder.setCenter(v);
    }

    @FXML
    private void switchtoHome(ActionEvent e) throws IOException {
        headerText.setText("Home");
        mBorder.setCenter(mainCenter);
        setCountsHome();
    }

    @FXML
    private void switchtoEmploye(ActionEvent e) throws IOException {
        headerText.setText("Employes");
        VBox v = FXMLLoader.load(getClass().getResource("/vue/EmployeVue.fxml"));
        mBorder.setCenter(v);
    }

    private void setCountsHome() {
        profilCount.setText(ps.getProfilsCount() + "");
        employeCount.setText(es.getEmployesCount() + "");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCountsHome();

        profilTicket.setOnMousePressed(e -> {
            try {
                headerText.setText("Profils");
                VBox v = FXMLLoader.load(getClass().getResource("/vue/ProfilVue.fxml"));
                mBorder.setCenter(v);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        employeTicket.setOnMousePressed(e -> {
            try {
                headerText.setText("Employes");
                VBox v = FXMLLoader.load(getClass().getResource("/vue/EmployeVue.fxml"));
                mBorder.setCenter(v);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Preferences userPreferences = Preferences.userRoot();
        int currentUserId = userPreferences.getInt("currentUserId", 0);

        Employe currentEmploye = es.findById(currentUserId);
        employeNameLabel.setText(currentEmploye.getPrenom() + " " + currentEmploye.getNom());

        logOutBtn.setOnMousePressed(e -> {
            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation de déconnexion");
                alert.setContentText("Voulez-vous vraiment vous déconnecter ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    userPreferences.clear();

                    Parent root = FXMLLoader.load(getClass().getResource("/vue/LoginVue.fxml"));

                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.getIcons().add(new Image(this.getClass().getResource("/images/icon.png").toString()));
                    stage.setScene(scene);
                    stage.show();

                    ((Stage) logOutBtn.getScene().getWindow()).close();
                    
                }

                }catch (BackingStoreException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        }

    }
