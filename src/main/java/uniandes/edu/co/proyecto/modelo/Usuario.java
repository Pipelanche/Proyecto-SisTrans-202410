package uniandes.edu.co.proyecto.modelo;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@IdClass(UsuarioPK.class)
public class Usuario {

    @Id
    private String tipoDeDocumento;
    @Id
    private String numeroDeDocumento;

    private String nombre;
    private String nacionalidad;
    private String direccionFisica;
    private String correo;
    private String telefono;
    private String login;
    private String palabraClave;

    @Enumerated(EnumType.STRING)
    private TipoPersona tipoPersona;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum TipoPersona {
        natural, juridica
    }

    public enum Rol {
        cliente, cajero, gerente_oficina, gerente_general, administrador
    }

    public Usuario() {}

    public Usuario(String tipoDeDocumento, String numeroDeDocumento, String nombre, String nacionalidad, String direccionFisica, String correo, String telefono, String login, String palabraClave, TipoPersona tipoPersona, Rol rol) {
        this.tipoDeDocumento = tipoDeDocumento;
        this.numeroDeDocumento = numeroDeDocumento;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.direccionFisica = direccionFisica;
        this.correo = correo;
        this.telefono = telefono;
        this.login = login;
        this.palabraClave = palabraClave;
        this.tipoPersona = tipoPersona;
        this.rol = rol;
    }

    @OneToMany(mappedBy = "cliente")
    private Set<Producto> productos;


    public String getTipoDeDocumento() {
        return tipoDeDocumento; 
    }

    public String getNumeroDeDocumento() {
        return numeroDeDocumento; 
    }

    public String getNombre() {
        return nombre; 
    }
    
    public String getNacionalidad() {
        return nacionalidad; 
    }

    public String getDireccionFisica() {
        return direccionFisica; 
    }
    
    public String getCorreo() {
        return correo; 
    }
    
    public String getTelefono() {
        return telefono; 
    }
    
    public String getLogin() {
        return login; 
    }

    public String getPalabraClave() {
        return palabraClave; 
    }
    
    public TipoPersona getTipoPersona() {
        return tipoPersona; 
    }

    public Rol getRol() {
        return rol; 
    }
    
    public void setTipoDeDocumento(String tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento; 
    }

    public void setNumeroDeDocumento(String numeroDeDocumento) {
        this.numeroDeDocumento = numeroDeDocumento; 
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre; 
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setDireccionFisica(String direccionFisica) {
        this.direccionFisica = direccionFisica; 
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono; 
    }

    public void setLogin(String login) {
        this.login = login; 
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave; 
    }
    
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona; 
    }
    
    public void setRol(Rol rol) {
        this.rol = rol; 
    }
}
