package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Repository
public interface PuntoDeAtencionRepository extends JpaRepository<PuntoDeAtencion, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO puntos_de_atencion (tipo) VALUES (:tipo)", nativeQuery = true)
    void createPuntoDeAtencion(@Param("tipo") String tipo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM puntos_de_atencion WHERE id = :id AND NOT EXISTS (SELECT 1 FROM operaciones WHERE punto_de_atencion_id = :id)", nativeQuery = true)
    void deletePuntoDeAtencionIfNoOperations(@Param("id") Long id);
}
