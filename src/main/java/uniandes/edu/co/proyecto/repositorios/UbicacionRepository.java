package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Ubicacion;
import uniandes.edu.co.proyecto.modelo.UbicacionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, UbicacionPK> {

    
}
