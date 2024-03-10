package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;



@Entity
@Table(name="puntos_fisicos")
public class PuntoFisico extends PuntoDeAtencion {

    private String localizacionGeografica;

    public PuntoFisico() {}

    public PuntoFisico(String localizacionGeografica) {
        this.localizacionGeografica = localizacionGeografica;   
    }

    public String getLocalizacionGeografica() {
        return localizacionGeografica; 
    }

    public void setLocalizacionGeografica(String localizacionGeografica) {
        this.localizacionGeografica = localizacionGeografica; 
    }
}
