package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ubicaciones")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String ciudad;
    private String departamento;
    private String codigoPostal;
    
    public Ubicacion() {}

    public Ubicacion(String ciudad, String departamento, String codigoPostal) {
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
    }

    public Integer getId() {
        return id; 
    }

    public String getCiudad() {
        return ciudad; 
    }

    public String getDepartamento() {
        return departamento; 
    }

    public String getCodigoPostal() {
         return codigoPostal; 
    }

    public void setId(Integer id) {
        this.id = id; 
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}