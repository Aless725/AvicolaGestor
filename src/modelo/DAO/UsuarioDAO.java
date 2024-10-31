package modelo.DAO;

import controlador.ConexionDB;
import controlador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roberto
 */
public class UsuarioDAO {

    ConexionDB obconexion = new ConexionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Listado de todos los datos para la tablita de la base de datos
public List<Usuario> listar() {
    List<Usuario> listaUsuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuario";
    
    try (Connection con = obconexion.EstablecerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("IDUsuario")); // Asegúrate de que los nombres de las columnas coincidan
            usuario.setNombreUsuario(rs.getString("Nombre"));
            usuario.setApellidoUsuario(rs.getString("Apellido"));
            usuario.setEmail(rs.getString("Email"));
            usuario.setContraseña(rs.getString("Contraseña"));
            usuario.setRol(rs.getString("Rol"));
            listaUsuarios.add(usuario);
        }
        System.out.println("Usuarios listados correctamente: " + listaUsuarios.size());
    } catch (SQLException e) {
        System.err.println("(DAO) - Error al listar los usuarios: " + e.getMessage());
    }
    
    return listaUsuarios;
}

    //Metodo de AGREGAR
    public void agregar(Usuario usuario) {
        String sql = "INSERT INTO usuario(Nombre, Apellido, Email, Contraseña, Rol) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = obconexion.EstablecerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getApellidoUsuario());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getRol());
            ps.executeUpdate();  //se usa para INSERT de los datitos
        } catch (SQLException e) {
            System.out.println("(DAO) - Error al agregar: " + e.getMessage());
        }
    }

    //Metodo ACTUALIZAR
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE usuario set Nombre = ?, Apellido = ?, Email = ?, Contraseña = ?, Rol = ? WHERE IDUsuario = ?";
        try {
            con = obconexion.EstablecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getApellidoUsuario());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getRol());
            ps.setInt(6, usuario.getIdUsuario());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("(DAO) - Error al actualizar: " + e);
        }
    } //Fin del metodo de ACTUALIZAR

    //Metodo BORRAR
    public void borrar(int id) {
        String sqlCheck = "SELECT COUNT(*) FROM ventadetalle WHERE IDUsuario = ?";
        String sqlDelete = "DELETE FROM usuario WHERE IDUsuario = ?";

        try {
            con = obconexion.EstablecerConexion();
            // Verificar si existen dependencias en ventadetalle
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("(DAO) - No se puede borrar el cliente, existen registros relacionados en ventadetalle");
                return;
            }

            // Si no hay dependencias, elimina el cliente
            ps = con.prepareStatement(sqlDelete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("(DAO) - Error al borrar: " + e);
        }
    }
}
//Fin del metodo de BORRAR
