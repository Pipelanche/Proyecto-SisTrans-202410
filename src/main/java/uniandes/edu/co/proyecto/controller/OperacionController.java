package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion.TipoPuntoDeAtencion;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.PuntoDeAtencionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Calendar;
import java.sql.Date;
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

    

    @GetMapping("/extracto")
    public String cuentaForm(Model model) {
        Object[] datos = new Object[3];
        model.addAttribute("datos", datos);
        return "rfc3";
    }
    @PostMapping("/extracto/ver")
    public String cuentaGuardar(@ModelAttribute Object[] datos, Model model) {
        System.out.println(datos[0]);
        System.out.println(datos[1]);
        Long cuentaId = Long.parseLong(datos[0].toString());
        Date fecha = (Date) datos[1];

        Cuenta cuenta = cuentaRepository.darCuentaPorId(cuentaId);
        if (cuenta == null) {
            return "redirect:/";
        }

        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        
        calendario.set(Calendar.DAY_OF_MONTH, 1);
        Date primerDiaDelMes = new Date(calendario.getTimeInMillis());
        
        calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date ultimoDiaDelMes = new Date(calendario.getTimeInMillis());

        model.addAttribute("operaciones", operacionRepository.findOperacionesByProductoAndMes(cuenta.getId(), primerDiaDelMes, ultimoDiaDelMes));
        
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operacion> getOperacionById(@PathVariable String id) {
        return operacionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Operacion createOperacion(@RequestBody Operacion operacion) {
        return operacionRepository.save(operacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operacion> updateOperacion(@PathVariable String id, @RequestBody Operacion operacionDetails) {
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
    public ResponseEntity<?> deleteOperacion(@PathVariable String id) {
        return operacionRepository.findById(id)
                .map(operacion -> {
                    operacionRepository.delete(operacion);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private boolean isOperationAllowed(TipoPuntoDeAtencion tipoPunto, TipoOperacion tipoOperacion) {
        switch (tipoPunto) {
            case atencion_personalizada:
                return true; //todos sirven.
            case cajero_automatico:
            
                return EnumSet.of(TipoOperacion.consignacion_cuenta, TipoOperacion.retiro_cuenta).contains(tipoOperacion);
            case digital:
                
                return EnumSet.of(TipoOperacion.transferencia_cuenta, TipoOperacion.abrir_cuenta, TipoOperacion.desactivar_cuenta,
                                  TipoOperacion.actualizar_cuenta, TipoOperacion.solicitar_prestamo, TipoOperacion.aprobar_prestamo,
                                  TipoOperacion.rechazar_prestamo, TipoOperacion.pago_cuota_ordinaria, 
                                  TipoOperacion.pago_cuota_extraordinaria).contains(tipoOperacion);
            default:
                return false;
        }
    }

    @PostMapping("/{tipoOperacion}/{puntoDeAtencionId}")
    public ResponseEntity<?> performOperation(@PathVariable TipoOperacion tipoOperacion, @PathVariable String puntoDeAtencionId) {
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
