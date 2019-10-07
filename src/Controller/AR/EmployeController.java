/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AR;

import Controller.FR.*;
import Classe.Employe;
import Classe.Profil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.EmployeService;
import service.ProfilService;
import util.Util;
//import util.Util;

/**
 * FXML Controller class
 *
 * @author Sinponzakra
 */
public class EmployeController implements Initializable {

    //Utils
    Util util = new Util();
    //services
    ProfilService ps = new ProfilService();
    EmployeService es = new EmployeService();

    //init Lists
    ObservableList<Profil> profils = FXCollections.observableArrayList();
    ObservableList<Employe> employes = FXCollections.observableArrayList();

    //inner static varriable
    private static int index;
    private static int selectedProfilId;
    private static String selectedSexe;
    Date dt = new Date();

    //Fields
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton radH, radF;
    @FXML
    private TextField telephone;
    @FXML
    private TextField cin;
    @FXML
    private ComboBox<Profil> profil;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TableView<Employe> mTable;
    @FXML
    private TableColumn<Employe, String> nomColumn;
    @FXML
    private TableColumn<Employe, String> prenomColumn;
    @FXML
    private TableColumn<Employe, LocalDate> dateColumn;
    @FXML
    private TableColumn<Employe, String> sexeColumn;
    @FXML
    private TableColumn<Employe, String> telephoneColumn;
    @FXML
    private TableColumn<Employe, String> cinColumn;
    @FXML
    private TableColumn<Employe, Profil> profilColumn;
    @FXML
    private TableColumn<Employe, String> usernameColumn;

    //FXML Methods
    @FXML
    private void saveAction(ActionEvent e) {
        Instant instant = Instant.from(date.getValue().atStartOfDay(ZoneId.systemDefault()));
        dt = Date.from(instant);

        if (radH.isSelected()) {
            selectedSexe = "Homme";
        } else if (radF.isSelected()) {
            selectedSexe = "Femme";
        }

        es.create(new Employe(nom.getText(), prenom.getText(), selectedSexe, telephone.getText(), dt, cin.getText(), ps.findById(selectedProfilId), username.getText(), util.MD5(password.getText())));

        load();
        clean();
    }

    @FXML
    private void deleteAction(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer ce employe ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            load();
            clean();
        }
    }

    @FXML
    private void updateAction(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de modification");
        alert.setContentText("Voulez-vous vraiment modifier ce employe ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Instant instant = Instant.from(date.getValue().atStartOfDay(ZoneId.systemDefault()));
            dt = Date.from(instant);

            if (radH.isSelected()) {
                selectedSexe = "Homme";
            } else if (radF.isSelected()) {
                selectedSexe = "Femme";
            }

            Employe emp = es.findById(index);
            emp.setNom(nom.getText());
            emp.setPrenom(prenom.getText());
            emp.setSexe(selectedSexe);
            emp.setTelephone(telephone.getText());
            emp.setDateNaissance(dt);
            emp.setCin(cin.getText());
            emp.setProfil(ps.findById(selectedProfilId));
            emp.setUsername(username.getText());
            emp.setPassword(util.MD5(password.getText()));
            es.update(emp);
        }
        clean();
        load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        load();

        profil.setOnAction(e -> {
            Profil p = profil.getSelectionModel().getSelectedItem();
            selectedProfilId = p.getId();
        });

        mTable.setOnMousePressed(e -> {
            TablePosition pos = (TablePosition) mTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Employe item = mTable.getItems().get(row);
            index = item.getId();

            nom.setText(item.getNom());
            prenom.setText(item.getPrenom());
            telephone.setText(item.getTelephone());
            cin.setText(item.getCin());

            if (item.getSexe().equals("Homme")) {
                radH.setSelected(true);
            } else if (item.getSexe().equals("Femme")) {
                radF.setSelected(true);
            }

            Date dts = item.getDateNaissance();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(sdf.format(dts), formatter);
            date.setValue(localDate);

            profil.getSelectionModel().select(item.getProfil());

            username.setText(item.getUsername());
        });
    }

    public void clean() {
        nom.clear();
        prenom.clear();
        date.setValue(null);
        radH.setSelected(false);
        radF.setSelected(false);
        telephone.clear();
        cin.clear();
        profil.getSelectionModel().clearSelection();
        username.clear();
        password.clear();
    }

    public void load() {
        profils.clear();
        employes.clear();
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        sexeColumn.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
        profilColumn.setCellValueFactory(new PropertyValueFactory<>("profil"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        profils.addAll(ps.findAll());
        employes.addAll(es.findAll());

        profil.setItems(profils);
        mTable.setItems(employes);
    }

    //clear Fields function
    public void clearFields() {
        nom.clear();
        prenom.clear();
        date.setValue(null);
        radH.setSelected(false);
        radF.setSelected(false);
        telephone.clear();
        cin.clear();
        profil.getSelectionModel().clearSelection();
        username.clear();
        password.clear();
    }
}
