package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "oficinas")
public class Oficina {

    @Id
    private String id;

    private String nombre;
    private String direccion;
    private Integer cantidadPuntosDeAtencion;
    private String horaAbre;
    private String horaCierre;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCantidadPuntosDeAtencion() {
        return cantidadPuntosDeAtencion;
    }

    public void setCantidadPuntosDeAtencion(Integer cantidadPuntosDeAtencion) {
        this.cantidadPuntosDeAtencion = cantidadPuntosDeAtencion;
    }

    public String getHoraAbre() {
        return horaAbre;
    }

    public void setHoraAbre(String horaAbre) {
        this.horaAbre = horaAbre;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Usuario getGerente() {
        return gerente;
    }

    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }
}
