package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.modelo.UsuarioPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import javax.transaction.Transactional;

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


    // RFM1 - Crear Usuario
    @Modifying
    //@Transactional revisar no sirve el import
    // Administrador debe hacer este req para (gerente_general, gerente_oficina y cajero) y gerente_oficina hace el de cliente. REVIEW QUERY
    @Query(value = "INSERT INTO usuarios (tipo_de_documento, numero_de_documento, nombre, nacionalidad, direccion_fisica, correo, telefono, login, palabra_clave, tipo_persona, rol) VALUES (:tipoDeDocumento, :numeroDeDocumento, :nombre, :nacionalidad, :direccionFisica, :correo, :telefono, :login, :palabraClave, :tipoPersona, :rol)", nativeQuery = true)
    void crearUsuario(@Param("tipoDeDocumento") String tipoDeDocumento, @Param("numeroDeDocumento") String numeroDeDocumento, @Param("nombre") String nombre,
     @Param("nacionalidad") String nacionalidad, @Param("direccionFisica") String direccionFisica, @Param("correo") String correo, @Param("telefono") String telefono,
      @Param("login") String login, @Param("palabraClave") String palabraClave, @Param("tipoPersona") String tipoPersona, @Param("rol") String rol);
    //crud crear, revisar

    // RFC2 - Consultar un Cliente
    @Query("SELECT u, c, p FROM Usuario u LEFT JOIN u.productos p LEFT JOIN p.cuentas c WHERE u.tipoDeDocumento = :tipoDeDocumento AND u.numeroDeDocumento = :numeroDeDocumento")
    List<Object[]> findUsuarioWithCuentasAndPrestamos(String tipoDeDocumento, String numeroDeDocumento);

}
