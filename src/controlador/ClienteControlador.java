package controlador;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.DAO.ClienteDAO;
import vista.Clientes;

/**
 *
 * @author Roberto
 */
public class ClienteControlador implements ActionListener {

    //Instancias de cada objeto jeje
    Cliente cliente = new Cliente();
    ClienteDAO clientedao = new ClienteDAO();
    Clientes vista = new Clientes();
    DefaultTableModel TablaClientes = new DefaultTableModel();

    //Variables globales de cliente
    private int idCliente = 0;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipo;
    private String email;

    public ClienteControlador(Clientes vista) {
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos() {
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnBorrar().addActionListener(this);
        vista.getBtnLimpiar().addActionListener(this);
        vista.getTablaDatos().addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }

        });
    }

    private void listarTabla() {
        String[] titulos = new String[]{"ID", "Nombre", "Dirección", "Telefono", "TipoCliente", "Email"};
        TablaClientes = new DefaultTableModel(titulos, 0);
        List<Cliente> listaClientes = clientedao.listar();
        for (Cliente cliente1 : listaClientes) {
            TablaClientes.addRow(new Object[]{cliente1.getIdCliente(),
                cliente1.getNombre(), cliente1.getDireccion(),
                cliente1.getTelefono(), cliente1.getTipo(),
                cliente1.getEmail()});
        }
        vista.getTablaDatos().setModel(TablaClientes);
        vista.getTablaDatos().setPreferredSize(new Dimension(500, TablaClientes.getRowCount() * 16));
    }

    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        idCliente = (int) vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 0);
        vista.getNombreTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vista.getDireccionTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vista.getTelefonoTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 3).toString());
        vista.getTipoSeleccion().setSelectedItem(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 4).toString());
        vista.getEmailTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 5).toString());
    }

    //---------------------------------------------------------------------------
    private boolean validarDatos() {
        if ("".equals(vista.getNombreTxt().getText())
                || "".equals(vista.getDireccionTxt().getText())
                || "".equals(vista.getTelefonoTxt().getText())
                || "".equals(vista.getTipoSeleccion().getSelectedItem())
                || "".equals(vista.getEmailTxt().getText())) {
            JOptionPane.showMessageDialog(null, "Los campos no se pueden encontrar vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean cargarDatos() {
        try {
            nombre = vista.getNombreTxt().getText();
            direccion = vista.getDireccionTxt().getText();
            telefono = vista.getTelefonoTxt().getText();
            tipo = (String) vista.getTipoSeleccion().getSelectedItem();
            email = vista.getEmailTxt().getText();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El campo telefono debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al cargar los datos: " + e);
            return false;
        }
    }

    private void limpiarCampos() {

        vista.getNombreTxt().setText("");
        vista.getDireccionTxt().setText("");
        vista.getTelefonoTxt().setText("");
        vista.getTipoSeleccion().setSelectedItem("");
        vista.getEmailTxt().setText("");

        idCliente = 0;
        nombre = "";
        direccion = "";
        tipo = null;
        email = "";

    }

    //--------------------------------------------------------------------------
    public void agregarCliente() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Cliente cliente = new Cliente(nombre, direccion, telefono, tipo, email);
                    clientedao.agregar(cliente); //Objeto y el metodo 
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    limpiarCampos();
                }
            }
        } catch (Exception e) {
            System.out.println("(Controlador) - Error al agregar: " + e);
        } finally {
            listarTabla();
        }
    }

    private void actualizarCliente() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Cliente cliente = new Cliente(idCliente, nombre, direccion, telefono, tipo, email);
                    clientedao.actualizar(cliente); //Objeto y el metodo 
                    JOptionPane.showMessageDialog(null, "Actualización exitosa");
                    limpiarCampos();
                }

            }
        } catch (Exception e) {
            System.out.println("(Controlador) - Error al actualizar: " + e);
        } finally {
            listarTabla();
        }
    }

    private void borrarCliente() {
        try {
            if (idCliente != 0) {
                clientedao.borrar(idCliente);
                JOptionPane.showMessageDialog(null, "Eliminación exitosa");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Se debe seleccionar un cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            System.out.println("(Controlador) - Error al borrar: " + e);
        } finally {
            listarTabla();
        }
    }

    //ACCIONES A LOS BOTONCITOS
    @Override

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnAgregar()) {
            agregarCliente();
        }

        if (ae.getSource() == vista.getBtnActualizar()) {
            actualizarCliente();
        }

        if (ae.getSource() == vista.getBtnBorrar()) {
            borrarCliente();

        }

        if (ae.getSource() == vista.getBtnLimpiar()) {
            limpiarCampos();
        }

    }
}
