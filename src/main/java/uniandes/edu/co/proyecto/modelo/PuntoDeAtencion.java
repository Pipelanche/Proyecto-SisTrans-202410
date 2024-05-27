package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "puntosDeAtencion")
public class PuntoDeAtencion {

    @Id
    private String id;

    private TipoPuntoDeAtencion tipo;

    public enum TipoPuntoDeAtencion {
        atencion_personalizada, cajero_automatico, digital
    }

    public PuntoDeAtencion() {}

    public PuntoDeAtencion(TipoPuntoDeAtencion tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoPuntoDeAtencion getTipo() {
        return tipo;
    }

    public void setTipo(TipoPuntoDeAtencion tipo) {
        this.tipo = tipo;
    }
}
