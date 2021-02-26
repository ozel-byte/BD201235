package persistencia;

import java.io.Serializable;

public class Cita implements Serializable {

    private int idcita;
    private String fecha;
    private int idmascota;
    private int idservicio;
    private Mascota mascotaC;

    public Cita (){}

    public Cita (int idcita, String fecha, int idmascota, int idservicio){

        this.idcita = idcita;
        this.fecha = fecha;
        this.idmascota = idmascota;
        this.idservicio = idservicio;

    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public Mascota getMascotaC() {
        return mascotaC;
    }

    public void setMascotaC(Mascota mascotaC) {
        this.mascotaC = mascotaC;
    }
}
