package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="puntos_de_atencion")
public class PuntoDeAtencion {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    private TipoPuntoDeAtencion tipo;
    
    private String ubicacion;
    
    
    public PuntoDeAtencion() {}

    
    public PuntoDeAtencion(TipoPuntoDeAtencion tipo, String ubicacion) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
        return id; 
    }

    public TipoPuntoDeAtencion getTipo() {
        return tipo; 
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setId(Integer id) {
        this.id = id; 
    }
    
    public void setTipo(TipoPuntoDeAtencion tipo) {
        this.tipo = tipo; 
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion; 
    }
    
}