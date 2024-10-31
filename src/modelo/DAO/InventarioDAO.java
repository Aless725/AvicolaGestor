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
import modelo.Inventario;

/**
 *
 * @author Roberto
 */
public class InventarioDAO {

    ConexionDB conexion = new ConexionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

        //Listado de todos los datos para la tablita de la base de datos
    public List<Inventario> listar() {
        String sql = "SELECT * FROM inventario";
        List<Inventario> lista = new ArrayList<>();
        try (Connection con = conexion.EstablecerConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setIdInventario(rs.getInt("IDInventario"));
                inventario.setDescripcion(rs.getString("Descripcion"));
                lista.add(inventario);
            }

        } catch (SQLException e) {
            System.err.println("(DAO) - Error al listar los clientes: " + e.getMessage());
        }

        return lista;
    }

    
    
}
