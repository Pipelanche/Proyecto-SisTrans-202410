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

}
