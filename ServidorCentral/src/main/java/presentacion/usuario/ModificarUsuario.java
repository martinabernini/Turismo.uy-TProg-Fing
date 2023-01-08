package presentacion.usuario;

import javax.swing.JInternalFrame;

import logica.datatypes.DtProveedor;
import logica.datatypes.DtTurista;
import logica.datatypes.DtUsuario;
import logica.interfaces.IControladorUsuario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.toedter.calendar.JDateChooser;

import excepciones.CampoInvalidoException;
import logica.entidades.InscripcionSalida;
import excepciones.EntidadNoExisteException;
import excepciones.NoHayEntidadesParaListarException;
import javax.swing.SwingConstants;

public class ModificarUsuario extends JInternalFrame {

    private IControladorUsuario controladorUsuario;

    private JLabel lblUsuarios;
    private JComboBox comboBoxUsuarios;

    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblFechaDeNacimiento;
    private JLabel lblNacionalidad;
    private JLabel lblDescripcion;
    private JLabel lblSitioWeburl;

    private JLabel lblInformacionUsuario;

    private JTextField textFieldNombre;
    private JTextField textFieldApellido;
    private JTextField textFieldNacionalidad;
    private JTextField textFieldURL;
    private JTextArea textAreaDescripcion;
    
    private JDateChooser dateChooser;

    private String usuario;

    private JButton btnModificar;
    private JButton btnCerrar;

    private GridBagConstraints gbc_textFieldNombre;
    private GridBagConstraints gbc_textFieldApellido;
    private GridBagConstraints gbc_textFieldNacionalidad;
    private GridBagConstraints gbc_textFieldURL;
    private JLabel lblPassword;
    private JTextField textFieldPassword;


