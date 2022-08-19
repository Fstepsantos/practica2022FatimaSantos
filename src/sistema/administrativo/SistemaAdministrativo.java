package sistema.administrativo;

import javax.swing.JFrame;

/**
 *
 * @author Fstep
 */
public class SistemaAdministrativo {

    public static void main(String[] args) {
        ventana marco = new ventana();
        marco.setVisible(true);
        marco.setTitle("Sistema Administrativo de clientes y recursos");
        marco.setSize(500, 350);
        marco.setLocationRelativeTo(null);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
