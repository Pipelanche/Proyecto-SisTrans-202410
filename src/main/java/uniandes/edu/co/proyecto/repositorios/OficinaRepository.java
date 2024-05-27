package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Oficina;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaRepository extends MongoRepository<Oficina, String> {

}
