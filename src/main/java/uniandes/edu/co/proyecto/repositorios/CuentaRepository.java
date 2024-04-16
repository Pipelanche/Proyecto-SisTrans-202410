package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    //Crear Cuenta con inicialización de estado y registro de operacion
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO producto (tipo_producto, cliente_id, tipo_cuenta, estado, saldo, fecha_ultima_transaccion) " + 
                   "VALUES ('CUENTA', :clienteId, :tipoCuenta, 'activa', :saldo, CURRENT_TIMESTAMP)", nativeQuery = true)
    void insertCuenta(@Param("clienteId") Long clienteId, @Param("tipoCuenta") String tipoCuenta, @Param("saldo") Double saldo);

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
    @Query("SELECT c FROM Cuenta c WHERE c.tipoCuenta = :tipo AND c.saldo BETWEEN :saldoMin AND :saldoMax AND c.fechaUltimaTransaccion BETWEEN :fechaInicio AND :fechaFin")
    List<Cuenta> findByTipoAndSaldoRangeAndFechaMovimiento(@Param("tipo") String tipo, @Param("saldoMin") BigDecimal saldoMin, @Param("saldoMax") BigDecimal saldoMax, @Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
    
}

