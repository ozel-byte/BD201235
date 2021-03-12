package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.List;

public class TipoMascota implements Serializable {
    private final IntegerProperty idtipo = new SimpleIntegerProperty();
    private final StringProperty especie = new SimpleStringProperty();
    private final StringProperty sexo =new SimpleStringProperty();
    private final StringProperty raza = new SimpleStringProperty();
    private List<Mascota> mascota;

    public TipoMascota(){}

    public TipoMascota(String especie, String sexo, String raza){
        this.especie.setValue(especie);
        this.sexo.setValue(sexo);
        this.raza.setValue(raza);
    }

    public int getIdtipo() {
        return idtipo.get();
    }

    public IntegerProperty idtipoProperty() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo.set(idtipo);
    }

    public String getEspecie() {
        return especie.get();
    }

    public StringProperty especieProperty() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie.set(especie);
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public String getRaza() {
        return raza.get();
    }

    public StringProperty razaProperty() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza.set(raza);
    }

    public List<Mascota> getMascota() {
        return mascota;
    }

    public void setMascota(List<Mascota> mascota) {
        this.mascota = mascota;
    }
}
