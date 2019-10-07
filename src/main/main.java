package main;

import Classe.Employe;
import Classe.Profil;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.EmployeService;
import service.ProfilService;
import util.HibernateUtil;

/**
 *
 * @author mst
 */
public class main extends Application {

    /*
        background : #414d5b;
        background-button : #546070;
        background-button-hover : #f3a978;
    
        UTF-8 : URL(hibernate.cfg) = &amp;useunicode=yes&amp;characterEncoding=UTF-8
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/AuthentificationView.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/view/AR/DashboardView.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().openSession();
        EmployeService es = new EmployeService();
        Employe emp = new  Employe();
//        emp = es.CheckLogin("admin");
//
//        System.out.println("emp : "+ emp);
        launch(args);
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
