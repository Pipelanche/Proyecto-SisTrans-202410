package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerentes_oficina")
@IdClass(UsuarioPK.class)
public class GerenteOficina extends Usuario{

    @Id
    private String tipoDeDocumento;
    @Id
    private String numeroDeDocumento;

    @OneToOne
    @JoinColumn(name = "tipoDeDocumento", referencedColumnName = "tipoDeDocumento", insertable = false, updatable = false)
    @JoinColumn(name = "numeroDeDocumento", referencedColumnName = "numeroDeDocumento", insertable = false, updatable = false)
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
