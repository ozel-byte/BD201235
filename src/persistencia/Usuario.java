package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Usuario implements Serializable {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty rol = new SimpleStringProperty();

    public Usuario (){

    }

    public Usuario (String username, String password, String status, String rol){
        this.nombre.setValue(username);
        this.password.setValue(password);
        this.status.setValue(status);
        this.rol.setValue(rol);
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

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getRol() {
        return rol.get();
    }

    public StringProperty rolProperty() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol.set(rol);
    }
}
