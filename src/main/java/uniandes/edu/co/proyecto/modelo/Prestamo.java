package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Producto producto;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    private Double monto;
    private Double interes;
    private Integer cantidadCuotas;
    private Integer diaPagoDeCuotas;
    private Double valorCuota;

    public enum TipoProducto {
        vivienda, estudio, automovil, libre_inversion
    }

    public enum EstadoPrestamo {
        solicitado, aprobado, rechazado, pagado
    }

    public Object getTipoProducto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipoProducto'");
    }

    public void setTipoProducto(Object tipoProducto2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTipoProducto'");
    }

    public Object getEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstado'");
    }

    public void setEstado(Object estado2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }

    public Object getMonto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMonto'");
    }

    public void setMonto(Object monto2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMonto'");
    }

    public Object getInteres() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInteres'");
    }

    public void setInteres(Object interes2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInteres'");
    }

    public Object getCantidadCuotas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCantidadCuotas'");
    }

    public void setCantidadCuotas(Object cantidadCuotas2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCantidadCuotas'");
    }

    public Object getDiaPagoDeCuotas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDiaPagoDeCuotas'");
    }

    public void setDiaPagoDeCuotas(Object diaPagoDeCuotas2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDiaPagoDeCuotas'");
    }

    public Object getValorCuota() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValorCuota'");
    }

    public void setValorCuota(Object valorCuota2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValorCuota'");
    }

}
