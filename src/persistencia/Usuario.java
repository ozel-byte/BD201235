package persistencia;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String password;
    private String status;

    public Usuario (){}

    public Usuario (String username, String password,String status){
        this.nombre = username;
        this.password = password;
        this.status=status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
