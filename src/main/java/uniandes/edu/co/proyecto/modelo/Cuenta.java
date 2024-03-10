package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="cuentas")
public class Cuenta extends Producto {
    
    
    private String numeroCuenta;
    private double saldo;
    
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;
    
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado;
    
    private Date fechaUltimaTransaccion;
    
    public Cuenta() {}

    public Cuenta(String numeroCuenta, double saldo, TipoCuenta tipoCuenta, EstadoCuenta estado, Date fechaUltimaTransaccion) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.estado = estado;
        this.fechaUltimaTransaccion = fechaUltimaTransaccion;
    }

    

    public String getNumeroCuenta() {
        return numeroCuenta; 
    }

    public double getSaldo() {
        return saldo; 
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public EstadoCuenta getEstado() {
         return estado; 
    }

    public Date getFechaUltimaTransaccion() {
        return fechaUltimaTransaccion; 
    }

    
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta; 
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo; 
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    public void setEstado(EstadoCuenta estado) {
        this.estado = estado; 
    }
    public void setFechaUltimaTransaccion(Date fechaUltimaTransaccion) {
        this.fechaUltimaTransaccion = fechaUltimaTransaccion; 
    }

}