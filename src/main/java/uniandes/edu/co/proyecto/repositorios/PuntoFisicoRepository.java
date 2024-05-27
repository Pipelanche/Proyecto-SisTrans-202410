package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoFisico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PuntoFisicoRepository extends MongoRepository<PuntoFisico, String> {

    // Obtener todos los puntos físicos
    Collection<PuntoFisico> findBy();

    // Crear un punto físico (esto se maneja automáticamente por MongoRepository)
    // void save(PuntoFisico puntoFisico);

    // Eliminar un punto físico
    void deleteById(String id);
}
