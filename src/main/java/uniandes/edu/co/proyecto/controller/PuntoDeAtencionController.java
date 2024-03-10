package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import uniandes.edu.co.proyecto.repositorios.PuntoDeAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntosDeAtencion")
public class PuntoDeAtencionController {

    @Autowired
    private PuntoDeAtencionRepository puntoDeAtencionRepository;

    @GetMapping
    public List<PuntoDeAtencion> getAllPuntosDeAtencion() {
        return puntoDeAtencionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeAtencion> getPuntoDeAtencionById(@PathVariable Long id) {
        return puntoDeAtencionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PuntoDeAtencion createPuntoDeAtencion(@RequestBody PuntoDeAtencion puntoDeAtencion) {
        return puntoDeAtencionRepository.save(puntoDeAtencion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoDeAtencion> updatePuntoDeAtencion(@PathVariable Long id, @RequestBody PuntoDeAtencion puntoDeAtencionDetails) {
        return puntoDeAtencionRepository.findById(id)
                .map(puntoDeAtencion -> {
                    puntoDeAtencion.setTipo(puntoDeAtencionDetails.getTipo());
                    return ResponseEntity.ok(puntoDeAtencionRepository.save(puntoDeAtencion));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoDeAtencion(@PathVariable Long id) {
        return puntoDeAtencionRepository.findById(id)
                .map(puntoDeAtencion -> {
                    puntoDeAtencionRepository.delete(puntoDeAtencion);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
