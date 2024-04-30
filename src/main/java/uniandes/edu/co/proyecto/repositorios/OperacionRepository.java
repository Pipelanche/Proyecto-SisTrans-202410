package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;

import java.time.LocalDateTime;
import java.util.Collection;
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
    @Query(value = "INSERT INTO operaciones (id, tipo, monto, fechahora, puntodeatencion, producto) VALUES (idOperaciones.nextval,:tipo, :monto, :fechaHora, :puntoDeAtencion, :producto)", nativeQuery = true)
    void insertOperacion(@Param("tipo") String tipo, @Param("monto") Double monto, @Param("fechaHora") Date fechaHora, @Param("puntoDeAtencion") Long puntoDeAtencionId, @Param("producto") Long productoId);

        // RFC3 - Extracto Bancario para una Cuenta
    @Query(value = "SELECT c.*, o.tipo," + 
    "(SELECT op.monto FROM Operaciones op, Cuentas cu, Productos pr WHERE op.producto = pr.id AND pr.id = cu.id" +
    "AND op.fechaHora = TO_DATE(':fechaInicio') AS saldo_inicial," +
    "(SELECT ope.monto FROM Operaciones ope, Cuentas cue, Productos pro WHERE ope.producto = pro.id AND pro.id = cue.id" +
    "AND ope.fechaHora = TO_DATE(':fechaFin') AS saldo_final" +
    "FROM Operaciones o, Cuentas c, Productos p WHERE o.producto = p.id AND p.id = c.id" +
    "AND o.fechaHora BETWEEN TO_DATE(':fechaInicio', 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(':fechaFin', 'YYYY-MM-DD HH24:MI:SS')", nativeQuery = true)
    Collection<Operacion> findOperacionesByProductoAndMes(@Param("productoId") Long productoId, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    //Count para requerimento 2 (crear oficina)
    @Query("SELECT COUNT(o) FROM Operacion o WHERE o.puntoDeAtencion.id = :puntoDeAtencionId")
    Long countByPuntoDeAtencionId(@Param("puntoDeAtencionId") Long puntoDeAtencionId);

    default boolean hasOperations(Long puntoDeAtencionId) {
        return countByPuntoDeAtencionId(puntoDeAtencionId) > 0;
    }

    // RFC4 – Consulta de operaciones realizadas sobre una cuenta - SERIALIZABLE
    // RFC5 – Consulta de operaciones realizadas sobre una cuenta – READ COMMITTED
    @Query(value = "SELECT o.* FROM Operaciones o, Productos p, Cuentas c WHERE o.producto = p.id AND p.id = c.id AND c.numero = :numeroCuenta AND o.fechaHora BETWEEN :fechaInicio AND :fechaFin AND o.tipo IN ('consignacion_cuenta', 'retiro_cuenta', 'transferencia_cuenta')", nativeQuery = true)
    List<Operacion> findByCuentaAndFecha(@Param("numeroCuenta") int numeroCuenta, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
 
}