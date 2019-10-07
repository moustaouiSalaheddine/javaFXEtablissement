/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.AR;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import service.EtudiantService;
import service.ProfilService;

/**
 * FXML Controller class
 *
 * @author mst
 */
public class ChartController implements Initializable {

    ProfilService ps = new ProfilService();
    EtudiantService es = new EtudiantService();
    @FXML
    final Label caption = new Label("");

    @FXML
    private CategoryAxis xAxes = new CategoryAxis();

    @FXML
    private NumberAxis yAxes = new NumberAxis();

    @FXML
    private BarChart<String, Number> barChart = new BarChart<String, Number>(xAxes, yAxes);

    @FXML
    private PieChart pieChart1;
//    }
    @FXML
    ObservableList<PieChart.Data> pieChartData;
    

    public ChartController() {
        this.pieChartData = FXCollections.observableArrayList();
    }

    private void setBarChart() {
//        List<Object[])
//        object[1] =  " select count(e),e.nom from Employe"
//      

        barChart.getData().clear();

        XYChart.Series chartSeries = new XYChart.Series();
        for (String var : ps.getProfilsLibelle()) {
            chartSeries.getData().add(new XYChart.Data(var, ps.getProfilsCountByLibelle(var)));
            System.out.println(" var : " + var);
            System.out.println(" count :  " + ps.getProfilsCountByLibelle(var));
        }

        barChart.getData().addAll(chartSeries);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBarChart();
        caption.setTextFill(Color.DARKORANGE);
        
        caption.setStyle(
                "-fx-font: 24 arial;");
        for (String var : es.getEtudiantsNiveau()) {
            pieChartData.add(new PieChart.Data(var, es.getEtudiantsCountByNiveau(var)));
        }
        for (final PieChart.Data data : pieChart1.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
        }
//        pieChartData.add(new PieChart.Data("Oranges", 25));

        pieChart1.setData(pieChartData);
    }

}
