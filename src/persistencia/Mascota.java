package persistencia;

import java.io.Serializable;
import java.util.List;

public class Mascota implements Serializable {
    private int idmascota;
    private String nombre;
    private int iddueno;
    private int idtipo;
    private TipoMascota tipoMascota;
    private Dueno dueno;
    private List<Cita> citaList;
    //lista de citas

    public Mascota (){

    }

    public Mascota (int idmascota, String nombre, int iddueno, int idtipo){

        this.idmascota = idmascota;
        this.nombre = nombre;
        this.iddueno = iddueno;
        this.idtipo = idtipo;

    }

    public int getIdmascota() {
        return idmascota;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIddueno() {
        return iddueno;
    }

    public void setIddueno(int iddueno) {
        this.iddueno = iddueno;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
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
