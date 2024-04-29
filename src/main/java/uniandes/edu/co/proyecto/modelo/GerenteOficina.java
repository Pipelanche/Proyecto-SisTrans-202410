package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;



@Entity
@IdClass(UsuarioPK.class)
@DiscriminatorValue("gerente_oficina")
public class GerenteOficina extends Usuario {

    public GerenteOficina(String tipoDeDocumento, String numeroDeDocumento, String nombre, String nacionalidad, String direccionFisica, String correo, String telefono, String login, String palabraClave, TipoPersona tipoPersona, Rol rol){
        super(tipoDeDocumento, numeroDeDocumento, nombre, nacionalidad, direccionFisica, correo, telefono, login, palabraClave, tipoPersona, rol);
    }

    public GerenteOficina() {
        super();
    }
}
