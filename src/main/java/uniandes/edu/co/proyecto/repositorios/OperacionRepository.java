package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Long> {
    
}
