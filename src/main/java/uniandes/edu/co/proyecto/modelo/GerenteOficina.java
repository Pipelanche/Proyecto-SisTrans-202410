package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerentes_oficina")
@IdClass(UsuarioPK.class)
public class GerenteOficina extends Usuario {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id")
    private Oficina oficina;

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
}
