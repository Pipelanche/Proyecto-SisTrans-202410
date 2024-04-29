package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;

import java.time.LocalDateTime;
import java.util.List;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Long> {

    // RFC6 - Registrar operacion sobre Cuenta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operacion (tipo, monto, fecha_hora, punto_de_atencion_id, producto_id) VALUES (:tipo, :monto, :fechaHora, :puntoDeAtencionId, :productoId)", nativeQuery = true)
    void insertOperacion(@Param("tipo") String tipo, @Param("monto") Double monto, @Param("fechaHora") Date fechaHora, @Param("puntoDeAtencionId") Long puntoDeAtencionId, @Param("productoId") Long productoId);

    // RFC3 - Extracto Bancario para una Cuenta
    @Query("SELECT o FROM Operacion o WHERE o.producto.id = :productoId AND o.fechaHora BETWEEN :fechaInicioMes AND :fechaFinMes")
    List<Operacion> findOperacionesByProductoAndMes(@Param("productoId") Long productoId, @Param("fechaInicioMes") LocalDateTime fechaInicioMes, @Param("fechaFinMes") LocalDateTime fechaFinMes);

    //Count para requerimento 2 (crear oficina)
    @Query("SELECT COUNT(o) FROM Operacion o WHERE o.puntoDeAtencion.id = :puntoDeAtencionId")
    Long countByPuntoDeAtencionId(@Param("puntoDeAtencionId") Long puntoDeAtencionId);

    default boolean hasOperations(Long puntoDeAtencionId) {
        return countByPuntoDeAtencionId(puntoDeAtencionId) > 0;
    }

    // RFC4 – Consulta de operaciones realizadas sobre una cuenta - SERIALIZABLE
    // RFC5 – Consulta de operaciones realizadas sobre una cuenta – READ COMMITTED
    @Query(value = "SELECT * FROM Operacion o, Producto p, Cuenta c WHERE o.producto = p.id AND p.id = c.id AND c.numero = :numeroCuenta AND o.fechaHora BETWEEN :fechaInicio AND :fechaFin AND o.tipo IN ('consignacion_cuenta', 'retiro_cuenta', 'transferencia_cuenta')", nativeQuery = true)
    List<Operacion> findByCuentaAndFecha(@Param("numeroCuenta") int numeroCuenta, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
 
}
