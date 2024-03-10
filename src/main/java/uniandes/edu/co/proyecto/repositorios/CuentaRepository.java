package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Cuenta;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findByNumero(String numero);

    // RFC1 - Consultar las cuentas en BancAndes
    @Query("SELECT c FROM Cuenta c JOIN c.producto p JOIN p.usuario u WHERE c.tipo = :tipo AND c.saldo BETWEEN :saldoMin AND :saldoMax AND c.fechaUltimaTransaccion BETWEEN :fechaInicio AND :fechaFin")
    List<Cuenta> findByTipoAndSaldoRangeAndFechaMovimiento(String tipo, BigDecimal saldoMin, BigDecimal saldoMax, LocalDate fechaInicio, LocalDate fechaFin);
}
