/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO;

import controlador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author carlo
 */
public class ProductoDAO {
        ConexionDB obconexion = new ConexionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Listado de todos los datos para la tablita de la base de datos
public List<Producto> listar() {
    List<Producto> listaProductos = new ArrayList<>();
    String sql = "SELECT * FROM producto";
    
    try (Connection con = obconexion.EstablecerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Producto producto = new Producto();
            producto.setIdProducto(rs.getInt("IDProducto"));
            producto.setNombre(rs.getString("SKU"));
            producto.setDescripcion(rs.getString("Nombre"));
            producto.setPrecio(rs.getDouble("Descripcion"));
            listaProductos.add(producto);
        }
        System.out.println("Usuarios listados correctamente: " + listaProductos.size());
    } catch (SQLException e) {
        System.err.println("(DAO) - Error al listar los prodcutos: " + e.getMessage());
    }
    
    return listaProductos ;
}

    //Metodo de AGREGAR
    public void agregar(Producto producto) {
        String sql = "INSERT INTO producto(IDProducto, SKU, Nombre, Descripción, Precio, Stock, IDInventario) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = obconexion.EstablecerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getFechaVencimiento());
            ps.executeUpdate();  //se usa para INSERT de los datitos
        } catch (SQLException e) {
            System.out.println("(DAO) - Error al agregar: " + e.getMessage());
        }
    }

    //Metodo ACTUALIZAR
    public void actualizar(Producto producto) {
        String sql = "UPDATE producto set Nombre = ?, Descripcion = ?, Precio = ?, FechaVencimiento = ?, Stock = ? WHERE IDProducto = ?";
        try {
            con = obconexion.EstablecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getFechaVencimiento());
            ps.setInt(6, producto.getIdProducto());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("(DAO) - Error al actualizar: " + e);
        }
    } //Fin del metodo de ACTUALIZAR

    //Metodo BORRAR
    public void borrar(int id) {
        String sqlCheck = "SELECT COUNT(*) FROM inventario WHERE IDProducto = ?";
        String sqlDelete = "DELETE FROM producto WHERE IDProducto = ?";

        try {
            con = obconexion.EstablecerConexion();
            // Verificar si existen dependencias en ventadetalle
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("(DAO) - No se puede borrar el producto, existen registros relacionados en inventario");
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
