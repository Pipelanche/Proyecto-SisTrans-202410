package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.PuntoDeAtencionRepository;
import uniandes.edu.co.proyecto.repositorios.PuntoFisicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/puntosDeAtencion")
public class PuntoDeAtencionController {

    @Autowired
    private PuntoDeAtencionRepository puntoDeAtencionRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private PuntoFisicoRepository puntoFisicoRepository;

    @GetMapping
    public List<PuntoDeAtencion> getAllPuntosDeAtencion() {
        return puntoDeAtencionRepository.findAll();
    }

    @GetMapping("/puntoDeAtencion")
    public String puntosDeAtencion(Model model) {
        model.addAttribute("puntosDeAtencion", puntoDeAtencionRepository.findBy());
        model.addAttribute("puntosFisicos", puntoFisicoRepository.findBy());
        return "puntosDeAtencion";
    }

    @GetMapping("new")
    public String puntoDeAtencionForm(Model model) {
        model.addAttribute("puntoDeAtencion", new PuntoDeAtencion());
        return "puntoDeAtencionNuevo";
    }

    @PostMapping("new/save")
    public String puntoDeAtencionGuardar(@ModelAttribute PuntoDeAtencion puntoDeAtencion) {
        puntoDeAtencionRepository.save(puntoDeAtencion);
        return "redirect:/administrador";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeAtencion> getPuntoDeAtencionById(@PathVariable String id) {
        return puntoDeAtencionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoDeAtencion> updatePuntoDeAtencion(@PathVariable String id, @RequestBody PuntoDeAtencion puntoDeAtencionDetails) {
        return puntoDeAtencionRepository.findById(id)
                .map(puntoDeAtencion -> {
                    puntoDeAtencion.setTipo(puntoDeAtencionDetails.getTipo());
                    return ResponseEntity.ok(puntoDeAtencionRepository.save(puntoDeAtencion));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntoDeAtencion(@PathVariable String id) {
        if (operacionRepository.hasOperations(id)) {
            return ResponseEntity.badRequest().body("No se puede borrar: Se han hecho operaciones en este punto de atencion.");
        }

        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findById(id).orElse(null);
        if (puntoDeAtencion == null) {
            return ResponseEntity.notFound().build();
        }

        puntoDeAtencionRepository.delete(puntoDeAtencion);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/digital")
    public ResponseEntity<?> createPuntoDeAtencionDigital(@RequestBody PuntoDeAtencion puntoDeAtencion) {
        if (puntoDeAtencion.getTipo() != PuntoDeAtencion.TipoPuntoDeAtencion.digital) {
            return ResponseEntity.badRequest().body("Solo los puntos digitales pueden crearse sin oficina.");
        }
        PuntoDeAtencion savedPuntoDeAtencion = puntoDeAtencionRepository.save(puntoDeAtencion);
        return ResponseEntity.ok(savedPuntoDeAtencion);
    }
}

