package uniandes.edu.co.proyecto.modelo;

public class SpringHelper {
    private String tipoHelper;
    private Long numeroHelper; 

    public SpringHelper() {
    }

    public SpringHelper(String tipoHelper, Long numeroHelper) {
        this.tipoHelper = tipoHelper;
        this.numeroHelper = numeroHelper;
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

}
