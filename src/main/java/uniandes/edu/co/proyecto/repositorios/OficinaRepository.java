package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
    
}
