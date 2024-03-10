package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "operaciones")
public class Operacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoOperacion tipo;
    
    private double monto;
    private Date fechaHora;
    
    
    public Operacion() {}

    
    public Operacion(TipoOperacion tipo, double monto, Date fechaHora) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
    }

    public Integer getId() {
        return id; 
    }

    public TipoOperacion getTipo() {
        return tipo; 
    }

    public double getMonto() {
        return monto; 
    }

    public Date getFechaHora() {
        return fechaHora; 
    }

    public void setId(Integer id) {
         this.id = id;
    }

    public void setTipo(TipoOperacion tipo) {
        this.tipo = tipo; 
    }

    public void setMonto(double monto) {
        this.monto = monto; 
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora; 
    }
}
