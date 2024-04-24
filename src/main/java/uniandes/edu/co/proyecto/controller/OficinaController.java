package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.OficinaRepository;
import uniandes.edu.co.proyecto.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        if (!usuarioRepository.findByTipoDeDocumentoAndNumeroDeDocumento(usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento()).getRol().name().equals("gerente_oficina")) {
            return "redirect:/oficinas/oficina/new";
        }
        oficinaRepository.crearOficina(oficina.getNombre(), oficina.getDireccion(), oficina.getCantidadPuntosDeAtencion(),  usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento());
        return "redirect:/administrador";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oficina> getOficinaById(@PathVariable Long id) {
        return oficinaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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

    @PostMapping
    public ResponseEntity<?> createOficina(@RequestBody Oficina oficina) {
        oficina.setHoraAbre("08:00");
        oficina.setHoraCierre("17:00");

        if (oficina.getGerente() == null || oficina.getGerente().getTipoDeDocumento() == null || oficina.getGerente().getNumeroDeDocumento() == null) {
            return ResponseEntity.badRequest().body("Informacion del gerente incompleta.");
        }
        String tipoDeDocumento = oficina.getGerente().getTipoDeDocumento();
        String numeroDeDocumento = oficina.getGerente().getNumeroDeDocumento();

        Usuario gerenteUsuario = usuarioRepository.findByTipoDeDocumentoAndNumeroDeDocumento(tipoDeDocumento, numeroDeDocumento);
        if (gerenteUsuario == null) {
            return ResponseEntity.badRequest().body("El gerente especificado no existe en el sistema.");
        }
        if (gerenteUsuario.getRol() != Usuario.Rol.gerente_oficina) {
            return ResponseEntity.badRequest().body("El usuario especificado no es un gerente de oficina.");
        }
        oficina.setGerente(gerenteUsuario); 
        Oficina savedOficina = oficinaRepository.save(oficina);
        return ResponseEntity.ok(savedOficina);
    }


}

