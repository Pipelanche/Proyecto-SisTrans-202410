package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.OficinaRepository;
import uniandes.edu.co.proyecto.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/oficinas")
public class OficinaController {

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Oficina> getAllOficinas() {
        return oficinaRepository.findAll();
    }

    @GetMapping("oficina/new")
    public String oficinaForm(Model model) {
        model.addAttribute("oficina", new Oficina());
        model.addAttribute("usuario", new Usuario());
        return "oficinaNueva";
    }

    @PostMapping("oficina/new/save")
    public String oficinaGuardar(@ModelAttribute Oficina oficina, @ModelAttribute Usuario usuario) {
        Usuario gerente = usuarioRepository.findByTipoDeDocumentoAndNumeroDeDocumento(usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento());
        if (gerente == null) {
            return "redirect:/oficinas/oficina/new";
        }
        oficina.setGerente(gerente);
        oficinaRepository.save(oficina);
        return "redirect:/administrador";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oficina> getOficinaById(@PathVariable String id) {
        return oficinaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oficina> updateOficina(@PathVariable String id, @RequestBody Oficina oficinaDetails) {
        return oficinaRepository.findById(id)
                .map(oficina -> {
                    oficina.setNombre(oficinaDetails.getNombre());
                    oficina.setDireccion(oficinaDetails.getDireccion());
                    oficina.setCantidadPuntosDeAtencion(oficinaDetails.getCantidadPuntosDeAtencion());
                    oficina.setHoraAbre(oficinaDetails.getHoraAbre());
                    oficina.setHoraCierre(oficinaDetails.getHoraCierre());
                    return ResponseEntity.ok(oficinaRepository.save(oficina));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOficina(@PathVariable String id) {
        return oficinaRepository.findById(id)
                .map(oficina -> {
                    oficinaRepository.delete(oficina);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
