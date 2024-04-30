package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Oficina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface OficinaRepository extends JpaRepository<Oficina, Long> {
        
    // RFM2 - Crear oficina
    @Modifying
    @Transactional 
    @Query(value = "INSERT INTO oficinas (id, nombre, direccion, cantidadpuntosdeatencion, horaabre, horacierre, gerentetipodedocumento, gerentenumerodedocumento) VALUES (idOficinas.nextval,:nombre, :direccion, :numeroPuntosAtencion,INTERVAL '8' HOUR, INTERVAL '17' HOUR, :gerenteTipoDeDocumento, :gerenteNumeroDeDocumento)", nativeQuery = true)
    void crearOficina(@Param("nombre") String nombre, @Param("direccion") String direccion, @Param("numeroPuntosAtencion") Integer numeroPuntosAtencion, @Param("gerenteTipoDeDocumento") String gerenteTipoDeDocumento, @Param("gerenteNumeroDeDocumento") String gerenteNumeroDeDocumento);

}
