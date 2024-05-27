package uniandes.edu.co.proyecto.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.modelo.Ubicacion;

@Repository
public interface UbicacionRepository extends MongoRepository<Ubicacion, String> {

}
