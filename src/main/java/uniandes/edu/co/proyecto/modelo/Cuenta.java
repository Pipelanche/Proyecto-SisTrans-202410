package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@DiscriminatorValue("CUENTA")
public class Cuenta extends Producto {

    
    @Column(unique = true)
    private String numero;

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

    public Cuenta(String numero, TipoCuenta tipoCuenta, EstadoCuenta estado, Double saldo, Date fechaUltimaTransaccion) {
        this.numero = numero;
        this.tipoCuenta = tipoCuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
    }

    public String getNumero() {
        return numero; 
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

    public void setNumero(String numero) {
        this.numero = numero; 
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
