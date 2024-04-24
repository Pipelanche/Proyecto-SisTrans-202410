package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @GetMapping("/new")
    public String cuentaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "cuentaNueva";
    }
    @PostMapping("/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta) {
        cuentaRepository.insertCuenta((Long) 2321312L, cuenta.getTipoCuenta().name(), cuenta.getSaldo());
        return "redirect:/";
    }

    @PostMapping
    public ResponseEntity<?> createCuenta(@RequestBody Cuenta cuenta, @RequestParam String tipoDeDocumentoGerente, @RequestParam String numeroDeDocumentoGerente) {
        Usuario gerente = usuarioRepository.findByTipoDeDocumentoAndNumeroDeDocumento(tipoDeDocumentoGerente, numeroDeDocumentoGerente);
    
   
        if (gerente == null || !gerente.getRol().equals(Usuario.Rol.gerente_oficina)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Para crear una cuenta el usuario debe ser un gerente de oficina.");
        }
        cuenta.setEstado(Cuenta.EstadoCuenta.activa);
        cuenta.setFechaUltimaTransaccion(new Date());

        Cuenta savedCuenta = cuentaRepository.save(cuenta);
        Operacion operacion = new Operacion(Operacion.TipoOperacion.abrir_cuenta, 0.0, new Date(), null, savedCuenta);
        operacionRepository.save(operacion);

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
    
    
        if (nuevoEstado == EstadoCuenta.cerrada && cuenta.getSaldo() != 0) {
            return ResponseEntity.badRequest().body("La cuenta debe tener saldo cero para ser cerrada.");
        }
        if (nuevoEstado == EstadoCuenta.desactivada && cuenta.getEstado() != EstadoCuenta.activa) {
            return ResponseEntity.badRequest().body("La cuenta solo puede ser desactivada si esta activa.");
        }
        if (cuenta.getEstado() != EstadoCuenta.activa && nuevoEstado != EstadoCuenta.activa) {
            return ResponseEntity.badRequest().body("La cuenta ya esta desactivada o cerrada.");
        }
        cuenta.setEstado(nuevoEstado);
        Cuenta updatedCuenta = cuentaRepository.save(cuenta);

        Operacion operacion = new Operacion(nuevoEstado == EstadoCuenta.cerrada ? TipoOperacion.cerrar_cuenta : TipoOperacion.desactivar_cuenta, 0.0, new Date(), null, updatedCuenta);
        operacionRepository.save(operacion);

        return ResponseEntity.ok().body("Estado de la cuenta actualizado correctamente.");
    }

}
