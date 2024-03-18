package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.modelo.Prestamo.EstadoPrestamo;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @GetMapping
    public List<Prestamo> getAllPrestamos() {
        return prestamoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        return prestamoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody Prestamo prestamo) {
        Prestamo savedPrestamo = prestamoRepository.save(prestamo);
        
        Operacion operacion = new Operacion(TipoOperacion.solicitar_prestamo, prestamo.getMonto(), new Date(), null, prestamo);
        operacionRepository.save(operacion);
        
        return ResponseEntity.ok(savedPrestamo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamoDetails) {
        return prestamoRepository.findById(id)
                .map(prestamo -> {
                    prestamo.setTipoPrestamo(prestamoDetails.getTipoPrestamo());
                    prestamo.setEstadoPrestamo(prestamoDetails.getEstadoPrestamo());
                    prestamo.setMonto(prestamoDetails.getMonto());
                    prestamo.setInteres(prestamoDetails.getInteres());
                    prestamo.setCantidadCuotas(prestamoDetails.getCantidadCuotas());
                    prestamo.setDiaPagoDeCuotas(prestamoDetails.getDiaPagoDeCuotas());
                    prestamo.setValorCuota(prestamoDetails.getValorCuota());
                    return ResponseEntity.ok(prestamoRepository.save(prestamo));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrestamo(@PathVariable Long id) {
        return prestamoRepository.findById(id)
                .map(prestamo -> {
                    prestamoRepository.delete(prestamo);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<?> cerrarPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElse(null);
        if (prestamo == null) {
            return ResponseEntity.notFound().build();
        }
    
        if (prestamo.getSaldoPendiente() != 0) {
            return ResponseEntity.badRequest().body("El prestamo tiene un saldo pendiente y no puede ser cerrado.");
        }
    
        prestamo.setEstadoPrestamo(EstadoPrestamo.cerrado);
        prestamoRepository.save(prestamo);

        Operacion operacion = new Operacion(TipoOperacion.cerrar_prestamo, 0.0, new Date(), null, prestamo);
        operacionRepository.save(operacion);
    
        return ResponseEntity.ok().body("El prestamo ha sido cerrado exitosamente.");
    }


}


