package uniandes.edu.co.proyecto.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import uniandes.edu.co.proyecto.modelo.Cuenta;
import uniandes.edu.co.proyecto.modelo.Cuenta.EstadoCuenta;
import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.modelo.Operacion.TipoOperacion;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion;
import uniandes.edu.co.proyecto.modelo.PuntoDeAtencion.TipoPuntoDeAtencion;
import uniandes.edu.co.proyecto.repositorios.CuentaRepository;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;
import uniandes.edu.co.proyecto.repositorios.PuntoDeAtencionRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.List;

@Service
public class CuentaService {
    private OperacionRepository operacionRepository;

    private CuentaRepository cuentaRepository;
    
    private PuntoDeAtencionRepository puntoDeAtencionRepository;

    public CuentaService(OperacionRepository operacionRepository) {
        this.operacionRepository = operacionRepository;
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

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 30) // segundos
    public List<Operacion> consultarOperacionesSerializable(int numeroCuenta) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaFinTexto = "2023-02-15 13:00:00"; // Fecha final fija
        try {
            Date fechaFin = new java.sql.Date(sdf.parse(fechaFinTexto).getTime());
            long treintaDiasMilis = 30L * 24 * 3600 * 1000; // 30 días en milisegundos
            Date fechaInicio = new java.sql.Date(fechaFin.getTime() - treintaDiasMilis); // Fecha de inicio hace 30 días
            
            return operacionRepository.findByCuentaAndFecha(numeroCuenta, fechaInicio, fechaFin);
        } catch (Exception e) {
            System.out.println("No se pudo completar la consulta: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Consulta fallida.");
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 30) // segundos
    public List<Operacion> consultarOperacionesReadCommitted(int numeroCuenta) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaFinTexto = "2023-02-15 13:00:00"; // Fecha final fija
        try {
            Date fechaFin = new java.sql.Date(sdf.parse(fechaFinTexto).getTime());
            long treintaDiasMilis = 30L * 24 * 3600 * 1000; // 30 días en milisegundos
            Date fechaInicio = new java.sql.Date(fechaFin.getTime() - treintaDiasMilis); // Fecha de inicio hace 30 días
            
            return operacionRepository.findByCuentaAndFecha(numeroCuenta, fechaInicio, fechaFin);
        } catch (Exception e) {
            System.out.println("No se pudo completar la consulta: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Consulta fallida.");
        }
    } 

    
    //Manejo transaccional de RFM6 - Como las validaciones se quitaron del controller se haran acá.

    @Transactional
    public String consignarDinero(String cuentaId, Double monto) {
        
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElse(null);
        if (cuenta == null) return "La cuenta no existe.";
        if (monto <= 0) return "El monto debe ser mayor que cero.";
        if (cuenta.getEstado() != EstadoCuenta.activa) return "La cuenta no esta activa.";
        
        try {
            cuentaRepository.consignarEnCuenta(cuentaId, monto);
            return "Consignacion realizada con exito.";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Error al realizar la consignacion.";
        }
    }

    @Transactional
    public String retirarDinero(String cuentaId, String puntoDeAtencionId, Double monto) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElse(null);
        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findById(String.valueOf(puntoDeAtencionId)).orElse(null);
    
        if (cuenta == null) return "La cuenta no existe.";
        if (monto <= 0) return "El monto debe ser mayor que cero.";
        if (cuenta.getSaldo() < monto) return "Fondos insuficientes para retirar.";
        if (cuenta.getEstado() != EstadoCuenta.activa) return "La cuenta no esta activa.";
        if (puntoDeAtencion == null) return "El punto de atencion no existe.";
        if (!isOperationAllowed(puntoDeAtencion.getTipo(), TipoOperacion.retiro_cuenta)) {
            return "La operacion de retiro no está permitida en este tipo de punto de atencion.";
        }

        try {
            cuentaRepository.retirarDeCuenta(cuentaId, monto);
            return "Retiro realizado con exito.";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Error al realizar el retiro.";
        }
    }


    @Transactional
    public String transferirDinero(String cuentaOrigenId, String cuentaDestinoId, String puntoDeAtencionId, Double monto) {
        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId).orElse(null);
        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId).orElse(null);
        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findById(puntoDeAtencionId).orElse(null);

        if (cuentaOrigen == null || cuentaDestino == null) return "Una o ambas cuentas no existen.";
        if (monto <= 0) return "El monto debe ser mayor que cero.";
        if (cuentaOrigen.getSaldo() < monto) return "Fondos insuficientes en la cuenta de origen.";
        if (cuentaOrigen.getEstado() != EstadoCuenta.activa || cuentaDestino.getEstado() != EstadoCuenta.activa) {
            return "Una o ambas cuentas no estan activas.";
        }
        if (puntoDeAtencion == null) return "El punto de atencion no existe.";
        if (!isOperationAllowed(puntoDeAtencion.getTipo(), TipoOperacion.transferencia_cuenta)) {
            return "La operacion de transferencia no esta permitida en este tipo de punto de atencion.";
        }

        try {
            cuentaRepository.transferirEntreCuentas(cuentaOrigenId, cuentaDestinoId, monto);
            return "Transferencia realizada con exito.";
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "Error al realizar la transferencia.";
        }
    }

}