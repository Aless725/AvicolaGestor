package modelo;

/**
 *
 * @author Roberto
 */
public class Inventario {

    private int idInventario;
    private String Descripcion;

    public Inventario() {
    }

    //Contructor para agregar
    public Inventario(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    //Constructor para actualizar
    public Inventario(int idInventario, String Descripcion) {
        this.idInventario = idInventario;
        this.Descripcion = Descripcion;
    }

    //Getter y setter
    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

}
