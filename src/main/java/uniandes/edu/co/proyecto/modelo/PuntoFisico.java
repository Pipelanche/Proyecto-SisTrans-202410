package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class PuntoFisico extends PuntoDeAtencion{

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private PuntoDeAtencion puntoDeAtencion;

    private String localizacionGeografica;

    @ManyToOne
    @JoinColumn(name = "oficina", referencedColumnName = "id")
    private Oficina oficina;

    public String getLocalizacionGeografica() {
        return localizacionGeografica;
    }

    public void setLocalizacionGeografica(String localizacionGeografica) {
        this.localizacionGeografica = localizacionGeografica;
    }
    //estos no estoy seguro que hacer con ellos.
    public Object getOficina() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOficina'");
    }

    public void setOficina(Object oficina2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setOficina'");
    }

}
