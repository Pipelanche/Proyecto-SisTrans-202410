package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.modelo.UsuarioPK;
import uniandes.edu.co.proyecto.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    public String index() {
        return "usuarios";
    }

    @GetMapping("cliente/new")
    public String clienteForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "clienteNuevo";
    }

    @GetMapping("rfc2")
    public String rfc2(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "rfc2";
    }

    @PostMapping("rfc2Lista")
    public String rfc2Lista(@ModelAttribute Usuario usuario,Model model) {
        model.addAttribute("usuarios", usuarioRepository.findUsuarioWithProductos(usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento()));
        return "rfc2Lista";
    }

    @PostMapping("cliente/new/save")
    public String clienteGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.crearUsuario(usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento(), usuario.getNombre(), usuario.getNacionalidad(), usuario.getDireccionFisica(), usuario.getCorreo(), usuario.getTelefono(), usuario.getLogin(), usuario.getPalabraClave(), usuario.getTipoPersona().name(), "cliente");
        return "redirect:/gerente";
    }

    @GetMapping("usuario/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }
    @PostMapping("usuario/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.crearUsuario(usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento(), usuario.getNombre(), usuario.getNacionalidad(), usuario.getDireccionFisica(), usuario.getCorreo(), usuario.getTelefono(), usuario.getLogin(), usuario.getPalabraClave(), usuario.getTipoPersona().name(), usuario.getRol().name());
        return "redirect:/administrador";
    }


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{tipoDeDocumento}/{numeroDeDocumento}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable String tipoDeDocumento,
                                                  @PathVariable String numeroDeDocumento) {
        return usuarioRepository.findById(new UsuarioPK(tipoDeDocumento, numeroDeDocumento))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{tipoDeDocumento}/{numeroDeDocumento}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable String tipoDeDocumento,
                                                 @PathVariable String numeroDeDocumento,
                                                 @RequestBody Usuario usuarioDetails) {
        UsuarioPK usuarioId = new UsuarioPK(tipoDeDocumento, numeroDeDocumento);
        return usuarioRepository.findById(usuarioId)
                .map(usuario -> {
                    usuario.setNombre(usuarioDetails.getNombre());
                    usuario.setNacionalidad(usuarioDetails.getNacionalidad());
                    usuario.setDireccionFisica(usuarioDetails.getDireccionFisica());
                    usuario.setCorreo(usuarioDetails.getCorreo());
                    usuario.setTelefono(usuarioDetails.getTelefono());
                    usuario.setLogin(usuarioDetails.getLogin());
                    usuario.setPalabraClave(usuarioDetails.getPalabraClave());
                    usuario.setTipoPersona(usuarioDetails.getTipoPersona());
                    usuario.setRol(usuarioDetails.getRol());
                    return ResponseEntity.ok(usuarioRepository.save(usuario));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{tipoDeDocumento}/{numeroDeDocumento}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String tipoDeDocumento,
                                              @PathVariable String numeroDeDocumento) {
        UsuarioPK usuarioId = new UsuarioPK(tipoDeDocumento, numeroDeDocumento);
        return usuarioRepository.findById(usuarioId)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return ResponseEntity.ok().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
