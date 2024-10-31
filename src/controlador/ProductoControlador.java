package controlador;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.DAO.ProductoDAO;
import vista.Productos;

/**
 * Controlador para la gestión de productos en el sistema avícola.
 *
 * @author Roberto
 */
public class ProductoControlador {

    // Instancias de cada objeto
    private ProductoDAO productoDAO = new ProductoDAO();
    private Productos vista;
    private DefaultTableModel tablaProductos = new DefaultTableModel();

    // Variables globales de producto
    private int idProducto = 0;

    public ProductoControlador(Productos vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos() {
        vista.getTablaProducto().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }
        });
    }

    private void listarTabla() {
        String[] titulos = {"IDProducto", "SKU", "Nombre", "Descripción", "Precio", "Stock", "IDInventario"};
        tablaProductos = new DefaultTableModel(titulos, 0);
        List<Producto> listaProductos = productoDAO.listar();
        for (Producto producto : listaProductos) {
            tablaProductos.addRow(new Object[]{
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
            });
        }
        vista.getTablaProducto().setModel(tablaProductos);
        vista.getTablaProducto().setPreferredSize(new Dimension(500, tablaProductos.getRowCount() * 16));
    }

    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        idProducto = (int) vista.getTablaProducto().getModel().getValueAt(target.getSelectedRow(), 0);
        vista.getTxtSku().setText(vista.getTablaProducto().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vista.getTxtNombre().setText(vista.getTablaProducto().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vista.getTxtPrecio().setText(vista.getTablaProducto().getModel().getValueAt(target.getSelectedRow(), 4).toString());
        vista.getTxtStock().setText(vista.getTablaProducto().getModel().getValueAt(target.getSelectedRow(), 5).toString());
        // Aquí puedes agregar el campo para IDInventario si lo necesitas
    }
}
