package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistencia.Dueno;
import persistencia.DuenoDAO;

public class controllerActualizarDueno {

    @FXML
    private TextField txtnombre;

    @FXML
    private TextField txtcorreo;

    @FXML
    private TextField txttelefono;

    @FXML
    private TextField txtdireccion;

    @FXML
    private Button btExit;


    @FXML
    private Button actualizar;

    controllerHome h;
    Dueno dueno;

    @FXML
    public void recibir(controllerHome h, Dueno dueno){
        this.h = h;
        this.dueno = dueno;

        System.out.println(dueno.getNombre());
        System.out.println(this.dueno.getNombre());
        txtnombre.setText(this.dueno.getNombre());
        txttelefono.setText(this.dueno.getTelefono());
        txtdireccion.setText(this.dueno.getDireccion());
        txtcorreo.setText(this.dueno.getCorreo());
    }

    @FXML
    private void upDate(){

        if(txtnombre.getText().length()>0 && txtcorreo.getText().length()>0 && txtdireccion.getText().length()>0 && txttelefono.getText().length()>0){
            DuenoDAO duenoDAO = new DuenoDAO();
            dueno.setNombre(txtnombre.getText());
            dueno.setCorreo(txtcorreo.getText());
            dueno.setDireccion(txtdireccion.getText());
            dueno.setTelefono(txttelefono.getText());

            duenoDAO.actualizarDueno(dueno);

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Actualizar dueño");
            mensaje.setHeaderText("Se actualizo correctamente");
            mensaje.show();

            h.llenarListaCitas();
            salir();

        } else {

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Error actualizar");
            mensaje.setHeaderText("Llene todos los campos");
            mensaje.show();


        }
    }

    @FXML
    private void salir(){
        Stage stage = (Stage) btExit.getScene().getWindow();
        stage.close();
    }
}
