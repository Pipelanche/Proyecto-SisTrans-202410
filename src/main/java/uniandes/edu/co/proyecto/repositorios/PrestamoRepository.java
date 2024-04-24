package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Prestamo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query(value = "SELECT * FROM prestamos", nativeQuery = true)
    Collection<Prestamo> darPrestamos();

    // RFM5 - Crear Prestamo 
    
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO prestamo (tipo_prestamo, estado_prestamo, monto, interes, cantidad_cuotas, dia_pago_cuotas, valor_cuota, saldo_pendiente) VALUES (:tipoPrestamo, :estadoPrestamo, :monto, :interes, :cantidadCuotas, :diaPagoCuotas, :valorCuota, :saldoPendiente)", nativeQuery = true)
    void createPrestamo(@Param("tipoPrestamo") String tipoPrestamo, @Param("estadoPrestamo") String estadoPrestamo, @Param("monto") Double monto, @Param("interes") Double interes, @Param("cantidadCuotas") Integer cantidadCuotas, @Param("diaPagoCuotas") Integer diaPagoCuotas, @Param("valorCuota") Double valorCuota, @Param("saldoPendiente") Double saldoPendiente);
   
    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET saldoPendiente = saldoPendiente - :montoPago WHERE id = :prestamoId", nativeQuery = true)
    void actualizarSaldoPendiente(@Param("prestamoId") Long prestamoId, @Param("montoPago") Double montoPago);


    // RFM8 - Registrar Operacion Sobre Prestamo
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones (tipo_operacion, monto, fecha_hora, prestamo_id) VALUES (:tipoOperacion, :monto, CURRENT_TIMESTAMP, :prestamoId)", nativeQuery = true)
    void registrarOperacionSobrePrestamo(@Param("prestamoId") Long prestamoId, @Param("monto") Double monto, @Param("tipoOperacion") String tipoOperacion);

    // RFM6 - Actualizar prestamo a cerrado
    @Modifying
    @Transactional
    @Query(value = "UPDATE Prestamo p SET p.estadoPrestamo = 'cerrado' WHERE p.id = :prestamoId AND p.saldoPendiente = 0", nativeQuery = true)
    void cerrarPrestamoSiSaldoEsCero(@Param("prestamoId") Long prestamoId);



}