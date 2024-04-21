package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;


@Repository
public interface PuntoFisicoRepository extends JpaRepository<PuntoFisico, Long> {

    // Encontrar los puntos fisicos asociados a una oficina.
    List<PuntoFisico> findByOficinaId(Long oficinaId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO puntos_fisicos (tipo, localizacion_geografica, oficina_id) VALUES (:tipo, :localizacionGeografica, :oficinaId)", nativeQuery = true)
    void createPuntoFisico(@Param("tipo") String tipo, @Param("localizacionGeografica") String localizacionGeografica, @Param("oficinaId") Long oficinaId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM puntos_fisicos WHERE id = :id", nativeQuery = true)
    void deletePuntoFisico(@Param("id") Long id);
}
