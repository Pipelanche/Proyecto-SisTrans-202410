package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {


    // RFM5 - Crear Prestamo 
    //revisar, la logica de esto aun no siento que este bien.
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO operaciones_prestamo (prestamo_id, fecha_operacion, monto_pago, tipo_operacion) VALUES (:prestamoId, :fechaOperacion, :montoPago, :tipoOperacion)", nativeQuery = true)
    void registrarOperacionSobrePrestamo(@Param("prestamoId") Long prestamoId, @Param("fechaOperacion") Date fechaOperacion, @Param("montoPago") Double montoPago, @Param("tipoOperacion") String tipoOperacion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET saldoPendiente = saldoPendiente - :montoPago WHERE id = :prestamoId", nativeQuery = true)
    void actualizarSaldoPendiente(@Param("prestamoId") Long prestamoId, @Param("montoPago") Double montoPago);


    // RFM6 - Actualizar prestamo a cerrado
    @Modifying
    @Transactional
    @Query(value = "UPDATE Prestamo p SET p.estadoPrestamo = 'cerrado' WHERE p.id = :prestamoId AND p.saldoPendiente = 0", nativeQuery = true)
    void cerrarPrestamoSiSaldoEsCero(@Param("prestamoId") Long prestamoId);



}