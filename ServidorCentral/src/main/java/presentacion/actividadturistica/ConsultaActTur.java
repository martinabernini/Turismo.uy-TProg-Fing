package presentacion.actividadturistica;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtActividadTuristica;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorDepartamento;
import presentacion.Principal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class ConsultaActTur extends JInternalFrame {
	private JButton btnCerrar;

	private JLabel lblActividades;
	private JComboBox<String> comboBoxActividades;

	private JLabel lblDepartamento;
	private JComboBox<String> comboBoxDepartamentos;

	private JLabel lblInfoActividad;

	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblDuracion;
	private JLabel lblCostoPorPersona;
	private JLabel lblCiudad;

	private JLabel lblInfoNombre;
	private JTextArea lblInfoDescripcion;
	private JLabel lblInfoDuracion;
	private JLabel lblInfoCostoPorPersona;
	private JLabel lblInfoCiudad;
	private JLabel lblInfoFechaDeAlta;

	private JLabel lblNombre_6;
	private JLabel lblSalidas;
	private JComboBox<String> comboBoxSalidas;
	private JLabel lblPaquetes;
	private JComboBox<String> comboBoxPaquetes;

	// ------------------------------------------

	private String[] listaDepartamentos;
	private String[] listaActividades;
	private String[] listaSalidas;
	private String[] listaPaquetes;
	private String[] listaCategorias;

	private IControladorDepartamento controladorDepartamento;
	private IControladorActividadTuristica controladorActividadTuristica;

	private DtActividadTuristica actividadSeleccionada;
	private JScrollPane scrollPaneDescripcion;
	private JLabel lblDescripcion_1;
	private JLabel lblCategorias;
	private JLabel lblEstado;
	private JLabel lblInfoEstado;

	/*
	 * */
	public ConsultaActTur() {
	}

	public ConsultaActTur(IControladorDepartamento controladorDepartamento,
			IControladorActividadTuristica controladorActividadTuristica, String nombreActividad) {

		this(controladorDepartamento, controladorActividadTuristica);

		try {
			DtActividadTuristica actividad = controladorActividadTuristica.getActividadTuristica(nombreActividad);
			actualizarActividades(actividad.getDepartamento());
			comboBoxDepartamentos.setSelectedItem(actividad.getDepartamento());
			comboBoxActividades.setSelectedItem(nombreActividad);

		} catch (EntidadNoExisteException e) {
			e.printStackTrace();
			mostrarCajitaError(e);
		} catch (CampoInvalidoException e) {
			mostrarCajitaError(e);
		}

		mostrarActividadSeleccionada(nombreActividad);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public ConsultaActTur(IControladorDepartamento controladorDepartamento,
			IControladorActividadTuristica controladorActividadTuristica) {

		this.controladorDepartamento = controladorDepartamento;
		this.controladorActividadTuristica = controladorActividadTuristica;

		try {

			listaDepartamentos =  controladorDepartamento.listarDepartamentos().toArray(new String[0]);
			listaActividades = new String[] { "" };
			listaSalidas = new String[] { "" };
			listaPaquetes = new String[] { "" };

			// --------------------------------------------

			setResizable(true);
			setIconifiable(true);
			setMaximizable(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setClosable(true);
			setTitle("Consulta de actividad turistica");
			setBounds(10, 40, 550, 514);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 40, 159, 22, 237, 95, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 33, 23, 23, 22, 22, 0, 101, 22, 22, 22, 22, 23, 23, 23, 30, 0, 0, 0 };
			gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
			gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
					0.0, 0.0, 1.0, Double.MIN_VALUE };
			getContentPane().setLayout(gridBagLayout);

			comboBoxDepartamentos = new JComboBox<String>();
			comboBoxDepartamentos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarActividades(comboBoxDepartamentos.getSelectedItem().toString());
				}
			});
			// configuacionBasicaDeVentana();

			lblDepartamento = new JLabel("Departamento:");
			lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
			gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
			gbc_lblDepartamento.fill = GridBagConstraints.VERTICAL;
			gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
			gbc_lblDepartamento.gridx = 1;
			gbc_lblDepartamento.gridy = 1;
			getContentPane().add(lblDepartamento, gbc_lblDepartamento);

			comboBoxDepartamentos.setModel(new DefaultComboBoxModel<String>(listaDepartamentos));

			comboBoxDepartamentos.setEditable(false);
			GridBagConstraints gbc_comboBoxDepartamentos = new GridBagConstraints();
			gbc_comboBoxDepartamentos.fill = GridBagConstraints.BOTH;
			gbc_comboBoxDepartamentos.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxDepartamentos.gridwidth = 2;
			gbc_comboBoxDepartamentos.gridx = 3;
			gbc_comboBoxDepartamentos.gridy = 1;
			getContentPane().add(comboBoxDepartamentos, gbc_comboBoxDepartamentos);

			lblActividades = new JLabel("Actividades:");
			lblActividades.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblActividades = new GridBagConstraints();
			gbc_lblActividades.anchor = GridBagConstraints.EAST;
			gbc_lblActividades.fill = GridBagConstraints.VERTICAL;
			gbc_lblActividades.insets = new Insets(0, 0, 5, 5);
			gbc_lblActividades.gridx = 1;
			gbc_lblActividades.gridy = 2;
			getContentPane().add(lblActividades, gbc_lblActividades);

			comboBoxActividades = new JComboBox<String>();
			comboBoxActividades.setEnabled(false);
			comboBoxActividades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarActividadSeleccionada((String) comboBoxActividades.getSelectedItem());
				}
			});

			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(listaActividades));
			GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
			gbc_comboBoxActividades.fill = GridBagConstraints.BOTH;
			gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxActividades.gridwidth = 2;
			gbc_comboBoxActividades.gridx = 3;
			gbc_comboBoxActividades.gridy = 2;
			getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);

			lblInfoActividad = new JLabel("Informacion de la actividad");
			lblInfoActividad.setHorizontalAlignment(SwingConstants.CENTER);
			lblInfoActividad.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInfoActividad = new GridBagConstraints();
			gbc_lblInfoActividad.fill = GridBagConstraints.VERTICAL;
			gbc_lblInfoActividad.insets = new Insets(0, 0, 5, 5);
			gbc_lblInfoActividad.gridx = 3;
			gbc_lblInfoActividad.gridy = 3;
			getContentPane().add(lblInfoActividad, gbc_lblInfoActividad);

			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.anchor = GridBagConstraints.EAST;
			gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 1;
			gbc_lblNombre.gridy = 4;
			getContentPane().add(lblNombre, gbc_lblNombre);

			lblInfoNombre = new JLabel("");
			lblInfoNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInfoNombre = new GridBagConstraints();
			gbc_lblInfoNombre.anchor = GridBagConstraints.WEST;
			gbc_lblInfoNombre.fill = GridBagConstraints.VERTICAL;
			gbc_lblInfoNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblInfoNombre.gridx = 3;
			gbc_lblInfoNombre.gridy = 4;
			getContentPane().add(lblInfoNombre, gbc_lblInfoNombre);
			
			lblEstado = new JLabel("Estado:");
			lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblEstado = new GridBagConstraints();
			gbc_lblEstado.anchor = GridBagConstraints.EAST;
			gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
			gbc_lblEstado.gridx = 1;
			gbc_lblEstado.gridy = 5;
			getContentPane().add(lblEstado, gbc_lblEstado);
			
			lblInfoEstado = new JLabel("");
			lblInfoEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInfoEstado = new GridBagConstraints();
			gbc_lblInfoEstado.anchor = GridBagConstraints.WEST;
			gbc_lblInfoNombre.anchor = GridBagConstraints.WEST;
			gbc_lblInfoEstado.insets = new Insets(0, 0, 5, 5);
			gbc_lblInfoEstado.gridx = 3;
			gbc_lblInfoEstado.gridy = 5;
			getContentPane().add(lblInfoEstado, gbc_lblInfoEstado);

			lblDescripcion = new JLabel("Descripcion:");
			lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
			gbc_lblDescripcion.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcion.gridx = 1;
			gbc_lblDescripcion.gridy = 6;
			getContentPane().add(lblDescripcion, gbc_lblDescripcion);

			scrollPaneDescripcion = new JScrollPane();
			scrollPaneDescripcion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneDescripcion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			GridBagConstraints gbc_scrollPaneDescripcion = new GridBagConstraints();
			gbc_scrollPaneDescripcion.gridwidth = 2;
			gbc_scrollPaneDescripcion.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPaneDescripcion.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneDescripcion.gridx = 3;
			gbc_scrollPaneDescripcion.gridy = 6;
			getContentPane().add(scrollPaneDescripcion, gbc_scrollPaneDescripcion);

			lblInfoDescripcion = new JTextArea("");
			lblInfoDescripcion.setLineWrap(true);
			scrollPaneDescripcion.setViewportView(lblInfoDescripcion);
			lblInfoDescripcion.setEditable(false);
			lblInfoDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));

			lblDuracion = new JLabel("Duracion (Horas):");
			lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
			gbc_lblDuracion.anchor = GridBagConstraints.EAST;
			gbc_lblDuracion.fill = GridBagConstraints.VERTICAL;
			gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
			gbc_lblDuracion.gridx = 1;
			gbc_lblDuracion.gridy = 7;
			getContentPane().add(lblDuracion, gbc_lblDuracion);

			lblInfoDuracion = new JLabel("");
			lblInfoDuracion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInfoDuracion = new GridBagConstraints();
			gbc_lblInfoDuracion.anchor = GridBagConstraints.WEST;
			gbc_lblInfoDuracion.fill = GridBagConstraints.VERTICAL;
			gbc_lblInfoDuracion.insets = new Insets(0, 0, 5, 5);
			gbc_lblInfoDuracion.gridx = 3;
			gbc_lblInfoDuracion.gridy = 7;
			getContentPane().add(lblInfoDuracion, gbc_lblInfoDuracion);

			lblCostoPorPersona = new JLabel("Costo por persona (UYU):");
			lblCostoPorPersona.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblCostoPorPersona = new GridBagConstraints();
			gbc_lblCostoPorPersona.anchor = GridBagConstraints.EAST;
			gbc_lblCostoPorPersona.fill = GridBagConstraints.VERTICAL;
			gbc_lblCostoPorPersona.insets = new Insets(0, 0, 5, 5);
			gbc_lblCostoPorPersona.gridx = 1;
			gbc_lblCostoPorPersona.gridy = 8;
			getContentPane().add(lblCostoPorPersona, gbc_lblCostoPorPersona);

			lblInfoCostoPorPersona = new JLabel("");
			lblInfoCostoPorPersona.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInfoCostoPorPersona = new GridBagConstraints();
			gbc_lblInfoCostoPorPersona.anchor = GridBagConstraints.WEST;
			gbc_lblInfoCostoPorPersona.fill = GridBagConstraints.VERTICAL;
			gbc_lblInfoCostoPorPersona.insets = new Insets(0, 0, 5, 5);
			gbc_lblInfoCostoPorPersona.gridx = 3;
			gbc_lblInfoCostoPorPersona.gridy = 8;
			getContentPane().add(lblInfoCostoPorPersona, gbc_lblInfoCostoPorPersona);

			lblCiudad = new JLabel("Ciudad:");
			lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
			gbc_lblCiudad.anchor = GridBagConstraints.EAST;
			gbc_lblCiudad.fill = GridBagConstraints.VERTICAL;
			gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
			gbc_lblCiudad.gridx = 1;
			gbc_lblCiudad.gridy = 9;
			getContentPane().add(lblCiudad, gbc_lblCiudad);

			lblInfoCiudad = new JLabel("");
			lblInfoCiudad.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInfoCiudad = new GridBagConstraints();
			gbc_lblInfoCiudad.anchor = GridBagConstraints.WEST;
			gbc_lblInfoCiudad.fill = GridBagConstraints.VERTICAL;
			gbc_lblInfoCiudad.insets = new Insets(0, 0, 5, 5);
			gbc_lblInfoCiudad.gridx = 3;
			gbc_lblInfoCiudad.gridy = 9;
			getContentPane().add(lblInfoCiudad, gbc_lblInfoCiudad);

			lblNombre_6 = new JLabel("Fecha de alta:");
			lblNombre_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNombre_6 = new GridBagConstraints();
			gbc_lblNombre_6.anchor = GridBagConstraints.EAST;
			gbc_lblNombre_6.fill = GridBagConstraints.VERTICAL;
			gbc_lblNombre_6.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre_6.gridx = 1;
			gbc_lblNombre_6.gridy = 10;
			getContentPane().add(lblNombre_6, gbc_lblNombre_6);

			lblInfoFechaDeAlta = new JLabel("");
			lblInfoFechaDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblInfoFechaDeAlta = new GridBagConstraints();
			gbc_lblInfoFechaDeAlta.anchor = GridBagConstraints.WEST;
			gbc_lblInfoFechaDeAlta.fill = GridBagConstraints.VERTICAL;
			gbc_lblInfoFechaDeAlta.insets = new Insets(0, 0, 5, 5);
			gbc_lblInfoFechaDeAlta.gridx = 3;
			gbc_lblInfoFechaDeAlta.gridy = 10;
			getContentPane().add(lblInfoFechaDeAlta, gbc_lblInfoFechaDeAlta);

			lblSalidas = new JLabel("Salidas:");
			lblSalidas.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblSalidas = new GridBagConstraints();
			gbc_lblSalidas.anchor = GridBagConstraints.EAST;
			gbc_lblSalidas.fill = GridBagConstraints.VERTICAL;
			gbc_lblSalidas.insets = new Insets(0, 0, 5, 5);
			gbc_lblSalidas.gridx = 1;
			gbc_lblSalidas.gridy = 11;
			getContentPane().add(lblSalidas, gbc_lblSalidas);

			comboBoxPaquetes = new JComboBox<String>();
			comboBoxPaquetes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (comboBoxPaquetes.getSelectedItem() != null) {
						seleccionadoPaquete(comboBoxPaquetes.getSelectedItem().toString());
					}
				}
			});

			comboBoxSalidas = new JComboBox<String>();
			comboBoxSalidas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionadoSalida((String) comboBoxSalidas.getSelectedItem(),
							(String) comboBoxDepartamentos.getSelectedItem(),
							(String) comboBoxActividades.getSelectedItem());
				}
			});

			comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(listaSalidas));
			GridBagConstraints gbc_comboBoxSalidas = new GridBagConstraints();
			gbc_comboBoxSalidas.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxSalidas.anchor = GridBagConstraints.SOUTH;
			gbc_comboBoxSalidas.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxSalidas.gridwidth = 2;
			gbc_comboBoxSalidas.gridx = 3;
			gbc_comboBoxSalidas.gridy = 11;
			getContentPane().add(comboBoxSalidas, gbc_comboBoxSalidas);

			if (listaPaquetes != null && listaPaquetes.length > 0) {
				comboBoxPaquetes.setModel(new DefaultComboBoxModel<String>(listaPaquetes));
			}
			
						lblPaquetes = new JLabel("Paquetes:");
						lblPaquetes.setFont(new Font("Tahoma", Font.PLAIN, 12));
						GridBagConstraints gbc_lblPaquetes = new GridBagConstraints();
						gbc_lblPaquetes.anchor = GridBagConstraints.EAST;
						gbc_lblPaquetes.fill = GridBagConstraints.VERTICAL;
						gbc_lblPaquetes.insets = new Insets(0, 0, 5, 5);
						gbc_lblPaquetes.gridx = 1;
						gbc_lblPaquetes.gridy = 12;
						getContentPane().add(lblPaquetes, gbc_lblPaquetes);

			GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
			gbc_comboBoxPaquetes.fill = GridBagConstraints.BOTH;
			gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxPaquetes.gridwidth = 2;
			gbc_comboBoxPaquetes.gridx = 3;
			gbc_comboBoxPaquetes.gridy = 12;
			getContentPane().add(comboBoxPaquetes, gbc_comboBoxPaquetes);
						
						lblCategorias = new JLabel("");
						GridBagConstraints gbc_lblCategorias = new GridBagConstraints();
						gbc_lblCategorias.anchor = GridBagConstraints.NORTHWEST;
						gbc_lblCategorias.insets = new Insets(0, 0, 5, 5);
						gbc_lblCategorias.gridx = 3;
						gbc_lblCategorias.gridy = 14;
						getContentPane().add(lblCategorias, gbc_lblCategorias);
										
						lblDescripcion_1 = new JLabel("Categoria(s):");
						lblDescripcion_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
						GridBagConstraints gbc_lblDescripcion_1 = new GridBagConstraints();
						gbc_lblDescripcion_1.anchor = GridBagConstraints.NORTHEAST;
						gbc_lblDescripcion_1.insets = new Insets(0, 0, 5, 5);
						gbc_lblDescripcion_1.gridx = 1;
						gbc_lblDescripcion_1.gridy = 14;
						getContentPane().add(lblDescripcion_1, gbc_lblDescripcion_1);
						
						btnCerrar = new JButton("Cerrar");
						btnCerrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						// limpiarFormulario();
						// setVisible(false);
						doDefaultCloseAction();
						}
						});
						GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
						gbc_btnCerrar.insets = new Insets(0, 0, 5, 5);
						gbc_btnCerrar.fill = GridBagConstraints.BOTH;
						gbc_btnCerrar.gridx = 4;
						gbc_btnCerrar.gridy = 15;
						getContentPane().add(btnCerrar, gbc_btnCerrar);

		} catch (NoHayEntidadesParaListarException e) {
			mostrarCajitaError(e);
		}

	}

	private void actualizarActividades(String nombreDepartamento) {

		try {
			listaActividades = (String[]) controladorActividadTuristica
					.listarActividadesAsociadasADepartamento(nombreDepartamento).toArray(new String[0]);
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(listaActividades));
			comboBoxActividades.setEnabled(true);

			limpiarComboboxSalidaYPaquete();

		} catch (NoHayEntidadesParaListarException e) {
			limpiarComboboxSalidaYPaquete();

			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
			comboBoxActividades.setEnabled(false);

			//comboBoxPaquetes.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
			//comboBoxPaquetes.setEnabled(false);

			limpiarInfoSalida();


			mostrarCajitaError(e);

		} catch (CampoInvalidoException e) {
			mostrarCajitaError(e);
		}

	}

	private void limpiarInfoSalida() {
		lblInfoNombre.setText("");
		lblInfoDescripcion.setText("");
		lblInfoDuracion.setText("");
		lblInfoCostoPorPersona.setText("");
		lblInfoCiudad.setText("");
		lblInfoFechaDeAlta.setText("");
		lblCategorias.setText("");
	}

	private void limpiarComboboxSalidaYPaquete() {
		comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		comboBoxSalidas.setEnabled(false);
		comboBoxPaquetes.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		comboBoxPaquetes.setEnabled(false);
	}

	private void mostrarActividadSeleccionada(String nombreActividadSeleccionada) {
		try {
			actividadSeleccionada = controladorActividadTuristica.getActividadTuristica(nombreActividadSeleccionada);

			lblInfoNombre.setText(actividadSeleccionada.getNombre());
			lblInfoDescripcion.setText(actividadSeleccionada.getDescripcion());
			lblInfoDuracion.setText(Integer.toString(actividadSeleccionada.getDuracionHrs()));
			lblInfoCostoPorPersona.setText(Float.toString(actividadSeleccionada.getCostoPorPersona()));
			lblInfoCiudad.setText(actividadSeleccionada.getCiudad());
			lblInfoFechaDeAlta.setText(actividadSeleccionada.getFechaAlta().toString());
			lblInfoEstado.setText(actividadSeleccionada.getEstado().name());
			String cat = "";
			for(String categoria: actividadSeleccionada.getCategorias()) {
				cat = cat + "[" + categoria + "]" + " ";
			}
			lblCategorias.setText(cat);

		} catch (EntidadNoExisteException | CampoInvalidoException e) {
			mostrarCajitaError(e);
		}

		actualizarComboBoxSalidas();
		actualizarComboBoxPaquetes();
	}

	private void actualizarComboBoxSalidas() {
		if (actividadSeleccionada.getSalidas() != null) {
			comboBoxSalidas.setModel(new DefaultComboBoxModel<String>((String[]) actividadSeleccionada.getSalidas().toArray(new String[0])));
			comboBoxSalidas.setEnabled(true);
			return;
		}
		comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
		comboBoxSalidas.setVisible(false);
	}

	private void actualizarComboBoxPaquetes() {

		if (actividadSeleccionada.getPaquetes() != null) {
			comboBoxPaquetes.setModel(new DefaultComboBoxModel<String>((String[]) actividadSeleccionada.getPaquetes().toArray(new String[0])));
			comboBoxPaquetes.setEnabled(true);
			return;
		}
		comboBoxPaquetes.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));
	}

	private void seleccionadoSalida(String nombreSalida, String nombreDepartamento, String nombreActividad) {
		Principal.crearConsultaSalidaConSalidaSeleccionaInternalFrame(nombreSalida);

	}

	private void seleccionadoPaquete(String nombrePaquete) {
		Principal.crearConsultaPaquetesConPaqueteSeleccionadoInternalFrame(nombrePaquete);
	}

	public void limpiarFormulario() {
		limpiarInfoSalida();

		comboBoxDepartamentos.setSelectedIndex(0);
		comboBoxActividades.setSelectedIndex(0);
		comboBoxSalidas.setSelectedIndex(0);
		comboBoxPaquetes.setSelectedIndex(0);
		lblInfoEstado.setText("");

	}

	private void mostrarCajitaError(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Consutar Actividad Turistica", JOptionPane.ERROR_MESSAGE);
	}

}
