package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class PuntoFisico {

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

    public Object getLocalizacionGeografica() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocalizacionGeografica'");
    }

    public void setLocalizacionGeografica(Object localizacionGeografica2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLocalizacionGeografica'");
    }

    public Object getOficina() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOficina'");
    }

    public void setOficina(Object oficina2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setOficina'");
    }

}
