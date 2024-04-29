package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.PuntoFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;


@Repository
public interface PuntoFisicoRepository extends JpaRepository<PuntoFisico, Long> {

    // Obtener todos los puntos fisicos
    @Query(value = "SELECT * FROM puntosfisicos", nativeQuery = true)
    Collection<PuntoFisico> darPuntosFisicos();


    // RFM3 - Crear punto fisico
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO puntosfisicos (id, localizaciongeografica, oficina) VALUES (:id, :localizacionGeografica, :oficina)", nativeQuery = true)
    void crearPuntoFisico(@Param("id") Long id, @Param("localizacionGeografica") String localizacionGeografica, @Param("oficina") Long oficina);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM puntosfisicos WHERE id = :id", nativeQuery = true)
    void deletePuntoFisico(@Param("id") Long id);
}
