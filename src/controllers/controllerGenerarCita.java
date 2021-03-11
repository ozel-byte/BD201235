package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class controllerGenerarCita {

    @FXML
    private Button btExit;

    @FXML
    private ComboBox<?> elegirServicio;

    @FXML
    private DatePicker fechaCita;

    @FXML
    private ComboBox<?> buscarDueno;

    @FXML
    private ComboBox<?> buscarMascota;

    @FXML
    private Button generarCita;

    @FXML
    private void generarCita(){
        if(fechaCita.getValue() != null){
            System.out.println("llego aqui 1");
        } else {
            System.out.println("llego aqui 2");
        }
    }

}
