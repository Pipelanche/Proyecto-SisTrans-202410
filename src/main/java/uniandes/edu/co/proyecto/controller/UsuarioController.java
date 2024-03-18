package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.modelo.UsuarioPK;
import uniandes.edu.co.proyecto.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    public String index() {
        return "usuarios";
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
