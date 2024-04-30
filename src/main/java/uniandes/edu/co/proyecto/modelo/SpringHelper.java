package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

public class SpringHelper {
    private String tipoHelper;
    private Long numeroHelper;
    private Date fecha1Helper;
    private Date fecha2Helper;
    private Long saldo1Helper;
    private Long saldo2Helper;

    public SpringHelper() {
    }

    public SpringHelper(String tipoHelper, Long numeroHelper, Date fecha1Helper, Date fecha2Helper, Long saldo1Helper, Long saldo2Helper) {
        this.tipoHelper = tipoHelper;
        this.numeroHelper = numeroHelper;
        this.fecha1Helper = fecha1Helper;
        this.fecha2Helper = fecha2Helper;
        this.saldo1Helper = saldo1Helper;
        this.saldo2Helper = saldo2Helper;
    }

    public String getTipoHelper() {
        return tipoHelper;
    }

    public void setTipoHelper(String tipoHelper) {
        this.tipoHelper = tipoHelper;
    }

    public Long getNumeroHelper() {
        return numeroHelper;
    }

    public void setNumeroHelper(Long numeroHelper) {
        this.numeroHelper = numeroHelper;
    }

    public Date getFecha1Helper() {
        return fecha1Helper;
    }

    public void setFecha1Helper(Date fecha1Helper) {
        this.fecha1Helper = fecha1Helper;
    }

    public Date getFecha2Helper() {
        return fecha2Helper;
    }

    public void setFecha2Helper(Date fecha2Helper) {
        this.fecha2Helper = fecha2Helper;
    }

    public Long getSaldo1Helper() {
        return saldo1Helper;
    }

    public void setSaldo1Helper(Long saldo1Helper) {
        this.saldo1Helper = saldo1Helper;
    }

    public Long getSaldo2Helper() {
        return saldo2Helper;
    }

    public void setSaldo2Helper(Long saldo2Helper) {
        this.saldo2Helper = saldo2Helper;
    }

    

}
