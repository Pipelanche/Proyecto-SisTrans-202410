package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private OperacionRepository operacionRepository;

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
    public ResponseEntity<Cuenta> createCuenta(@RequestBody Cuenta cuenta) {
        cuenta.setEstado(Cuenta.EstadoCuenta.activa); 
        cuenta.setFechaUltimaTransaccion(new Date()); 
        Cuenta savedCuenta = cuentaRepository.save(cuenta);
        return ResponseEntity.ok(savedCuenta);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
        return cuentaRepository.findById(id)
                .map(cuenta -> {
                    cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
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

    @PutMapping("/{numeroCuenta}/cambiarEstado")
    @Transactional
        public ResponseEntity<?> cambiarEstadoCuenta(@PathVariable String numeroCuenta, @RequestParam EstadoCuenta nuevoEstado) {
        Cuenta cuenta = cuentaRepository.findByNumero(numeroCuenta);
    
        if (cuenta == null) {
            return ResponseEntity.badRequest().body("Cuenta no encontrada.");
        }
    
        if (nuevoEstado == EstadoCuenta.cerrada) {
            if (cuenta.getSaldo() != 0 || cuenta.getEstado() != EstadoCuenta.activa) {
                return ResponseEntity.badRequest().body("La cuenta no puede ser cerrada debido a su saldo o estado actual.");
            }
        } else if (nuevoEstado == EstadoCuenta.desactivada && cuenta.getEstado() != EstadoCuenta.activa) {
            return ResponseEntity.badRequest().body("La cuenta solo puede ser desactivada si está activa.");
        }
        cuenta.setEstado(nuevoEstado);
        cuentaRepository.save(cuenta);
    
        Operacion operacion = new Operacion(nuevoEstado == EstadoCuenta.cerrada ? TipoOperacion.cerrar_cuenta : TipoOperacion.desactivar_cuenta, 0.0, new Date(), null, cuenta);
        operacionRepository.save(operacion);

        return ResponseEntity.ok().build();
    }
}
