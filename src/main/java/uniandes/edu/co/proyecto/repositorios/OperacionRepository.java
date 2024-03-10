package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Operacion;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Long> {

    // RFC3 - Extracto Bancario para una Cuenta
    @Query("SELECT o FROM Operacion o WHERE o.cuenta.numero = :numeroCuenta AND o.fechaHora BETWEEN :fechaInicioMes AND :fechaFinMes")
    List<Operacion> findOperacionesByCuentaAndMes(Long numeroCuenta, LocalDateTime fechaInicioMes, LocalDateTime fechaFinMes);

    
}