    public ModificarUsuario(IControladorUsuario controladorUsuario) {

        this.controladorUsuario = controladorUsuario;

        comboBoxUsuarios = new JComboBox();
        try {
            comboBoxUsuarios.setModel(new DefaultComboBoxModel<String>((String[]) controladorUsuario.listarUsuarios().toArray(new String[0]) ));
        } catch (NoHayEntidadesParaListarException e1) {
            mostrarCajitaError(e1);
            // do default close action
            return;
        }

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Modificar usuario");
        setBounds(10, 40, 501, 425);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{30, 97, 229, 97, 10};
        gridBagLayout.rowHeights = new int[]{10, 30, 30, 30, 30, 30, 30, 30, 30, 27, 30, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        lblUsuarios = new JLabel("Usuarios:");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 1;
        getContentPane().add(lblUsuarios, gbc_lblPassword);

        lblDescripcion = new JLabel("Descripcion:");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 1;
        gbc_lblDescripcion.gridy = 7;
        getContentPane().add(lblDescripcion, gbc_lblDescripcion);

        
        textAreaDescripcion = new JTextArea();
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 5, 5);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 2;
        gbc_textArea.gridy = 7;
        getContentPane().add(textAreaDescripcion, gbc_textArea);

        lblSitioWeburl = new JLabel("Sitio web (URL):");
        GridBagConstraints gbc_lblSitioWeburl = new GridBagConstraints();
        gbc_lblSitioWeburl.anchor = GridBagConstraints.EAST;
        gbc_lblSitioWeburl.insets = new Insets(0, 0, 5, 5);
        gbc_lblSitioWeburl.gridx = 1;
        gbc_lblSitioWeburl.gridy = 8;
        getContentPane().add(lblSitioWeburl, gbc_lblSitioWeburl);

        textFieldURL = new JTextField();
        textFieldURL.setEnabled(false);
        textFieldURL.setEditable(false);
        textFieldURL.setColumns(10);
        GridBagConstraints gbc_textField_4;
        gbc_textFieldURL = new GridBagConstraints();
        gbc_textFieldURL.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldURL.fill = GridBagConstraints.BOTH;
        gbc_textFieldURL.gridx = 2;
        gbc_textFieldURL.gridy = 8;
        getContentPane().add(textFieldURL, gbc_textFieldURL);

        comboBoxUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                textFieldNombre.setEditable(true);
                textFieldNombre.setEnabled(true);

                textFieldApellido.setEditable(true);
                textFieldApellido.setEnabled(true);

                textFieldNacionalidad.setEditable(true);
                textFieldNacionalidad.setEnabled(true);

                textAreaDescripcion.setEditable(true);
                textAreaDescripcion.setEnabled(true);

                textFieldURL.setEditable(true);
                textFieldURL.setEnabled(true);

                dateChooser.setEnabled(true);

                usuario = comboBoxUsuarios.getSelectedItem().toString();

                cargarDatosUsuario(usuario);
            }
        });
        GridBagConstraints gbc_comboBoxUsuarios = new GridBagConstraints();
        gbc_comboBoxUsuarios.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxUsuarios.fill = GridBagConstraints.BOTH;
        gbc_comboBoxUsuarios.gridx = 2;
        gbc_comboBoxUsuarios.gridy = 1;
        getContentPane().add(comboBoxUsuarios, gbc_comboBoxUsuarios);

        lblInformacionUsuario = new JLabel("Informacion de usuario");
        GridBagConstraints gbc_lblInformacionUsuario = new GridBagConstraints();
        gbc_lblInformacionUsuario.insets = new Insets(0, 0, 5, 5);
        gbc_lblInformacionUsuario.gridx = 2;
        gbc_lblInformacionUsuario.gridy = 2;
        getContentPane().add(lblInformacionUsuario, gbc_lblInformacionUsuario);

        lblNombre = new JLabel("Nombre:");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = 3;
        getContentPane().add(lblNombre, gbc_lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setEnabled(false);
        textFieldNombre.setEditable(false);
        GridBagConstraints gbc_textFieldPassword;
        gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.gridx = 2;
        gbc_textFieldNombre.gridy = 3;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        lblApellido = new JLabel("Apellido:");
        GridBagConstraints gbc_lblApellido = new GridBagConstraints();
        gbc_lblApellido.anchor = GridBagConstraints.EAST;
        gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido.gridx = 1;
        gbc_lblApellido.gridy = 4;
        getContentPane().add(lblApellido, gbc_lblApellido);

        textFieldApellido = new JTextField();
        textFieldApellido.setEnabled(false);
        textFieldApellido.setEditable(false);
        textFieldApellido.setColumns(10);
        GridBagConstraints gbc_textField_1;
        gbc_textFieldApellido = new GridBagConstraints();
        gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
        gbc_textFieldApellido.gridx = 2;
        gbc_textFieldApellido.gridy = 4;
        getContentPane().add(textFieldApellido, gbc_textFieldApellido);

        lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
        GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
        gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
        gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaDeNacimiento.gridx = 1;
        gbc_lblFechaDeNacimiento.gridy = 5;
        getContentPane().add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);

        dateChooser = new JDateChooser();
        dateChooser.setEnabled(false);
        GridBagConstraints gbc_dateChooser = new GridBagConstraints();
        gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooser.fill = GridBagConstraints.BOTH;
        gbc_dateChooser.gridx = 2;
        gbc_dateChooser.gridy = 5;
        getContentPane().add(dateChooser, gbc_dateChooser);

        lblNacionalidad = new JLabel("Nacionalidad:");
        GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
        gbc_lblNacionalidad.anchor = GridBagConstraints.EAST;
        gbc_lblNacionalidad.insets = new Insets(0, 0, 5, 5);
        gbc_lblNacionalidad.gridx = 1;
        gbc_lblNacionalidad.gridy = 6;
        getContentPane().add(lblNacionalidad, gbc_lblNacionalidad);

        textFieldNacionalidad = new JTextField();
        textFieldNacionalidad.setEditable(false);
        textFieldNacionalidad.setEnabled(false);
        textFieldNacionalidad.setColumns(10);
        GridBagConstraints gbc_textField_2;
        gbc_textFieldNacionalidad = new GridBagConstraints();
        gbc_textFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldNacionalidad.fill = GridBagConstraints.BOTH;
        gbc_textFieldNacionalidad.gridx = 2;
        gbc_textFieldNacionalidad.gridy = 6;
        getContentPane().add(textFieldNacionalidad, gbc_textFieldNacionalidad);

        btnModificar = new JButton("       Modificar      ");
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    verificarCampos();
                    actualizarDatos(usuario);
                    limpiarFormulario();

                } catch (CampoInvalidoException e1) {
                    mostrarCajitaError(e1);
                }

            }
        });
        
        lblPassword = new JLabel("Contraseña:");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblPassword1 = new GridBagConstraints();
        gbc_lblPassword1.anchor = GridBagConstraints.EAST;
        gbc_lblPassword1.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword1.gridx = 1;
        gbc_lblPassword1.gridy = 9;
        getContentPane().add(lblPassword, gbc_lblPassword1);
        
        textFieldPassword = new JTextField();
        textFieldPassword.setEnabled(false);
        textFieldPassword.setEditable(false);
        GridBagConstraints gbc_textFieldPassword1 = new GridBagConstraints();
        gbc_textFieldPassword1.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldPassword1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldPassword1.gridx = 2;
        gbc_textFieldPassword1.gridy = 9;
        getContentPane().add(textFieldPassword, gbc_textFieldPassword1);
        textFieldPassword.setColumns(10);
        GridBagConstraints gbc_btnModificar = new GridBagConstraints();
        gbc_btnModificar.anchor = GridBagConstraints.EAST;
        gbc_btnModificar.insets = new Insets(0, 0, 0, 5);
        gbc_btnModificar.gridx = 2;
        gbc_btnModificar.gridy = 10;
        getContentPane().add(btnModificar, gbc_btnModificar);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
        gbc_btnCerrar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCerrar.gridx = 3;
        gbc_btnCerrar.gridy = 10;
        getContentPane().add(btnCerrar, gbc_btnCerrar);

    }

    private void cargarDatosUsuario(String usuario) {
        try {
            DtUsuario user = controladorUsuario.getUsuario(usuario);
            textFieldNombre.setText(user.getNombre());
            textFieldApellido.setText(user.getApellido());
            
            LocalDate fecha = LocalDate.parse(user.getFechaNacimiento());
            Date fechaAux = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            dateChooser.setDate(fechaAux);
            textFieldPassword.setEnabled(true);
            textFieldPassword.setEditable(true);

            if (user instanceof DtTurista) {
                textFieldNacionalidad.setEnabled(true);
                textFieldNacionalidad.setText(((DtTurista) user).getNacionalidad());
                textAreaDescripcion.setText("");
                textFieldURL.setText("");
                textAreaDescripcion.setEnabled(false);
                textFieldURL.setEnabled(false);
            }
            if (user instanceof DtProveedor) {
                textFieldNacionalidad.setText("");
                textAreaDescripcion.setEnabled(true);
                textFieldURL.setEnabled(true);
                textFieldNacionalidad.setEnabled(false);

                String des = ((DtProveedor) user).getDescripcion();
                String url = ((DtProveedor) user).getUrlSitioWeb();
                textAreaDescripcion.setText(((DtProveedor) user).getDescripcion());

                textFieldURL.setText(((DtProveedor) user).getUrlSitioWeb());
            }
        } catch (EntidadNoExisteException | CampoInvalidoException e) {
            mostrarCajitaError(e);
        }

    }

    private void actualizarDatos(String usuario) {
        try {
            verificarCampos();
            DtUsuario usuarioActual = controladorUsuario.getUsuario(usuario);

            String nombre = textFieldNombre.getText();
            String apellido = textFieldApellido.getText();
            
            Date fechaNueva = dateChooser.getDate();
            
            LocalDate fecha =  fechaNueva.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
         
            
            if (usuarioActual instanceof DtTurista) {
                String nacionalidad = textFieldNacionalidad.getText();

                DtTurista usuarioMod = (DtTurista) usuarioActual;
                usuarioMod.setNickname(usuario);
                usuarioMod.setNombre(nombre);
                usuarioMod.setApellido(apellido);
                usuarioMod.setFechaNacimiento(fecha.toString());
                usuarioMod.setNacionalidad(nacionalidad);
                usuarioMod.setImagen(usuarioActual.getImagen());
                if (textFieldPassword.getText().length() > 0) {
                    usuarioMod.setPassword(textFieldPassword.getText());
                } else {
                    usuarioMod.setPassword(usuarioActual.getPassword());
                }

                controladorUsuario.modificarUsuario(usuarioMod);

            } else {
                String descripcion = textAreaDescripcion.getText();
                String url = textFieldURL.getText();

                DtProveedor usuarioMod =  (DtProveedor) usuarioActual;
                usuarioMod.setNickname(usuario);
                usuarioMod.setNombre(nombre);
                usuarioMod.setApellido(apellido);
                usuarioMod.setFechaNacimiento(fecha.toString());
                usuarioMod.setImagen(usuarioActual.getImagen());
                if (textFieldPassword.getText().length() > 0) {
                    usuarioMod.setPassword(textFieldPassword.getText());
                } else {
                    usuarioMod.setPassword(usuarioActual.getPassword());
                }
                usuarioMod.setUrlSitioWeb(url);
                usuarioMod.setDescripcion(descripcion);

                controladorUsuario.modificarUsuario(usuarioMod);
            }

            mostrarCajitaExito("Usuario modificado con exito");
            setVisible(false);
            limpiarFormulario();
        } catch (Exception e) {
            mostrarCajitaError(e);
        }
    }

    private void verificarCampos() throws CampoInvalidoException {
        if (textFieldNombre.getText().isBlank()) {
            throw new CampoInvalidoException("Error: nombre de usuario invalido");
        }
        if (textFieldApellido.getText().isBlank()) {
            throw new CampoInvalidoException("Error: apellido de usuario invalido");
        }
        if ((dateChooser.isValid())) {
            throw new CampoInvalidoException("Error: fecha de nacimiento invalida");
        }
        if (textAreaDescripcion.isEnabled()) {
            if (textAreaDescripcion.getText().isBlank()) {
                throw new CampoInvalidoException("Error: descripcion de usuario invalido");
            }

        } else {
            if (textFieldNacionalidad.getText().isBlank()) {
                throw new CampoInvalidoException("Error: nacionalidad de usuario invalida");
            }
        }
    }

    private void mostrarCajitaError(Exception e) {
        mostrarCajitaError(e.getMessage());
    }

    private void mostrarCajitaError(String str) {
        JOptionPane.showMessageDialog(this, str, "Modificar usuario", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarCajitaExito(String str) {
        JOptionPane.showMessageDialog(this, str, "Modificar usuario", JOptionPane.PLAIN_MESSAGE);
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldApellido.setText("");
        textFieldNacionalidad.setText("");
        textAreaDescripcion.setText("");
        textFieldURL.setText("");
    }
}