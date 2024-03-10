package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.repositorios.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oficinas")
public class OficinaController {

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping
    public List<Oficina> getAllOficinas() {
        return oficinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oficina> getOficinaById(@PathVariable Long id) {
        return oficinaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Oficina createOficina(@RequestBody Oficina oficina) {
        return oficinaRepository.save(oficina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oficina> updateOficina(@PathVariable Long id, @RequestBody Oficina oficinaDetails) {
        return oficinaRepository.findById(id)
                .map(oficina -> {
                    oficina.setNombre(oficinaDetails.getNombre());
                    oficina.setDireccion(oficinaDetails.getDireccion());
                    oficina.setCantidadPuntosDeAtencion(oficinaDetails.getCantidadPuntosDeAtencion());
                    oficina.setHoraAbre(oficinaDetails.getHoraAbre());
                    oficina.setHoraCierre(oficinaDetails.getHoraCierre());
                    oficina.setGerente(oficinaDetails.getGerente());
                    return ResponseEntity.ok(oficinaRepository.save(oficina));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOficina(@PathVariable Long id) {
        return oficinaRepository.findById(id)
                .map(oficina -> {
                    oficinaRepository.delete(oficina);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
