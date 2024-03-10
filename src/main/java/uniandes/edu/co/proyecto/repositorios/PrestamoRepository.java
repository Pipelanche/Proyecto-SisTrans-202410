package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    
}
