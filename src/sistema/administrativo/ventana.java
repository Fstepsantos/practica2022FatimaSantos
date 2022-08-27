package sistema.administrativo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Fstep
 */
public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel PLogin;
    JPanel PControl;
    JPanel PCrear;
    int control = 2;
    cliente clientes[] = new cliente[100];
    //int controlCliente = 0;
    JPanel PCClientes;
    int controlCli = 0;
    Producto Pro[] = new Producto[100];
    JPanel PCPro;
    int controlPro = 0;

    //Metodo constructor
    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
        crearProductos();
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

    public void crearProductos() {
        Pro[0] = new Producto();
        Pro[0].Nombre = "Camisa polo";
        //Pro[0].Precio = (float) 250.50;
        Pro[0].Precio = 250;//prueba
        Pro[0].Cantidad = 12;
    }

    public void objetos() {
        //crear cristal donde estarán todos los elementos 
        PLogin = new JPanel();
        this.getContentPane().add(PLogin); //this sirve para indicarle que ha esa ventana se le ha creado un panel

        JLabel lblLogin = new JLabel("Iniciar Sesión");
        PLogin.setLayout(null); //setLayout se utiliza para que el programa tome nuestra posición
        lblLogin.setFont(new Font("Roboto", Font.TYPE1_FONT, 22));
        lblLogin.setBounds(21, 10, 300, 35);
        PLogin.add(lblLogin);

        JLabel lbUsu = new JLabel("Usuario");
        PLogin.setLayout(null);
        lbUsu.setBounds(61, 62, 100, 35);
        lbUsu.setFont(new Font("Roboto", Font.PLAIN, 15));
        PLogin.add(lbUsu);

        JLabel lbCon = new JLabel("Contraseña");
        PLogin.setLayout(null);
        lbCon.setFont(new Font("Roboto", Font.PLAIN, 15));
        lbCon.setBounds(61, 120, 100, 35);
        PLogin.add(lbCon);

        JTextField txtUsu = new JTextField();
        txtUsu.setBounds(190, 66, 205, 30);

        PLogin.add(txtUsu);

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(190, 126, 205, 30);
        PLogin.add(txtContra);

        JButton btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBounds(61, 230, 150, 39);
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

        JButton btnCrearUsu = new JButton("REGISTRARSE");
        btnCrearUsu.setBounds(245, 230, 150, 39);
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
        PControl = new JPanel();
        this.getContentPane().add(PControl);
        PControl.setLayout(null);
        this.setSize(500, 350);
        this.setTitle("Control Principal");
        PLogin.setVisible(false);

        //Boton de clientes
        JButton btnAdminCl = new JButton("Administración de Clientes");
        btnAdminCl.setBounds(100, 60, 280, 39);
        PControl.add(btnAdminCl);
        ActionListener AdmiClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PCCli();
                PCClientes.setVisible(true);
            }

        };
        btnAdminCl.addActionListener(AdmiClientes);

        //Boton de productos
        JButton btnAdminPro = new JButton("Administración de Productos");
        btnAdminPro.setBounds(100, 120, 280, 39);
        PControl.add(btnAdminPro);
        ActionListener AdmiPro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PCProductos();
                PCPro.setVisible(true);
            }

        };
        btnAdminPro.addActionListener(AdmiPro);

    }

    public void panelCrearUsu() {
        PCrear = new JPanel();
        this.getContentPane().add(PCrear);
        PCrear.setLayout(null);
        this.setSize(500, 350);
        this.setTitle("Crear Usuario");
        PLogin.setVisible(false);

        JLabel lblRegistro = new JLabel("Crea tu usuario");
        lblRegistro.setFont(new Font("Roboto", Font.TYPE1_FONT, 22));
        lblRegistro.setForeground(Color.BLACK);
        PCrear.setLayout(null);
        lblRegistro.setFont(new Font("Roboto", Font.PLAIN, 25));
        lblRegistro.setBounds(21, 10, 300, 35);
        PCrear.add(lblRegistro);

        JLabel lblN = new JLabel("Usuario");
        PCrear.setLayout(null);
        lblN.setBounds(61, 62, 100, 35);
        lblN.setFont(new Font("Roboto", Font.PLAIN, 15));
        PCrear.add(lblN);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(215, 68, 180, 25);
        PCrear.add(txtNombre);

        JLabel lblU = new JLabel("Nombre");
        PCrear.setLayout(null);
        lblU.setBounds(61, 102, 200, 35);
        lblU.setFont(new Font("Roboto", Font.PLAIN, 15));
        PCrear.add(lblU);

        JTextField txtUsu = new JTextField();
        txtUsu.setBounds(215, 108, 180, 25);
        PCrear.add(txtUsu);

        JLabel lblC = new JLabel("Contraseña");
        PCrear.setLayout(null);
        lblC.setFont(new Font("Roboto", Font.PLAIN, 15));
        lblC.setBounds(61, 142, 200, 35);
        PCrear.add(lblC);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(215, 148, 180, 25);
        PCrear.add(txtContra);

        JLabel lblCC = new JLabel("Confirmar contraseña");
        PCrear.setLayout(null);
        lblCC.setFont(new Font("Roboto", Font.PLAIN, 15));
        lblCC.setBounds(61, 182, 200, 35);
        PCrear.add(lblCC);

        JTextField txtCC = new JTextField();
        txtCC.setBounds(215, 188, 180, 25);
        PCrear.add(txtCC);

        JButton btnReg = new JButton("REGISTRARSE");
        btnReg.setBounds(61, 245, 150, 39);
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

        JButton btnVolver = new JButton("VOLVER AL INICIO");
        btnVolver.setBounds(245, 245, 150, 39);
        PCrear.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PLogin.setVisible(true);
                PCrear.setVisible(false);
                volverinicio();
            }
        };
        btnVolver.addActionListener(volverInicio);

    }

    public void volverinicio() {
        this.setTitle("Sistema administrativo de clientes y recursos");
        this.setSize(500, 350);
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
        PCClientes = new JPanel();
        this.getContentPane().add(PCClientes);
        PCClientes.setLayout(null);
        this.setSize(980, 370);
        this.setTitle("Control de clientes");
        PControl.setVisible(false);

        //Creacion de la tabla
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Género");
        datosTabla.addColumn("NIT");

        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                String fila[] = {clientes[i].Nombre, String.valueOf(clientes[i].Edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].NIT)};
                datosTabla.addRow(fila);
            }
        }
        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 320, 200);
        PCClientes.add(barraTablaClientes);

        //Creacion del grafico circular
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", TMasculino());
        datos.setValue("Femenino", TFemenino());
        JFreeChart GCircular = ChartFactory.createPieChart("Género", datos);
        ChartPanel PCircular = new ChartPanel(GCircular);
        PCircular.setBounds(350, 10, 290, 270);
        PCClientes.add(PCircular);

        //Creacion del grafico de columnas
        DefaultCategoryDataset Datos = new DefaultCategoryDataset();
        Datos.addValue(rango18(), "18-30", "Edad");
        Datos.addValue(rango31(), "31-44", "Edad");
        Datos.addValue(rango45(), "Mayor a 45", "Edad");
        JButton btnCargarArchivo = new JButton("Buscar archivos CSV");
        btnCargarArchivo.setBounds(10, 215, 320, 30);
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango edades", "Edad", "Escala", Datos, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(660, 10, 290, 270);
        PCClientes.add(panelColumnas);

        PCClientes.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                System.out.println("La ubicación del archivo es:" + archivoSeleccionado.getPath());
                leerArchivoCSV(archivoSeleccionado.getPath());
                PCClientes.setVisible(false);
                PCCli();
            }
        };
        btnCargarArchivo.addActionListener(buscarArchivo);
        //boton para crear reporte clientes
        JButton btnReport = new JButton("Crear Reporte");
        btnReport.setBounds(10, 250, 320, 30);
        PCClientes.add(btnReport);
        ActionListener CrearHtml = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CrearReporte();
            }
        };
        btnReport.addActionListener(CrearHtml);
    }

    public void CrearReporte() {

        try {
            PrintWriter pincelCSS = new PrintWriter("Reportes/Style.css", "UTF-8");
            pincelCSS.println("html{font-size: 20px, font-Time new roman}");
            pincelCSS.println("body{background-image:url(degradado.png);background-repeat: no-repeat; background-size: 100%;}");
            pincelCSS.println("h1{font-size:60px; text-align: center; color: white;}");
            pincelCSS.println("table{border-collapse: collapse; width: 800px}");
            pincelCSS.println("td{background-color: #D0FFD6;text-align: center;padding:6px;color:black;}");
            pincelCSS.println("th{background-color:#a7f8dd}");
            pincelCSS.println("th{text-align: center; padding: 6px;}");

            //falta completar CSS
            pincelCSS.close();

            PrintWriter pincel = new PrintWriter("Reportes/reporte.html", "UTF-8");
            pincel.println("<!DOCTYPE html>");
            pincel.println("<html>");
            pincel.println("<head>");
            pincel.println("<title>Reporte del sistema</title>");
            pincel.println("<link rel=\"stylesheet\" href=\"Style.css\">");
            pincel.println("</head>");
            pincel.println("<body>");
            pincel.println("<center>");
            pincel.println("<h1>Listado de clientes en el sistema</h1>");
            pincel.println("<br>");

            pincel.println("<table>");
            pincel.println("<tr>");
            pincel.println("<th>NIT</th><th>Nombre</th><th>Edad</th><th>Género</th>");
            pincel.println("</tr>");

            for (int i = 0; i < 99; i++) {
                if (clientes[i] != null) {
                    pincel.println("<tr>");
                    pincel.println("<td>" + clientes[i].NIT + "</td>" + "<td>" + clientes[i].Nombre + "</td>" + "<td>" + clientes[i].Edad + "</td>" + "<td>" + clientes[i].genero + "</td>");
                    pincel.println("</tr>");
                }
            }
            pincel.println("</table>");

            pincel.println("</center>");
            pincel.println("</body>");
            pincel.println("</html>");

            pincel.close();
            JOptionPane.showMessageDialog(null, "Se logro crear el reporte");

        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se logro crear el reporte");
        }
    }

    public int TMasculino() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'M') {
                    total++;
                }
            }
        }
        return total;
    }

    public int TFemenino() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'F') {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango18() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].Edad >= 18 && clientes[i].Edad < 30) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango31() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].Edad >= 31 && clientes[i].Edad < 45) {
                    total++;
                }
            }
        }
        return total;
    }

    public int rango45() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].Edad > 45) {
                    total++;
                }
            }
        }
        return total;
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
                    if (controlCli < 10) {
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
                        controlCli++;
                    } else {

                        JOptionPane.showMessageDialog(null, "No se pueden registrar más clientes");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clientes registrados correctamente, total de clientes" + " " + controlCli);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir archivo CSV");

        }

    }

    public void leerCSVPro(String ruta) {
        try {
            BufferedReader archivoTemp = new BufferedReader(new FileReader(ruta));
            String LineLeidaPro = "";
            while (LineLeidaPro != null) {
                LineLeidaPro = archivoTemp.readLine();
                if (LineLeidaPro != null) {
                    String DatosSeparadosPro[] = LineLeidaPro.split(",");
                    int posicion = 0;
                    if (controlPro < 10) {
                        for (int i = 0; 1 < 99; i++) {
                            if (Pro[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        Pro[posicion] = new Producto();
                        Pro[posicion].Nombre = DatosSeparadosPro[0];
                        Pro[posicion].Precio = Integer.parseInt(DatosSeparadosPro[1]);//Parte que falla con float
                        Pro[posicion].Cantidad = Integer.parseInt(DatosSeparadosPro[2]);
                        controlPro++;
                    } else {

                        JOptionPane.showMessageDialog(null, "No se pueden registrar más productos");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Los productos fueron registrados correctamente, total de productos " + " " + controlPro);
            archivoTemp.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo CSV");

        }
    }

    public void PCProductos() {
        PCPro = new JPanel();
        this.getContentPane().add(PCPro);
        PCPro.setLayout(null);
        this.setSize(910, 370);
        this.setTitle("Control de productos");
        PControl.setVisible(false);

        //Creacion de la tabla de productos
        DefaultTableModel datosProTabla = new DefaultTableModel();
        datosProTabla.addColumn("Productos");
        datosProTabla.addColumn("Precio");
        datosProTabla.addColumn("Cantidad");

        for (int i = 0; i < 100; i++) {
            if (Pro[i] != null) {
                String fila[] = {Pro[i].Nombre, String.valueOf((Pro[i].Precio)), String.valueOf(Pro[i].Cantidad)};
                datosProTabla.addRow(fila);
            }
        }
        JTable tablaProductos = new JTable(datosProTabla);
        JScrollPane barraTablaProductos = new JScrollPane(tablaProductos);
        barraTablaProductos.setBounds(10, 10, 320, 200);
        PCPro.add(barraTablaProductos);

        JButton btnCargarArchPro = new JButton("Buscar archivos CSV");
        btnCargarArchPro.setBounds(10, 215, 320, 30);

        PCPro.add(btnCargarArchPro);
        ActionListener buscarArchivoCSV = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSelectPro;
                JFileChooser ventanaSelection = new JFileChooser();
                ventanaSelection.showOpenDialog(null);
                archivoSelectPro = ventanaSelection.getSelectedFile();
                System.out.println("La ubicación del archivo es:" + archivoSelectPro.getPath());
                leerCSVPro(archivoSelectPro.getPath());
                PCPro.setVisible(false);
                PCProductos();
                System.out.println("Boton funciona");
            }
        };
        btnCargarArchPro.addActionListener(buscarArchivoCSV);
    }

}

// lengh obteniamos el tamaño de caracteres
//Split es un metodo que separa las cadenas de textos en vectores 
