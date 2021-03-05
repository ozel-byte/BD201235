package persistencia;

import java.io.Serializable;
import java.util.List;

public class TipoMascota implements Serializable {
    private int idtipo;
    private String especie;
    private String sexo;
    private String raza;
    private List<Mascota> mascota;

    public TipoMascota(){}

    public TipoMascota(String especie, String sexo, String raza){
        this.especie = especie;
        this.sexo = sexo;
        this.raza = raza;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public List<Mascota> getMascota() {
        return mascota;
    }

    public void setMascota(List<Mascota> mascota) {
        this.mascota = mascota;
    }
}
