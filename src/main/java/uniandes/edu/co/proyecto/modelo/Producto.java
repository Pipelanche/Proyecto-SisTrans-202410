package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoProducto tipo;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "clienteTipoDeDocumento", referencedColumnName = "tipoDeDocumento"),
        @JoinColumn(name = "clienteNumeroDeDocumento", referencedColumnName = "numeroDeDocumento")
    })
    private Usuario cliente;

    public enum TipoProducto {
        cuenta, prestamo, cdt
    }

    public Producto() {
    }

    public Producto(TipoProducto tipo, Usuario cliente) {
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }
}
