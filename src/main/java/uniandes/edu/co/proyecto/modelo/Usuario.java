package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {

    @Id
    private String id;
    private String tipoDeDocumento;
    private String numeroDeDocumento;
    private String nombre;
    private String nacionalidad;
    private String direccionFisica;
    private String correo;
    private String telefono;
    private String login;
    private String palabraClave;
    private TipoPersona tipoPersona;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(String tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public String getNumeroDeDocumento() {
        return numeroDeDocumento;
    }

    public void setNumeroDeDocumento(String numeroDeDocumento) {
        this.numeroDeDocumento = numeroDeDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public void setDireccionFisica(String direccionFisica) {
        this.direccionFisica = direccionFisica;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
