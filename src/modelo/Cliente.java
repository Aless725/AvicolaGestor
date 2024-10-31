package modelo;

/**
 * MODELO DTO CLIENTE
 */
public class Cliente {

    private int idCliente;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipo;
    private String email;

    //El constructor vacio
    public Cliente() {
    }

    //Constructor para AGREGAR
    public Cliente(String nombre, String direccion, String telefono, String tipo, String email) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.email = email;
    }

    //Contructor para ACTUALIZAR
    public Cliente(int idCliente, String nombre, String direccion, String telefono, String tipo, String email) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.email = email;
    }

    //sus reales getter y setter
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
