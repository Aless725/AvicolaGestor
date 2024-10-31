package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vista.Home;
import vista.Login;  // Importar la clase Login

public class LoginControlador {

    private Login loginForm;  // Añadir una referencia a la ventana de login

    // Constructor que recibe la ventana de login
    public LoginControlador(Login loginForm) {
        this.loginForm = loginForm;
    }

    public void ValidarUsuario(JTextField usuario, JPasswordField contraseña) {
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;

            controlador.ConexionDB obConexion = new controlador.ConexionDB();
            Connection con = obConexion.EstablecerConexion();

            // Consulta SQL para la base de datos
            String consulta = "SELECT * FROM usuario WHERE Email = ? AND Contraseña = ?";
            ps = con.prepareStatement(consulta);

            String contra = String.valueOf(contraseña.getPassword());

            // Configurando los parámetros del PreparedStatement
            ps.setString(1, usuario.getText());
            ps.setString(2, contra);

            // Ejecutar la consulta
            rs = ps.executeQuery();

            // Verificar si el usuario existe en la base de datos
            if (rs.next()) {
                Home objeMenu = new Home();
                objeMenu.setVisible(true);  // Mostrar la ventana principal
                objeMenu.setLocationRelativeTo(null);  // Esto centrará la ventana Home

                loginForm.dispose();  // Cerrar la ventana de login
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ingresado no es válido");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
}
