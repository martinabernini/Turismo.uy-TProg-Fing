package presentacion.usuario;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.NoHayEntidadesParaListarException;
import logica.datatypes.DtProveedor;
import logica.datatypes.DtTurista;
import logica.datatypes.DtUsuario;
import logica.interfaces.IControladorUsuario;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ConsultarUsuario extends JInternalFrame {

	private JLabel lblUsuarios;
	private JButton btnCerrar;
	private JLabel lblInfoUsuario;
	private JComboBox<String> comboBoxUsuario;

	private JLabel lblNombre;
	private JLabel lblTipo;
	private JLabel lblFechadeNacimiento;
	private JLabel lblEmail;
	private JLabel lblApellido;
	private JLabel lblNacionalidad;
	private JLabel lblDescripcion;
	private JLabel lblSitioWeb;

	private JLabel lblInfoFecha;
	private JLabel lblInfoTIPO;
	private JLabel lblInfoNombre;
	private JLabel lblInfoApellido;
	private JLabel lblInfoEmail;
	private JLabel lblInfoNacionalidad;
	private JTextArea lblInfoDescripcion;
	private JLabel lblInfoSitioWeb;
	

	private DtUsuario usuarioSeleccionado;

	private JLabel lblActividaesTuristicas;
	private JComboBox<String> comboBoxActividades;
	private JLabel lblSalidasTuristicasInscr;
	private JComboBox<String> comboBoxSalidas;


	public ConsultarUsuario(IControladorUsuario controladorUsuario) {
		
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta de Usuario");
		setBounds(10, 30, 578, 603);
		getContentPane().setLayout(null);
		
		comboBoxSalidas = new JComboBox<String>();
		comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
		comboBoxSalidas.setBounds(160, 494, 392, 30);
		getContentPane().add(comboBoxSalidas);
		comboBoxSalidas.setEditable(false);
		comboBoxSalidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				String salida = comboBoxSalidas.getSelectedItem().toString();
				presentacion.Principal.crearConsultaSalidaConSalidaSeleccionaInternalFrame(salida);
			}
		});
		
		comboBoxUsuario= new JComboBox<String>();
		comboBoxUsuario.setBounds(193, 16, 275, 30);
		getContentPane().add(comboBoxUsuario);

		comboBoxActividades = new JComboBox<String>();
		comboBoxActividades.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
		comboBoxActividades.setBounds(160, 453, 392, 30);
		getContentPane().add(comboBoxActividades);
		comboBoxActividades.setEditable(false);
		comboBoxActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				String actividad = comboBoxActividades.getSelectedItem().toString();
				presentacion.Principal.crearConsultaActividadConActividadSeleccionadaInternalFrame(actividad);
			}
		});

		lblInfoUsuario = new JLabel("Informacion de Usuario:");
		lblInfoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfoUsuario.setBounds(10, 57, 542, 27);
		lblInfoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblInfoUsuario);

		lblUsuarios = new JLabel("Usuarios: ");
		lblUsuarios.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuarios.setBounds(76, 15, 107, 30);
		getContentPane().add(lblUsuarios);

		lblTipo = new JLabel("Tipo: ");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(10, 95, 140, 30);
		// lblTipo.setAlignmentX(CENTER_ALIGNMENT);
		getContentPane().add(lblTipo);

		lblEmail = new JLabel("Email: ");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(10, 235, 140, 30);
		getContentPane().add(lblEmail);

		lblApellido = new JLabel("Apellido: ");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellido.setBounds(10, 165, 140, 30);
		getContentPane().add(lblApellido);

		lblNombre = new JLabel("Nombre: ");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(10, 130, 140, 30);
		getContentPane().add(lblNombre);

		lblFechadeNacimiento = new JLabel("Fecha de nacimiento: ");
		lblFechadeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechadeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechadeNacimiento.setBounds(10, 200, 140, 30);
		getContentPane().add(lblFechadeNacimiento);

		lblActividaesTuristicas = new JLabel("Actividades Turisticas: ");
		lblActividaesTuristicas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActividaesTuristicas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblActividaesTuristicas.setBounds(10, 452, 140, 30);
		getContentPane().add(lblActividaesTuristicas);

		lblSalidasTuristicasInscr = new JLabel("Salidas Turisticas: ");
		lblSalidasTuristicasInscr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalidasTuristicasInscr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSalidasTuristicasInscr.setBounds(10, 493, 140, 30);
		getContentPane().add(lblSalidasTuristicasInscr);

		// Labels con info cargada

		lblInfoTIPO = new JLabel("");
		lblInfoTIPO.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoTIPO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfoTIPO.setBounds(160, 95, 392, 30);
		getContentPane().add(lblInfoTIPO);

		lblInfoNombre = new JLabel("");
		lblInfoNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfoNombre.setBounds(160, 130, 392, 30);
		getContentPane().add(lblInfoNombre);

		lblInfoApellido = new JLabel("");
		lblInfoApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoApellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfoApellido.setBounds(160, 165, 392, 30);
		getContentPane().add(lblInfoApellido);

		lblInfoFecha = new JLabel("");
		lblInfoFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfoFecha.setBounds(160, 200, 392, 30);
		getContentPane().add(lblInfoFecha);

		lblInfoEmail = new JLabel("");
		lblInfoEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfoEmail.setBounds(160, 235, 392, 30);
		getContentPane().add(lblInfoEmail);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				// limpiarFormulario();
				setVisible(false);
				doDefaultCloseAction();
			}
		});
		btnCerrar.setBounds(430, 532, 122, 30);
		getContentPane().add(btnCerrar);
		
		lblNacionalidad = new JLabel("Nacionalidad: ");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNacionalidad.setBounds(10, 270, 140, 30);
		getContentPane().add(lblNacionalidad);
		
		lblInfoNacionalidad = new JLabel("");
		lblInfoNacionalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoNacionalidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfoNacionalidad.setBounds(160, 270, 392, 30);
		getContentPane().add(lblInfoNacionalidad);
		
		lblDescripcion = new JLabel("Descripcion: ");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcion.setBounds(10, 305, 140, 30);
		getContentPane().add(lblDescripcion);
		
		lblSitioWeb = new JLabel("Sitio web: ");
		lblSitioWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSitioWeb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSitioWeb.setBounds(10, 411, 140, 30);
		getContentPane().add(lblSitioWeb);
		
		lblInfoDescripcion = new JTextArea();
		lblInfoDescripcion.setRows(5);
		lblInfoDescripcion.setLineWrap(true);
		lblInfoDescripcion.setEditable(false);
		lblInfoDescripcion.setBounds(160, 311, 392, 89);
		getContentPane().add(lblInfoDescripcion);
		
		lblInfoSitioWeb = new JLabel("");
		lblInfoSitioWeb.setHorizontalAlignment(SwingConstants.LEFT);
		lblInfoSitioWeb.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfoSitioWeb.setBounds(160, 412, 392, 30);
		getContentPane().add(lblInfoSitioWeb);

	    ImageIcon imagenModificada= new ImageIcon("../Tarea2/SitioWebEstatico/img/defaultUserImg.jpg");
	    Image imagenAux= imagenModificada.getImage();
	    Image nuevaImg= imagenAux.getScaledInstance(170, 137, Image.SCALE_SMOOTH);
	     
	    imagenModificada= new ImageIcon(nuevaImg);
	    
		
		/*
		  lblInfoImagen = new JLabel(); //JLabel Creation lblInfoImagen.setIcon(new
		  ImageIcon(
		  "C:\\Users\\berni\\Documents\\Git\\tpgr22\\Tarea2\\SitioWebEstatico\\img/defaultUserImg.jpg"
		  )); //Sets the image to be displayed as an icon lblInfoImagen.setBounds(282,
		  130, 205 , 177); //Sets the location of the image
		  getContentPane().add(lblInfoImagen);
		 */
		

		try {
			if (controladorUsuario.listarUsuarios().size() != 0) {
					
				comboBoxUsuario.setModel(new DefaultComboBoxModel<String>((String[]) controladorUsuario.listarUsuarios().toArray(new String[0])));
				comboBoxUsuario.setEditable(false);
				comboBoxUsuario.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent exception) {
						cargarYMostrarInfoUsuario(controladorUsuario);
					}
				});
					
					
				}
			} catch (NoHayEntidadesParaListarException e) {
			}
			
			
			
		
	}

	private void cargarYMostrarInfoUsuario(IControladorUsuario controladorUsuario) {
		try {
			usuarioSeleccionado = controladorUsuario.getUsuario(comboBoxUsuario.getSelectedItem().toString());
		} catch (EntidadNoExisteException | CampoInvalidoException e1) {
			mostrarCajitaError(e1);
			return;
		}
		
		    


		lblInfoNombre.setText(usuarioSeleccionado.getNombre());
		lblInfoApellido.setText(usuarioSeleccionado.getApellido());
		lblInfoEmail.setText(usuarioSeleccionado.getEmail());
		lblInfoFecha.setText(usuarioSeleccionado.getFechaNacimiento());
		

		if (usuarioSeleccionado instanceof DtTurista) {
			lblInfoTIPO.setText("Turista");

			
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>(new String[] { "No aplica" }));
			comboBoxActividades.setEnabled(false);

			comboBoxSalidas.setEnabled(true);
			comboBoxSalidas.setModel(
					new DefaultComboBoxModel<String>((String[]) ((DtTurista) usuarioSeleccionado).getNombreSalidasALasQueEstaInscripto().getLista().toArray(new String[0])));

			DtTurista turista = ((DtTurista) usuarioSeleccionado);

			if (tieneInscripciones(turista)) {
				comboBoxSalidas.setModel(new DefaultComboBoxModel<String>((String[]) turista.getNombreSalidasALasQueEstaInscripto().getLista().toArray(new String[0])));
				comboBoxSalidas.setEnabled(true);
				comboBoxSalidas.setVisible(true);
			}
			
			lblInfoNacionalidad.setText(turista.getNacionalidad());
			lblInfoSitioWeb.setText("");
			lblInfoDescripcion.setText("");
			
			return;
		}

		lblInfoTIPO.setText("Proveedor");
		lblInfoNacionalidad.setText("");

		comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(new String[] { "No aplica" }));
		comboBoxSalidas.setEnabled(false);

		comboBoxActividades.setModel(
				new DefaultComboBoxModel<String>((String[]) ((DtProveedor) usuarioSeleccionado).getActividadesTuristicas().toArray(new String[0])));
		DtProveedor proveedor = (DtProveedor) usuarioSeleccionado;
		
		lblInfoSitioWeb.setText(proveedor.getUrlSitioWeb());
		lblInfoDescripcion.setText(proveedor.getDescripcion());

		if (tieneActividades(proveedor)) {
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>((String[]) proveedor.getActividadesTuristicas().toArray(new String[0])));
			comboBoxActividades.setEnabled(true);
			comboBoxActividades.setVisible(true);
		}

	}

	private boolean tieneActividades(DtProveedor proveedor) {
		return proveedor.getActividadesTuristicas() != null && proveedor.getActividadesTuristicas().size() > 0;
	}

	private boolean tieneInscripciones(DtTurista turista) {
		return turista.getNombreSalidasALasQueEstaInscripto().getLista() != null && turista.getNombreSalidasALasQueEstaInscripto().getLista().size() > 0;
	}

	private void mostrarCajitaError(Exception exception) {
		mostrarCajitaError(exception.getMessage());
	}

	private void mostrarCajitaError(String str) {
		JOptionPane.showMessageDialog(this, str, "Consutar Usuario", JOptionPane.ERROR_MESSAGE);
	}

//	private void limpiarFormulario() {
//		lblInfoNombre.setText("");
//		lblInfoApellido.setText("");
//		lblInfoEmail.setText("");
//		lblInfoTIPO.setText("");
//		lblInfoFecha.setText("");
//		comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
//		comboBoxSalidas.setVisible(false);
//		comboBoxActividades.setModel(new DefaultComboBoxModel<String>(new String[] { " " }));
//		comboBoxActividades.setVisible(false);
//
//	}
}
