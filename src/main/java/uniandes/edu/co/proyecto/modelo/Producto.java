package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;
    
    public Producto() {;}

    
    public Producto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Integer getId() {
        return id;
    }
    
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}