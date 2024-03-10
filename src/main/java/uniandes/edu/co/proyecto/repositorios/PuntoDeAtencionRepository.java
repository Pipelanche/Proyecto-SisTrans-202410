package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoDeAtencionRepository extends JpaRepository<PuntoDeAtencion, Long> {

}
