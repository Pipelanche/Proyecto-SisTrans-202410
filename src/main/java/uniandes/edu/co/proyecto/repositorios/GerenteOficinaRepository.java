package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.GerenteOficina;
import uniandes.edu.co.proyecto.modelo.UsuarioPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteOficinaRepository extends JpaRepository<GerenteOficina, UsuarioPK> {
    
    @Query(value = "SELECT * FROM usuarios WHERE TIPODEDOCUMENTO = :tipoDeDocumento AND NUMERODEDOCUMENTO = :numeroDeDocumento AND ROL = 'gerente_oficina'", nativeQuery = true)
    GerenteOficina darUsuarioPorDocumento(@Param("tipoDeDocumento") String tipoDeDocumento, @Param("numeroDeDocumento") String numeroDeDocumento);
}
