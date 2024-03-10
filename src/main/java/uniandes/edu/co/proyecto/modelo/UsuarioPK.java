package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioPK implements Serializable {
    private String clienteTipoDeDocumento;
    private String clienteNumeroDeDocumento;

    public UsuarioPK() {
    }

    public UsuarioPK(String clienteTipoDeDocumento, String clienteNumeroDeDocumento) {
        this.clienteTipoDeDocumento = clienteTipoDeDocumento;
        this.clienteNumeroDeDocumento = clienteNumeroDeDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPK that = (UsuarioPK) o;
        return Objects.equals(clienteTipoDeDocumento, that.clienteTipoDeDocumento) &&
               Objects.equals(clienteNumeroDeDocumento, that.clienteNumeroDeDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteTipoDeDocumento, clienteNumeroDeDocumento);
    }
}
