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
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/operaciones")
public class OperacionController {

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping
    public List<Operacion> getAllOperaciones() {
        return operacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operacion> getOperacionById(@PathVariable Long id) {
        return operacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Operacion createOperacion(@RequestBody Operacion operacion) {
        return operacionRepository.save(operacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operacion> updateOperacion(@PathVariable Long id, @RequestBody Operacion operacionDetails) {
        return operacionRepository.findById(id)
                .map(operacion -> {
                    operacion.setTipo(operacionDetails.getTipo());
                    operacion.setMonto(operacionDetails.getMonto());
                    operacion.setFechaHora(operacionDetails.getFechaHora());
                    operacion.setPuntoDeAtencion(operacionDetails.getPuntoDeAtencion());
                    operacion.setProducto(operacionDetails.getProducto());
                    return ResponseEntity.ok(operacionRepository.save(operacion));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperacion(@PathVariable Long id) {
        return operacionRepository.findById(id)
                .map(operacion -> {
                    operacionRepository.delete(operacion);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //transacciones...  
    @PostMapping("/consignar/{numeroCuenta}")
    @Transactional
    public ResponseEntity<?> consignar(@PathVariable String numeroCuenta, @RequestParam Double monto) {
        Cuenta cuenta = cuentaRepository.findByNumero(numeroCuenta);
        if (cuenta == null || cuenta.getEstado() != EstadoCuenta.activa) {
            return ResponseEntity.badRequest().body("La cuenta no existe.");
        }
        cuenta.setSaldo(cuenta.getSaldo() + monto); 
        cuentaRepository.save(cuenta);

        Operacion operacion = new Operacion(TipoOperacion.consignacion_cuenta, monto, new Date(), null, cuenta);
        operacionRepository.save(operacion);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/retirar/{numeroCuenta}")
    @Transactional
    public ResponseEntity<?> retirar(@PathVariable String numeroCuenta, @RequestParam Double monto) {
        Cuenta cuenta = cuentaRepository.findByNumero(numeroCuenta);
        if (cuenta == null || cuenta.getEstado() != EstadoCuenta.activa || cuenta.getSaldo() < monto) {
            return ResponseEntity.badRequest().body("Fondos insuficientes o cuenta desactivada.");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto); 
        cuentaRepository.save(cuenta);

        Operacion operacion = new Operacion(TipoOperacion.retiro_cuenta, monto, new Date(), null, cuenta);
        operacionRepository.save(operacion);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/transferir/{numeroCuentaOrigen}/{numeroCuentaDestino}")
    @Transactional
    public ResponseEntity<?> transferir(@PathVariable String numeroCuentaOrigen, @PathVariable String numeroCuentaDestino, @RequestParam Double monto) {
    Cuenta salida = cuentaRepository.findByNumero(numeroCuentaOrigen);
    Cuenta destino = cuentaRepository.findByNumero(numeroCuentaDestino);
    
    if (salida == null || destino == null) {
        return ResponseEntity.badRequest().body("Una o ambas cuentas no existen.");
    }
    if (salida.getEstado() != EstadoCuenta.activa || destino.getEstado() != EstadoCuenta.activa) {
        return ResponseEntity.badRequest().body("Una o ambas cuentas no se encuentran activas.");
    }
    if (salida.getSaldo() < monto) {
        return ResponseEntity.badRequest().body("Fondos insfucientees en la cuenta de salida.");
    }
    salida.setSaldo(salida.getSaldo() - monto);
    destino.setSaldo(destino.getSaldo() + monto);
    
    Operacion operacionSource = new Operacion(TipoOperacion.retiro_cuenta, monto, new Date(), null, salida);
    Operacion operacionDestination = new Operacion(TipoOperacion.consignacion_cuenta, monto, new Date(), null, destino);
    operacionRepository.save(operacionSource);
    operacionRepository.save(operacionDestination);

    cuentaRepository.save(salida);
    cuentaRepository.save(destino);

    return ResponseEntity.ok().build();
    }

}
