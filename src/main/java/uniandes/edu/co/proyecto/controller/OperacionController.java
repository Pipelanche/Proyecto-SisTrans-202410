package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion.TipoPuntoDeAtencion;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.PuntoDeAtencionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/operaciones")
public class OperacionController {

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private PuntoDeAtencionRepository puntoDeAtencionRepository;

    private static final Map<TipoPuntoDeAtencion, Set<TipoOperacion>> permissions = new HashMap<>();

        //permisos
        static {
            permissions.put(TipoPuntoDeAtencion.atencion_personalizada, EnumSet.allOf(TipoOperacion.class));
            permissions.put(TipoPuntoDeAtencion.cajero_automatico, EnumSet.of(TipoOperacion.consignacion_cuenta, TipoOperacion.retiro_cuenta));
            permissions.put(TipoPuntoDeAtencion.digital, EnumSet.of(TipoOperacion.transferencia_cuenta));
        }

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
    @PostMapping("/consignar/{numeroCuenta}/{puntoDeAtencionId}")
    @Transactional
    public ResponseEntity<?> consignar(@PathVariable String numeroCuenta, @PathVariable Long puntoDeAtencionId, @RequestParam Double monto) {
    
        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findById(puntoDeAtencionId).orElse(null);
        if (puntoDeAtencion == null) {
            return ResponseEntity.badRequest().body("El punto de atencion no existe.");
        }
        if (!isOperationAllowed(puntoDeAtencion.getTipo(), TipoOperacion.consignacion_cuenta)) {
            return ResponseEntity.badRequest().body("La operacion no está permitida en este tipo de punto de atencion.");
        }

        Cuenta cuenta = cuentaRepository.findByNumero(numeroCuenta);
        if (cuenta == null || cuenta.getEstado() != EstadoCuenta.activa) {
            return ResponseEntity.badRequest().body("La cuenta no existe o no está activa.");
        }
        cuenta.setSaldo(cuenta.getSaldo() + monto); 
        cuentaRepository.save(cuenta);

        Operacion operacion = new Operacion(TipoOperacion.consignacion_cuenta, monto, new Date(), puntoDeAtencion, cuenta);
        operacionRepository.save(operacion);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/retirar/{numeroCuenta}/{puntoDeAtencionId}")
    @Transactional
    public ResponseEntity<?> retirar(@PathVariable String numeroCuenta, @PathVariable Long puntoDeAtencionId, @RequestParam Double monto) {
        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findById(puntoDeAtencionId).orElse(null);
        if (puntoDeAtencion == null) {
            return ResponseEntity.badRequest().body("El punto de atencion no existe.");
        }
        if (!isOperationAllowed(puntoDeAtencion.getTipo(), TipoOperacion.retiro_cuenta)) {
            return ResponseEntity.badRequest().body("La operacion de retiro no está permitida en este tipo de punto de atencion.");
        }

        Cuenta cuenta = cuentaRepository.findByNumero(numeroCuenta);
        if (cuenta == null || cuenta.getEstado() != EstadoCuenta.activa || cuenta.getSaldo() < monto) {
            return ResponseEntity.badRequest().body("Fondos insuficientes o cuenta desactivada.");
        }
        cuenta.setSaldo(cuenta.getSaldo() - monto);
        cuentaRepository.save(cuenta);

        Operacion operacion = new Operacion(TipoOperacion.retiro_cuenta, monto, new Date(), puntoDeAtencion, cuenta);
        operacionRepository.save(operacion);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/transferir/{numeroCuentaOrigen}/{numeroCuentaDestino}/{puntoDeAtencionId}")
    @Transactional
    public ResponseEntity<?> transferir(@PathVariable String numeroCuentaOrigen, @PathVariable String numeroCuentaDestino, @PathVariable Long puntoDeAtencionId, @RequestParam Double monto) {
        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findById(puntoDeAtencionId).orElse(null);
        if (puntoDeAtencion == null) {
            return ResponseEntity.badRequest().body("El punto de atencion no existe.");
        }
        if (!isOperationAllowed(puntoDeAtencion.getTipo(), TipoOperacion.transferencia_cuenta)) {
            return ResponseEntity.badRequest().body("La operacion de transferencia no está permitida en este tipo de punto de atencion.");
        }

        Cuenta cuentaOrigen = cuentaRepository.findByNumero(numeroCuentaOrigen);
        Cuenta cuentaDestino = cuentaRepository.findByNumero(numeroCuentaDestino);
    
        if (cuentaOrigen == null || cuentaDestino == null) {
            return ResponseEntity.badRequest().body("Una o ambas cuentas no existen.");
        }
        if (cuentaOrigen.getEstado() != EstadoCuenta.activa || cuentaDestino.getEstado() != EstadoCuenta.activa) {
            return ResponseEntity.badRequest().body("Una o ambas cuentas no se encuentran activas.");
        }
        if (cuentaOrigen.getSaldo() < monto) {
            return ResponseEntity.badRequest().body("Fondos insuficientes en la cuenta de origen.");
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
    
        Operacion operacionOrigen = new Operacion(TipoOperacion.retiro_cuenta, monto, new Date(), puntoDeAtencion, cuentaOrigen);
        Operacion operacionDestino = new Operacion(TipoOperacion.consignacion_cuenta, monto, new Date(), puntoDeAtencion, cuentaDestino);
        operacionRepository.save(operacionOrigen);
        operacionRepository.save(operacionDestino);

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        return ResponseEntity.ok().build();
    }


    //revisar operaciones correctas en cada cual con el enum de operacion...
    private boolean isOperationAllowed(TipoPuntoDeAtencion puntoDeAtencionTipo, TipoOperacion operacionTipo) {
        
        switch(puntoDeAtencionTipo) {
            case atencion_personalizada:
                return true; 
            case cajero_automatico:
                
                return operacionTipo == TipoOperacion.consignacion_cuenta || operacionTipo == TipoOperacion.retiro_cuenta;
            case digital:
                
                return operacionTipo != TipoOperacion.consignacion_cuenta && operacionTipo != TipoOperacion.retiro_cuenta;
            default:
                return false;
        }
    }

    @PostMapping("/{tipoOperacion}/{puntoDeAtencionId}")
    public ResponseEntity<?> performOperation(@PathVariable TipoOperacion tipoOperacion, @PathVariable Long puntoDeAtencionId) {
        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findById(puntoDeAtencionId).orElse(null);
        if (puntoDeAtencion == null) {
            return ResponseEntity.notFound().build();
        }

        if (!isOperationAllowed(puntoDeAtencion.getTipo(), tipoOperacion)) {
            return ResponseEntity.badRequest().body("Esta operacion no puede hacerse en este punto de atencion.");
        }
        return ResponseEntity.ok().build();

    }

}
