package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import uniandes.edu.co.proyecto.modelo.Producto;

@Repository
public interface OperacionRepository extends MongoRepository<Operacion, String> {

    // RFC6 - Registrar operacion sobre Cuenta
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones (id, tipo, monto, fechahora, puntodeatencion, producto) VALUES (idOperaciones.nextval,:tipo, :monto, :fechaHora, :puntoDeAtencion, :producto)", nativeQuery = true)
    void insertOperacion(@Param("tipo") String tipo, @Param("monto") Double monto, @Param("fechaHora") Date fechaHora, @Param("puntoDeAtencion") Long puntoDeAtencionId, @Param("producto") Long productoId);

    // RFC3 - Extracto Bancario para una Cuenta
    @Query("{ 'producto.id': ?0, 'fechaHora': { $gte: ?1, $lte: ?2 } }")
    List<Operacion> findOperacionesByProductoAndMes(String productoId, Date fechaInicio, Date fechaFin);

    //Count para requerimento 2 (crear oficina)
    @Query(value = "{ 'puntoDeAtencion.id': ?0 }", count = true)
    Long countByPuntoDeAtencionId(String puntoDeAtencionId);

    default boolean hasOperations(String puntoDeAtencionId) {
        return countByPuntoDeAtencionId(puntoDeAtencionId) > 0;
    }

    // RFC4 – Consulta de operaciones realizadas sobre una cuenta - SERIALIZABLE
    // RFC5 – Consulta de operaciones realizadas sobre una cuenta – READ COMMITTED
    @Query("{ 'producto.cuenta.numero': ?0, 'fechaHora': { $gte: ?1, $lte: ?2 }, 'tipo': { $in: ['consignacion_cuenta', 'retiro_cuenta', 'transferencia_cuenta'] } }")
    List<Operacion> findByCuentaAndFecha(int numeroCuenta, Date fechaInicio, Date fechaFin);
}