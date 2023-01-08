package presentacion.usuario;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadRepetidaException;
import logica.datatypes.DtProveedor;
import logica.datatypes.DtTurista;
import logica.datatypes.DtUsuario;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class CrearUsuario extends JInternalFrame {

	private IControladorUsuario controladorUsuario;
	private IValidador validador;

	// Los componentes gráficos se agregan como atributos de la clase
	// para facilitar su acceso desde diferentes métodos de la misma.
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldNickname;
	private JLabel lblIngreseNombre;
	private JLabel lblIngreseApellido;
	private JLabel lblIngreseCi;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel;
	private JDateChooser dateChooser;
	private JLabel lblTipoDeUsuario;
	private JComboBox<String> comboBoxTipoUsuario;
	private JLabel lblNacionalidad;
	private JTextField textField_Nacionalidad;
	private JLabel lblDescripcionGeneral;
	private JTextArea textField_DescripcionGeneral;
	private JLabel lblSitioWeb;
	private JTextField textField_SitioWeb;
	private JLabel lblPass;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepetir;
	private JLabel lblRepetirPass;

	/**
	 * Create the frame.
	 */

	public CrearUsuario(IControladorUsuario controladorUsuario, IValidador validador) {

		this.controladorUsuario = controladorUsuario;
		this.validador = validador;

		// Propiedades del JInternalFrame como dimensión, posición dentro del frame,
		// etc.
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registrar un Usuario");
		setBounds(10, 40, 451, 417);

		// En este caso utilizaremos el GridBagLayout que permite armar una grilla
		// en donde las filas y columnas no son uniformes.
		// Conviene trabajar este componente desde la vista de diseño gráfico y sólo
		// manipular los valores para ajustar alguna cosa.
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 130, 120, 120, 12 };
		gridBagLayout.rowHeights = new int[] { 28, 28, 30, 30, 27, 0, 0, 30, 0, 0, 29, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTipoDeUsuario = new GridBagConstraints();
		gbc_lblTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeUsuario.gridx = 0;
		gbc_lblTipoDeUsuario.gridy = 0;
		getContentPane().add(lblTipoDeUsuario, gbc_lblTipoDeUsuario);

		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos(comboBoxTipoUsuario.getSelectedItem().toString());
			}
		});
		
		comboBoxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] { "-", "Turista", "Proveedor" }));
		comboBoxTipoUsuario.setEditable(false);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		getContentPane().add(comboBoxTipoUsuario, gbc_comboBox);

		// Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse
		// el nombre del usuario. El texto está alineado horizontalmente a la derecha
		// para
		// que quede casi pegado al campo de texto.
		lblIngreseNombre = new JLabel("Nombre:");
		lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
		gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseNombre.gridx = 0;
		gbc_lblIngreseNombre.gridy = 1;
		getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

		// Una campo de texto (JTextField) para ingresar el nombre del usuario.
		// Por defecto es posible ingresar cualquier string.
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 1;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		// Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse
		// el apellido del usuario. El texto está alineado horizontalmente a la derecha
		// para
		// que quede casi pegado al campo de texto.
		lblIngreseApellido = new JLabel("Apellido:");
		lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseApellido = new GridBagConstraints();
		gbc_lblIngreseApellido.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseApellido.gridx = 0;
		gbc_lblIngreseApellido.gridy = 2;
		getContentPane().add(lblIngreseApellido, gbc_lblIngreseApellido);

		// Una campo de texto (JTextField) para ingresar el apellido del usuario.
		// Por defecto es posible ingresar cualquier string.
		textFieldApellido = new JTextField();
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 2;
		gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 2;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);

		// Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse
		// la cédula del usuario. El texto está alineado horizontalmente a la derecha
		// para
		// que quede casi pegado al campo de texto.
		lblIngreseCi = new JLabel("Nickname:");
		lblIngreseCi.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseCi = new GridBagConstraints();
		gbc_lblIngreseCi.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseCi.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseCi.gridx = 0;
		gbc_lblIngreseCi.gridy = 3;
		getContentPane().add(lblIngreseCi, gbc_lblIngreseCi);

		// Una campo de texto (JTextField) para ingresar la cédula del usuario.
		// Por defecto es posible ingresar cualquier string.
		// Al campo se le incluye un Tooltip que, al pasar el mouse por encima,
		// despliega un mensaje.
		textFieldNickname = new JTextField();
		textFieldNickname.setColumns(10);
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 2;
		gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNickname.gridx = 1;
		gbc_textFieldNickname.gridy = 3;
		getContentPane().add(textFieldNickname, gbc_textFieldNickname);

		// Un botón (JButton) con un evento asociado que permite registrar el usuario.
		// Dado que el código de registro tiene cierta complejidad, conviene delegarlo
		// a otro método en lugar de incluirlo directamente de el método actionPerformed
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				intentarRegistrarUsuario();
			}
		});

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 4;
		getContentPane().add(lblEmail, gbc_lblEmail);

		textFieldEmail = new JTextField();
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.gridwidth = 2;
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.BOTH;
		gbc_textFieldEmail.gridx = 1;
		gbc_textFieldEmail.gridy = 4;
		getContentPane().add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		lblPass = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblPass = new GridBagConstraints();
		gbc_lblPass.anchor = GridBagConstraints.EAST;
		gbc_lblPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblPass.gridx = 0;
		gbc_lblPass.gridy = 5;
		getContentPane().add(lblPass, gbc_lblPass);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		getContentPane().add(passwordField, gbc_passwordField);
		
		lblRepetirPass = new JLabel("Repita Contraseña:");
		GridBagConstraints gbc_lblRepetirPass = new GridBagConstraints();
		gbc_lblRepetirPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepetirPass.anchor = GridBagConstraints.EAST;
		gbc_lblRepetirPass.gridx = 0;
		gbc_lblRepetirPass.gridy = 6;
		getContentPane().add(lblRepetirPass, gbc_lblRepetirPass);
		
		passwordFieldRepetir = new JPasswordField();
		GridBagConstraints gbc_passwordFieldRepetir = new GridBagConstraints();
		gbc_passwordFieldRepetir.gridwidth = 2;
		gbc_passwordFieldRepetir.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldRepetir.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRepetir.gridx = 1;
		gbc_passwordFieldRepetir.gridy = 6;
		getContentPane().add(passwordFieldRepetir, gbc_passwordFieldRepetir);

		lblNewLabel = new JLabel("Fecha de nacimiento:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 7;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 7;
		getContentPane().add(dateChooser, gbc_dateChooser);
		
				lblNacionalidad = new JLabel("Nacionalidad:");
				GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
				gbc_lblNacionalidad.anchor = GridBagConstraints.EAST;
				gbc_lblNacionalidad.insets = new Insets(0, 0, 5, 5);
				gbc_lblNacionalidad.gridx = 0;
				gbc_lblNacionalidad.gridy = 8;
				getContentPane().add(lblNacionalidad, gbc_lblNacionalidad);
		
				textField_Nacionalidad = new JTextField();
				textField_Nacionalidad.setColumns(10);
				GridBagConstraints gbc_textField_Nacionalidad = new GridBagConstraints();
				gbc_textField_Nacionalidad.gridwidth = 2;
				gbc_textField_Nacionalidad.insets = new Insets(0, 0, 5, 5);
				gbc_textField_Nacionalidad.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_Nacionalidad.gridx = 1;
				gbc_textField_Nacionalidad.gridy = 8;
				getContentPane().add(textField_Nacionalidad, gbc_textField_Nacionalidad);
				
						textField_Nacionalidad.setEditable(false);
						textField_Nacionalidad.setEnabled(false);

		lblSitioWeb = new JLabel("Sitio web:");
		GridBagConstraints gbc_lblSitioWeb = new GridBagConstraints();
		gbc_lblSitioWeb.anchor = GridBagConstraints.EAST;
		gbc_lblSitioWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblSitioWeb.gridx = 0;
		gbc_lblSitioWeb.gridy = 9;
		getContentPane().add(lblSitioWeb, gbc_lblSitioWeb);

		textField_SitioWeb = new JTextField();
		textField_SitioWeb.setColumns(10);
		GridBagConstraints gbc_textField_SitioWeb = new GridBagConstraints();
		gbc_textField_SitioWeb.gridwidth = 2;
		gbc_textField_SitioWeb.insets = new Insets(0, 0, 5, 5);
		gbc_textField_SitioWeb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_SitioWeb.gridx = 1;
		gbc_textField_SitioWeb.gridy = 9;
		getContentPane().add(textField_SitioWeb, gbc_textField_SitioWeb);

		textField_SitioWeb.setEditable(false);
		textField_SitioWeb.setEnabled(false);
		
				lblDescripcionGeneral = new JLabel("Descripcion general:");
				GridBagConstraints gbc_lblDescripcionGeneral = new GridBagConstraints();
				gbc_lblDescripcionGeneral.anchor = GridBagConstraints.EAST;
				gbc_lblDescripcionGeneral.insets = new Insets(0, 0, 5, 5);
				gbc_lblDescripcionGeneral.gridx = 0;
				gbc_lblDescripcionGeneral.gridy = 10;
				getContentPane().add(lblDescripcionGeneral, gbc_lblDescripcionGeneral);
		
				textField_DescripcionGeneral = new JTextArea();
				textField_DescripcionGeneral.setLineWrap(true);
				textField_DescripcionGeneral.setRows(3);
				textField_DescripcionGeneral.setColumns(10);
				GridBagConstraints gbc_textField_DescripcionGeneral = new GridBagConstraints();
				gbc_textField_DescripcionGeneral.gridheight = 2;
				gbc_textField_DescripcionGeneral.gridwidth = 2;
				gbc_textField_DescripcionGeneral.insets = new Insets(0, 0, 5, 5);
				gbc_textField_DescripcionGeneral.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField_DescripcionGeneral.gridx = 1;
				gbc_textField_DescripcionGeneral.gridy = 10;
				getContentPane().add(textField_DescripcionGeneral, gbc_textField_DescripcionGeneral);
				
						textField_DescripcionGeneral.setEditable(false);
						textField_DescripcionGeneral.setEnabled(false);

		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 12;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

		// Un botón (JButton) con un evento asociado que permite cerrar el formulario
		// (solo ocultarlo).
		// Dado que antes de cerrar se limpia el formulario, se invoca un método
		// reutilizable para ello.
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 12;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void intentarRegistrarUsuario() {
		String tipoUsuario = comboBoxTipoUsuario.getSelectedItem().toString();
		if (tipoUsuario != "Turista" && tipoUsuario != "Proveedor") {
			mostrarCajitaError("Seleccione Turista o Proveedor");
			return;
		}

		// Obtengo datos de los controles Swing
		String nombreU = this.textFieldNombre.getText();
		String apellidoU = this.textFieldApellido.getText();
		String nicknameU = this.textFieldNickname.getText();
		String emailU = this.textFieldEmail.getText();
		String nacionalidadU = this.textField_Nacionalidad.getText();
		String descripcionGeneralU = this.textField_DescripcionGeneral.getText();
		String sitiowebU = this.textField_SitioWeb.getText();
		
		
		Date fechanacimientoU = this.dateChooser.getDate();
		LocalDate fecha =  fechanacimientoU.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		
		String passU = this.passwordField.getText();
		String pass2U = this.passwordFieldRepetir.getText();

		if (!passU.equals(pass2U)) {
			mostrarCajitaError("Las contraseñas no coinciden");
			return;
		}
		DtUsuario nuevoUsuario;

		if (tipoUsuario == "Turista") {
			nuevoUsuario = new DtTurista();			
			nuevoUsuario.setNickname(nicknameU);
			nuevoUsuario.setNombre(nombreU);
			nuevoUsuario.setApellido(apellidoU);
			nuevoUsuario.setPassword(passU);
			nuevoUsuario.setEmail(emailU);
			nuevoUsuario.setFechaNacimiento(fecha.toString());
			((DtTurista) nuevoUsuario).setNacionalidad(nacionalidadU);

		} else {
			nuevoUsuario = new DtProveedor();
			nuevoUsuario.setNickname(nicknameU);
			nuevoUsuario.setNombre(nombreU);
			nuevoUsuario.setApellido(apellidoU);
			nuevoUsuario.setEmail(emailU);
			nuevoUsuario.setPassword(passU);
			nuevoUsuario.setFechaNacimiento(fecha.toString());
			((DtProveedor) nuevoUsuario).setDescripcion(descripcionGeneralU);
			((DtProveedor) nuevoUsuario).setUrlSitioWeb(sitiowebU);
		}

		if (validador.campoInvalidoAltaUsuario(nuevoUsuario)) {
			mostrarCajitaError(MensajeError.campoInvalidoAltaUsuario(nuevoUsuario));
			return;
		}

		try {
			controladorUsuario.darDeAltaUsuario(nuevoUsuario);
			mostrarCajitaExito("El usuario se ha creado con exito");

			limpiarFormulario();
			setVisible(false);

		} catch (EntidadRepetidaException e) {
			mostrarCajitaError(e);

		} catch (CampoInvalidoException e) {
			mostrarCajitaError(e);
		}
	}

	// Permite borrar el contenido de un formulario antes de cerrarlo.
	// Recordar que las ventanas no se destruyen, sino que simplemente
	// se ocultan, por lo que conviene borrar la información para que
	// no aparezca al mostrarlas nuevamente.
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldNickname.setText("");
		textFieldEmail.setText("");
		textField_Nacionalidad.setText("");
		passwordField.setText("");
		passwordFieldRepetir.setText("");
		textField_DescripcionGeneral.setText("");
		textField_SitioWeb.setText("");
		dateChooser.setCalendar(null);
	}

	private void mostrarCajitaError(Exception e) {
		mostrarCajitaError(e.getMessage());
	}

	private void mostrarCajitaError(String str) {
		JOptionPane.showMessageDialog(this, str, "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarCajitaExito(String str) {
		JOptionPane.showMessageDialog(this, str, "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
	}

	private void habilitarCampos(String tipoUsuario) {

		if (tipoUsuario == "Turista") {
			textField_DescripcionGeneral.setEditable(false);
			textField_DescripcionGeneral.setEnabled(false);
			textField_SitioWeb.setEditable(false);
			textField_SitioWeb.setEnabled(false);
			textField_Nacionalidad.setEditable(true);
			textField_Nacionalidad.setEnabled(true);
		} else {
			textField_Nacionalidad.setEditable(false);
			textField_Nacionalidad.setEnabled(false);
			textField_DescripcionGeneral.setEditable(true);
			textField_DescripcionGeneral.setEnabled(true);
			textField_SitioWeb.setEditable(true);
			textField_SitioWeb.setEnabled(true);
		}
	}
}
