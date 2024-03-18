package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioPK implements Serializable {
    private String tipoDeDocumento;
    private String numeroDeDocumento;

    public UsuarioPK() {
    }

    public UsuarioPK(String tipoDeDocumento, String numeroDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
        this.numeroDeDocumento = numeroDeDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPK that = (UsuarioPK) o;
        return Objects.equals(tipoDeDocumento, that.tipoDeDocumento) &&
               Objects.equals(numeroDeDocumento, that.numeroDeDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoDeDocumento, numeroDeDocumento);
    }
}
