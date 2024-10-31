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
import modelo.Usuario;
import modelo.DAO.UsuarioDAO;
import vista.Usuarios;

/**
 *
 * @author Roberto
 */
public class UsuarioControlador implements ActionListener {

    // Instancias de cada objeto
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuarios vista;
    private DefaultTableModel tablaUsuarios = new DefaultTableModel();

    // Variables globales de usuario
    private int idUsuario = 0;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String rol;

    public UsuarioControlador(Usuarios vista) {
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
        String[] titulos = {"ID", "Nombre", "Apellido", "Email", "Contraseña", "Rol"};
        tablaUsuarios = new DefaultTableModel(titulos, 0);
        List<Usuario> listaUsuarios = usuarioDAO.listar();
        for (Usuario usuario : listaUsuarios) {
            tablaUsuarios.addRow(new Object[]{
                usuario.getIdUsuario(), usuario.getNombreUsuario(),
                usuario.getApellidoUsuario(), usuario.getEmail(),
                usuario.getContraseña(), usuario.getRol()
            });
        }
        vista.getTablaDatos().setModel(tablaUsuarios);
        vista.getTablaDatos().setPreferredSize(new Dimension(500, tablaUsuarios.getRowCount() * 16));
    }

    private void llenarCampos(MouseEvent e) {
        JTable target = (JTable) e.getSource();
        idUsuario = (int) vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 0);
        vista.getNombreTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vista.getApellidoTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vista.getEmailTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 3).toString());
        vista.getContraseñaTxt().setText(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 4).toString());
        vista.getRolSeleccion().setSelectedItem(vista.getTablaDatos().getModel().getValueAt(target.getSelectedRow(), 5).toString());
    }

    private boolean validarDatos() {
        if (vista.getNombreTxt().getText().isEmpty()
                || vista.getApellidoTxt().getText().isEmpty()
                || vista.getEmailTxt().getText().isEmpty()
                || vista.getContraseñaTxt().getText().isEmpty()
                || vista.getRolSeleccion().getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Los campos no se pueden encontrar vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean cargarDatos() {
        try {
            nombre = vista.getNombreTxt().getText();
            apellido = vista.getApellidoTxt().getText();
            email = vista.getEmailTxt().getText();
            contraseña = vista.getContraseñaTxt().getText();
            rol = vista.getRolSeleccion().getSelectedItem().toString();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void limpiarCampos() {
        vista.getNombreTxt().setText("");
        vista.getApellidoTxt().setText("");
        vista.getEmailTxt().setText("");
        vista.getContraseñaTxt().setText("");
        vista.getRolSeleccion().setSelectedItem("");

        idUsuario = 0;
        nombre = "";
        apellido = "";
        email = "";
        contraseña = "";
        rol = "";
    }

    private void agregarUsuario() {
        if (validarDatos() && cargarDatos()) {
            Usuario usuario = new Usuario(nombre, apellido, email, contraseña, rol);
            usuarioDAO.agregar(usuario);
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            limpiarCampos();
            listarTabla();
        }
    }

    private void actualizarUsuario() {
        if (validarDatos() && cargarDatos()) {
            Usuario usuario = new Usuario(idUsuario, nombre, apellido, email, contraseña, rol);
            usuarioDAO.actualizar(usuario);
            JOptionPane.showMessageDialog(null, "Actualización exitosa");
            limpiarCampos();
            listarTabla();
        }
    }

    private void borrarUsuario() {
        if (idUsuario != 0) {
            usuarioDAO.borrar(idUsuario);
            JOptionPane.showMessageDialog(null, "Eliminación exitosa");
            limpiarCampos();
            listarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnAgregar()) {
            agregarUsuario();
        } else if (ae.getSource() == vista.getBtnActualizar()) {
            actualizarUsuario();
        } else if (ae.getSource() == vista.getBtnBorrar()) {
            borrarUsuario();
        } else if (ae.getSource() == vista.getBtnLimpiar()) {
            limpiarCampos();
        }
    }
}
