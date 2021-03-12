package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import persistencia.*;

import java.time.format.DateTimeFormatter;

public class controllerActualizarCita {
    @FXML
    private ComboBox<Servicio> elegirServicio;

    @FXML
    private DatePicker fechaCita;

    @FXML
    private Button btExit;

    @FXML
    private Text good;

    @FXML
    private Text fail;

    controllerHome hh;
    Cita citaA ;

    @FXML
    private void initialize(){
        llenarComboServicio();
    }

    @FXML
    public void recibir(controllerHome h, Cita cita){
        hh = h;
        citaA = cita;
        //h.setCita(cita);

    }


   @FXML
    private void ctualizarCita(){


        if(fechaCita.getValue() != null && elegirServicio.getValue() != null){
            fail.setVisible(false);
            citaA.setFecha(fechaCita.getValue().format(DateTimeFormatter.ofPattern("dd/LL/yy")));
            citaA.setServicioC(elegirServicio.getValue());

            CitaDAO citaDAO = new CitaDAO();
            citaDAO.actualizar(citaA);

            hh.llenarListaCitas();
            limpiar();
            good.setVisible(true);

        }else {
            good.setVisible(false);
            fail.setVisible(true);
            System.out.println("Los dos cambos deben estar llenos");
        }
    }

    @FXML
    private void llenarComboServicio(){
        ObservableList<Servicio> items = FXCollections.observableArrayList();
        ServicioDAO servicioDAO = new ServicioDAO();

        items = servicioDAO.obtenerServicio();
        elegirServicio.setItems(items);

    }

    @FXML
    private void salir(){
        Stage stage = (Stage) btExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void limpiar(){
        fechaCita.setValue(null);
        elegirServicio.setValue(null);
        llenarComboServicio();
    }

    //Kevin

}
