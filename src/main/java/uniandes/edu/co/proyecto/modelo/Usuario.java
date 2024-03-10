package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
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
        cliente, cajero, gerente_oficina, gerente_general
    }

}
