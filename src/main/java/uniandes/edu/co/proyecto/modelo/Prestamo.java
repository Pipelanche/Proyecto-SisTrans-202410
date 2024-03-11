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
public class Prestamo extends Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Producto producto;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estadoPrestamo;

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

    
    public Prestamo() {}

    public Prestamo(Producto producto, TipoProducto tipoProducto, EstadoPrestamo estadoPrestamo, Double monto, Double interes, Integer cantidadCuotas, Integer diaPagoDeCuotas, Double valorCuota) {
        this.producto = producto;
        this.tipoProducto = tipoProducto;
        this.estadoPrestamo = estadoPrestamo;
        this.monto = monto;
        this.interes = interes;
        this.cantidadCuotas = cantidadCuotas;
        this.diaPagoDeCuotas = diaPagoDeCuotas;
        this.valorCuota = valorCuota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Integer getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(Integer cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public Integer getDiaPagoDeCuotas() {
        return diaPagoDeCuotas;
    }

    public void setDiaPagoDeCuotas(Integer diaPagoDeCuotas) {
        this.diaPagoDeCuotas = diaPagoDeCuotas;
    }

    public Double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(Double valorCuota) {
        this.valorCuota = valorCuota;
    }

}
