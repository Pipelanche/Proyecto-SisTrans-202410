package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;

@Document(collection = "operaciones")
public class Operacion {

    @Id
    private String id;

    private TipoOperacion tipo;

    private Double monto;

    private Date fechaHora;

    @DBRef
    private PuntoDeAtencion puntoDeAtencion;

    @DBRef
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

    public void setId(String id) {
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

    public String getId() {
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