package persistencia;

import java.io.Serializable;
import java.util.ArrayList;

public class Servicio implements Serializable {
    private int id;
    private String tipo;
    private int costo;
    private ArrayList<Cita> cita;


    public Servicio(){

    }
    public Servicio(String _nombreServicio, int _costo){
       this.tipo=_nombreServicio;
       this.costo=_costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public ArrayList<Cita> getCita() {
        return cita;
    }

    public void setCita(ArrayList<Cita> cita) {
        this.cita = cita;
    }
}


/*Ozel_struct*/
