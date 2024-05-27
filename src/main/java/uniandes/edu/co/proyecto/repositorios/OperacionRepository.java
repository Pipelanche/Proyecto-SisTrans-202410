package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion; // Import the PuntoDeAtencion class
import uniandes.edu.co.proyecto.modelo.Producto; // Import the Producto class

@Repository
public interface OperacionRepository extends MongoRepository<Operacion, String> {

    // RFC6 - Registrar operacion sobre Cuenta
    default Operacion insertOperacion(String tipo, Double monto, Date fechaHora, String puntoDeAtencionId, String productoId) {
        Operacion operacion = new Operacion(
            Operacion.TipoOperacion.valueOf(tipo), monto, fechaHora, 
            new PuntoDeAtencion(puntoDeAtencionId), new Producto(productoId)
        );
        return save(operacion);
    }

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