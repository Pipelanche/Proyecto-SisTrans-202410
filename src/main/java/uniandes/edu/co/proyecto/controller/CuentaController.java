package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        return cuentaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
        return cuentaRepository.findById(id)
                .map(cuenta -> {
                    cuenta.setTipo(cuentaDetails.getTipo());
                    cuenta.setEstado(cuentaDetails.getEstado());
                    cuenta.setSaldo(cuentaDetails.getSaldo());
                    cuenta.setFechaUltimaTransaccion(cuentaDetails.getFechaUltimaTransaccion());
                    return ResponseEntity.ok(cuentaRepository.save(cuenta));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable Long id) {
        return cuentaRepository.findById(id)
                .map(cuenta -> {
                    cuentaRepository.delete(cuenta);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
