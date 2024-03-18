package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "oficinas")
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

    public Oficina() {}

    public Oficina(String nombre, String direccion, Integer cantidadPuntosDeAtencion, String horaAbre, String horaCierre, Usuario gerente) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantidadPuntosDeAtencion = cantidadPuntosDeAtencion;
        this.horaAbre = horaAbre;
        this.horaCierre = horaCierre;
        this.gerente = gerente;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCantidadPuntosDeAtencion(Integer cantidadPuntosDeAtencion) {
        this.cantidadPuntosDeAtencion = cantidadPuntosDeAtencion;
    }

    public void setHoraAbre(String horaAbre) {
        this.horaAbre = horaAbre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Integer getCantidadPuntosDeAtencion() {
        return cantidadPuntosDeAtencion;
    }

    public String getHoraAbre() {
        return horaAbre;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public Usuario getGerente() {
        return gerente;
    }


}
