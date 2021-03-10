package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import persistencia.Medicamento;
import persistencia.MedicamentoDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class controllerHome {

    @FXML
    private AnchorPane anchorHome;

    @FXML
    private DatePicker fecha;

    @FXML
    private ListView<?> listHome;

    @FXML
    private AnchorPane anchorMedi;

    @FXML
    private ListView<String> listMedicamentos;

    @FXML
    private Button agregarMed;

    @FXML
    private AnchorPane anchorMascota;

    @FXML
    private ListView<?> listMascota;

    @FXML
    private AnchorPane anchorDuenos;

    @FXML
    private ListView<?> listDuenos;

    @FXML
    private Button agendarCita;

    @FXML
    private Button registro;

    @FXML
    private ImageView Home;

    @FXML
    private ImageView pets;

    @FXML
    private ImageView medicamentos;

    @FXML
    private ImageView users;

    @FXML
    private Button btHome;

    @FXML
    private Button btMascota;

    @FXML
    private Button btDueno;

    @FXML
    private Button btMedi;

    @FXML
    private void initialize(){
        ArrayList<String> p = new ArrayList<>();

        MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        List<Medicamento> m = medicamentoDAO.getMedicamento();

        for (Iterator iterator = m.iterator(); iterator.hasNext();) {
            Medicamento dao = (Medicamento) iterator.next();
            String p2 = dao.getNombre() + "     " + dao.getCodigo() + "     " + dao.getSustancia() + "     " + dao.getFecha_Cad();
            p.add(p2);

        }

        //ObservableList<Medicamento> names = FXCollections.observableArrayList(m);
        //listMedicamentos.setItems(names);

        ObservableList<String> name = FXCollections.observableArrayList(p);
        listMedicamentos.setItems(name);
        //ListView<String> listView = new ListView<String>(name);
    }

    @FXML
    private void btHome(){
        anchorHome.setVisible(true);
        anchorMascota.setVisible(false);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
    }

    @FXML
    private void btMascota(){
        anchorHome.setVisible(false);
        anchorMascota.setVisible(true);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
    }

    @FXML
    private void btDueno(){
        anchorHome.setVisible(false);
        anchorMascota.setVisible(false);
        anchorDuenos.setVisible(true);
        anchorMedi.setVisible(false);
        agregarMed.setVisible(false);
    }

    @FXML
    private void btMedicamento(){
        anchorHome.setVisible(false);
        anchorMascota.setVisible(false);
        anchorDuenos.setVisible(false);
        anchorMedi.setVisible(true);
        agregarMed.setVisible(true);
    }

    @FXML
    private void agregaMedi(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/GestionMedicamentos.fxml"));
            //Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void registrar(){

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Registros.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    private void generarCita(){

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GenerarCita.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


}
