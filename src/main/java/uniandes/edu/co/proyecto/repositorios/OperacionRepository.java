package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Long> {

    // RFC3 - Extracto Bancario para una Cuenta
    @Query("SELECT o FROM Operacion o WHERE o.producto.id = :productoId AND o.fechaHora BETWEEN :fechaInicioMes AND :fechaFinMes")
    List<Operacion> findOperacionesByProductoAndMes(@Param("productoId") Long productoId, @Param("fechaInicioMes") LocalDateTime fechaInicioMes, @Param("fechaFinMes") LocalDateTime fechaFinMes);

    //Count para requerimento 2 (crear oficina)
    @Query("SELECT COUNT(o) FROM Operacion o WHERE o.puntoDeAtencion.id = :puntoDeAtencionId")
    Long countByPuntoDeAtencionId(@Param("puntoDeAtencionId") Long puntoDeAtencionId);

    default boolean hasOperations(Long puntoDeAtencionId) {
        return countByPuntoDeAtencionId(puntoDeAtencionId) > 0;
    }
}
