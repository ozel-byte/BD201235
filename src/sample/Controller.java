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
        m.getMascota();

        /*DuenoDAO d = new DuenoDAO();
        d.getDueno();

        d.deleteDueno(1);
        d.getDueno();*/



        /*TipoMascotaDAO tp = new TipoMascotaDAO();
        tp.getTipo();

        tp.deleteTipo(10);
        tp.getTipo2();*/

    }
}
