package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.List;

public class Dueno implements Serializable
{
    //id, nombre, direccion, telefono, correo
    private final IntegerProperty idDueno = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty direccion = new SimpleStringProperty();
    private final StringProperty telefono = new SimpleStringProperty();
    private final StringProperty correo = new SimpleStringProperty();
    private List<Mascota> mascotaList;

    public Dueno (){}

    public Dueno (String nombre, String direccion, String telefono, String correo)
    {
        this.nombre.setValue(nombre);
        this.direccion.setValue(direccion);
        this.telefono.setValue(telefono);
        this.correo.setValue(correo);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getNombre());
    }

    public int getIdDueno() {
        return idDueno.get();
    }

    public IntegerProperty idDuenoProperty() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno.set(idDueno);
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

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public List<Mascota> getMascotaList() {
        return mascotaList;
    }

    public void setMascotaList(List<Mascota> mascotaList) {
        this.mascotaList = mascotaList;
    }
}