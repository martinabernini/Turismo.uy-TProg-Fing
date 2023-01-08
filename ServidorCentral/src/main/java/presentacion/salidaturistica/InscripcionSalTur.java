package presentacion.salidaturistica;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.EntidadRepetidaException;
import excepciones.MaximoDeTuristasAlcanzadoException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtInscripcionSalida;
import logica.datatypes.DtSalidaTuristica;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IControladorSalidaTuristica;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;


public class InscripcionSalTur extends JInternalFrame {

	private IControladorDepartamento ctrlDepartamento;
	private IControladorSalidaTuristica ctrlSalidaTuristica;
	private IControladorActividadTuristica ctrlActividadTuristica;
	private IControladorUsuario ctrlUsuario;
	private IValidador validador;

	// ------------------------------------------

	private JLabel lblDepartamento;
	private JComboBox<String> comboBoxDepartamento;

	private JLabel lblActividades;
	private JComboBox<String> comboBoxActividades;

	private JLabel lblSalidas;
	private JTextArea txtAreaInfoSalidas;

	private JLabel lblTuristas;
	private JComboBox<String> comboBoxTuristas;

	private JLabel lblSeleccionarSalida;
	private JComboBox<String> comboBoxSalidas;

	private JLabel lblCantTuristas;
	private JTextField textFieldCantTuristas;

	private JDateChooser dateChooser;
	private JLabel lblFechaDeAlta;

	private JButton btnInscribir;
	private JButton btnCerrar;

	private String nombreActividad;
	private String[] listaDepartamentos;
	private String[] listaActividades;
	private String[] listaSalidas;

	public InscripcionSalTur(IControladorDepartamento controladorDepartamento, IControladorSalidaTuristica controladorSalidaTuristica,
			IControladorActividadTuristica controladorActividadTuristica, IControladorUsuario controladorUsuario, IValidador validador) {

		ctrlDepartamento = controladorDepartamento;
		ctrlSalidaTuristica = controladorSalidaTuristica;
		ctrlActividadTuristica = controladorActividadTuristica;
		ctrlUsuario = controladorUsuario;
		this.validador = validador;

		try {

			listaDepartamentos = (String[]) ctrlDepartamento.listarDepartamentos().toArray(new String[0]);

			setResizable(true);
			setIconifiable(true);
			setMaximizable(true);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			setClosable(true);
			setTitle("Inscripcion salida turistica");
			setBounds(10, 40, 481, 384);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 157, 149, 152, 12 };
			gridBagLayout.rowHeights = new int[] { 30, 30, 30, 100, 30, 30, 30, 30, 30 };
			gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0 };
			gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
			getContentPane().setLayout(gridBagLayout);

			// DEPARTAMENTO

			lblDepartamento = new JLabel("Departamento:");
			lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDepartamento.setBounds(10, 11, 89, 20);
			GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
			gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
			gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
			gbc_lblDepartamento.gridx = 0;
			gbc_lblDepartamento.gridy = 0;
			getContentPane().add(lblDepartamento, gbc_lblDepartamento);

