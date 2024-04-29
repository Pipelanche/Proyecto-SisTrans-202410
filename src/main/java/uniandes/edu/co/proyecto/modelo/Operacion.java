package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoOperacion tipo;

    private Double monto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @ManyToOne
    @JoinColumn(name = "puntoDeAtencion", referencedColumnName = "id")
    private PuntoDeAtencion puntoDeAtencion;

    @ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "id")
    private Producto producto;

    public enum TipoOperacion {
        abrir_cuenta, cerrar_cuenta, consignacion_cuenta, retiro_cuenta, transferencia_cuenta, 
        desactivar_cuenta, actualizar_cuenta, solicitar_prestamo, aprobar_prestamo, rechazar_prestamo, 
        pago_cuota_ordinaria, pago_cuota_extraordinaria, cerrar_prestamo, actualizar_prestamo
    }

    public Operacion() {}

    public Operacion(TipoOperacion tipo, Double monto, Date fechaHora, PuntoDeAtencion puntoDeAtencion, Producto producto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.puntoDeAtencion = puntoDeAtencion;
        this.producto = producto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(TipoOperacion tipo) {
        this.tipo = tipo;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setPuntoDeAtencion(PuntoDeAtencion puntoDeAtencion) {
        this.puntoDeAtencion = puntoDeAtencion;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public TipoOperacion getTipo() {
        return tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public PuntoDeAtencion getPuntoDeAtencion() {
        return puntoDeAtencion;
    }

    public Producto getProducto() {
        return producto;
    }
    
}
