package sistema.administrativo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fstep
 */
public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel PLogin = new JPanel();
    JPanel PControl = new JPanel();
    JPanel PCrear = new JPanel();
    int control = 2;
    cliente clientes[] = new cliente[100];
    int controlCliente = 0;
    JPanel PCClientes = new JPanel();
    int controlClientes = 2;

    //Metodo constructor
    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "Admi";
        usuSistema[0].nombre = "administrador";
        usuSistema[0].contra = "123";
        //Usuarios de prueba  
        usuSistema[1] = new usuario();
        usuSistema[1].nombreUsuario = "j";
        usuSistema[1].nombre = "Juan Perez";
        usuSistema[1].contra = "0";
    }

    public void crearClientes() {
        clientes[0] = new cliente();
        clientes[0].Nombre = "Marta";
        clientes[0].Edad = 20;
        clientes[0].genero = 'F';
        clientes[0].NIT = 147852;

    }

    public void objetos() {
        //crear cristal donde estarán todos los elementos 
        this.getContentPane().add(PLogin); //this sirve para indicarle que ha esa ventana se le ha creado un panel

        JLabel lblLogin = new JLabel("Login");
        PLogin.setLayout(null); //setLayout se utiliza para que el programa tome nuestra posición
        lblLogin.setFont(new Font("Serif", Font.PLAIN, 25));
        lblLogin.setBounds(21, 10, 300, 35);
        PLogin.add(lblLogin);

        JLabel lbUsu = new JLabel("Usuario");
        PLogin.setLayout(null);
        lbUsu.setBounds(41, 62, 100, 35);
        lbUsu.setFont(new Font("Serif", Font.PLAIN, 15));
        PLogin.add(lbUsu);

        JLabel lbCon = new JLabel("Contraseña");
        PLogin.setLayout(null);
        lbCon.setFont(new Font("Serif", Font.PLAIN, 15));
        lbCon.setBounds(41, 120, 100, 35);
        PLogin.add(lbCon);

        JTextField txtUsu = new JTextField();
        txtUsu.setBounds(160, 66, 180, 29);
        PLogin.add(txtUsu);

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(160, 126, 180, 29);
        PLogin.add(txtContra);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(50, 260, 150, 29);
        PLogin.add(btnIngresar);
        ActionListener ingresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtUsu.getText().equals("") || txtContra.getPassword().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");
                } else {
                    String nombreUsuario = txtUsu.getText();
                    char contra[] = txtContra.getPassword();
                    String con = new String(contra);
                    recorrerUsuarios(txtUsu.getText(), con);
                }
            }
        };
        btnIngresar.addActionListener(ingresar);

        JButton btnCrearUsu = new JButton("Registrarse");
        btnCrearUsu.setBounds(230, 260, 150, 29);
        PLogin.add(btnCrearUsu);
        ActionListener crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelCrearUsu();
                PCrear.setVisible(true);
            }
        };
        btnCrearUsu.addActionListener(crear);
    }

    public void recorrerUsuarios(String nombreUsuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (usuSistema[i] != null) {
                if (usuSistema[i].nombreUsuario.equals(nombreUsuario) && usuSistema[i].contra.equals(contra)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido al Sistema" + " " + nombreUsuario);
                    panelControl();
                    encontrado = true;
                    break;
                } else {
                    encontrado = false;
                }
            }
        }
        if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }

    public void panelControl() {
        this.getContentPane().add(PControl);
        PControl.setLayout(null);
        this.setSize(450, 350);
        this.setTitle("Control Principal");
        PLogin.setVisible(false);

        JButton btnAdminCl = new JButton("Administración de Clientes");
        btnAdminCl.setBounds(100, 60, 250, 30);
        PControl.add(btnAdminCl);
        ActionListener AdmiClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PCCli();
                PCClientes.setVisible(true);
            }

        };
        btnAdminCl.addActionListener(AdmiClientes);

        JButton btnAdminPro = new JButton("Administración de Productos");
        btnAdminPro.setBounds(100, 120, 250, 30);
        PControl.add(btnAdminPro);

        JButton btnAdminRe = new JButton("Administración de Reportes");
        btnAdminRe.setBounds(100, 180, 250, 30);
        PControl.add(btnAdminRe);
    }

    public void panelCrearUsu() {
        this.getContentPane().add(PCrear);
        PCrear.setLayout(null);
        this.setSize(500, 350);
        this.setTitle("Crear Usuario");
        PLogin.setVisible(false);

        JLabel lblRegistro = new JLabel("Crea tu usuario");
        PCrear.setLayout(null);
        lblRegistro.setFont(new Font("Serif", Font.PLAIN, 25));
        lblRegistro.setBounds(21, 10, 300, 35);
        PCrear.add(lblRegistro);

        JLabel lblN = new JLabel("Usuario");
        PCrear.setLayout(null);
        lblN.setBounds(41, 62, 100, 35);
        lblN.setFont(new Font("Serif", Font.PLAIN, 15));
        PCrear.add(lblN);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(200, 68, 180, 25);
        PCrear.add(txtNombre);

        JLabel lblU = new JLabel("Nombre");
        PCrear.setLayout(null);
        lblU.setBounds(41, 102, 200, 35);
        lblU.setFont(new Font("Serif", Font.PLAIN, 15));
        PCrear.add(lblU);

        JTextField txtUsu = new JTextField();
        txtUsu.setBounds(200, 108, 180, 25);
        PCrear.add(txtUsu);

        JLabel lblC = new JLabel("Contraseña");
        PCrear.setLayout(null);
        lblC.setFont(new Font("Serif", Font.PLAIN, 15));
        lblC.setBounds(41, 142, 200, 35);
        PCrear.add(lblC);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(200, 148, 180, 25);
        PCrear.add(txtContra);

        JLabel lblCC = new JLabel("Confirmar contraseña");
        PCrear.setLayout(null);
        lblCC.setFont(new Font("Serif", Font.PLAIN, 15));
        lblCC.setBounds(41, 182, 200, 35);
        PCrear.add(lblCC);

        JTextField txtCC = new JTextField();
        txtCC.setBounds(200, 188, 180, 25);
        PCrear.add(txtCC);

        JButton btnReg = new JButton("Registrarse");
        btnReg.setBounds(60, 260, 140, 29);
        PCrear.add(btnReg);
        ActionListener registrar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsu.getText().equals("") || txtNombre.getText().equals("") || txtContra.getText().equals("") || txtCC.getText().equals("")) {
                    System.out.println("Si coinciden");
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");

                } else {
                    if (txtContra.getText().equals(txtCC.getText())) {
                        GuardarUsu(txtNombre.getText(), txtUsu.getText(), txtContra.getText());
                        txtNombre.setText("");
                        txtUsu.setText("");
                        txtContra.setText("");
                        txtCC.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas NO coinciden");
                    }
                }
            }
        };
        btnReg.addActionListener(registrar);

        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBounds(250, 260, 140, 29);
        PCrear.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PLogin.setVisible(true);
                PCrear.setVisible(false);
            }
        };
        btnVolver.addActionListener(volverInicio);
        volverinicio();
    }

    public void volverinicio() {
        this.setTitle("Sistema de clientes y recursos");
        this.setSize(450, 350);
    }

    public void GuardarUsu(String Usu, String nombre, String Contra) {
        int posicion = 0;
        if (control < 10) {
            for (int i = 0; 1 < 9; i++) {
                if (usuSistema[i] == null) {
                    posicion = i;
                    break;
                }
            }
            //System.out.println("Posición libre" + " " +posicion);
            usuSistema[posicion] = new usuario();
            usuSistema[posicion].nombreUsuario = Usu;
            usuSistema[posicion].nombre = nombre;
            usuSistema[posicion].contra = Contra;
            control++;
            JOptionPane.showMessageDialog(null, "Ususario registrado correctamente, total de espacios" + " " + control);

        } else {

            JOptionPane.showMessageDialog(null, "No se pueden registrar más usuarios");
        }
    }

    public void PCCli() {
        this.getContentPane().add(PCClientes);
        PCClientes.setLayout(null);
        this.setSize(590, 400);
        this.setTitle("Control de clientes");
        PControl.setVisible(false);

        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Genero");
        datosTabla.addColumn("NIT");

        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                String fila[] = {clientes[i].Nombre, String.valueOf(clientes[i].Edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].NIT)};
                datosTabla.addRow(fila);
            }
        }
        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 300, 300);
        PCClientes.add(barraTablaClientes);

        JButton btnCargarArchivo = new JButton("Buscar Archivos CSV");
        btnCargarArchivo.setBounds(320, 10, 200, 25);
        PCClientes.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                //Prueba
                System.out.println("La ubicación del archivo es:" + archivoSeleccionado.getPath());
                leerArchivoCSV(archivoSeleccionado.getPath());
            }
        };
        btnCargarArchivo.addActionListener(buscarArchivo);
    }

    public void leerArchivoCSV(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String DatosSeparados[] = lineaLeida.split(",");
                    int posicion = 0;
                    if (controlClientes < 10) {
                        for (int i = 0; 1 < 99; i++) {
                            if (clientes[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        clientes[posicion] = new cliente();
                        clientes[posicion].Nombre = DatosSeparados[0];
                        clientes[posicion].Edad = Integer.parseInt(DatosSeparados[1]);
                        clientes[posicion].genero = DatosSeparados[2].charAt(0);
                        clientes[posicion].NIT = Integer.parseInt(DatosSeparados[3]);
                        controlClientes++;
                        JOptionPane.showMessageDialog(null, "Clientes registrados correctamente, total de espacios" + " " + controlClientes);

                    } else {

                        JOptionPane.showMessageDialog(null, "No se pueden registrar más clientes");
                    }
                }
            }
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir archivo CSV");

        }

    }
}
// lengh obteniamos el tamaño de caracteres
//Split es un metodo que separa las cadenas de textos en vectores 
