package uniandes.edu.co.proyecto.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Operacion;
import uniandes.edu.co.proyecto.repositorios.OperacionRepository;

import java.sql.Date;
import java.util.List;

@Service
public class CuentaService {

    private OperacionRepository operacionRepository;

    public CuentaService(OperacionRepository operacionRepository) {
        this.operacionRepository = operacionRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 30) // segundos
    public List<Operacion> consultarOperacionesSerializable(int numeroCuenta) {
        try {
            return operacionRepository.findByCuentaAndFecha(numeroCuenta, new Date(System.currentTimeMillis() - 30L * 24 * 3600 * 1000), new Date(numeroCuenta));
        } catch (Exception e) {
            System.out.println("No se pudo completar la consulta: " + e.getMessage());
            throw new RuntimeException("Consulta fallida.");
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 30) // segundos
    public List<Operacion> consultarOperacionesReadCommitted(int numeroCuenta) {
        try {
            return operacionRepository.findByCuentaAndFecha(numeroCuenta, new Date(System.currentTimeMillis() - 30L * 24 * 3600 * 1000), new Date(numeroCuenta));
        } catch (Exception e) {
            System.out.println("No se pudo completar la consulta: " + e.getMessage());
            throw new RuntimeException("Consulta fallida.");
        }
    }
}
