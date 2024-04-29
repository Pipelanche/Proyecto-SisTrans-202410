package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "puntosfisicos")
public class PuntoFisico{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private PuntoDeAtencion puntoDeAtencion;

    private String localizacionGeografica;

    @ManyToOne
    @JoinColumn(name = "oficina", referencedColumnName = "id")
    private Oficina oficina;

    public PuntoFisico() {}

    public PuntoFisico(String localizacionGeografica, Oficina oficina) {
        this.localizacionGeografica = localizacionGeografica;
        this.oficina = oficina;
    }

    public String getLocalizacionGeografica() {
        return localizacionGeografica;
    }

    public void setLocalizacionGeografica(String localizacionGeografica) {
        this.localizacionGeografica = localizacionGeografica;
    }
    
    public Oficina getOficina() {
        return oficina;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PuntoDeAtencion getPuntoDeAtencion() {
        return puntoDeAtencion;
    }

    public void setPuntoDeAtencion(PuntoDeAtencion puntoDeAtencion) {
        this.puntoDeAtencion = puntoDeAtencion;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

}
