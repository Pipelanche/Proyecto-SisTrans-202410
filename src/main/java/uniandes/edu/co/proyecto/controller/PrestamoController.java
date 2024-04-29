package uniandes.edu.co.proyecto.controller;
import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.Prestamo;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.modelo.Prestamo.EstadoPrestamo;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.PrestamoRepository;
import uniandes.edu.co.proyecto.repositorios.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @GetMapping
    public List<Prestamo> getAllPrestamos() {
        return prestamoRepository.findAll();
    }
    
    @GetMapping("/prestamo")
    public String getPrestamos(Model model) {
        model.addAttribute("prestamos", prestamoRepository.darPrestamos());
        return "prestamos";
    }

    @GetMapping("/pago")
    public String PagoOrdinarioForm(Model model) {
        model.addAttribute("operacion", new Operacion());
        model.addAttribute("producto", new Producto());
        model.addAttribute("datos", new Object[2]);
        return "pagoCuota";
    }

    @GetMapping("/pago/save")
    public String hacerPagoOrdinario(@ModelAttribute Operacion operacion, @ModelAttribute Producto producto, @ModelAttribute Object[] datos) {
        Long oficina = Long.parseLong(datos[0].toString());
        Date date  = new Date(System.currentTimeMillis());
        operacionRepository.insertOperacion(operacion.getTipo().name(), operacion.getMonto(), date , oficina , producto.getId());
        return "redirect:/";
    }

    @GetMapping("/prestamo/{id}/cerrar")
    public String cerrarPrestamo(@PathVariable Long id) {
        prestamoRepository.cerrarPrestamoSiSaldoEsCero(id);
        return "redirect:/prestamos/prestamo";
    }

    
    @GetMapping("/new")
    public String cuentaForm(Model model) {
        model.addAttribute("prestamo", new Prestamo());
        model.addAttribute("usuario", new Usuario());
        return "prestamoNuevo";
    }
    @PostMapping("/new/save")
    public String cuentaGuardar(@ModelAttribute Prestamo prestamo, @ModelAttribute Usuario usuario) {
        productoRepository.crearProducto("cuenta",usuario.getTipoDeDocumento(), usuario.getNumeroDeDocumento());
        prestamoRepository.crearPrestamo(prestamo.getTipoPrestamo().name(), "solicitado", prestamo.getMonto(), prestamo.getInteres(), prestamo.getCantidadCuotas(), prestamo.getDiaPagoDeCuotas(), prestamo.getValorCuota());
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        return prestamoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamoDetails) {
        return prestamoRepository.findById(id)
                .map(prestamo -> {
                    prestamo.setTipoPrestamo(prestamoDetails.getTipoPrestamo());
                    prestamo.setEstadoPrestamo(prestamoDetails.getEstadoPrestamo());
                    prestamo.setMonto(prestamoDetails.getMonto());
                    prestamo.setInteres(prestamoDetails.getInteres());
                    prestamo.setCantidadCuotas(prestamoDetails.getCantidadCuotas());
                    prestamo.setDiaPagoDeCuotas(prestamoDetails.getDiaPagoDeCuotas());
                    prestamo.setValorCuota(prestamoDetails.getValorCuota());
                    return ResponseEntity.ok(prestamoRepository.save(prestamo));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrestamo(@PathVariable Long id) {
        return prestamoRepository.findById(id)
                .map(prestamo -> {
                    prestamoRepository.delete(prestamo);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }




}


