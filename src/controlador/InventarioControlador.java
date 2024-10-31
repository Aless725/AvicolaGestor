package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.DAO.InventarioDAO;
import modelo.Inventario;
import vista.Inventarios;

/**
 *
 * @author Roberto
 */
public class InventarioControlador implements ActionListener {

    Inventario inventario = new Inventario();
    InventarioDAO inventariodao = new InventarioDAO();
    Inventarios vista = new Inventarios();
    DefaultTableModel TablaDatos = new DefaultTableModel();

    public InventarioControlador(Inventarios vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos() {
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnLimpiar().addActionListener(this);
        vista.getTablaDatos().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }
        });
    }

    private void listarTabla() {
        String[] titulos = new String[]{"IDInventario", "Descripcion"};
        TablaDatos = new DefaultTableModel(titulos, 0);
        List<Inventario> listarInventario = inventariodao.listar();
        for (Inventario inventario : listarInventario) {
            TablaDatos.addRow(new Object[]{inventario.getIdInventario(), inventario.getDescripcion()});
        }
        vista.getTablaDatos().setModel(TablaDatos);
        vista.getTablaDatos().setPreferredSize(new Dimension(250, TablaDatos.getRowCount() * 16));
    }

    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        vista.getNInventarioTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 2).toString());
    }

    @Override

    public void actionPerformed(ActionEvent ae) {
    }

}
