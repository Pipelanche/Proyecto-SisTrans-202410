package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Cuenta extends Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Producto producto;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    private Double saldo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimaTransaccion;

    public enum TipoCuenta {
        ahorros, corriente, afc
    }

    public enum EstadoCuenta {
        activa, cerrada, desactivada
    }

    public Cuenta() {}

    public Cuenta(String numero, Producto producto, TipoCuenta tipoCuenta, EstadoCuenta estado, Double saldo, Date fechaUltimaTransaccion) {
        this.numero = numero;
        this.producto = producto;
        this.tipoCuenta = tipoCuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
    }

    public Long getId() {
        return id; 
        }

    public String getNumero() {
        return numero; 
    }

    public Producto getProducto() {
        return producto; 
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta; 
    }

    public EstadoCuenta getEstado() {
        return estado; 
    }

    public Double getSaldo() {
        return saldo; 
    }

    public Date getFechaUltimaTransaccion() {
        return fechaUltimaTransaccion; 
    }

    public void setId(Long id) {
        this.id = id; 
    }

    public void setNumero(String numero) {
        this.numero = numero; 
    }

    public void setProducto(Producto producto) {
        this.producto = producto; 
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta; 
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado; 
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo; 
    }

    public void setFechaUltimaTransaccion(Date fechaUltimaTransaccion) {
         this.fechaUltimaTransaccion = fechaUltimaTransaccion; 
        }

}
