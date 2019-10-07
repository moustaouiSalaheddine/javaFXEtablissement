/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AR;

import Classe.Employe;
import Classe.Etablissement;
import Classe.Etudiant;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import service.EmployeService;
import service.EtablissementService;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author mst
 */
public class RechercheEtudiantController implements Initializable {

    private static List<Etudiant> etud = null;
    // Services
    EtudiantService es = new EtudiantService();
    EtablissementService ebs = new EtablissementService();
    EmployeService ems = new EmployeService();
    // Lists
    ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
    ObservableList<Etudiant> etudiants2 = FXCollections.observableArrayList();
    ObservableList<Etablissement> etablissements = FXCollections.observableArrayList();
    // inner static variable
    private static int idEtablissement;
    private static int idEtudiant;
    Date dateN = new Date();
    // Fields
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private ComboBox<Etablissement> etablissement;
    @FXML
    private TableView<Etudiant> tableEtudiants;
    @FXML
    private TableColumn<Etudiant, String> nomColumn;
    @FXML
    private TableColumn<Etudiant, String> prenomColumn;
    @FXML
    private TableColumn<Etudiant, LocalDate> dateNaissanceColumn;
    @FXML
    private TableColumn<Etudiant, String> codeNationalColumn;
    @FXML
    private TableColumn<Etudiant, String> niveauEtudeColumn;
    @FXML
    private TableColumn<Etudiant, String> decisionConseilColumn;
    @FXML
    private TableColumn<Etudiant, Etablissement> etablissementColumn;

    @FXML
    private void showReport(ActionEvent e) throws JRException {
//        String reportSrcFile = "D:\\Desktop\\OFPPT\\offshoring\\JavaFX\\javaFXEtablissement\\src\\report/CertificatReport.jrxml";
        String reportSrcFile = "src/report/CertificatReport.jrxml";

        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for report
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        //  nomDerecteur etablissement  date commenter dateFin nomecomplte ville dateNaissance codeEtude codeNational niveauEtude

        Etablissement etablissement = ebs.findById(1);
        Etudiant etudiant = es.findById(idEtudiant);
        // employe == Directeur
        Employe employe = ems.findById(1);
        Date date = new Date();
        Date dateF = etudiant.getDateFin();
        Date dateN = etudiant.getDateNaissance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dM = sdf.format(date);
        System.out.println("date Maintenant : "+ dM);
        String dF = sdf.format(dateF);
        System.out.println("date Fin : " + dF);
        String dN = sdf.format(dateN);
        System.out.println("date Fin : " + dN);
//        LocalDate localDateM = LocalDate.parse(sdf.format(date), formatter);
//        LocalDate localDateF = LocalDate.parse(sdf.format(dateF), formatter);
        
        parameters.put("etablissement", etablissement.getNom());
        parameters.put("nomecomplte", etudiant.getNom());
        parameters.put("nomDerecteur", employe.getNom() + " " + employe.getPrenom());
        parameters.put("date", dM.toString());
        parameters.put("dateNaissance", dN.toString());
        parameters.put("commenter", etudiant.getDecisionConseil());
        parameters.put("dateFin", dF.toString());
        parameters.put("ville", etudiant.getLieuNaissane());
        parameters.put("codeEtude", etudiant.getNumeroInscription());
        parameters.put("codeNational", etudiant.getCodeNational());
        parameters.put("niveauEtude", etudiant.getNiveauEtude());

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        JFrame jFrame = new JFrame();
//        viewer.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);
//        viewer.setVisible(true);
        jFrame.add(viewer);
        jFrame.setSize(700, 500);
        jFrame.setVisible(true);
        System.out.print("Done!");
    }

    // FXML Method CRUD
    @FXML
    private void searchNom(KeyEvent e) {
        System.out.println("txt : " + nom.getText());
        if (nom.getText().equals("")) {
            load();
        } else {
            etud = es.findByLastName(nom.getText());
        }

        loadSearch(etud);
        if (e.getCode() == KeyCode.ENTER) {
            clean();
        }
    }

    @FXML
    private void searchPrenom(KeyEvent e) {
        System.out.println("txt0000000 : " + prenom.getText());
        if (nom.getText().equals("")) {
            System.out.println("txt1111 : " + prenom.getText());
            etud = es.findByFirstName(prenom.getText());
            System.out.println("txt22222 : " + prenom.getText());
        } else if (prenom.getText() == "" && nom.getText() == "") {
            load();
        }

        loadSearch(etud);
        if (e.getCode() == KeyCode.ENTER) {
            clean();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        tableEtudiants.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableEtudiants.setOnMousePressed(e -> {
            TablePosition pos = (TablePosition) tableEtudiants.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Etudiant item = tableEtudiants.getItems().get(row);
            idEtudiant = item.getId();

            nom.setText(item.getNom());
//            prenom.setText(item.getPrenom());
        });
    }

    public void clean() {
        nom.clear();
        prenom.clear();
    }

    public void load() {
        etudiants.clear();
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        codeNationalColumn.setCellValueFactory(new PropertyValueFactory<>("codeNational"));
        etablissementColumn.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        niveauEtudeColumn.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
        decisionConseilColumn.setCellValueFactory(new PropertyValueFactory<>("decisionConseil"));

        etudiants.addAll(es.findAll());

        tableEtudiants.setItems(etudiants);
    }

    public void loadSearch(List<Etudiant> etud) {
        etudiants2.clear();
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        codeNationalColumn.setCellValueFactory(new PropertyValueFactory<>("codeNational"));
        etablissementColumn.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        niveauEtudeColumn.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));

        if (etud != null) {
            etudiants2.addAll(etud);
        }

        tableEtudiants.setItems(etudiants2);
    }
}
