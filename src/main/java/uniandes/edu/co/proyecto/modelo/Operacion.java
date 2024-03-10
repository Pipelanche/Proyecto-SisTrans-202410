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

    public Object getTipo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
    }

    public void setTipo(Object tipo2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTipo'");
    }

    public Object getMonto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMonto'");
    }

    public void setMonto(Object monto2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMonto'");
    }

    public Object getFechaHora() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFechaHora'");
    }

    public void setFechaHora(Object fechaHora2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFechaHora'");
    }

    public Object getPuntoDeAtencion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPuntoDeAtencion'");
    }

    public void setPuntoDeAtencion(Object puntoDeAtencion2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPuntoDeAtencion'");
    }

    public Object getProducto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProducto'");
    }

    public void setProducto(Object producto2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setProducto'");
    }

}
