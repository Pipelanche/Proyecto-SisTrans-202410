package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private Integer cantidadPuntosDeAtencion;
    private String horaAbre;
    private String horaCierre;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "gerenteTipoDeDocumento", referencedColumnName = "tipoDeDocumento"),
        @JoinColumn(name = "gerenteNumeroDeDocumento", referencedColumnName = "numeroDeDocumento")
    })
    private Usuario gerente;

    public Object getNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }

    public void setNombre(Object nombre2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    }

    public Object getDireccion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDireccion'");
    }

    public void setDireccion(Object direccion2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDireccion'");
    }

    public Object getCantidadPuntosDeAtencion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCantidadPuntosDeAtencion'");
    }

    public void setCantidadPuntosDeAtencion(Object cantidadPuntosDeAtencion2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCantidadPuntosDeAtencion'");
    }

    public Object getHoraAbre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHoraAbre'");
    }

    public void setHoraAbre(Object horaAbre2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHoraAbre'");
    }

    public Object getHoraCierre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHoraCierre'");
    }

    public void setHoraCierre(Object horaCierre2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHoraCierre'");
    }

    public Object getGerente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGerente'");
    }

    public void setGerente(Object gerente2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGerente'");
    }

}
