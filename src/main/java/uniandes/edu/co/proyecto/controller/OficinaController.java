package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Oficina;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.GerenteOficinaRepository;
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

    @Autowired
    private GerenteOficinaRepository gerenteOficinaRepository;

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

        try {
            gerenteOficinaRepository.darUsuarioPorDocumento(usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return "redirect:/oficinas/oficina/new";
        }
        System.out.println("Creando oficina");System.out.println("Creando oficina");System.out.println("Creando oficina");System.out.println("Creando oficina");

        System.out.println(oficina.getNombre());
        System.out.println(oficina.getDireccion());
        System.out.println(oficina.getCantidadPuntosDeAtencion());
        System.out.println(usuario.getTipoDeDocumento());
        System.out.println(usuario.getNumeroDeDocumento());

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

