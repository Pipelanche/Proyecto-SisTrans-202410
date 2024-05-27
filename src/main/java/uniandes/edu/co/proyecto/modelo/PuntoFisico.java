package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "puntosFisicos")
public class PuntoFisico {

    @Id
    private String id;

    @DBRef
    private PuntoDeAtencion puntoDeAtencion;

    private String localizacionGeografica;

    @DBRef
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

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PuntoDeAtencion getPuntoDeAtencion() {
        return puntoDeAtencion;
    }

    public void setPuntoDeAtencion(PuntoDeAtencion puntoDeAtencion) {
        this.puntoDeAtencion = puntoDeAtencion;
    }
}
