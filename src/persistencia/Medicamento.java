package persistencia;

import java.io.Serializable;

public class Medicamento implements Serializable {
    private Integer idmedicamento;
    private Integer codigo;
    private String nombre;
    private String sustancia;
    private String fecha_Cad;


    public  Medicamento(){

    }
    public Medicamento(String nombre, Integer codigo, String sustancia, String fecha_cad){
        this.codigo = codigo;
        this.nombre=nombre;
        this.sustancia = sustancia;
        this.fecha_Cad=fecha_cad;
    }

    public Integer getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Integer idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSustancia() {
        return sustancia;
    }

    public void setSustancia(String sustancia) {
        this.sustancia = sustancia;
    }

    public String getFecha_Cad() {
        return fecha_Cad;
    }

    public void setFecha_Cad(String fecha_Cad) {
        this.fecha_Cad = fecha_Cad;
    }
}
