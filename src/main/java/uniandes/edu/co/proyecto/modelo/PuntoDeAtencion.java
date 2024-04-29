package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PuntoDeAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPuntoDeAtencion tipo;

    public enum TipoPuntoDeAtencion {
        atencion_personalizada, cajero_automatico, digital
    }

    public PuntoDeAtencion() {}

    public PuntoDeAtencion(TipoPuntoDeAtencion tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPuntoDeAtencion getTipo() {
        return tipo;
    }

    public void setTipo(TipoPuntoDeAtencion tipo) {
        this.tipo = tipo;
    }
}
