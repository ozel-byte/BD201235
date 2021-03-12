package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persistencia.*;

import java.time.format.DateTimeFormatter;

public class controllerGenerarCitaUsuariosNuevos {

    @FXML
    private Text nombreCita;

    @FXML
    private Text TipoMascotaCita;

    @FXML
    private Text duenoCita;
    @FXML
    private ComboBox<Servicio> elegirServicio;

    @FXML
    private DatePicker fechaCita;
    @FXML
    private ProgressIndicator loadingGenerarCita;
    @FXML
    private Button generarCitabutton;


    Dueno duenoRegistro;
    Mascota mascotaRegistro;
    TipoMascota tipoMascotaRegistro;
    controllerRegistros cr;

    Task task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            Dueno nuevodueno;
            TipoMascota nuevoTipo;
            Mascota nuevoMascota;

            Servicio se = elegirServicio.getValue();
            DateTimeFormatter formatte = DateTimeFormatter.ofPattern("dd/LL/yy");
            String fecha = fechaCita.getValue().format(formatte);
            DuenoDAO duenoDAO = new DuenoDAO();
         nuevodueno= duenoDAO.agregarDueno(duenoRegistro.getNombre(), duenoRegistro.getDireccion(),duenoRegistro.getTelefono(), duenoRegistro.getCorreo());
            TipoMascotaDAO tipoMascotaDAO = new TipoMascotaDAO();
           nuevoTipo= tipoMascotaDAO.crearTipo(tipoMascotaRegistro);
            MascotaDAO mascotaDAO = new MascotaDAO();
           nuevoMascota= mascotaDAO.agregarMascota(nuevodueno,nuevoTipo,mascotaRegistro.getNombre());
            CitaDAO citaDAO = new CitaDAO();
            citaDAO.agregar(nuevoMascota,se,fecha);
            return null;
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Home.fxml"));
                Parent root = (Parent) loader.load();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                Stage stage2 = (Stage) generarCitabutton.getScene().getWindow();
                stage2.close();
                loadingGenerarCita.setVisible(false);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        @Override
        protected void failed() {
            super.failed();
            System.out.println("fallo al completar");
        }
    };


    @FXML
    private void initialize(){
        ObservableList<Servicio> servicioArray = FXCollections.observableArrayList();
        ServicioDAO servicio = new ServicioDAO();
       servicioArray = servicio.obtenerServicio();
       elegirServicio.setItems(servicioArray);

       elegirServicio.valueProperty().addListener(new ChangeListener<Servicio>() {
           @Override
           public void changed(ObservableValue<? extends Servicio> observableValue, Servicio servicio, Servicio t1) {

           }
       });

    }

    public void parametros(Dueno d, Mascota m, TipoMascota t, controllerRegistros _cr){
        this.cr=_cr;
        this.duenoRegistro=d;
        this.tipoMascotaRegistro=t;
        this.mascotaRegistro=m;

        nombreCita.setText(mascotaRegistro.getNombre());
        duenoCita.setText(duenoRegistro.getNombre());
        TipoMascotaCita.setText(tipoMascotaRegistro.getEspecie());


    }

    @FXML
    public void generarCita(){

    if(elegirServicio.getValue()!=null &&duenoRegistro.getNombre().length()>0 && mascotaRegistro.getNombre().length()>0 && tipoMascotaRegistro.getSexo().length()>0 && fechaCita.getValue()!=null){

       Thread t = new Thread(task);
       t.setDaemon(true);
       t.start();
       loadingGenerarCita.setVisible(true);


    }
    }
}
