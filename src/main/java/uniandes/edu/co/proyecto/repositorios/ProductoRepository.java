package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    

    // Crear producto
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO productos (id, tipo, clientetipodedocumento, clientenumerodedocumento) VALUES (idProductos.nextval, :tipo, :clienteTipoDeDocumento, :clienteNumeroDeDocumento)", nativeQuery = true)
    void crearProducto(@Param("tipo") String tipo, @Param("clienteTipoDeDocumento") String clienteTipoDeDocumento, @Param("clienteNumeroDeDocumento") String clienteNumeroDeDocumento);

}