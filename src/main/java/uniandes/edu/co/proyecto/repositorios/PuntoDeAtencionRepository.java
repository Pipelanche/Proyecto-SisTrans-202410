package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PuntoDeAtencionRepository extends MongoRepository<PuntoDeAtencion, String> {

    // Método personalizado para obtener puntos de atención
    Collection<PuntoDeAtencion> findBy();

    // Eliminar un punto de atención si no tiene operaciones
    void deleteByIdAndOperacionesIsNull(String id);
}
