package modelo;

/**
 *
 * @author Roberto
 */
public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private String contraseña;
    private String rol;

    //El constructor vacio
    public Usuario() {
    }

    //Constructor para AGREGAR
    public Usuario(String nombreUsuario, String apellidoUsuario, String email, String contraseña, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.email = email;
        this.rol = rol;
        this.contraseña = contraseña;
    }

    //Contructor para ACTUALIZAR
    public Usuario(int idUsuario, String nombreUsuario, String apellidoUsuario, String email, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.email = email;
        this.rol = rol;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
