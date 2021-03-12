package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.List;

public class Mascota implements Serializable {
    private final IntegerProperty idmascota = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private TipoMascota tipoMascota;
    private Dueno dueno;
    private List<Cita> citaList;
    //lista de citas

    public Mascota (){

    }

    public Mascota (Dueno _dueno,TipoMascota _tipoMascota , String _nombre){
        this.nombre.setValue(_nombre);
       this.dueno=_dueno;
       this.tipoMascota=_tipoMascota;

    }

    public int getIdmascota() {
        return idmascota.get();
    }

    public IntegerProperty idmascotaProperty() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota.set(idmascota);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public List<Cita> getCitaList() {
        return citaList;
    }

    public void setCitaList(List<Cita> citaList) {
        this.citaList = citaList;
    }


}
