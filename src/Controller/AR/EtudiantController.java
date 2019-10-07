/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AR;

import Classe.Employe;
import Classe.Etablissement;
import Classe.Etudiant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import progressBar.RingProgressIndicator;
import service.EmployeService;
import service.EtablissementService;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author mst
 */
public class EtudiantController implements Initializable {

    // init Employe
    Employe emp = new Employe();
    // Services
    EtudiantService es = new EtudiantService();
    EtablissementService ebs = new EtablissementService();
    EmployeService ems = new EmployeService();
    Employe employe = ems.findById(getEmployeConnected());
    // Lists
    ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
    ObservableList<Etablissement> etablissements = FXCollections.observableArrayList();
    // inner static variable
    private static int idEtablissement;
    private static int idEtudiant;
    private static int countRowsFile;
//    private static List<String> listString= new ArrayList<>();
    Date dateN = new Date();
    Date dateF = new Date();
    // Fields
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker dateNaissance;
    @FXML
    private TextField codeNational;
    @FXML
    private TextField niveauEtude;
    @FXML
    private TextField decisionConseil;
    @FXML
    private TextField numeroDossier;
    @FXML
    private TextField lieuNaissane;
    @FXML
    private TextField numeroInscription;
    @FXML
    private DatePicker dateFin;
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
    private TableColumn<Etudiant, String> numeroDossierColumn;
    @FXML
    private TableColumn<Etudiant, String> lieuNaissanceColumn;
    @FXML
    private TableColumn<Etudiant, String> numeroInscriptionColumn;
    @FXML
    private TableColumn<Etudiant, LocalDate> dateFinColumn;
    @FXML
    private TableColumn<Etudiant, Etablissement> etablissementColumn;

    // FXML Method CRUD
    @FXML
    private void saveEtudiant(ActionEvent e) {
        Instant instant = Instant.from(dateNaissance.getValue().atStartOfDay(ZoneId.systemDefault()));
        dateN = Date.from(instant);
        Instant instantF = Instant.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()));
        dateF = Date.from(instantF);
//        System.out.println(nom.getText()+" "+ prenom.getText()+" "+ dateN+" "+ codeNational.getText()+" "+ niveauEtude.getText()+" "+ ebs.findById(idEtablissement));

