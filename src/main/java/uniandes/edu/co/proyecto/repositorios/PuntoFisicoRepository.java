package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoFisicoRepository extends JpaRepository<PuntoFisico, Long> {
    
}
