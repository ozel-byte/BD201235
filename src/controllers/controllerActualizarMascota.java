package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import persistencia.Mascota;
import persistencia.MascotaDAO;
import persistencia.TipoMascota;
import persistencia.TipoMascotaDAO;

public class controllerActualizarMascota {

    @FXML
    private TextField textNombre;

    @FXML
    private RadioButton checkH;

    @FXML
    private RadioButton checkM;

    @FXML
    private TextField textEspecie;

    @FXML
    private TextField razaEspecie;

    controllerHome ch;
    Mascota mascota;
    TipoMascota tipoMascotaupdate;
    String selecioncheck;

    @FXML
    private void initialize(){
        checkH.setSelected(true);
        selecioncheck=checkH.getText();
        checkH.setOnAction(actionEvent -> {
            selecioncheck=checkH.getText();
            checkM.setSelected(false);
        });
        checkM.setOnAction(actionEvent -> {
            selecioncheck=checkM.getText();
            checkH.setSelected(false);
        });
    }
    @FXML
    public void recibirParametros(controllerHome _ch,Mascota _mascota){
        this.ch=_ch;
        this.mascota=_mascota;
        ObservableList<TipoMascota> tipoArray = FXCollections.observableArrayList();
        TipoMascotaDAO tipoMascotaDAO = new TipoMascotaDAO();
      tipoArray = tipoMascotaDAO.getTipo2();
        textNombre.setText(mascota.getNombre());
        for (int i=0; i<tipoArray.size(); i++){
            if (tipoArray.get(i).getIdtipo() == mascota.getTipoMascota().getIdtipo()){
                textEspecie.setText(tipoArray.get(i).getEspecie());
                razaEspecie.setText(tipoArray.get(i).getRaza());
                tipoMascotaupdate=tipoArray.get(i);
            }
        }



    }

    @FXML
    public void actualizarDatos(){
        if (textNombre.getText().length()>0 && textEspecie.getText().length()>0 && razaEspecie.getText().length()>0 && selecioncheck.length()>0){
            TipoMascotaDAO tipoMascotaDAO = new TipoMascotaDAO();
            tipoMascotaupdate.setSexo(selecioncheck);
            tipoMascotaupdate.setRaza(razaEspecie.getText());
            tipoMascotaupdate.setEspecie(textEspecie.getText());
            tipoMascotaDAO.updateTipo(tipoMascotaupdate);
            MascotaDAO mascotaDAO = new MascotaDAO();
            mascota.setNombre(textNombre.getText());
            mascotaDAO.actualizarMascota(mascota);
        }
    }

}
