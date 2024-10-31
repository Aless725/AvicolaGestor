package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Venta;
import vista.Ventas;
import modelo.DAO.VentasDAO;

/**
 *
 * @author Roberto
 */
public class VentasControlador implements ActionListener {

    // Instancias de cada objeto
    Venta venta = new Venta();
    VentasDAO ventasdao = new VentasDAO();
    Ventas vista;
    DefaultTableModel tablaVentas = new DefaultTableModel();

    // Variables globales de venta
    private int idVenta = 0;
    private String producto;
    private int cantidad;
    private double precio;
    private double total;

    public VentasControlador(Ventas vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos() {
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnEditar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnLimpiar().addActionListener(this);
        vista.getTablaDatos().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }
        });
    }

    private void listarTabla() {
        String[] titulos = {"IDVenta", "Fecha", "MetodoPago", "IDUsuario", "IDCliente", "TipoDocumento", "SerieDocumento", "RUCCLiente", "Total", "IDProducto", "Cantidad", "PrecioUnitario", "Subtotal"};
        tablaVentas = new DefaultTableModel(titulos, 0);
        List<Venta> listaVentas = ventasdao.listar();

        for (Venta venta : listaVentas) {
            tablaVentas.addRow(new Object[]{
                venta.getIdVenta(),
                venta.getFecha(),
                venta.getMetodoPago(),
                venta.getIdUsuario(),
                venta.getIdCliente(),
                venta.getTipoDocumento(),
                venta.getSerieDocumento(),
                venta.getRucCliente(),
                venta.getTotal(),
                venta.getIdProducto(),
                venta.getCantidad(),
                venta.getPrecioUnitario(),
                venta.getSubtotal()
            });
        }

        vista.getTablaDatos().setModel(tablaVentas);
        vista.getTablaDatos().setPreferredSize(new Dimension(500, tablaVentas.getRowCount() * 16));
    }

    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();

        // Obtener los valores de la fila seleccionada
        idVenta = (int) vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 0);
        String metodoPago = vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 3).toString();
        String tipoDocumento = vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 5).toString();
        String numeroDocumento = vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 7).toString();
        String producto = vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 9).toString();

        // Asignar los valores a los campos correspondientes en la vista
        vista.getSelectMetodoPago().setSelectedItem(metodoPago);
        vista.getSelectTDocumento().setSelectedItem(tipoDocumento);
        vista.getNDocumentoTxt().setText(numeroDocumento);
        vista.getSelectProductos().setSelectedItem(producto);
    }
    
    /*
    private boolean validarDatos() {
        if (vista.getProductoTxt().getText().isEmpty()
                || vista.getCantidadTxt().getText().isEmpty()
                || vista.getPrecioTxt().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean cargarDatos() {
        try {
            producto = vista.getProductoTxt().getText();
            cantidad = Integer.parseInt(vista.getCantidadTxt().getText());
            precio = Double.parseDouble(vista.getPrecioTxt().getText());
            total = cantidad * precio;
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void limpiarCampos() {
        vista.getProductoTxt().setText("");
        vista.getCantidadTxt().setText("");
        vista.getPrecioTxt().setText("");

        idVenta = 0;
        producto = "";
        cantidad = 0;
        precio = 0.0;
        total = 0.0;
    }

    private void agregarVenta() {
        if (validarDatos() && cargarDatos()) {
            Venta venta = new Venta(producto, cantidad, precio, total);
            ventaDAO.agregar(venta);
            JOptionPane.showMessageDialog(null, "Venta registrada con éxito");
            limpiarCampos();
            listarTabla();
        }
    }

    private void actualizarVenta() {
        if (validarDatos() && cargarDatos()) {
            Venta venta = new Venta(idVenta, producto, cantidad, precio, total);
            ventaDAO.actualizar(venta);
            JOptionPane.showMessageDialog(null, "Venta actualizada con éxito");
            limpiarCampos();
            listarTabla();
        }
    }

    private void borrarVenta() {
        if (idVenta != 0) {
            ventaDAO.borrar(idVenta);
            JOptionPane.showMessageDialog(null, "Venta eliminada con éxito");
            limpiarCampos();
            listarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una venta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    */
    /*
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnAgregar()) {
            agregarVenta();
        } else if (ae.getSource() == vista.getBtnActualizar()) {
            actualizarVenta();
        } else if (ae.getSource() == vista.getBtnBorrar()) {
            borrarVenta();
        } else if (ae.getSource() == vista.getBtnLimpiar()) {
            limpiarCampos();
        }
    }
*/

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
}
