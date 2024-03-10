package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="oficinas")
public class Oficina {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String nombre;
    private String direccion;
    private int cantidadPuntosDeAtencion;
    private Date horaAbre;
    private Date horaCierra;
    
    
    public Oficina() {}

    public Oficina(String nombre, String direccion, int cantidadPuntosDeAtencion, Date horaAbre, Date horaCierra) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantidadPuntosDeAtencion = cantidadPuntosDeAtencion;
        this.horaAbre = horaAbre;
        this.horaCierra = horaCierra;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() { 
        return nombre; 
    }

    public String getDireccion() { 
        return direccion;
    }

    public int getCantidadPuntosDeAtencion() {
         return cantidadPuntosDeAtencion; 
    }

    public Date getHoraAbre() {
         return horaAbre; 
    }

    public Date getHoraCierra() {
        return horaCierra; 
   }

    
    public void setId(Integer id) { 
        this.id = id;
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }

    public void setCantidadPuntosDeAtencion(int cantidadPuntosDeAtencion) {
         this.cantidadPuntosDeAtencion = cantidadPuntosDeAtencion; 
    }

    public void setHoraAbre(Date horaAbre) { 
        this.horaAbre = horaAbre;
    }
    
    public void setHoraCierra(Date horaCierra) { 
        this.horaCierra = horaCierra;
    }  
}