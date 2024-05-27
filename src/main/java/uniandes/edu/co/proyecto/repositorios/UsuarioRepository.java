package uniandes.edu.co.proyecto.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.Usuario;

import java.util.Collection;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    @Query("{ 'tipoDeDocumento' : ?0, 'numeroDeDocumento' : ?1 }")
    Usuario darUsuarioPorDocumento(String tipoDeDocumento, String numeroDeDocumento);

    @Query("{ 'tipoDeDocumento' : ?0, 'numeroDeDocumento' : ?1, 'productos' : { $exists: true, $not: { $size: 0 } } }")
    Collection<Usuario> findUsuarioWithProductos(String tipoDeDocumento, String numeroDeDocumento);

    @Query(value = "{ 'tipoDeDocumento' : ?0, 'numeroDeDocumento' : ?1 }", exists = true)
    Usuario findByTipoDeDocumentoAndNumeroDeDocumento(String tipoDeDocumento, String numeroDeDocumento);

    
}
