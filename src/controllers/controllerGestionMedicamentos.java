package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import persistencia.Medicamento;
import persistencia.MedicamentoDAO;

import java.time.LocalDate;
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

    controllerHome home;
    int opcion;
    MedicamentoDAO m;
    Medicamento medicamentoG;

    @FXML
    private void initialize(){
    }

    @FXML
    public void recibir(controllerHome h, int op, MedicamentoDAO mm, Medicamento medicamento){
        home = h;
        opcion = op;
        medicamentoG = medicamento;
        m = mm;
        if(opcion == 1){
            imgAgregar.setVisible(true);
            addMedicamento.setVisible(true);
            btAgregar.setVisible(true);
            imgModificar.setVisible(false);
            updateMed.setVisible(false);
            btModificar.setVisible(false);
            addOk.setText("Agregado correctamente");
        } else {
            imgAgregar.setVisible(false);
            imgAgregar.setVisible(false);
            addMedicamento.setVisible(false);
            imgModificar.setVisible(true);
            updateMed.setVisible(true);
            btModificar.setVisible(true);
            nombre.setText(medicamento.getNombre());
            codigo.setText(String.valueOf(medicamento.getCodigo()));
            sustancia.setText(medicamento.getSustancia());
            fecha.setValue(LocalDate.parse(medicamento.getFecha_Cad(), DateTimeFormatter.ofPattern("dd/LL/yy")));
            addOk.setText("Actualizado correctamente");
        }
    }


    @FXML
    private void gestion(){
        if(nombre.getText().length()>0 && codigo.getText().length()>0 && sustancia.getText().length()>0 && fecha.getValue() != null ){
            addFail.setVisible(false);
            DateTimeFormatter formatte = DateTimeFormatter.ofPattern("dd/LL/yy");
            if(opcion == 1){
                m.agregar(nombre.getText(),Integer.parseInt(codigo.getText()), sustancia.getText(), fecha.getValue().format(formatte));
                limpiar();
                home.obeserbaleMedicamentos();
            }else {
                medicamentoG.setNombre(nombre.getText());
                medicamentoG.setCodigo(Integer.parseInt(codigo.getText()));
                medicamentoG.setSustancia(sustancia.getText());
                medicamentoG.setFecha_Cad(fecha.getValue().format(DateTimeFormatter.ofPattern("dd/LL/yy")));

                m.actualizar(medicamentoG);
                limpiar();
                System.out.println("Si llego aqui");
            }
            addOk.setVisible(true);

        } else {
            addFail.setVisible(true);
            addOk.setVisible(false);
        }
    }

    @FXML
    private void salir(){
        Stage stage = (Stage) btExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void limpiar(){
        nombre.setText(null);
        codigo.setText(null);
        sustancia.setText(null);
        fecha.setValue(null);
    }

}
