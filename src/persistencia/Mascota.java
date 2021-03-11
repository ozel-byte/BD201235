package persistencia;

import java.io.Serializable;
import java.util.List;

public class Mascota implements Serializable {
    private int idmascota;
    private String nombre;
    private TipoMascota tipoMascota;
    private Dueno dueno;
    private List<Cita> citaList;
    //lista de citas

    public Mascota (){

    }

    public Mascota (Dueno _dueno,TipoMascota _tipoMascota , String _nombre){
        this.nombre=_nombre;
       this.dueno=_dueno;
       this.tipoMascota=_tipoMascota;

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
