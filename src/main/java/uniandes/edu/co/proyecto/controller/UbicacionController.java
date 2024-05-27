package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Ubicacion;
import uniandes.edu.co.proyecto.repositorios.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @GetMapping
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    @PostMapping
    public Ubicacion createUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getUbicacionById(@PathVariable String id) {
        return ubicacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateUbicacion(@PathVariable String id, @RequestBody Ubicacion ubicacionDetails) {
        return ubicacionRepository.findById(id)
                .map(ubicacion -> {
                    ubicacion.setClienteTipoDeDocumento(ubicacionDetails.getClienteTipoDeDocumento());
                    ubicacion.setClienteNumeroDeDocumento(ubicacionDetails.getClienteNumeroDeDocumento());
                    ubicacion.setUsuario(ubicacionDetails.getUsuario());
                    ubicacion.setCiudad(ubicacionDetails.getCiudad());
                    ubicacion.setDepartamento(ubicacionDetails.getDepartamento());
                    ubicacion.setCodigoPostal(ubicacionDetails.getCodigoPostal());
                    return ResponseEntity.ok(ubicacionRepository.save(ubicacion));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable String id) {
        return ubicacionRepository.findById(id)
                .map(ubicacion -> {
                    ubicacionRepository.delete(ubicacion);
                    return ResponseEntity.ok().<Void>build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
