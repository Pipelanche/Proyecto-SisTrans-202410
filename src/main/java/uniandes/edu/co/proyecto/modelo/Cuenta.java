package uniandes.edu.co.proyecto.modelo;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cuentas")
public class Cuenta {

    @Id
    private String id;

    @DBRef
    private Producto producto;

    private String numero;

    private TipoCuenta tipo;

    private EstadoCuenta estado;

    private Double saldo;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
