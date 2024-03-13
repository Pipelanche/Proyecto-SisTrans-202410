package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import javax.transaction.Transactional;


@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
    
// RFM2 - Crear oficina
@Modifying
//@Transactional revisar no sirve el import

//Esto solo lo puede hacer el usuario con rol: administrador. Revisar query para la restriccion
@Query(value = "INSERT INTO oficinas (nombre, direccion, numero_puntos_atencion, gerente_id) VALUES (:nombre, :direccion, :numeroPuntosAtencion, (SELECT id FROM usuarios WHERE tipo_de_documento = :gerenteTipoDeDocumento AND numero_de_documento = :gerenteNumeroDeDocumento))", nativeQuery = true)
void crearOficina(@Param("nombre") String nombre, @Param("direccion") String direccion, @Param("numeroPuntosAtencion") Integer numeroPuntosAtencion, @Param("gerenteTipoDeDocumento") String gerenteTipoDeDocumento, @Param("gerenteNumeroDeDocumento") String gerenteNumeroDeDocumento);
}
