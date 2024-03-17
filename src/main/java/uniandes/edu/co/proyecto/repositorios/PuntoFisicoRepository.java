package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PuntoFisicoRepository extends JpaRepository<PuntoFisico, Long> {

    // Encontrar los puntos fisicos asociados a una oficina.
    List<PuntoFisico> findByOficinaId(Long oficinaId);
}
