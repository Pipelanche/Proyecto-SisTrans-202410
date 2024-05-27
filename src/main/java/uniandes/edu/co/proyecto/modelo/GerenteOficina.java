package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class GerenteOficina extends Usuario {

    public GerenteOficina(String tipoDeDocumento, String numeroDeDocumento, String nombre, String nacionalidad, String direccionFisica, String correo, String telefono, String login, String palabraClave, TipoPersona tipoPersona, Rol rol) {
        super(tipoDeDocumento, numeroDeDocumento, nombre, nacionalidad, direccionFisica, correo, telefono, login, palabraClave, tipoPersona, rol);
    }

    public GerenteOficina() {
        super();
    }
}
