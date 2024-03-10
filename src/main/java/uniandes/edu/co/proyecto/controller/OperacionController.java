package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operaciones")
public class OperacionController {

    @Autowired
    private OperacionRepository operacionRepository;

    @GetMapping
    public List<Operacion> getAllOperaciones() {
        return operacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operacion> getOperacionById(@PathVariable Long id) {
        return operacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Operacion createOperacion(@RequestBody Operacion operacion) {
        return operacionRepository.save(operacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operacion> updateOperacion(@PathVariable Long id, @RequestBody Operacion operacionDetails) {
        return operacionRepository.findById(id)
                .map(operacion -> {
                    operacion.setTipo(operacionDetails.getTipo());
                    operacion.setMonto(operacionDetails.getMonto());
                    operacion.setFechaHora(operacionDetails.getFechaHora());
                    operacion.setPuntoDeAtencion(operacionDetails.getPuntoDeAtencion());
                    operacion.setProducto(operacionDetails.getProducto());
                    return ResponseEntity.ok(operacionRepository.save(operacion));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperacion(@PathVariable Long id) {
        return operacionRepository.findById(id)
                .map(operacion -> {
                    operacionRepository.delete(operacion);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
