package controlador;

import vista.Login;

/**
 *
 * @author Roberto
 */
public class Autenticador {

    public static void main(String[] args) {
        Login objetoLogin = new Login();
        objetoLogin.setLocationRelativeTo(null); // Esto centra la ventana no lo quiten xd
        objetoLogin.setVisible(true);
    }

}