//        es.create(new Etudiant(nom.getText(), dateN, codeNational.getText(), niveauEtude.getText(), decisionConseil.getText(), Integer.valueOf(numeroDossier.getText()), ebs.findById(idEtablissement)));
        es.create(new Etudiant(nom.getText(), dateN, codeNational.getText(), niveauEtude.getText(), decisionConseil.getText(), Integer.valueOf(numeroDossier.getText()), lieuNaissane.getText(), dateF, numeroInscription.getText(), employe.getEtablissement()));
        load();
        clean();
    }

    @FXML
    private void updateEtudiant(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de modification");
        alert.setContentText("Voulez-vous vraiment modifier ce employe ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Instant instant = Instant.from(dateNaissance.getValue().atStartOfDay(ZoneId.systemDefault()));
            dateN = Date.from(instant);
            Instant instantF = Instant.from(dateFin.getValue().atStartOfDay(ZoneId.systemDefault()));
            dateF = Date.from(instantF);

            Etudiant etud = es.findById(idEtudiant);
            etud.setNom(nom.getText());
            etud.setCodeNational(codeNational.getText());
            etud.setDateNaissance(dateN);
            etud.setDateFin(dateF);
            etud.setNiveauEtude(niveauEtude.getText());
            etud.setLieuNaissane(lieuNaissane.getText());
            etud.setDecisionConseil(decisionConseil.getText());
            etud.setNumeroDossier(Integer.parseInt(numeroDossier.getText()));
            etud.setEtablissement(ebs.findById(idEtablissement));
            es.update(etud);
        }
        clean();
        load();
    }

    @FXML
    private void deleteEtudiant(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer ce employe ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(idEtudiant));
            load();
            clean();
        }
    }

    // import Etudians From Excel
    @FXML
    private void importEtudiant(ActionEvent e) throws FileNotFoundException, IOException {
//        File myFile = new File("E:\\etudiants.xlsx");

        FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(new File("data"));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"),
                new FileChooser.ExtensionFilter("Excel2 Files", "*.xls"),
                new FileChooser.ExtensionFilter("Word Files", "*.docs"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
//        FileInputStream fis = new FileInputStream(myFile);
        FileInputStream fis = null;
        if (selectedFile.getName().endsWith("xlsx") || selectedFile.getName().endsWith("xlsx")) {
            System.out.println("Ok!!!!!!!");
            fis = new FileInputStream(selectedFile);
            // Finds the workbook instance for XLSX file
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

            // Return first sheet from the XLSX workbook
            XSSFSheet mySheet = myWorkBook.getSheetAt(1);

            //XSSFRow row = mySheet.getRow(1);
            //XSSFCell cell = row.getCell(2);
            for (int i = 1; i < mySheet.getLastRowNum(); i++) {
                XSSFRow rowx = mySheet.getRow(i);
                //for(int j=1;j<rowx.getLastCellNum();j++) {
                XSSFCell cell2 = rowx.getCell(2);
                // Rmplir Count Rows in Excel File
        
                if (cell2 == null || cell2.getCellType() == CellType.BLANK) {
                    
                    countRowsFile = i;
                    break;
                }
            }
            RingProgressIndicator ringProgressIndicator = new RingProgressIndicator();
            ringProgressIndicator.setRingWidth(200);
            ringProgressIndicator.makeIndeterminate();

            StackPane root = new StackPane();
            root.getChildren().add(ringProgressIndicator);
            Scene scene = new Scene(root, 300, 250);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.show();
            new WorkerThread(ringProgressIndicator).start();

            for (int i = 1; i < mySheet.getLastRowNum(); i++) {
                XSSFRow rowx = mySheet.getRow(i);
                //for(int j=1;j<rowx.getLastCellNum();j++) {
                XSSFCell cell2 = rowx.getCell(2);
                // Rmplir Count Rows in Excel File
                countRowsFile = mySheet.getLastRowNum();
                // TODO 
                if (cell2 == null || cell2.getCellType() == CellType.BLANK) {
                    continue;
                } else {
//                listString = null;

                    List<String> listString = new ArrayList<>();
                    XSSFCell cell1 = rowx.getCell(1);
                    System.out.print(cell1.getStringCellValue() + "\t");
                    System.out.print(cell2.getStringCellValue().toString() + "\t");
                    XSSFCell cell3 = rowx.getCell(3);
                    System.out.print(cell3.getDateCellValue() + "\t");
                    XSSFCell cell4 = rowx.getCell(4);
                    System.out.print(cell4.getStringCellValue().toString() + "\t");
                    XSSFCell cell5 = rowx.getCell(5);
                    System.out.print(cell5.getStringCellValue().toString() + "\t");
                    XSSFCell cell6 = rowx.getCell(6);
                    System.out.print(cell6.getNumericCellValue() + "\t");
                    XSSFCell cell7 = rowx.getCell(7);
                    System.out.print(cell7.getNumericCellValue() + "\t");
                    XSSFCell cell8 = rowx.getCell(8);
                    System.out.print(cell8.getStringCellValue().toString() + "\t");
                    XSSFCell cell9 = rowx.getCell(9);
                    System.out.print(cell9.getNumericCellValue());
                    System.out.println("");
//                    for (String var : SeparatFirstLastName(String.valueOf(cell2))) {
//                        listString.add(var);
//                    }
//                    System.out.println("nom : " + listString.get(0));
//                    System.out.println("prenom : " + listString.get(1));
                    Date date = cell3.getDateCellValue();
                    if (!cell1.equals("")) {
                        cell9.setCellType(CellType.STRING);
                        int value = Integer.parseInt(cell9.getStringCellValue());
                        System.out.println("numero : " + cell1);
                        System.out.println("salah : :   " + cell2.getStringCellValue() + " " + cell3.getDateCellValue() + " " + String.valueOf(cell1) + " " + String.valueOf(cell5) + " " + value);
//                        es.create(new Etudiant(cell2.getStringCellValue(), cell3.getDateCellValue(), String.valueOf(cell1), String.valueOf(cell5), String.valueOf(cell8), String.valueOf(cell4), cell7.getDateCellValue(), Integer.parseInt(cell9.getStringCellValue()), null));
                        es.create(new Etudiant(cell2.getStringCellValue(), cell3.getDateCellValue(), String.valueOf(cell6), String.valueOf(cell5), String.valueOf(cell8), Integer.parseInt(cell9.getStringCellValue()), String.valueOf(cell4), cell7.getDateCellValue(), cell1.getStringCellValue(), employe.getEtablissement()));
                        load();
                    } else {
                        // TODO msg : this file is not Excel file .xls .xlsx
                        System.out.println("hello");
                    }
                }

                //}
            }

        } else {
            System.out.println("No!!!!!!!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        tableEtudiants.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        etablissement.setOnAction(e -> {
            Etablissement etab = etablissement.getSelectionModel().getSelectedItem();
            idEtablissement = etab.getId();
        });
        tableEtudiants.setOnMousePressed(e -> {
            TablePosition pos = (TablePosition) tableEtudiants.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Etudiant item = tableEtudiants.getItems().get(row);
            idEtudiant = item.getId();
            Date dts = item.getDateNaissance();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            LocalDate localDateN = LocalDate.parse(sdf.format(dts), formatter);
            Date dtF = item.getDateFin();
            DateTimeFormatter formatterF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            SimpleDateFormat sdfF = new SimpleDateFormat("dd-MM-yyyy");
            LocalDate localDateF = LocalDate.parse(sdfF.format(dtF), formatterF);

            nom.setText(item.getNom());
            codeNational.setText(item.getCodeNational());
            niveauEtude.setText(item.getNiveauEtude());
            numeroDossier.setText(String.valueOf(item.getNumeroDossier()));
            decisionConseil.setText(item.getDecisionConseil());
            dateNaissance.setValue(localDateN);
            dateFin.setValue(localDateF);
            lieuNaissane.setText(item.getLieuNaissane());
            etablissement.getSelectionModel().select(item.getEtablissement());
        });
    }

    public void clean() {
        nom.clear();
        prenom.clear();
        dateNaissance.setValue(null);
        etablissement.getSelectionModel().clearSelection();
        codeNational.clear();
        niveauEtude.clear();
    }

    public void load() {
        etablissements.clear();
        etudiants.clear();
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        codeNationalColumn.setCellValueFactory(new PropertyValueFactory<>("codeNational"));
        etablissementColumn.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        niveauEtudeColumn.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
        decisionConseilColumn.setCellValueFactory(new PropertyValueFactory<>("decisionConseil"));
        numeroDossierColumn.setCellValueFactory(new PropertyValueFactory<>("numeroDossier"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        lieuNaissanceColumn.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        numeroInscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("numeroInscription"));

        etablissements.addAll(ebs.findAll());
        etudiants.addAll(es.findAll());

        etablissement.setItems(etablissements);
        tableEtudiants.setItems(etudiants);
    }

    // Session return id Connected
    public int getEmployeConnected() {
        Preferences userPreferences = Preferences.userRoot();

        String idEmp = userPreferences.get("currentUserId", String.valueOf(emp.getId()));
        if (idEmp != null) {
            return Integer.parseInt(idEmp);
        } else {
            return -1;
        }
    }

    class WorkerThread extends Thread {

        RingProgressIndicator rpi;
        int progress = 0;

        public WorkerThread(RingProgressIndicator rpi) {
            this.rpi = rpi;
        }

        @Override
        public void run() {

            while (true) {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EtudiantController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    rpi.setProgress(progress);
                });
                progress += 1;
                if (progress > countRowsFile) {
                    break;

                }
            }
        }

    }
//    public static List<String> SeparatFirstLastName(String str) {
//        List<String> list = new ArrayList<>();
//        int a = -1;
//        char cNom;
//        char cPrenom;
//        String nom = "";
//        String prenom = "";
////        String str = buff.readLine(); 
//        for (int i = 0; i < str.length(); ++i) {
//            if (a == -1) {
//                nom += cNom = str.charAt(i);
//                int asci = (int) cNom;
//                if (asci == 32) {
//                    a = 1;
//                }
//            } else {
//                prenom += cPrenom = str.charAt(i);
//            }
//
//        }
//        list.add(nom);
//        list.add(prenom);
//        return list;
//    }
}
