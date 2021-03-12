package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import persistencia.*;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class controllerGenerarCita {

    @FXML
    private Button btExit;

    @FXML
    private ComboBox<Servicio> elegirServicio;

    @FXML
    private DatePicker fechaCita;

    @FXML
    private ComboBox<Dueno> buscarDueno;

    @FXML
    private ComboBox<Mascota> buscarMascota;

    @FXML
    private Button generarCita;

    controllerHome h;

    @FXML
    public void recibir(controllerHome h){
        this.h = h;
    }

    @FXML
    private void initialize(){
        llenarComboDueno();
        llenarComboServicio();

    }

    @FXML
    private void generarCita(){
        if(fechaCita.getValue() != null && buscarDueno.getValue() != null && elegirServicio.getValue() != null && buscarMascota.getValue() != null){
            CitaDAO citaDAO = new CitaDAO();

            DateTimeFormatter formatte = DateTimeFormatter.ofPattern("dd/LL/yy");
            String fecha = fechaCita.getValue().format(formatte);
            Servicio servicio = elegirServicio.getValue();
            Mascota mascota = buscarMascota.getValue();
            citaDAO.agregar(mascota, servicio, fecha);

            h.llenarListaCitas();



        } else {
            System.out.println("llego aqui 2");
        }
    }

    @FXML
    private void llenarComboDueno(){
        ObservableList<Dueno> items = FXCollections.observableArrayList();
        //ObservableList<String> nombres = FXCollections.observableArrayList();
        DuenoDAO duenoDAO = new DuenoDAO();
        items = duenoDAO.getDueno();

        buscarDueno.setItems(items);
        buscarDueno.valueProperty().addListener((ov, p1, p2) -> {
            System.out.println("Nueva Selección: " + p2);
            System.out.println("Vieja Selección: " + p1);
            llenarComboMascota(p2.getMascotaList());
        });


    }

    @FXML
    private void llenarComboMascota(List<Mascota> duenosmascotaDueno){
        ObservableList<Mascota> mascotas = FXCollections.observableArrayList();
        for (Iterator iterator=duenosmascotaDueno.iterator(); iterator.hasNext();){
            Mascota lista = (Mascota)iterator.next();
            mascotas.add(lista);
        }
        buscarMascota.setItems(mascotas);
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

}
