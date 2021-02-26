package persistencia;

public class Servicio {
    private Integer idtipo;
    private String especie;
    private String sexo;
    private String raza;


    public Servicio(){

    }
    public Servicio(String especie, String sexo, String raza){
        this.especie=especie;
        this.sexo=sexo;
        this.raza=raza;
    }

    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
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
}
