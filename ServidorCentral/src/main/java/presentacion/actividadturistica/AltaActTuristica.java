package presentacion.actividadturistica;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadRepetidaException;
import excepciones.NoHayEntidadesParaListarException;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;
import logica.datatypes.DtActividadTuristica;
import logica.interfaces.IControladorDepartamento;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class AltaActTuristica extends JInternalFrame {

	private IControladorActividadTuristica controladorAct;
	private IControladorUsuario controladorUsu;
	private IControladorDepartamento controladorDpto;
	private IValidador validador;
	
	
	private Map<String, JCheckBox> checkBoxes = new HashMap<>();
	
	
	
	private JButton btnAgregar;
	private JButton btnCerrar;
	private JLabel lblDepartamento;
	private JLabel lblProveedor;
	private JComboBox<String> comboBoxProv;
	private JComboBox<String> comboBoxDep;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblDuracion;
	private JLabel lblCosto;
	private JLabel lblCiudad;
	private JLabel lblFechaAlta;
	private JTextField textFieldNombre;
	private JTextArea textFieldDescripcion;
	private JTextField textFieldDuracion;
	private JTextField textFieldCosto;
	private JTextField textFieldCiudad;
	private JDateChooser dateChooser;
	private String dptoAct;
	private JCheckBox chckbxNewCheckBox_1;

	public AltaActTuristica(IControladorUsuario controladorUsuario, IControladorDepartamento controladorDepartamento,
			IControladorActividadTuristica controladorActividad, IValidador validador) {

		this.controladorAct = controladorActividad;
		this.controladorUsu = controladorUsuario;
		this.controladorDpto = controladorDepartamento;
		this.validador = validador;
		
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta de actividad turistica");
		setBounds(10, 40, 474, 419);
		getContentPane().setLayout(null);

		lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProveedor.setBounds(10, 11, 130, 20);
		getContentPane().add(lblProveedor);
		lblProveedor.setAlignmentX(Component.RIGHT_ALIGNMENT);

		lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartamento.setBounds(10, 43, 130, 20);
		getContentPane().add(lblDepartamento);
		comboBoxProv = new JComboBox<String>();
		
		comboBoxProv.setEditable(false);
		comboBoxProv.setBounds(140, 10, 300, 25);
		getContentPane().add(comboBoxProv);
		comboBoxDep = new JComboBox<String>();
		
		comboBoxDep.setEditable(false);
		comboBoxDep.setBounds(140, 42, 300, 25);
		getContentPane().add(comboBoxDep);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 75, 130, 20);
		getContentPane().add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(140, 74, 300, 25);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(10, 107, 130, 20);
		getContentPane().add(lblDescripcion);

		textFieldDescripcion = new JTextArea();
		textFieldDescripcion.setLineWrap(true);
		textFieldDescripcion.setColumns(5);
		textFieldDescripcion.setToolTipText("");
		textFieldDescripcion.setBounds(140, 108, 300, 76);
		getContentPane().add(textFieldDescripcion);

		lblDuracion = new JLabel("Duracion (Horas):");
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDuracion.setBounds(10, 199, 130, 20);
		getContentPane().add(lblDuracion);

		textFieldDuracion = new JTextField();
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(140, 195, 80, 25);
		getContentPane().add(textFieldDuracion);

		lblCosto = new JLabel("Costo (UYU):");
		lblCosto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCosto.setBounds(10, 231, 130, 20);
		getContentPane().add(lblCosto);

		textFieldCosto = new JTextField();
		textFieldCosto.setColumns(10);
		textFieldCosto.setBounds(140, 230, 80, 25);
		getContentPane().add(textFieldCosto);

		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCiudad.setBounds(10, 263, 130, 20);
		getContentPane().add(lblCiudad);

		lblFechaAlta = new JLabel("Fecha de alta:");
		lblFechaAlta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaAlta.setBounds(10, 295, 130, 20);
		getContentPane().add(lblFechaAlta);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setColumns(10);
		textFieldCiudad.setBounds(140, 262, 300, 25);
		getContentPane().add(textFieldCiudad);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				intentarRegistrarActividad(exception);
			}
		});
		btnAgregar.setBounds(140, 352, 100, 30);
		getContentPane().add(btnAgregar);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				doDefaultCloseAction();
			}
		});
		btnCerrar.setBounds(250, 353, 100, 30);
		getContentPane().add(btnCerrar);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(140, 295, 300, 25);
		getContentPane().add(dateChooser);
	
		JLabel lblCategorias = new JLabel("Categorias:");
		lblCategorias.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategorias.setBounds(10, 327, 130, 20);
		getContentPane().add(lblCategorias);
		
		chckbxNewCheckBox_1 = new JCheckBox("New check box");
		getContentPane().add(chckbxNewCheckBox_1);
		


		try {
			if (controladorUsu.listarUsuarios().size() != 0) {
				

				comboBoxProv.setModel(new DefaultComboBoxModel<String>((String[]) controladorUsu.listarProveedores().toArray(new String[0])));
				comboBoxDep.setModel(new DefaultComboBoxModel<String>((String[]) controladorDpto.listarDepartamentos().toArray(new String[0])));
				String[] categorias = (String[]) controladorAct.listarTodasLasCategorias().toArray(new String[0]);	
				
				int moverCheckbox=0;
				int moverElementos=0;
				int cont= 1;
				
				for(String categoria: categorias) {
					JCheckBox chckbxNewCheckBox = new JCheckBox(categoria);
					chckbxNewCheckBox.setBounds(143, 327 + moverCheckbox, 300, 23);
					getContentPane().add(chckbxNewCheckBox);
					setBounds(10, 40, 474, 419 + moverElementos);
					btnAgregar.setBounds(140, 352 + moverElementos, 100, 30);
					btnCerrar.setBounds(282, 352 + moverElementos, 100, 30);
					moverCheckbox = moverCheckbox + 25;
					moverElementos = moverElementos +25;
					checkBoxes.put(categoria, chckbxNewCheckBox);
				}
				
			}

		} catch (NoHayEntidadesParaListarException e) {
			mostrarCajitaError(e);
		}
	}

	protected void intentarRegistrarActividad(ActionEvent arg0) {
		int duracionAct = -1;
		float costoAct = -1;
		
		ArrayList<String> categorias = new ArrayList<>();
		for (String categoria: checkBoxes.keySet()) {
			if(checkBoxes.get(categoria).isSelected()){
				categorias.add(categoria);
			}
		}

		
		// Obtengo datos de los controles Swing
		String nombreAct = this.textFieldNombre.getText();
		String descripcionAct = this.textFieldDescripcion.getText();
		try {
			duracionAct = Integer.parseInt(this.textFieldDuracion.getText());
			costoAct = Float.parseFloat(this.textFieldCosto.getText());
		} catch (NumberFormatException e) {
			mostrarCajitaError("La duracion y el costo deben ser numeros");
			return;
		}
		
		if(categorias.isEmpty()) {
			mostrarCajitaError("Debe seleccionar al menos una categoria");
			return;
		}
		
		
		Date fechaAltaAct = this.dateChooser.getDate();
		LocalDate fechaAlta =  fechaAltaAct.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
		String ciudadAct = this.textFieldCiudad.getText();
		String provAct = this.comboBoxProv.getSelectedItem().toString();
		dptoAct = comboBoxDep.getSelectedItem().toString();

		DtActividadTuristica dtAct = new DtActividadTuristica();
		dtAct.setNombre(nombreAct);
		dtAct.setDescripcion(descripcionAct);
		dtAct.setDuracionHrs(duracionAct);
		dtAct.setCostoPorPersona(costoAct);
		dtAct.setCiudad(ciudadAct);
		
		dtAct.setFechaAlta(fechaAlta.toString());
		dtAct.setDepartamento(dptoAct);
		dtAct.setProovedor(provAct);
		
		dtAct.setCategorias(categorias);

		if (validador.campoInvalidoAltaActividad(dtAct)) {
			mostrarCajitaError(MensajeError.campoInvalidoAltaActividad(dtAct));
			return;
		}
		try {
			controladorAct.darDeAltaActividadTuristica(dtAct);
			mostrarCajitaExito("La actividad turistica se ha creado con exito");
			limpiarFormulario();
			setVisible(false);
		} catch (EntidadRepetidaException | CampoInvalidoException e) {
			mostrarCajitaError(e);
		}
	}

	private void mostrarCajitaError(Exception exception) {
		mostrarCajitaError(exception.getMessage());
	}

	private void mostrarCajitaError(String str) {
		JOptionPane.showMessageDialog(this, str, "Registrar actividad turistica", JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarCajitaExito(String str) {
		JOptionPane.showMessageDialog(this, str, "Registrar actividad turistica", JOptionPane.INFORMATION_MESSAGE);
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldDuracion.setText("");
		textFieldCosto.setText("");
		textFieldCiudad.setText("");
		dateChooser.setCalendar(null);
	}
}