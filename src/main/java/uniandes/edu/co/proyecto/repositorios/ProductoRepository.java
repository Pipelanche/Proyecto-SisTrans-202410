package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
