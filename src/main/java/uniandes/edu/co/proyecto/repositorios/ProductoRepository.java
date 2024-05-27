package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {

    List<Producto> findByTipo(String tipo);

    List<Producto> findByCliente_TipoDeDocumentoAndCliente_NumeroDeDocumento(String clienteTipoDeDocumento, String clienteNumeroDeDocumento);
}