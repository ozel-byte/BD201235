package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import persistencia.MedicamentoDAO;

import java.time.format.DateTimeFormatter;

public class controllerGestionMedicamentos {

    @FXML
    private ImageView imgAgregar;

    @FXML
    private ImageView imgModificar;

    @FXML
    private Text addMedicamento;

    @FXML
    private Text updateMed;

    @FXML
    private TextField nombre;

    @FXML
    private TextField codigo;

    @FXML
    private TextField sustancia;

    @FXML
    private Button btAgregar;

    @FXML
    private Button btModificar;

    @FXML
    private DatePicker fecha;

    @FXML
    private Button btExit;

    @FXML
    private Text addOk;

    @FXML
    private Text addFail;

    @FXML
    private void agregar(){
        imgAgregar.setVisible(true);
        addMedicamento.setVisible(true);
        imgModificar.setVisible(false);
        updateMed.setVisible(false);

        if(nombre.getText().length()>0 && codigo.getText().length()>0 && sustancia.getText().length()>0 && fecha.getValue() != null ){
            addFail.setVisible(false);
            DateTimeFormatter formatte = DateTimeFormatter.ofPattern("dd/LL/yy");
            MedicamentoDAO m = new MedicamentoDAO();
            m.agregar(nombre.getText(),Integer.parseInt(codigo.getText()), sustancia.getText(), fecha.getValue().format(formatte));
            addOk.setVisible(true);

        } else {
            addFail.setVisible(true);
            addOk.setVisible(false);
        }
    }

    @FXML
    private void modificar(){
        imgAgregar.setVisible(false);
        addMedicamento.setVisible(false);
        imgModificar.setVisible(true);
        updateMed.setVisible(true);
    }

    @FXML
    private void salir(){
        Stage stage = (Stage) btExit.getScene().getWindow();
        stage.close();
    }

}
