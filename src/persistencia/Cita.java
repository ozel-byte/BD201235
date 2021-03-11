package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Cita implements Serializable {

    private final IntegerProperty idcita = new SimpleIntegerProperty();
    private final StringProperty fecha = new SimpleStringProperty();
    private Mascota mascotaC;
    private Servicio servicioC;

    public Cita (){}

    public Cita (String fecha){
        this.fecha.setValue(fecha);
    }

    public int getIdcita() {
        return idcita.get();
    }

    public IntegerProperty idcitaProperty() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita.set(idcita);
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public Mascota getMascotaC() {
        return mascotaC;
    }

    public void setMascotaC(Mascota mascotaC) {
        this.mascotaC = mascotaC;
    }

    public Servicio getServicioC() {
        return servicioC;
    }

    public void setServicioC(Servicio servicioC) {
        this.servicioC = servicioC;
    }
}
