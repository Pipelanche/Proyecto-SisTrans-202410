package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.GerenteOficina;
import uniandes.edu.co.proyecto.modelo.UsuarioPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteOficinaRepository extends JpaRepository<GerenteOficina, UsuarioPK> {
    
}
