package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cuentas")
public class Cuenta{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Producto producto;


    @Column(unique = true)
    @GeneratedValue 
    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;

    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;

    private Double saldo;

    private Date fechaUltimaTransaccion;

    public enum TipoCuenta {
        ahorros, corriente, afc
    }

    public enum EstadoCuenta {
        activa, cerrada, desactivada
    }

    public Cuenta() {;}

    public Cuenta(String numero, TipoCuenta tipoCuenta, EstadoCuenta estado, Double saldo, Date fechaUltimaTransaccion) {
        this.numero = numero;
        this.tipo = tipoCuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
    }

    public String getNumero() {
        return numero; 
    }

   
    public TipoCuenta getTipoCuenta() {
        return tipo; 
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

    public void setNumero(String numero) {
        this.numero = numero; 
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipo = tipoCuenta; 
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
