package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Cuenta;

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


    //RFM3 - Crear Cuenta
    //revisar porque cuenta es una herencia de producto, algo no cuadra
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuenta (tipo_cuenta, estado, saldo, fecha_ultima_transaccion, producto_id) VALUES (:tipoCuenta, 'activa', :saldo, CURRENT_DATE, :productoId)", nativeQuery = true)
    void crearCuenta(@Param("tipoCuenta") String tipoCuenta, @Param("saldo") Double saldo, @Param("productoId") Long productoId);

    // RFC1 - Consultar las cuentas en BancAndes
    @Query("SELECT c FROM Cuenta c JOIN c.producto p JOIN p.usuario u WHERE c.tipo = :tipo AND c.saldo BETWEEN :saldoMin AND :saldoMax AND c.fechaUltimaTransaccion BETWEEN :fechaInicio AND :fechaFin")
    List<Cuenta> findByTipoAndSaldoRangeAndFechaMovimiento(String tipo, BigDecimal saldoMin, BigDecimal saldoMax, LocalDate fechaInicio, LocalDate fechaFin);
}
