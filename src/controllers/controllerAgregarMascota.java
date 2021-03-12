package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import persistencia.*;

public class controllerAgregarMascota {

    @FXML
    private TextField nombreMascota;

    @FXML
    private TextField especieMascota;

    @FXML
    private TextField razaMascota;

    @FXML
    private RadioButton checkH;

    @FXML
    private RadioButton checkM;

    @FXML
    private ComboBox<Dueno> comboElegirDueno;

    @FXML
    private Text bien;

    @FXML
    private Text mal;

    String sexo="";
    TipoMascota tipoMascota;
    TipoMascota tpr;
    controllerHome h;

    @FXML
    public void recibir(controllerHome controllerHome){
        this.h = controllerHome;
    }

    @FXML
    private void initialize(){
        checkM.setSelected(true);
        checkH.setOnAction(event -> {
            checkM.setSelected(false);
        });
        checkM.setOnAction(actionEvent -> {
            checkH.setSelected(false);
        });
        llenaCombo();
    }

    @FXML
    void guardarMascota() {
        if (checkH.isSelected()){
            bien.setVisible(false);
            mal.setVisible(true);
            checkM.setSelected(false);
            sexo = checkH.getText();
            System.out.println(sexo);
        }else if (checkM.isSelected()){
            bien.setVisible(false);
            mal.setVisible(true);
            checkH.setSelected(false);
            sexo = checkM.getText();
            System.out.println(sexo);
        }
        if(sexo.length()>0 && nombreMascota.getText().length()>0 && especieMascota.getText().length()>0 && razaMascota.getText().length()>0 && comboElegirDueno.getValue() != null){
            mal.setVisible(false);

            TipoMascota tipo = new TipoMascota();

            tipo.setEspecie(especieMascota.getText());
            tipo.setRaza(razaMascota.getText());
            tipo.setSexo(sexo);
            TipoMascotaDAO tp = new TipoMascotaDAO();
            tpr = tp.crearTipo(tipo);

            MascotaDAO mascotaDAO = new MascotaDAO();
            mascotaDAO.agregarMascota(comboElegirDueno.getValue(), tpr, nombreMascota.getText());
            h.rellenarListaMascota();
            bien.setVisible(true);

        } else {
            bien.setVisible(false);
            mal.setVisible(true);
        }
    }

    @FXML
    private void llenaCombo(){
        ObservableList<Dueno> items = FXCollections.observableArrayList();

        DuenoDAO duenoDAO = new DuenoDAO();
        items = duenoDAO.getDueno();
        comboElegirDueno.setItems(items);

    }

}
