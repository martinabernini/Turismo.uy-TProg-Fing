package presentacion.salidaturistica;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorSalidaTuristica;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;
import logica.datatypes.DtSalidaTuristica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

public class AltaSalidaTur extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldCantidad;
	private JTextField textFieldHora;
	private JTextField textFieldLugar;
	private IControladorActividadTuristica controladorAct;
	private IControladorDepartamento controladorDpto;
	private IControladorSalidaTuristica controladorSal;
	private IValidador validador;

	private JComboBox<String> comboBoxDepartamentos;
	private JComboBox<String> comboBoxActividades;
	private JDateChooser dateChooserFechaAlta;
	private JDateChooser dateChooserFecha;
	private JLabel lblActividades;
	private JLabel lblNombre;
	private JLabel lblLugarDeSalida;
	private JLabel lblDepartamento;
	private JLabel lblCantTuristas;
	private JLabel lblFecha;
	private JButton btnInscribir;
	private JLabel lblHora;
	private JLabel lblFechaDeAlta;
	private JButton btnCerrar;

	public AltaSalidaTur() {

	};

	public AltaSalidaTur(IControladorSalidaTuristica controladorSalida,
			IControladorDepartamento controladorDepartamento, IControladorActividadTuristica controladorActividad,
			IValidador validador) {

		this.controladorAct = controladorActividad;
		this.controladorDpto = controladorDepartamento;
		this.controladorSal = controladorSalida;
		this.validador = validador;
		
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta salida turistica");
		setBounds(10, 40, 500, 367);
		getContentPane().setLayout(null);

		lblDepartamento = new JLabel("Departamento:  ");
		lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepartamento.setBounds(5, 16, 150, 15);
		getContentPane().add(lblDepartamento);
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);

		comboBoxActividades = new JComboBox();
		comboBoxActividades.setBounds(155, 41, 269, 25);
		getContentPane().add(comboBoxActividades);

		comboBoxDepartamentos = new JComboBox();
		comboBoxDepartamentos.setBounds(155, 11, 269, 25);
		getContentPane().add(comboBoxDepartamentos);	
		comboBoxDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarActividades(comboBoxDepartamentos.getSelectedItem().toString());
			}

		});

		lblActividades = new JLabel("Actividades:  ");
		lblActividades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblActividades.setBounds(5, 46, 150, 15);
		getContentPane().add(lblActividades);
		lblActividades.setHorizontalAlignment(SwingConstants.RIGHT);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(155, 78, 269, 23);
		getContentPane().add(textFieldNombre);

		lblCantTuristas = new JLabel("Cantidad de turistas: ");
		lblCantTuristas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantTuristas.setBounds(5, 231, 150, 15);
		getContentPane().add(lblCantTuristas);
		lblCantTuristas.setHorizontalAlignment(SwingConstants.RIGHT);

		textFieldCantidad = new JTextField();
		textFieldCantidad.setColumns(10);
		textFieldCantidad.setBounds(155, 226, 45, 25);
		getContentPane().add(textFieldCantidad);

		lblNombre = new JLabel("Nombre:  ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(5, 83, 150, 15);
		getContentPane().add(lblNombre);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);

		lblFecha = new JLabel("Fecha salida:  ");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBounds(5, 118, 150, 15);
		getContentPane().add(lblFecha);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);

		dateChooserFecha = new JDateChooser();
		dateChooserFecha.setBounds(155, 113, 269, 25);
		getContentPane().add(dateChooserFecha);

		lblHora = new JLabel("Hora salida:  ");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHora.setBounds(5, 157, 150, 15);
		getContentPane().add(lblHora);
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);

		textFieldHora = new JTextField();
		textFieldHora.setColumns(10);
		textFieldHora.setBounds(155, 153, 50, 23);
		getContentPane().add(textFieldHora);

		lblLugarDeSalida = new JLabel("Lugar de salida:  ");
		lblLugarDeSalida.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLugarDeSalida.setBounds(5, 195, 150, 15);
		getContentPane().add(lblLugarDeSalida);
		lblLugarDeSalida.setHorizontalAlignment(SwingConstants.RIGHT);

		textFieldLugar = new JTextField();
		textFieldLugar.setColumns(10);
		textFieldLugar.setBounds(155, 193, 269, 23);
		getContentPane().add(textFieldLugar);

		lblFechaDeAlta = new JLabel("Fecha de alta:  ");
		lblFechaDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaDeAlta.setBounds(5, 267, 150, 15);
		getContentPane().add(lblFechaDeAlta);
		lblFechaDeAlta.setHorizontalAlignment(SwingConstants.RIGHT);

		dateChooserFechaAlta = new JDateChooser();
		dateChooserFechaAlta.setBounds(155, 263, 269, 25);
		getContentPane().add(dateChooserFechaAlta);

		btnInscribir = new JButton();
		btnInscribir.setText("Aceptar");
		btnInscribir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInscribir.setBounds(65, 296, 148, 30);
		getContentPane().add(btnInscribir);
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intentarRegistrarSalida(e);
				// limpiarFormulario();
				// setVisible(false);
			}
		});

		btnCerrar = new JButton();
		btnCerrar.setText("Cerrar");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrar.setBounds(218, 296, 151, 30);
		getContentPane().add(btnCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
				doDefaultCloseAction();
			}
		});
		
		try {
			String[] listaDepartamentos = (String[]) controladorDpto.listarDepartamentos().toArray(new String[0]);
			comboBoxDepartamentos.setModel(new DefaultComboBoxModel<String>(listaDepartamentos));

			
		} catch (NoHayEntidadesParaListarException e) {
			mostrarCajitaError(e);
		}
	}

	private void actualizarActividades(String dpto) {
		try {
			comboBoxActividades.setModel(
					new DefaultComboBoxModel<String>((String[]) controladorAct.listarActividadesAsociadasADepartamentoConfirmadas(dpto).toArray(new String[0])));
			comboBoxActividades.setEnabled(true);
		} catch (NoHayEntidadesParaListarException e) {

			JOptionPane.showMessageDialog(this, e.getMessage(), "Alta salida turistica", JOptionPane.ERROR_MESSAGE);
			comboBoxActividades.setEnabled(false);
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));

		} catch (CampoInvalidoException e) {
			mostrarCajitaError(e);
		}
	}

	@SuppressWarnings("deprecation")
	protected void intentarRegistrarSalida(ActionEvent arg0) {
		String nombreActividad = this.comboBoxActividades.getSelectedItem().toString();
		String nombreSalida = this.textFieldNombre.getText();
		int cantidadMax = -1;
		try {
			cantidadMax = Integer.valueOf(this.textFieldCantidad.getText()).intValue();
		} catch (NumberFormatException e) {
			mostrarCajitaError("Campo invalido");
			return;
		}
		Date fechaAltaAux = this.dateChooserFechaAlta.getDate();

		Date fechaSalidaAux = this.dateChooserFecha.getDate();
		try {
			
		//	fechaSalidaAux.setHours((int) Math.floor(Float.parseFloat(textFieldHora.getText())));
			String lugar = this.textFieldLugar.getText();

			DtSalidaTuristica dtSalida = new DtSalidaTuristica();
			dtSalida.setNombreActividad(nombreActividad);
			dtSalida.setNombreSalida(nombreSalida);
			dtSalida.setCantidadMaximaTuristas(cantidadMax);
			
			LocalDate fechaAlta =  fechaAltaAux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			dtSalida.setFechaAlta(fechaAlta.toString());
			
			String fechaSalida =  fechaSalidaAux.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
			fechaSalida = fechaSalida + "T" + textFieldHora.getText();
			
			
			dtSalida.setFechaSalida(fechaSalida);
			
			dtSalida.setLugarSalida(lugar);
			dtSalida.setImagen("salida/_default.jpg");

			if (validador.campoInvalidoAltaSalida(dtSalida)) {
				mostrarCajitaError(MensajeError.campoInvalidoAltaSalida(dtSalida));
				return;
			}

			controladorSal.darDeAltaSalidaTuristica(dtSalida);
			mostrarCajitaExito("La salida turistica se ha realizado con exito");
			limpiarFormulario();
			doDefaultCloseAction();

		} catch (EntidadRepetidaException | CampoInvalidoException e) {
			mostrarCajitaError(e);

		} catch (NumberFormatException e) {
			mostrarCajitaError("Ingrese foramto hora correcto");
		}
	}

	private void mostrarCajitaError(Exception e) {
		mostrarCajitaError(e.getMessage());
	}

	private void mostrarCajitaError(String str) {
		JOptionPane.showMessageDialog(this, str, "Alta salida turistica", JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarCajitaExito(String str) {
		JOptionPane.showMessageDialog(this, str, "Alta salida turistica", JOptionPane.INFORMATION_MESSAGE);
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldCantidad.setText("");
		textFieldHora.setText("");
		textFieldLugar.setText("");
		dateChooserFecha.setCalendar(null);
		dateChooserFechaAlta.setCalendar(null);
	}
}