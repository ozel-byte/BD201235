package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.List;

public class Servicio implements Serializable {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty tipo = new SimpleStringProperty();
    private final IntegerProperty costo = new SimpleIntegerProperty();
    private List<Cita> cita;


    public Servicio(){

    }
    public Servicio(String _nombreServicio, int _costo){
       this.tipo.setValue(_nombreServicio);
       this.costo.setValue(_costo);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getTipo());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTipo() {
        return tipo.get();
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public int getCosto() {
        return costo.get();
    }

    public IntegerProperty costoProperty() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo.set(costo);
    }

    public List<Cita> getCita() {
        return cita;
    }

    public void setCita(List<Cita> cita) {
        this.cita = cita;
    }
}


/*Ozel_struct*/
