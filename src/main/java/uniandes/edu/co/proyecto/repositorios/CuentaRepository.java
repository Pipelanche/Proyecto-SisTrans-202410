package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    Cuenta findByNumero(String numero);
}
