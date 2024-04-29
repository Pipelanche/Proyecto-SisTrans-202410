package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Producto producto;

    @Enumerated(EnumType.STRING)
    private TipoPrestamo tipoProducto;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    private Double monto;
    private Double interes;
    private Integer cantidadCuotas;
    private Integer diaPagoDeCuotas;
    private Double valorCuota;


    public enum TipoPrestamo {
        vivienda, estudio, automovil, libre_inversion
    }

    public enum EstadoPrestamo {
        solicitado, aprobado, rechazado, pagado, cerrado
    }

    public Prestamo() {
    }
    

    public Prestamo( TipoPrestamo tipoPrestamo, EstadoPrestamo estadoPrestamo, Double monto, Double interes, Integer cantidadCuotas, Integer diaPagoDeCuotas, Double valorCuota) {
        this.tipoProducto = tipoPrestamo;
        this.estado = estadoPrestamo;
        this.monto = monto;
        this.interes = interes;
        this.cantidadCuotas = cantidadCuotas;
        this.diaPagoDeCuotas = diaPagoDeCuotas;
        this.valorCuota = valorCuota;
    }

    public TipoPrestamo getTipoPrestamo() {
        return tipoProducto;
    }

    public void setTipoPrestamo(TipoPrestamo tipoPrestamo) {
        this.tipoProducto = tipoPrestamo;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estado;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estado = estadoPrestamo;
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


    public TipoPrestamo getTipoProducto() {
        return tipoProducto;
    }


    public void setTipoProducto(TipoPrestamo tipoProducto) {
        this.tipoProducto = tipoProducto;
    }


    public EstadoPrestamo getEstado() {
        return estado;
    }


    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }


}
