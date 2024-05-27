package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.SpringHelper;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.ProductoRepository;
import uniandes.edu.co.proyecto.repositorios.UsuarioRepository;
import uniandes.edu.co.proyecto.servicios.CuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
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

    @Autowired
    private CuentaService CuentaService;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @GetMapping("/rfc1")
    public String rf1(Model model) {
        return "rfc1";
    }

    @GetMapping("/rfc3")
    public String rf3(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("datos", new SpringHelper());
        return "rfc3";
    }

    
    @PostMapping("/extracto")
    public String rf3Lista(@ModelAttribute Cuenta cuenta, @ModelAttribute SpringHelper datos, Model model) {
        //model.addAttribute("operaciones", operacionRepository.findOperacionesByProductoAndMes(cuenta.getId(), datos.getFecha1Helper(), datos.getFecha2Helper()));
        return "extractos";
    }

    @GetMapping("/saldo")
    public String rf1Saldo(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("datos", new SpringHelper());
        return "rfc1Saldo";
    }

    @PostMapping("/saldo/ver")
    public String rf1SaldoVer(@ModelAttribute Cuenta cuenta, Model model) {
        model.addAttribute("cuentas", cuentaRepository.findBySaldo(cuenta.getSaldo()));
        return "rfc1Lista";
    }

    
    @GetMapping("/tipo")
    public String rf1Tipo(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("datos", new SpringHelper());
        return "rfc1Tipo";
    }

    @PostMapping("/tipo/ver")
    public String rf1TipoVer(@ModelAttribute Cuenta cuenta, @ModelAttribute SpringHelper datos, Model model) {
        model.addAttribute("cuentas", cuentaRepository.findByTipo(datos.getTipoHelper()));
        return "rfc1Lista";
    }

    @GetMapping("/fecha")
    public String rf1Fecha(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("datos", new SpringHelper());
        return "rfc1Fecha";
    }

    @PostMapping("/fecha/ver")
    public String rf1FechaVer(@ModelAttribute Cuenta cuenta, Model model) {
        model.addAttribute("cuentas", cuentaRepository.findByFechaUltimaTransaccion(cuenta.getFechaUltimaTransaccion()));
        return "rfc1Lista";
    }




    @GetMapping("/rfc4")
    public String rf4(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "rfc4";
    }

    @PostMapping("/rfc4Lista")
    public String rfc4Lista(@ModelAttribute Cuenta cuenta,Model model) {
        model.addAttribute("operaciones", CuentaService.consultarOperacionesSerializable(Integer.parseInt(cuenta.getNumero())));
        return "rfc4Lista";
    }

    @GetMapping("/rfc5")
    public String rf5(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "rfc5";
    }


    @PostMapping("/rfc5Lista")
    public String rfc5Lista(@ModelAttribute Cuenta cuenta, Model model) {
        model.addAttribute("operaciones", CuentaService.consultarOperacionesReadCommitted(Integer.parseInt(cuenta.getNumero())));
        return "rfc5Lista";
    }

    @GetMapping("/rfc6")
    public String rf6(Model model) {
        return "rfc6";
    }

    @GetMapping("/rfc6Consignacion")
    public String rfc6Consignacion(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("datos", new SpringHelper());
        return "rfc6Consignacion";
    }

    @PostMapping("/rfc6Consignacion/save")
    public String hacerConsignacionRfc6(@ModelAttribute Cuenta cuenta, @ModelAttribute Operacion operacion, @ModelAttribute SpringHelper datos) {
        Cuenta cuenta1 = cuentaRepository.darCuentaPorNumero(cuenta.getNumero());
        CuentaService.consignarDinero(cuenta1.getId(), operacion.getMonto());
        return "redirect:/";
    }

    @GetMapping("/rfc6Retiro")
    public String rfc6Retiro(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("datos", new SpringHelper());
        return "rfc6Retiro";
    }

    @PostMapping("/rfc6Retiro/save")
    public String hacerRetiroRfc6(@ModelAttribute Cuenta cuenta, @ModelAttribute Operacion operacion, @ModelAttribute SpringHelper datos) {
        Cuenta cuenta1 = cuentaRepository.darCuentaPorNumero(cuenta.getNumero());
        CuentaService.retirarDinero(cuenta1.getId(), 1L, operacion.getMonto());
        return "redirect:/";
    }

    @GetMapping("/rfc6Transferencia")
    public String rfc6Transferencia(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("datos", new SpringHelper());
        return "rfc6Transferencia";
    }

    @PostMapping("/rfc6Transferencia/save")
    public String hacerTransferianciaRfc6(@ModelAttribute Cuenta cuenta, @ModelAttribute Operacion operacion, @ModelAttribute SpringHelper datos) {
        Cuenta cuenta1 = cuentaRepository.darCuentaPorNumero(cuenta.getNumero());
        Cuenta cuenta2 = cuentaRepository.darCuentaPorNumero(datos.getNumeroHelper().toString());

        CuentaService.transferirDinero(cuenta1.getId(), cuenta2.getId(), 1L, operacion.getMonto());
        return "redirect:/";
    }

    @GetMapping("/cuenta")
    public String puntosDeAtencion(Model model) {
        model.addAttribute("cuentas", cuentaRepository.darCuentas());


        return "cuentas";
    }

    @GetMapping("/consignacion")
    public String consignacionForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("datos", new SpringHelper());
        return "consignacion";
    }

    @PostMapping("/consignacion/save")
    public String hacerConsignacion(@ModelAttribute Cuenta cuenta, @ModelAttribute Operacion operacion, @ModelAttribute SpringHelper datos) {
        Date date  = new Date(System.currentTimeMillis());
        Cuenta cuenta1 = cuentaRepository.darCuentaPorNumero(cuenta.getNumero());
        operacionRepository.insertOperacion(datos.getTipoHelper(), operacion.getMonto(), date , (Long)2L, cuenta1.getId());
        return "redirect:/";
    }

    @GetMapping("/transferencia")
    public String transferenciaForm(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("datos", new SpringHelper());
        return "transferencia";
    }

    @PostMapping("/transferencia/save")
    public String hacerTransferencia(@ModelAttribute Cuenta cuenta, @ModelAttribute Operacion operacion, @ModelAttribute SpringHelper datos) {
        Date date  = new Date(System.currentTimeMillis());
        Cuenta cuenta1 = cuentaRepository.darCuentaPorNumero(cuenta.getNumero());
        Cuenta cuenta2 = cuentaRepository.darCuentaPorNumero(datos.getNumeroHelper().toString());
        operacionRepository.insertOperacion(datos.getTipoHelper(), operacion.getMonto(), date , (Long)2L, Long.valueOf(cuenta1.getId()));
        operacionRepository.insertOperacion(datos.getTipoHelper(), operacion.getMonto(), date , (Long)2L, cuenta2.getId());
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
