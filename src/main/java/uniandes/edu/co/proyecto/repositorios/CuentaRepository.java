package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Date;

@Repository
public interface CuentaRepository extends MongoRepository<Cuenta, String> {
    Cuenta findByNumero(String numero);

    @Query("{}")
    List<Cuenta> darCuentas();

    @Query("{ 'numero' : ?0 }")
    Cuenta darCuentaPorNumero(String numero);

    @Query("{ 'id' : ?0 }")
    Cuenta darCuentaPorId(String cuentaId);

    @Transactional
    @Query("{ '_id' : ?0 }")
    void cambiarEstadoCuenta(@Param("cuentaId") String cuentaId, @Param("estado") EstadoCuenta estado);

    @Transactional
    @Query("{ 'tipo' : ?0, 'estado' : ?1, 'saldo' : ?2, 'fechaUltimaTransaccion' : ?3 }")
    void crearCuenta(String tipo, String estado, Double saldo, Date fechaUltimaTransaccion);

    @Transactional
    @Query("{ '_id' : ?0 }")
    Cuenta insertCuenta(String clienteId, String tipo, Double saldo);

    @Transactional
    @Query("{ 'numero' : ?0, 'saldo' : 0, 'estado' : 'activa' }")
    int closeCuenta(String numero, EstadoCuenta estado);

    @Transactional
    @Query("{ 'numero' : ?0, 'estado' : 'activa' }")
    int deactivateCuenta(String numero);

    List<Cuenta> findByTipo(String tipo);

    List<Cuenta> findBySaldo(Double saldo);

    List<Cuenta> findByFechaUltimaTransaccion(Date fechaUltimaTransaccion);

    @Transactional
    @Query(value = "UPDATE { 'id' : ?0 } SET { 'saldo' : ?1, 'fechaUltimaTransaccion' : ?2 }")
    void consignarEnCuenta(String cuentaId, Double monto);

    @Transactional
    @Query(value = "UPDATE { 'id' : ?0, 'saldo' : { $gte : ?1 } } SET { 'saldo' : { $subtract : [ 'saldo', ?1 ] }, 'fechaUltimaTransaccion' : ?2 }")
    void retirarDeCuenta(String cuentaId, Double monto);

    @Transactional
    @Query(value = "UPDATE { 'id' : ?0, 'saldo' : { $gte : ?1 } } SET { 'saldo' : { $subtract : [ 'saldo', ?1 ] } }; " +
                   "UPDATE { 'id' : ?2 } SET { 'saldo' : { $add : [ 'saldo', ?1 ] } }")
    void transferirEntreCuentas(String cuentaOrigenId, String cuentaDestinoId, Double monto);
}
