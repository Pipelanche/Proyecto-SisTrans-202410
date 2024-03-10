package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.modelo.UsuarioPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioPK> {

    Usuario findByLogin(String login);
    Usuario findByLoginAndPalabraClave(String login, String palabraClave);
    Usuario findByTipoDeDocumentoAndNumeroDeDocumento(String tipoDeDocumento, String numeroDeDocumento);
    Usuario findByCorreo(String correo);
    Usuario findByTelefono(String telefono);
    Usuario findByNacionalidad(String nacionalidad);
    Usuario findByDireccionFisica(String direccionFisica);
    Usuario findByRol(String rol);
    Usuario findByTipoPersona(String tipoPersona);
    Usuario findByNombre(String nombre);
    Usuario findByTipoDeDocumento(String tipoDeDocumento);
    Usuario findByNumeroDeDocumento(String numeroDeDocumento);
    Usuario findByTipoDeDocumentoAndNumeroDeDocumentoAndNombre(String tipoDeDocumento, String numeroDeDocumento, String nombre);
    Usuario findByTipoDeDocumentoAndNumeroDeDocumentoAndNombreAndNacionalidadAndDireccionFisicaAndCorreoAndTelefonoAndLoginAndPalabraClaveAndTipoPersonaAndRol(String tipoDeDocumento, String numeroDeDocumento, String nombre, String nacionalidad, String direccionFisica, String correo, String telefono, String login, String palabraClave, String tipoPersona, String rol);
    Usuario findByTipoDeDocumentoAndNumeroDeDocumentoAndNombreAndNacionalidadAndDireccionFisicaAndCorreoAndTelefonoAndLoginAndPalabraClaveAndTipoPersonaAndRolAndId(String tipoDeDocumento, String numeroDeDocumento, String nombre, String nacionalidad, String direccionFisica, String correo, String telefono, String login, String palabraClave, String tipoPersona, String rol, Long id);
    Usuario findByTipoDeDocumentoAndNumeroDeDocumentoAndNombreAndNacionalidadAndDireccionFisicaAndCorreoAndTelefonoAndLoginAndPalabraClaveAndTipoPersonaAndRolAndIdAndVersion(String tipoDeDocumento, String numeroDeDocumento, String nombre, String nacionalidad, String direccionFisica, String correo, String telefono, String login, String palabraClave, String tipoPersona, String rol, Long id, Long version);
    Usuario findByTipoDeDocumentoAndNumeroDeDocumentoAndNombreAndNacionalidadAndDireccionFisicaAndCorreoAndTelefonoAndLoginAndPalabraClaveAndTipoPersonaAndRolAndIdAndVersionAndClienteTipoDeDocumentoAndClienteNumeroDeDocumento(String tipoDeDocumento, String numeroDeDocumento, String nombre, String nacionalidad, String direccionFisica, String correo, String telefono, String login, String palabraClave, String tipoPersona, String rol, Long id, Long version, String clienteTipoDeDocumento, String clienteNumeroDeDocumento);

    // RFC2 - Consultar un Cliente
    @Query("SELECT u, c, p FROM Usuario u LEFT JOIN u.productos p LEFT JOIN p.cuentas c WHERE u.tipoDeDocumento = :tipoDeDocumento AND u.numeroDeDocumento = :numeroDeDocumento")
    List<Object[]> findUsuarioWithCuentasAndPrestamos(String tipoDeDocumento, String numeroDeDocumento);

}
