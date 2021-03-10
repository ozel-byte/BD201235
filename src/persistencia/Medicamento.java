package persistencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class Medicamento implements Serializable {
    private final IntegerProperty idmedicamento = new SimpleIntegerProperty();
    private final IntegerProperty codigo = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty sustancia = new SimpleStringProperty();
    private final StringProperty fecha_Cad = new SimpleStringProperty();


    public  Medicamento(){

    }
    public Medicamento(String nombre, Integer codigo, String sustancia, String fecha_cad){
        this.codigo.setValue(codigo);
        this.nombre.setValue(nombre);
        this.sustancia.setValue(sustancia);
        this.fecha_Cad.setValue(fecha_cad);
    }

    public int getIdmedicamento() {
        return idmedicamento.get();
    }

    public IntegerProperty idmedicamentoProperty() {
        return idmedicamento;
    }

    public void setIdmedicamento(int idmedicamento) {
        this.idmedicamento.set(idmedicamento);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
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

    public String getSustancia() {
        return sustancia.get();
    }

    public StringProperty sustanciaProperty() {
        return sustancia;
    }

    public void setSustancia(String sustancia) {
        this.sustancia.set(sustancia);
    }

    public String getFecha_Cad() {
        return fecha_Cad.get();
    }

    public StringProperty fecha_CadProperty() {
        return fecha_Cad;
    }

    public void setFecha_Cad(String fecha_Cad) {
        this.fecha_Cad.set(fecha_Cad);
    }

}
