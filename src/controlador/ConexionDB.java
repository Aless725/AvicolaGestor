package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionDB {

    Connection con;

    public Connection EstablecerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/avicoladb", "root", "");
            //JOptionPane.showMessageDialog(null, "Se realizo la conexión correctamente"); (NO ACTIVAR)
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se encontro un problema en la conexión" + e.toString());
        }
        return con;
    }
}
