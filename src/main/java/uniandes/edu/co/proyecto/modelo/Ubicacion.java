package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(UbicacionPK.class)
public class Ubicacion {

    @Id
    private String clienteTipoDeDocumento;

    @Id
    private String clienteNumeroDeDocumento;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="clienteTipoDeDocumento", referencedColumnName="tipoDeDocumento", insertable = false, updatable = false),
        @JoinColumn(name="clienteNumeroDeDocumento", referencedColumnName="numeroDeDocumento", insertable = false, updatable = false)
    })
    private Usuario usuario;

    private String ciudad;
    private String departamento;
    private String codigoPostal;

    public Ubicacion() {}

    public Ubicacion(String clienteTipoDeDocumento, String clienteNumeroDeDocumento, Usuario usuario, String ciudad, String departamento, String codigoPostal) {
        this.clienteTipoDeDocumento = clienteTipoDeDocumento;
        this.clienteNumeroDeDocumento = clienteNumeroDeDocumento;
        this.usuario = usuario;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
    }

    public String getClienteTipoDeDocumento() {
        return clienteTipoDeDocumento;
    }
    
    public String getClienteNumeroDeDocumento() {
        return clienteNumeroDeDocumento;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public String getCiudad() {
        return ciudad;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    public void setClienteTipoDeDocumento(String clienteTipoDeDocumento) {
        this.clienteTipoDeDocumento = clienteTipoDeDocumento;
    }
    
    public void setClienteNumeroDeDocumento(String clienteNumeroDeDocumento) {
        this.clienteNumeroDeDocumento = clienteNumeroDeDocumento;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
