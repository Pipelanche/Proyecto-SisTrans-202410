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
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Producto producto;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;

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

    public Object getTipo() {
        return tipo;
    }

    public void setTipo(Object tipo2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTipo'");
    }

    public Object getEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstado'");
    }

    public void setEstado(Object estado2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }

    public Object getSaldo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSaldo'");
    }

    public void setSaldo(Object saldo2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSaldo'");
    }

    public Object getFechaUltimaTransaccion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFechaUltimaTransaccion'");
    }

    public void setFechaUltimaTransaccion(Object fechaUltimaTransaccion2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFechaUltimaTransaccion'");
    }

}
