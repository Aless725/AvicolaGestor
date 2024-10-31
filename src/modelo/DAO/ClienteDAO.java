package modelo.DAO;

import controlador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roberto
 */
public class ClienteDAO {

    ConexionDB conexion = new ConexionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Listado de todos los datos para la tablita de la base de datos
    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> listaClientes = new ArrayList<>();
        try (Connection con = conexion.EstablecerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IDCliente")); // Se usa el nombre de las columnas
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setTipo(rs.getString("TipoCliente"));
                cliente.setEmail(rs.getString("Email"));

                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("(DAO) - Error al listar los clientes: " + e.getMessage());
        }

        return listaClientes;
    }

    //Metodo de AGREGAR
    public void agregar(Cliente cliente) {
        String sql = "INSERT INTO cliente(Nombre, Direccion, Telefono, TipoCliente, Email) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = conexion.EstablecerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getTipo());
            ps.setString(5, cliente.getEmail());
            ps.executeUpdate();  //se usa para INSERT de los datitos
        } catch (SQLException e) {
            System.out.println("(DAO) - Error al agregar: " + e.getMessage());
        }
    }

    //Metodo ACTUALIZAR
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE cliente set Nombre = ?, Direccion = ?, Telefono = ?, TipoCliente = ?, Email = ? WHERE IDCliente = ?";
        try {
            con = conexion.EstablecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getTipo());
            ps.setString(5, cliente.getEmail());
            ps.setInt(6, cliente.getIdCliente());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("(DAO) - Error al actualizar: " + e);
        }
    } //Fin del metodo de ACTUALIZAR

    //Metodo BORRAR
    public void borrar(int id) {
        String sqlCheck = "SELECT COUNT(*) FROM ventadetalle WHERE IDCliente = ?";
        String sqlDelete = "DELETE FROM cliente WHERE IDCliente = ?";

        try {
            con = conexion.EstablecerConexion();
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
//Fin del metodo de BORRAR

}
