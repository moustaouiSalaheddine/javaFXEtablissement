/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AR;

import Classe.Etablissement;
import Classe.Profil;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.EtablissementService;
import service.ProfilService;

/**
 * FXML Controller class
 *
 * @author mst
 */
public class EtablissementController implements Initializable {

    EtablissementService ebs = new EtablissementService();
    ObservableList<Etablissement> etablissements = FXCollections.observableArrayList();
    
    private static int idEtablissement;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField region;
    @FXML
    private TextField poursuite;
    @FXML
    private TextField codeEtablissement;
    @FXML
    private TextField telephone;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Etablissement> etablissementTable;
    @FXML
    private TableColumn<Etablissement, String> nomColumn;
    @FXML
    private TableColumn<Etablissement, String> typeColumn;
    @FXML
    private TableColumn<Etablissement, String> regionColumn;
    @FXML
    private TableColumn<Etablissement, String> poursuiteColumn;
    @FXML
    private TableColumn<Etablissement, String> codeEtablissementColumn;
    @FXML
    private TableColumn<Etablissement, String> telephoneColumn;
    
     @FXML
    private void saveEtablissement(ActionEvent e){
        ebs.create(new Etablissement(nom.getText(), type.getText(), region.getText(), poursuite.getText(), codeEtablissement.getText(), telephone.getText()));
        init();
        clearFields();
    }
    
     @FXML
    private void deleteEtablissement(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer ce profil ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ebs.delete(ebs.findById(idEtablissement));
            init();
            clearFields();
        }
    }
    
     @FXML
    private void updateEtablissement(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de modification");
        alert.setContentText("Voulez-vous vraiment modifier ce profil ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Etablissement e = ebs.findById(idEtablissement);
            e.setNom(nom.getText());
            e.setType(type.getText());
            e.setRegion(region.getText());
            e.setPoursuite(poursuite.getText());
            e.setCodeEtablissement(codeEtablissement.getText());
            e.setTelephone(telephone.getText());
            ebs.update(e);
            init();
            clearFields();
        }
    }
    
    private void clearFields(){
        nom.clear();
        region.clear();
        type.clear();
        poursuite.clear();
    }

    private void init() {
        etablissements.clear();
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        poursuiteColumn.setCellValueFactory(new PropertyValueFactory<>("poursuite"));
        codeEtablissementColumn.setCellValueFactory(new PropertyValueFactory<>("codeEtablissement"));
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        etablissements.addAll(ebs.findAll());

        etablissementTable.setItems(etablissements);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
        if (ebs.countEtablissement() == -1) {
            ajouter.setDisable(true);
        }else{
            ajouter.setDisable(false);
        }
        etablissementTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        etablissementTable.setOnMousePressed(e-> {
            TablePosition pos = (TablePosition) etablissementTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Etablissement item = etablissementTable.getItems().get(row);
            idEtablissement = item.getId();
            
            nom.setText(item.getNom());
            type.setText(item.getType());
            poursuite.setText(item.getPoursuite());
            region.setText(item.getRegion());
            codeEtablissement.setText(item.getCodeEtablissement());
            telephone.setText(item.getTelephone());
        });
    }

}
