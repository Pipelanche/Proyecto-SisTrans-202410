package uniandes.edu.co.proyecto.repositorios;

import uniandes.edu.co.proyecto.modelo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query(value = "SELECT * FROM prestamos WHERE id = :id", nativeQuery = true)
    Prestamo buscarPrestamoPorId(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM prestamos WHERE id = :id", nativeQuery = true)
    void eliminarPrestamoPorId(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE prestamos SET monto = :monto, interes = :interes WHERE id = :id", nativeQuery = true)
    void actualizarPrestamo(@Param("id") long id, @Param("monto") Double monto, @Param("interes") Double interes);

    //crud basico para prueba, se puede cambiar si no es necesario
}