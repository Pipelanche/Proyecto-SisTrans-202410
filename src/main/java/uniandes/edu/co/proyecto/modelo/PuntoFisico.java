package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class PuntoFisico extends PuntoDeAtencion{

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private PuntoDeAtencion puntoDeAtencion;

    private String localizacionGeografica;

    @ManyToOne
    @JoinColumn(name = "oficina", referencedColumnName = "id")
    private Oficina oficina;

    public PuntoFisico() {}

    public String getLocalizacionGeografica() {
        return localizacionGeografica;
    }

    public void setLocalizacionGeografica(String localizacionGeografica) {
        this.localizacionGeografica = localizacionGeografica;
    }
    
    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

}
