package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    List<Producto> findByTipo(String tipo);

    List<Producto> findByCliente_TipoDeDocumentoAndCliente_NumeroDeDocumento(String clienteTipoDeDocumento, String clienteNumeroDeDocumento);

    @Query(value = "{ 'tipoProducto' : ?0, 'clienteTipoDocumento' : ?1, 'clienteNumeroDocumento' : ?2 }", exists = true)
    void crearProducto(String tipoProducto, String clienteTipoDocumento, String clienteNumeroDocumento);
}