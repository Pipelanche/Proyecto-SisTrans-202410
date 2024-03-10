package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "prestamos")
public class Prestamo extends Producto{
    
    @Enumerated(EnumType.STRING)
    private TipoPrestamo tipoPrestamo;
    
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;
    
    private double monto;
    private float tasaInteres;
    private int cantidadCuotas;
    private Date diaPagoCuota;
    private double valorCuota;
    
    
    public Prestamo() {}

    
    public Prestamo(TipoPrestamo tipoPrestamo, EstadoPrestamo estado, double monto, float tasaInteres, int cantidadCuotas, Date diaPagoCuota, double valorCuota) {
        this.tipoPrestamo = tipoPrestamo;
        this.estado = estado;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.cantidadCuotas = cantidadCuotas;
        this.diaPagoCuota = diaPagoCuota;
        this.valorCuota = valorCuota;
    }
    
    

    public TipoPrestamo getTipoPrestamo() {
        return tipoPrestamo;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public double getMonto() {
        return monto;
    }

    public float getTasaInteres() {
        return tasaInteres;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public Date getDiaPagoCuota() {
        return diaPagoCuota;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    

    public void setTipoPrestamo(TipoPrestamo tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setTasaInteres(float tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public void setDiaPagoCuota(Date diaPagoCuota) {
        this.diaPagoCuota = diaPagoCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

}