package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Repository
public interface PuntoDeAtencionRepository extends JpaRepository<PuntoDeAtencion, Long> {

    @Query(value = "SELECT * FROM puntosdeatencion", nativeQuery = true)
    Collection<PuntoDeAtencion> darPuntos();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO puntosdeatencion (id, tipo) VALUES ( idPuntosDeAtencion.nextval,:tipo)", nativeQuery = true)
    void createPuntoDeAtencion(@Param("tipo") String tipo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM puntos_de_atencion WHERE id = :id AND NOT EXISTS (SELECT 1 FROM operaciones WHERE punto_de_atencion_id = :id)", nativeQuery = true)
    void deletePuntoDeAtencionIfNoOperations(@Param("id") Long id);
}
