package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import uniandes.edu.co.proyecto.modelo.PuntoFisico;
import uniandes.edu.co.proyecto.repositorios.OficinaRepository;
import uniandes.edu.co.proyecto.repositorios.PuntoFisicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntosFisicos")
public class PuntoFisicoController {

    @Autowired
    private PuntoFisicoRepository puntoFisicoRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @GetMapping
    public List<PuntoFisico> getAllPuntosFisicos() {
        return puntoFisicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoFisico> getPuntoFisicoById(@PathVariable Long id) {
        return puntoFisicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PuntoFisico createPuntoFisico(@RequestBody PuntoFisico puntoFisico) {
        return puntoFisicoRepository.save(puntoFisico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoFisico> updatePuntoFisico(@PathVariable Long id, @RequestBody PuntoFisico puntoFisicoDetails) {
        return puntoFisicoRepository.findById(id)
                .map(puntoFisico -> {
                    puntoFisico.setLocalizacionGeografica(puntoFisicoDetails.getLocalizacionGeografica());
                    puntoFisico.setOficina(puntoFisicoDetails.getOficina());
                    return ResponseEntity.ok(puntoFisicoRepository.save(puntoFisico));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoFisico(@PathVariable Long id) {
        return puntoFisicoRepository.findById(id)
                .map(puntoFisico -> {
                    puntoFisicoRepository.delete(puntoFisico);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //punto fisico req2
    @PostMapping("/fisico")
    public ResponseEntity<?> createPuntoFisico(@RequestBody PuntoFisico puntoFisico, @RequestParam Long oficinaId) {
        if (puntoFisico.getTipo() == PuntoDeAtencion.TipoPuntoDeAtencion.digital) {
            return ResponseEntity.badRequest().body("Puntos digitales no se relacionan con una oficina");
        }

        Oficina oficina = oficinaRepository.findById(oficinaId).orElse(null);
        if (oficina == null) {
            return ResponseEntity.badRequest().body("Ninguna oficina se encontro con el id: " + oficinaId);
        }

        puntoFisico.setOficina(oficina);
        PuntoFisico savedPuntoFisico = puntoFisicoRepository.save(puntoFisico);
        return ResponseEntity.ok(savedPuntoFisico);
    }

}
