package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.modelo.UsuarioPK;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioPK> {

    // Obtener usuario por tipo de documento y numero de documento
    @Query(value = "SELECT * FROM usuarios WHERE TIPODEDOCUMENTO = :tipoDeDocumento AND NUMERODEDOCUMENTO = :numeroDeDocumento", nativeQuery = true)
    Usuario darUsuarioPorDocumento(@Param("tipoDeDocumento") String tipoDeDocumento, @Param("numeroDeDocumento") String numeroDeDocumento);

    // RFM1 - Crear Usuario
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (TIPODEDOCUMENTO, NUMERODEDOCUMENTO, nombre, nacionalidad, direccionfisica, correo, telefono, login, palabraclave, tipopersona, rol) VALUES (:tipoDeDocumento, :numeroDeDocumento, :nombre, :nacionalidad, :direccionFisica, :correo, :telefono, :login, :palabraClave, :tipoPersona, :rol)", nativeQuery = true)
    void crearUsuario(@Param("tipoDeDocumento") String tipoDeDocumento, @Param("numeroDeDocumento") String numeroDeDocumento, @Param("nombre") String nombre,
    @Param("nacionalidad") String nacionalidad, @Param("direccionFisica") String direccionFisica, @Param("correo") String correo, @Param("telefono") String telefono,
    @Param("login") String login, @Param("palabraClave") String palabraClave, @Param("tipoPersona") String tipoPersona, @Param("rol") String rol);
    
    // RFC2 - Consultar un Cliente con sus productos
    @Query(value = "SELECT * FROM Usuarios u, Productos p WHERE u.tipodedocumento = :tipoDeDocumento AND u.tipodedocumento = p.clientetipodedocumento AND u.numerodedocumento = :numeroDeDocumento AND u.numerodedocumento = p.clientenumerodedocumento;", nativeQuery = true)
    Collection<Object[]> findUsuarioWithProductos(@Param("tipoDeDocumento") String tipoDeDocumento, @Param("numeroDeDocumento") String numeroDeDocumento);

}
