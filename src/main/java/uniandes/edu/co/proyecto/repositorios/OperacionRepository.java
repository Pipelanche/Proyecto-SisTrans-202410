package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OperacionRepository extends MongoRepository<Operacion, String> {

    // RFC6 - Registrar operacion sobre Cuenta
    @Query(value = "{ 'tipo' : ?0, 'monto' : ?1, 'fechaHora' : ?2, 'puntoDeAtencion' : ?3, 'producto' : ?4 }", exists = true)
    void insertOperacion(String tipo, Double monto, Date fechaHora, String puntoDeAtencionId, String productoId);

    // RFC3 - Extracto Bancario para una Cuenta
    @Query("{ 'producto.id': ?0, 'fechaHora': { $gte: ?1, $lte: ?2 } }")
    List<Operacion> findOperacionesByProductoAndMes(String productoId, Date fechaInicio, Date fechaFin);

    // Count para requerimiento 2 (crear oficina)
    @Query(value = "{ 'puntoDeAtencion.id': ?0 }", count = true)
    Long countByPuntoDeAtencionId(String puntoDeAtencionId);

    default boolean hasOperations(String puntoDeAtencionId) {
        return countByPuntoDeAtencionId(puntoDeAtencionId) > 0;
    }

    // RFC4 – Consulta de operaciones realizadas sobre una cuenta - SERIALIZABLE
    // RFC5 – Consulta de operaciones realizadas sobre una cuenta – READ COMMITTED
    @Query("{ 'producto.cuenta.numero': ?0, 'fechaHora': { $gte: ?1, $lte: ?2 }, 'tipo': { $in: ['consignacion_cuenta', 'retiro_cuenta', 'transferencia_cuenta'] } }")
    List<Operacion> findByCuentaAndFecha(String numeroCuenta, Date fechaInicio, Date fechaFin);
}
