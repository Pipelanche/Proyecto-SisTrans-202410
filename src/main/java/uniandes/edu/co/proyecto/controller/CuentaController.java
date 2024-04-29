package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.ProductoRepository;
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

    @Autowired
    private ProductoRepository  productoRepository;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @GetMapping("/cuenta")
    public String puntosDeAtencion(Model model) {
        model.addAttribute("cuentas", cuentaRepository.darCuentas());


        return "cuentas";
    }

    @GetMapping("/consignacion")
    public String consignacionForm(Model model) {
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("producto", new Producto());
        model.addAttribute("datos", new Object[2]);
        return "pagoCuota";
    }

    @GetMapping("/consignacion/save")
    public String hacerConsignacion(@ModelAttribute Operacion operacion, @ModelAttribute Producto producto, @ModelAttribute Object[] datos) {
        Long oficina = Long.parseLong(datos[0].toString());
        Date date  = new Date(System.currentTimeMillis());
        // operacionRepository.insertOperacion(operacion.getTipo().name(), operacion.getMonto(), date , oficina , producto.getId());
        return "redirect:/";
    }

    @GetMapping("/transferencia")
    public String transferenciaForm(Model model) {
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("producto", new Producto());
        model.addAttribute("datos", new Object[2]);
        return "pagoCuota";
    }

    @GetMapping("/transferencia/save")
    public String hacerTransferencia(@ModelAttribute Operacion operacion, @ModelAttribute Producto producto, @ModelAttribute Object[] datos) {
        Long oficina = Long.parseLong(datos[0].toString());
        Date date  = new Date(System.currentTimeMillis());
        // operacionRepository.insertOperacion(operacion.getTipo().name(), operacion.getMonto(), date , oficina , producto.getId());
        return "redirect:/";
    }
    
    @GetMapping("/cuenta/{id}/cerrar")
    public String cerrarPrestamo(@PathVariable Long id) {
        cuentaRepository.cambiarEstadoCuenta(id, "cerrada");
        return "redirect:/cuentas/cuenta";
    }

    
    @GetMapping("/cuenta/{id}/desactivar")
    public String desactivarPrestamo(@PathVariable Long id) {
        cuentaRepository.cambiarEstadoCuenta(id, "desactivada");
        return "redirect:/cuentas/cuenta";
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
        model.addAttribute("usuario", new Usuario());
        return "cuentaNueva";
    }
    @PostMapping("/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta, @ModelAttribute Usuario usuario) {
        productoRepository.crearProducto("cuenta",usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento());
        System.out.println(cuenta.getFechaUltimaTransaccion());
        System.out.println(cuenta.getFechaUltimaTransaccion());
        System.out.println(cuenta.getFechaUltimaTransaccion());
        cuentaRepository.crearCuenta(cuenta.getTipoCuenta().name(), "activa", cuenta.getSaldo(), cuenta.getFechaUltimaTransaccion());
        return "redirect:/";
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


}