			comboBoxDepartamento = new JComboBox<String>();
			comboBoxDepartamento.setModel(new DefaultComboBoxModel<String>(listaDepartamentos));
			comboBoxDepartamento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarActividades(comboBoxDepartamento.getSelectedItem().toString());
				}
			});

			comboBoxDepartamento.setBounds(109, 11, 222, 22);
			GridBagConstraints gbc_comboBoxDepartamento = new GridBagConstraints();
			gbc_comboBoxDepartamento.fill = GridBagConstraints.BOTH;
			gbc_comboBoxDepartamento.gridwidth = 2;
			gbc_comboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxDepartamento.gridx = 1;
			gbc_comboBoxDepartamento.gridy = 0;
			getContentPane().add(comboBoxDepartamento, gbc_comboBoxDepartamento);

			// ACTIVIDADES

			lblActividades = new JLabel("Actividades:");
			lblActividades.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblActividades.setBounds(10, 42, 89, 20);
			GridBagConstraints gbc_lblActividades = new GridBagConstraints();
			gbc_lblActividades.anchor = GridBagConstraints.EAST;
			gbc_lblActividades.insets = new Insets(0, 0, 5, 5);
			gbc_lblActividades.gridx = 0;
			gbc_lblActividades.gridy = 1;
			getContentPane().add(lblActividades, gbc_lblActividades);
			
			comboBoxActividades = new JComboBox<String>();
			comboBoxActividades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarSalidas(comboBoxActividades.getSelectedItem().toString());
				}
			});
			
			comboBoxActividades.setEnabled(false);
			comboBoxActividades.setBounds(109, 42, 222, 22);
			GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
			gbc_comboBoxActividades.fill = GridBagConstraints.BOTH;
			gbc_comboBoxActividades.gridwidth = 2;
			gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxActividades.gridx = 1;
			gbc_comboBoxActividades.gridy = 1;
			getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);

			// SALIDAS

			lblSalidas = new JLabel("Salidas:");
			lblSalidas.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSalidas.setBounds(10, 73, 89, 20);
			GridBagConstraints gbc_lblSalidas = new GridBagConstraints();
			gbc_lblSalidas.anchor = GridBagConstraints.EAST;
			gbc_lblSalidas.insets = new Insets(0, 0, 5, 5);
			gbc_lblSalidas.gridx = 0;
			gbc_lblSalidas.gridy = 2;
			getContentPane().add(lblSalidas, gbc_lblSalidas);

			txtAreaInfoSalidas = new JTextArea();
			txtAreaInfoSalidas.setEditable(false);
			txtAreaInfoSalidas.setLineWrap(true);
			txtAreaInfoSalidas.setText("");
			getContentPane().add(txtAreaInfoSalidas);
			txtAreaInfoSalidas.setColumns(10);

			JScrollPane scroll = new JScrollPane(txtAreaInfoSalidas);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(314, 75, 17, 113);
			GridBagConstraints gbc_scroll = new GridBagConstraints();
			gbc_scroll.fill = GridBagConstraints.BOTH;
			gbc_scroll.gridheight = 2;
			gbc_scroll.gridwidth = 2;
			gbc_scroll.insets = new Insets(0, 0, 5, 5);
			gbc_scroll.gridx = 1;
			gbc_scroll.gridy = 2;
			getContentPane().add(scroll, gbc_scroll);

			// TURISTAS

			lblTuristas = new JLabel("Turistas:");
			lblTuristas.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblTuristas = new GridBagConstraints();
			gbc_lblTuristas.anchor = GridBagConstraints.EAST;
			gbc_lblTuristas.insets = new Insets(0, 0, 5, 5);
			gbc_lblTuristas.gridx = 0;
			gbc_lblTuristas.gridy = 4;
			getContentPane().add(lblTuristas, gbc_lblTuristas);

			comboBoxTuristas = new JComboBox<String>();
			comboBoxTuristas.setModel(new DefaultComboBoxModel<String>((String[]) ctrlUsuario.listarTuristas().toArray(new String[0])));
			comboBoxTuristas.setEnabled(true);
			GridBagConstraints gbc_comboBoxTurista = new GridBagConstraints();
			gbc_comboBoxTurista.gridwidth = 2;
			gbc_comboBoxTurista.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxTurista.fill = GridBagConstraints.BOTH;
			gbc_comboBoxTurista.gridx = 1;
			gbc_comboBoxTurista.gridy = 4;
			getContentPane().add(comboBoxTuristas, gbc_comboBoxTurista);

			// SALIDAS X2

			lblSeleccionarSalida = new JLabel("Salidas:");
			lblSeleccionarSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblSeleccionarSalida = new GridBagConstraints();
			gbc_lblSeleccionarSalida.anchor = GridBagConstraints.EAST;
			gbc_lblSeleccionarSalida.insets = new Insets(0, 0, 5, 5);
			gbc_lblSeleccionarSalida.gridx = 0;
			gbc_lblSeleccionarSalida.gridy = 5;
			getContentPane().add(lblSeleccionarSalida, gbc_lblSeleccionarSalida);

			comboBoxSalidas = new JComboBox<String>();
			comboBoxSalidas.setEnabled(false);
			GridBagConstraints gbc_comboBoxSalidas = new GridBagConstraints();
			gbc_comboBoxSalidas.gridwidth = 2;
			gbc_comboBoxSalidas.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxSalidas.fill = GridBagConstraints.BOTH;
			gbc_comboBoxSalidas.gridx = 1;
			gbc_comboBoxSalidas.gridy = 5;
			getContentPane().add(comboBoxSalidas, gbc_comboBoxSalidas);

			// TURISTAS X2

			lblCantTuristas = new JLabel("Cantidad de turistas:");
			lblCantTuristas.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblCantTuristas = new GridBagConstraints();
			gbc_lblCantTuristas.anchor = GridBagConstraints.EAST;
			gbc_lblCantTuristas.insets = new Insets(0, 0, 5, 5);
			gbc_lblCantTuristas.gridx = 0;
			gbc_lblCantTuristas.gridy = 6;
			getContentPane().add(lblCantTuristas, gbc_lblCantTuristas);

			textFieldCantTuristas = new JTextField();
			GridBagConstraints gbc_textFieldCantTuristas = new GridBagConstraints();
			gbc_textFieldCantTuristas.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldCantTuristas.fill = GridBagConstraints.BOTH;
			gbc_textFieldCantTuristas.gridx = 1;
			gbc_textFieldCantTuristas.gridy = 6;
			getContentPane().add(textFieldCantTuristas, gbc_textFieldCantTuristas);
			textFieldCantTuristas.setColumns(10);

			// BOTONES

			btnInscribir = new JButton();
			btnInscribir.setText("Inscribir");
			btnInscribir.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnInscribir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					realizarInscipcion();
				}
			});

			lblFechaDeAlta = new JLabel("Fecha de inscripcion:");
			lblFechaDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblFechaDeAlta = new GridBagConstraints();
			gbc_lblFechaDeAlta.anchor = GridBagConstraints.EAST;
			gbc_lblFechaDeAlta.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaDeAlta.gridx = 0;
			gbc_lblFechaDeAlta.gridy = 7;
			getContentPane().add(lblFechaDeAlta, gbc_lblFechaDeAlta);

			dateChooser = new JDateChooser();
			GridBagConstraints gbc_dateChooser = new GridBagConstraints();
			gbc_dateChooser.gridwidth = 2;
			gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
			gbc_dateChooser.fill = GridBagConstraints.BOTH;
			gbc_dateChooser.gridx = 1;
			gbc_dateChooser.gridy = 7;
			getContentPane().add(dateChooser, gbc_dateChooser);
			btnInscribir.setBounds(362, 12, 95, 22);
			GridBagConstraints gbc_btnInscribir = new GridBagConstraints();
			gbc_btnInscribir.fill = GridBagConstraints.BOTH;
			gbc_btnInscribir.insets = new Insets(0, 0, 0, 5);
			gbc_btnInscribir.gridx = 1;
			gbc_btnInscribir.gridy = 8;
			getContentPane().add(btnInscribir, gbc_btnInscribir);

			btnCerrar = new JButton();
			btnCerrar.setText("Cerrar");
			btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					limpiarFormulario();
				}
			});
			btnCerrar.setBounds(362, 12, 95, 22);
			GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
			gbc_btnCerrar.fill = GridBagConstraints.BOTH;
			gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
			gbc_btnCerrar.gridx = 2;
			gbc_btnCerrar.gridy = 8;
			getContentPane().add(btnCerrar, gbc_btnCerrar);

		} catch (NoHayEntidadesParaListarException e) {
			mostrarCajitaError(e);
			doDefaultCloseAction();
		}

	}

	private void actualizarActividades(String departamento) {
		try {
			listaActividades = (String[]) ctrlActividadTuristica.listarActividadesAsociadasADepartamentoConfirmadas(departamento).toArray(new String[0]);
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(listaActividades));
			comboBoxActividades.setEnabled(true);

		} catch (NoHayEntidadesParaListarException e1) {
			mostrarCajitaError(e1);
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
			comboBoxActividades.setEnabled(false);
			comboBoxSalidas.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
			comboBoxSalidas.setEnabled(false);
			txtAreaInfoSalidas.setText("");
			

		} catch (CampoInvalidoException e1) {
			mostrarCajitaError(e1);
		}
	};

	private void actualizarSalidas(String nombreActividad) {
		try {
			listaSalidas = (String[]) ctrlSalidaTuristica.listarSalidasVigentesAsociadasAActividadTuristica(nombreActividad).toArray(new String[0]);
			txtAreaInfoSalidas.setText(infoSalidas(listaSalidas));
			comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(listaSalidas));
			comboBoxSalidas.setEnabled(true);
			
		} catch (EntidadNoExisteException | CampoInvalidoException | NoHayEntidadesParaListarException e1) {
			mostrarCajitaError(e1);
			comboBoxSalidas.setModel(new DefaultComboBoxModel<>(new String[] { "" }));
			comboBoxSalidas.setEnabled(false);
		}
	}

	private String infoSalidas(String[] salidas) throws EntidadNoExisteException, CampoInvalidoException {
		String info = "";
		for (String salida : salidas) {
			info += infoSalida(ctrlSalidaTuristica.getSalidaTuristica(salida));
		}
		return info;
	}

	private String infoSalida(DtSalidaTuristica salida) {
		String nombre = "Nombre de la salida: " + salida.getNombreSalida() + "\r\n";
		String fecha = "Fecha de salida: " + salida.getFechaSalida()+ "\r\n";
		String lugar = "Lugar de salida: " + salida.getLugarSalida() + "\r\n";
		return nombre + fecha + lugar + "\r\n";
	}

	private void realizarInscipcion() {
		DtInscripcionSalida inscripcion = new DtInscripcionSalida();
		inscripcion.setNickname(comboBoxTuristas.getSelectedItem().toString());
		inscripcion.setNombreSalidaTuristica(comboBoxSalidas.getSelectedItem().toString());		
		inscripcion.setFechaInscripcion((dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).toString());

		try {
			inscripcion.setCantidadTuristas(Integer.valueOf(textFieldCantTuristas.getText()));

		} catch (NumberFormatException e) {
			mostrarCajitaError("Cantidad de turistas tiene que ser un numero");
			return;
		}

		try {

			if (validador.campoInvalidoInscripcionASalida(inscripcion)) {
				mostrarCajitaError(MensajeError.campoInvalidoInscripcionASalida(inscripcion));
				return;
			}

			ctrlSalidaTuristica.inscribirTuristaASalidaTuristica(inscripcion);

			mostrarCajitaExito("Inscripcion realizada con exito");
			limpiarFormulario();
			doDefaultCloseAction();

		} catch (CampoInvalidoException | MaximoDeTuristasAlcanzadoException | EntidadRepetidaException e) {
			mostrarCajitaError(e);
		}

	}

	private void mostrarCajitaError(Exception e) {
		mostrarCajitaError(e.getMessage());
	}

	private void mostrarCajitaError(String str) {
		JOptionPane.showMessageDialog(this, str, "Inscripcion salida turistica", JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarCajitaExito(String str) {
		JOptionPane.showMessageDialog(this, str, "Inscripcion salida turistica", JOptionPane.PLAIN_MESSAGE);
	}

	public void limpiarFormulario() {
		comboBoxActividades.setModel(new DefaultComboBoxModel<String>(new String[0]));
		txtAreaInfoSalidas.setText("");
		comboBoxTuristas.setModel(new DefaultComboBoxModel<String>(new String[0]));
		comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(new String[0]));
		textFieldCantTuristas.setText("");
	}

}