/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.DAO;

import controlador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;

/**
 *
 * @author Roberto
 */
public class VentasDAO {

    ConexionDB conexion = new ConexionDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Método para listar todas las ventas
    public List<Venta> listar() {
        String sql = "SELECT * FROM ventadetalle";
        List<Venta> lista = new ArrayList<>();
        try {
            con = conexion.EstablecerConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("IDVenta"));
                venta.setFecha(rs.getString("Fecha"));
                venta.setMetodoPago(rs.getString("MetodoPago"));
                venta.setIdUsuario(rs.getInt("IDUsuario"));
                venta.setIdCliente(rs.getInt("IDCliente"));
                venta.setTipoDocumento(rs.getString("TipoDocumento"));
                venta.setSerieDocumento(rs.getString("SerieDocumento"));
                venta.setNumeroDocumento(rs.getString("NumeroDocumento"));
                venta.setRucCliente(rs.getString("RUCCliente"));
                venta.setTotal(rs.getDouble("Total"));
                venta.setIdProducto(rs.getInt("IDProducto"));
                venta.setCantidad(rs.getInt("Cantidad"));
                venta.setPrecioUnitario(rs.getDouble("PrecioUnitario"));
                venta.setSubtotal(rs.getDouble("Subtotal"));
                lista.add(venta);
            }
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR VENTAS: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("ERROR AL CERRAR CONEXIÓN: " + e);
            }
        }
        return lista;
    }

    // Método para agregar una nueva venta
    public boolean agregar(Venta venta) {
        String sql = "INSERT INTO ventadetalle (MetodoPago, TipoDocumento, NumeroDocumento, RUCCliente) VALUES (?, ?, ?, ?)";
        try {
            con = conexion.EstablecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getMetodoPago());
            ps.setString(2, venta.getTipoDocumento());
            ps.setString(3, venta.getNumeroDocumento());
            ps.setString(4, venta.getRucCliente());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR AL AGREGAR VENTA: " + e);
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("ERROR AL CERRAR CONEXIÓN: " + e);
            }
        }
    }

    // Método para actualizar una venta existente
    public boolean actualizar(Venta venta) {
        String sql = "UPDATE ventadetalle SET Fecha=?, MetodoPago=?, IDUsuario=?, IDCliente=?, TipoDocumento=?, SerieDocumento=?, NumeroDocumento=?, RUCCliente=?, Total=?, IDProducto=?, Cantidad=?, PrecioUnitario=?, Subtotal=? WHERE IDVenta=?";
        try {
            con = conexion.EstablecerConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getFecha());
            ps.setString(2, venta.getMetodoPago());
            ps.setInt(3, venta.getIdUsuario());
            ps.setInt(4, venta.getIdCliente());
            ps.setString(5, venta.getTipoDocumento());
            ps.setString(6, venta.getSerieDocumento());
            ps.setString(7, venta.getNumeroDocumento());
            ps.setString(8, venta.getRucCliente());
            ps.setDouble(9, venta.getTotal());
            ps.setInt(10, venta.getIdProducto());
            ps.setInt(11, venta.getCantidad());
            ps.setDouble(12, venta.getPrecioUnitario());
            ps.setDouble(13, venta.getSubtotal());
            ps.setInt(14, venta.getIdVenta());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR VENTA: " + e);
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("ERROR AL CERRAR CONEXIÓN: " + e);
            }
        }
    }
}
