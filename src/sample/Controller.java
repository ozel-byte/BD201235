package sample;

import javafx.fxml.FXML;
import persistencia.DuenoDAO;
import persistencia.MascotaDAO;
import persistencia.TipoMascota;
import persistencia.TipoMascotaDAO;

public class Controller {

    @FXML
    private void initialize(){
        MascotaDAO m = new MascotaDAO();
        m.getDueno();

    }
}
