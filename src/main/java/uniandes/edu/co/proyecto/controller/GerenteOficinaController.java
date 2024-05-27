package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.GerenteOficina;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerentesOficina")
public class GerenteOficinaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<GerenteOficina> createGerenteOficina(@RequestBody GerenteOficina gerenteOficina) {
        GerenteOficina savedGerente = usuarioRepository.save(gerenteOficina);
        return ResponseEntity.ok(savedGerente);
    }

    @GetMapping("/{tipoDeDocumento}/{numeroDeDocumento}")
    public ResponseEntity<GerenteOficina> getGerenteOficinaById(@PathVariable String tipoDeDocumento,
                                                                @PathVariable String numeroDeDocumento) {
        Usuario usuario = usuarioRepository.findByTipoDeDocumentoAndNumeroDeDocumento(tipoDeDocumento, numeroDeDocumento);
        if (usuario == null || !(usuario instanceof GerenteOficina)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((GerenteOficina) usuario);
    }
}
