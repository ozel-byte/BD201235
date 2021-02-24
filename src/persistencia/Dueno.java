package persistencia;

import java.io.Serializable;

public class Dueno implements Serializable
{
    //id, nombre, direccion, telefono, correo
    private int idDueno;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Dueno (){}

    public Dueno (int idDueno, String nombre, String direccion, String telefono, String correo)
    {
        this.idDueno = idDueno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.telefono = correo;
    }
}