package presentacion.salidaturistica;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.NoHayEntidadesParaListarException;
import excepciones.UsuarioNoExisteException;
import logica.datatypes.DtSalidaTuristica;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorSalidaTuristica;
import logica.interfaces.IControladorDepartamento;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class ConsultaSalTur extends JInternalFrame {

	private JLabel lblDepartamento;
	private JComboBox<String> comboBoxDepartamento;

	private JLabel lblActividades;
	private JComboBox<String> comboBoxActividades;

	private JLabel lblSalidas;
	private JComboBox<String> comboBoxSalidas;

	private JLabel lblInfoSalida;

	private JLabel lblNombre;
	private JLabel lblCantMaxTuristas;
	private JLabel lblFechaDeAlta;
	private JLabel lblFechaDeSalida;
	private JLabel lblLugarDeSalida;

	private JLabel lblInfoNombre;
	private JLabel lblInfoCantMaxTuristas;
	private JLabel lblInfoFechaDeAlta;
	private JLabel lblInfoFechaDeSalida;
	private JLabel lblInfoLugarDeSalida;

	private JButton btnCerrar;

	// ------------------------------------------

	private String[] listaDepartamentos;
	private String[] listaActividades;
	private String[] listaSalidas;

	private IControladorDepartamento controladorDep;
	private IControladorActividadTuristica controladorActTur;
	private IControladorSalidaTuristica controladorSalTur;

	private DtSalidaTuristica salidaSeleccionada;

	public ConsultaSalTur() {

	}
	/*
	 * public ConsultaSalTur(IControladorDepartamento controladorDep,
	 * IControladorActividadTuristica controladorActTur, IControladorSalidaTuristica
	 * controladorSalTur, ILogger logger, String nombreSalida) {
	 * 
	 * this(controladorDep, controladorActTur, controladorSalTur, logger,
	 * nombreSalida, nombreDepartamento, nombreActividad); String nombreDepartamento
	 * = ""; String nombreActividad = "";
	 * 
	 * }
	 * 
	 */

	public ConsultaSalTur(IControladorDepartamento controladorDep, IControladorActividadTuristica controladorActTur,
			IControladorSalidaTuristica controladorSalTur, String nombreSalida) {

		this(controladorDep, controladorActTur, controladorSalTur);

		DtSalidaTuristica salida;
		try {
			salida = controladorSalTur.getSalidaTuristica(nombreSalida);

			String nombreActividad = salida.getNombreActividad();
			String nombreDepartamento = controladorActTur.getActividadTuristica(nombreActividad).getDepartamento();

			comboBoxDepartamento.setSelectedItem(nombreDepartamento);
			actualizarActividades(nombreDepartamento);
			comboBoxActividades.setSelectedItem(nombreActividad);
			actualizarSalidas(nombreActividad);
			comboBoxSalidas.setSelectedItem(nombreSalida);
			mostrarSalidaSeleccionada(nombreSalida);

		} catch (EntidadNoExisteException | CampoInvalidoException e) {
			mostrarCajitaError(e);
		}
	}

	public ConsultaSalTur(IControladorDepartamento controladorDep, IControladorActividadTuristica controladorActTur,
			IControladorSalidaTuristica controladorSalTur) {

		this.controladorDep = controladorDep;
		this.controladorActTur = controladorActTur;
		this.controladorSalTur = controladorSalTur;

		try {

			listaDepartamentos = (String[]) controladorDep.listarDepartamentos().toArray(new String[0]);
			listaActividades = new String[] { "" };
			listaSalidas = new String[] { "" };

			setResizable(true);
			setIconifiable(true);
			setMaximizable(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setClosable(true);
			setTitle("Consulta de salida turistica");
			setBounds(10, 40, 465, 450);
			getContentPane().setLayout(null);

			// Departamentos

			lblDepartamento = new JLabel("Departamento:");
			lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDepartamento.setBounds(41, 26, 103, 22);
			getContentPane().add(lblDepartamento);

			comboBoxDepartamento = new JComboBox<String>();
			comboBoxDepartamento.setLocation(147, 23);
			comboBoxDepartamento.setSize(241, 22);
			comboBoxDepartamento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarActividades(comboBoxDepartamento.getSelectedItem().toString());
				}
			});

			comboBoxDepartamento.setModel(new DefaultComboBoxModel<String>(listaDepartamentos));

			GridBagConstraints gbc_comboBoxDepartamentos = new GridBagConstraints();
			gbc_comboBoxDepartamentos.fill = GridBagConstraints.BOTH;
			gbc_comboBoxDepartamentos.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxDepartamentos.gridwidth = 2;
			gbc_comboBoxDepartamentos.gridx = 3;
			gbc_comboBoxDepartamentos.gridy = 1;
			getContentPane().add(comboBoxDepartamento, gbc_comboBoxDepartamentos);

			// Actividades

			lblActividades = new JLabel("Actividades:");
			lblActividades.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblActividades.setBounds(41, 61, 77, 22);
			getContentPane().add(lblActividades);

			comboBoxActividades = new JComboBox<String>();
			comboBoxActividades.setLocation(147, 59);
			comboBoxActividades.setSize(241, 22);
			comboBoxActividades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarSalidas(comboBoxActividades.getSelectedItem().toString());
				}
			});
			GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
			gbc_comboBoxActividades.fill = GridBagConstraints.BOTH;
			gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxActividades.gridwidth = 2;
			gbc_comboBoxActividades.gridx = 3;
			gbc_comboBoxActividades.gridy = 1;
			getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);

			// Salidas

			lblSalidas = new JLabel("Salidas:");
			lblSalidas.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSalidas.setBounds(41, 94, 65, 22);
			getContentPane().add(lblSalidas);

			comboBoxSalidas = new JComboBox<String>();
			comboBoxSalidas.setLocation(147, 96);
			comboBoxSalidas.setSize(241, 22);
			comboBoxSalidas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarSalidaSeleccionada((String) comboBoxSalidas.getSelectedItem());
				}
			});
			GridBagConstraints gbc_comboBoxSalidas = new GridBagConstraints();
			gbc_comboBoxSalidas.fill = GridBagConstraints.BOTH;
			gbc_comboBoxSalidas.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxSalidas.gridwidth = 2;
			gbc_comboBoxSalidas.gridx = 3;
			gbc_comboBoxSalidas.gridy = 1;
			getContentPane().add(comboBoxSalidas, gbc_comboBoxSalidas);

			// BotonCerrar

			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doDefaultCloseAction();
				}
			});
			btnCerrar.setBounds(340, 380, 95, 30);
			getContentPane().add(btnCerrar);
			lblInfoSalida = new JLabel("Informacion de la salida");
			lblInfoSalida.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfoSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblInfoSalida.setBounds(173, 153, 187, 22);
			getContentPane().add(lblInfoSalida);

			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNombre.setBounds(20, 212, 70, 22);
			getContentPane().add(lblNombre);

			lblCantMaxTuristas = new JLabel("Cantidad maxima de turistas:");
			lblCantMaxTuristas.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCantMaxTuristas.setBounds(20, 245, 200, 22);
			getContentPane().add(lblCantMaxTuristas);

			lblFechaDeAlta = new JLabel("Fecha de alta:");
			lblFechaDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblFechaDeAlta.setBounds(20, 278, 120, 22);
			getContentPane().add(lblFechaDeAlta);

			lblFechaDeSalida = new JLabel("Fecha de salida:");
			lblFechaDeSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblFechaDeSalida.setBounds(20, 311, 120, 22);
			getContentPane().add(lblFechaDeSalida);

			lblLugarDeSalida = new JLabel("Lugar de salida:");
			lblLugarDeSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblLugarDeSalida.setBounds(20, 344, 120, 22);
			getContentPane().add(lblLugarDeSalida);

			lblInfoNombre = new JLabel("");
			lblInfoNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblInfoNombre.setBounds(98, 212, 366, 22);
			getContentPane().add(lblInfoNombre);

			lblInfoCantMaxTuristas = new JLabel("");
			lblInfoCantMaxTuristas.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblInfoCantMaxTuristas.setBounds(213, 245, 103, 22);
			getContentPane().add(lblInfoCantMaxTuristas);

			lblInfoFechaDeAlta = new JLabel("");
			lblInfoFechaDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblInfoFechaDeAlta.setBounds(128, 278, 247, 22);
			getContentPane().add(lblInfoFechaDeAlta);

			lblInfoFechaDeSalida = new JLabel("");
			lblInfoFechaDeSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblInfoFechaDeSalida.setBounds(138, 311, 267, 22);
			getContentPane().add(lblInfoFechaDeSalida);

			lblInfoLugarDeSalida = new JLabel("");
			lblInfoLugarDeSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblInfoLugarDeSalida.setBounds(137, 344, 220, 22);
			getContentPane().add(lblInfoLugarDeSalida);

		} catch (NoHayEntidadesParaListarException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consutar Salida Turistica", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void actualizarActividades(String nombreDepartamento) {
		try {
			listaActividades = (String[]) controladorActTur.listarActividadesAsociadasADepartamentoConfirmadas(nombreDepartamento).toArray(new String[0]);
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(listaActividades));

		} catch (NoHayEntidadesParaListarException e) {
			mostrarCajitaError(e);

		} catch (CampoInvalidoException e) {
			mostrarCajitaError(e);
		}

	}

	private void actualizarSalidas(String nombreActividad) {
		try {

			listaSalidas = (String[]) controladorSalTur.listarSalidasAsociadasAActividadTuristica(nombreActividad).toArray(new String[0]);
			comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(listaSalidas));

		} catch (NoHayEntidadesParaListarException e) {
			mostrarCajitaError(e);

		} catch (CampoInvalidoException e) {
			mostrarCajitaError(e);
		}

	}

	private void mostrarSalidaSeleccionada(String nombreSalidaSeleccionada) {
		try {
			salidaSeleccionada = controladorSalTur.getSalidaTuristica(nombreSalidaSeleccionada);

			lblInfoNombre.setText(salidaSeleccionada.getNombreSalida());
			lblInfoCantMaxTuristas.setText(Integer.toString(salidaSeleccionada.getCantidadMaximaTuristas()));
			lblInfoFechaDeAlta.setText(salidaSeleccionada.getFechaAlta().toString());
			
			String fechaSalida = "";
			String fecha = salidaSeleccionada.getFechaSalida();
			String[] fechaArray = fecha.split("T");
			fechaSalida = fechaArray[0] + " a las " + fechaArray[1];
			
			lblInfoFechaDeSalida.setText(fechaSalida);
			lblInfoLugarDeSalida.setText(salidaSeleccionada.getLugarSalida());

		} catch (EntidadNoExisteException e) {
			mostrarCajitaError(e);
		} catch (CampoInvalidoException e) {
			mostrarCajitaError(e);
		}

	}

	public void limpiarFormulario() {
		lblInfoNombre.setText("");
		lblInfoCantMaxTuristas.setText("");
		lblInfoFechaDeAlta.setText("");
		lblInfoFechaDeSalida.setText("");
		lblInfoLugarDeSalida.setText("");

		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxActividades.setSelectedIndex(0);
		comboBoxSalidas.setSelectedIndex(0);

		comboBoxSalidas.setEditable(false);
		comboBoxActividades.setEditable(false);

		comboBoxSalidas.setEnabled(true);
		comboBoxActividades.setEnabled(true);
	}

	private void mostrarCajitaError(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Consutar Salida Turistica", JOptionPane.ERROR_MESSAGE);
	}

}