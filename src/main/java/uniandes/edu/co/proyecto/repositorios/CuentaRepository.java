package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findByNumero(String numero);

    @Query(value = "Select * from cuentas", nativeQuery = true)
    Collection<Cuenta> darCuentas();

    @Query(value = "Select * from cuentas where cuentas.numero = :numero", nativeQuery = true)
    Cuenta darCuentaPorNumero(@Param("numero") String numero);

    @Query(value = "Select * from cuentas where cuentas.id = :cuentaId", nativeQuery = true)
    Cuenta darCuentaPorId(@Param("cuentaId") Long cuentaId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentas SET cuentas.estado = :estado WHERE cuentas.id = :cuentaId", nativeQuery = true)
    void cambiarEstadoCuenta(@Param("cuentaId") Long cuentaId, @Param("estado") String estado);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentas (id,numero, tipo, estado, saldo, fechaultimatransaccion) VALUES (idProductos.currval,numeroCuentas.nextval, :tipo, :estado, :saldo, :fechaUltimaTransaccion)", nativeQuery = true)
    void crearCuenta(@Param("tipo") String tipo, @Param("estado") String estado, @Param("saldo") Double saldo, @Param("fechaUltimaTransaccion") Date fechaUltimaTransaccion);

    //Crear Cuenta con inicialización de estado y registro de operacion
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO producto (tipo_producto, cliente_id, tipo_cuenta, estado, saldo, fecha_ultima_transaccion) " + 
                   "VALUES ('CUENTA', :clienteId, :tipo, 'activa', :saldo, CURRENT_TIMESTAMP)", nativeQuery = true)
    void insertCuenta(@Param("clienteId") Long clienteId, @Param("tipo") String tipo, @Param("saldo") Double saldo);

    //Cambiar estado de cuenta a cerrada saldo cero y estado activo
    @Modifying
    @Transactional
    @Query("UPDATE Cuenta c SET c.estado = :estado WHERE c.numero = :numero AND c.saldo = 0 AND c.estado = 'activa'")
    int closeCuenta(@Param("numero") String numero, @Param("estado") EstadoCuenta estado);

    //Cambiar estado de cuenta a desactivada estado activo
    @Modifying
    @Transactional
    @Query("UPDATE Cuenta c SET c.estado = 'desactivada' WHERE c.numero = :numero AND c.estado = 'activa'")
    int deactivateCuenta(@Param("numero") String numero);



    // RFC1 - Consultar las cuentas en BancAndes
    @Query(value = "SELECT c.tipo FROM Cuentas c WHERE c.tipo = :tipo GROUP BY c.tipo;", nativeQuery = true)
    List<Cuenta> findByTipo(@Param("tipo") String tipo);

    @Query(value = "SELECT c.saldo FROM Cuentas c WHERE c.saldo = :saldo GROUP BY c.saldo;", nativeQuery = true)
    List<Cuenta> findBySaldo(@Param("saldo") int tipo);

    @Query(value = "SELECT c.fechaUltimaTransaccion FROM Cuentas c WHERE c.fechaUltimaTransaccion = TO_DATE(':fechaUltimaTransaccion','YYYY-MM-DD') GROUP BY c.fechaUltimaTransaccion;", nativeQuery = true)
    List<Cuenta> findByFechaUltimaTransaccion(@Param("fechaUltimaTransaccion") int fechaUltimaTransaccion);

    //Transacciones 

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones (tipo, monto, fecha_hora, cuenta_id) VALUES ('CONSIGNACION', :monto, NOW(), :cuentaId); " +
                   "UPDATE cuentas SET saldo = saldo + :monto, fecha_ultima_transaccion = NOW() WHERE id = :cuentaId;", nativeQuery = true)
    void consignarEnCuenta(@Param("cuentaId") Long cuentaId, @Param("monto") Double monto);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones (tipo, monto, fecha_hora, cuenta_id) VALUES ('RETIRO', :monto, NOW(), :cuentaId); " +
                   "UPDATE cuentas SET saldo = saldo - :monto, fecha_ultima_transaccion = NOW() WHERE id = :cuentaId AND saldo >= :monto;", nativeQuery = true)
    void retirarDeCuenta(@Param("cuentaId") Long cuentaId, @Param("monto") Double monto);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones (tipo, monto, fecha_hora, cuenta_id) VALUES ('TRANSFERENCIA', -1 * :monto, NOW(), :cuentaOrigenId); " +
                   "UPDATE cuentas SET saldo = saldo - :monto WHERE id = :cuentaOrigenId AND saldo >= :monto; " +
                   "INSERT INTO operaciones (tipo, monto, fecha_hora, cuenta_id) VALUES ('CONSIGNACION', :monto, NOW(), :cuentaDestinoId); " +
                   "UPDATE cuentas SET saldo = saldo + :monto WHERE id = :cuentaDestinoId;", nativeQuery = true)
    void transferirEntreCuentas(@Param("cuentaOrigenId") Long cuentaOrigenId, 
                                @Param("cuentaDestinoId") Long cuentaDestinoId, 
                                @Param("monto") Double monto);
}

