package presentacion.paqueteactividades;

import javax.swing.JInternalFrame;

import logica.datatypes.DtPaqueteActividades;
import logica.interfaces.IControladorPaqueteActividades;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
import excepciones.NoHayEntidadesParaListarException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ConsultaPaqActTur extends JInternalFrame {
	
	
	private JLabel lblPaquetes;
	private JComboBox comboBoxPaquetes;
	
	private JLabel lblInformacionPaquete;
	
	private JLabel lblNombre;
	private JLabel lblInfoNombre;
	
	private JLabel lblDescripcion;
	private JTextArea lblInfoDescripcion;
	
	private JLabel lblValidez;
	private JLabel lblInfoValidez;
	
	private JLabel lblDescuento;
	private JLabel lblInfoDescuento;
	
	private JLabel lblFechaAlta;
	private JLabel lblInfoFechaAlta;
	
	private JLabel lblActividades;
	private JComboBox comboBoxActividades;
	
	
	private JButton btnCerrar;
	
	IControladorPaqueteActividades controladorPaqueteActividades;
	private JLabel lblCategorias;
	private JLabel lblInfoCategorias;
	
	public ConsultaPaqActTur(IControladorPaqueteActividades controladorPaqueteActividades) {
		
		this.controladorPaqueteActividades = controladorPaqueteActividades;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Consulta paquete de actividad turistica");
        setBounds(10, 40, 512, 383);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{21, 91, 176, 168, 0, 0};
        gridBagLayout.rowHeights = new int[]{15, 30, 30, 30, 30, 0, 30, 30, 30, 30, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        
        /* --------------- PAQUETES --------------------- */
        
        
        lblPaquetes = new JLabel("Paquetes de actividades:");
        GridBagConstraints gbc_lblPaquetes = new GridBagConstraints();
        gbc_lblPaquetes.anchor = GridBagConstraints.EAST;
        gbc_lblPaquetes.insets = new Insets(0, 0, 5, 5);
        gbc_lblPaquetes.gridx = 1;
        gbc_lblPaquetes.gridy = 1;
        getContentPane().add(lblPaquetes, gbc_lblPaquetes);
        
        comboBoxPaquetes = new JComboBox();
        comboBoxPaquetes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		completarDatosPaquete(controladorPaqueteActividades);
        	}
        });
        
        try {
			comboBoxPaquetes.setModel(new DefaultComboBoxModel<String>((String[]) controladorPaqueteActividades.listarPaquetes().toArray(new String[0])));
		} catch (NoHayEntidadesParaListarException e1) {
			JOptionPane.showMessageDialog(this, "No hay paquetes para listar", "Consutar paquete", JOptionPane.ERROR_MESSAGE);
		}
        
        
        GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
        gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxPaquetes.gridwidth = 2;
        gbc_comboBoxPaquetes.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxPaquetes.gridx = 2;
        gbc_comboBoxPaquetes.gridy = 1;
        getContentPane().add(comboBoxPaquetes, gbc_comboBoxPaquetes);
        
        
        
        /* --------------- INFO PAQUETES --------------------- */
        
        
        lblInformacionPaquete = new JLabel("Informacion de paquete:");
        GridBagConstraints gbc_lblInformacionPaquete = new GridBagConstraints();
        gbc_lblInformacionPaquete.insets = new Insets(0, 0, 5, 5);
        gbc_lblInformacionPaquete.gridx = 2;
        gbc_lblInformacionPaquete.gridy = 2;
        getContentPane().add(lblInformacionPaquete, gbc_lblInformacionPaquete);
        
        
        /* --------------- NOMBRE --------------------- */
        
        lblNombre = new JLabel("Nombre: ");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = 3;
        getContentPane().add(lblNombre, gbc_lblNombre);
        
        lblInfoNombre = new JLabel("");
        GridBagConstraints gbc_lblInfoNombre = new GridBagConstraints();
        gbc_lblInfoNombre.gridwidth = 2;
        gbc_lblInfoNombre.anchor = GridBagConstraints.WEST;
        gbc_lblInfoNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblInfoNombre.gridx = 2;
        gbc_lblInfoNombre.gridy = 3;
        getContentPane().add(lblInfoNombre, gbc_lblInfoNombre);
        
        
        /* --------------- DESCRIPCION --------------------- */
        
        
        lblDescripcion = new JLabel("Descripcion: ");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 1;
        gbc_lblDescripcion.gridy = 4;
        getContentPane().add(lblDescripcion, gbc_lblDescripcion);
        
        lblInfoDescripcion = new JTextArea("");
        lblInfoDescripcion.setLineWrap(true);
        lblInfoDescripcion.setTabSize(0);
        lblInfoDescripcion.setRows(3);
        GridBagConstraints gbc_lblInfoDescripcion = new GridBagConstraints();
        gbc_lblInfoDescripcion.fill = GridBagConstraints.BOTH;
        gbc_lblInfoDescripcion.gridwidth = 2;
        gbc_lblInfoDescripcion.gridheight = 2;
        gbc_lblInfoDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblInfoDescripcion.gridx = 2;
        gbc_lblInfoDescripcion.gridy = 4;
        getContentPane().add(lblInfoDescripcion, gbc_lblInfoDescripcion);
        
        
        /* --------------- VALIDEZ EN DIAS --------------------- */
        
        lblValidez = new JLabel("Validez en dias: ");
        GridBagConstraints gbc_lblValidez = new GridBagConstraints();
        gbc_lblValidez.anchor = GridBagConstraints.EAST;
        gbc_lblValidez.insets = new Insets(0, 0, 5, 5);
        gbc_lblValidez.gridx = 1;
        gbc_lblValidez.gridy = 6;
        getContentPane().add(lblValidez, gbc_lblValidez);
        
        lblInfoValidez = new JLabel("");
        GridBagConstraints gbc_lblInfoValidez = new GridBagConstraints();
        gbc_lblInfoValidez.gridwidth = 2;
        gbc_lblInfoValidez.anchor = GridBagConstraints.WEST;
        gbc_lblInfoValidez.insets = new Insets(0, 0, 5, 5);
        gbc_lblInfoValidez.gridx = 2;
        gbc_lblInfoValidez.gridy = 6;
        getContentPane().add(lblInfoValidez, gbc_lblInfoValidez);
        
        
        /* --------------- DESCUENTO --------------------- */
        
        
        
        lblDescuento = new JLabel("Descuento: ");
        GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
        gbc_lblDescuento.anchor = GridBagConstraints.EAST;
        gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescuento.gridx = 1;
        gbc_lblDescuento.gridy = 7;
        getContentPane().add(lblDescuento, gbc_lblDescuento);
        
        lblInfoDescuento = new JLabel("");
        GridBagConstraints gbc_lblInfoDescuento = new GridBagConstraints();
        gbc_lblInfoDescuento.gridwidth = 2;
        gbc_lblInfoDescuento.anchor = GridBagConstraints.WEST;
        gbc_lblInfoDescuento.insets = new Insets(0, 0, 5, 5);
        gbc_lblInfoDescuento.gridx = 2;
        gbc_lblInfoDescuento.gridy = 7;
        getContentPane().add(lblInfoDescuento, gbc_lblInfoDescuento);

        
        
        /* --------------- FECHA DE ALTA --------------------- */
        
        
        lblFechaAlta = new JLabel("Fecha de alta: ");
        GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
        gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
        gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaAlta.gridx = 1;
        gbc_lblFechaAlta.gridy = 8;
        getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);
        
        lblInfoFechaAlta = new JLabel("");
        GridBagConstraints gbc_lblInfoFechaAlta = new GridBagConstraints();
        gbc_lblInfoFechaAlta.gridwidth = 2;
        gbc_lblInfoFechaAlta.anchor = GridBagConstraints.WEST;
        gbc_lblInfoFechaAlta.insets = new Insets(0, 0, 5, 5);
        gbc_lblInfoFechaAlta.gridx = 2;
        gbc_lblInfoFechaAlta.gridy = 8;
        getContentPane().add(lblInfoFechaAlta, gbc_lblInfoFechaAlta);
        
        
        /* --------------- ACTIVIDADES --------------------- */
        
        
        /* --------------- BOTON --------------------- */
        
        lblCategorias = new JLabel("Categorias: ");
        GridBagConstraints gbc_lblCategorias = new GridBagConstraints();
        gbc_lblCategorias.anchor = GridBagConstraints.EAST;
        gbc_lblCategorias.insets = new Insets(0, 0, 5, 5);
        gbc_lblCategorias.gridx = 1;
        gbc_lblCategorias.gridy = 9;
        getContentPane().add(lblCategorias, gbc_lblCategorias);
        
        lblInfoCategorias = new JLabel("");
        GridBagConstraints gbc_lblInfoCategorias = new GridBagConstraints();
        gbc_lblInfoCategorias.anchor = GridBagConstraints.WEST;
        gbc_lblInfoCategorias.gridwidth = 2;
        gbc_lblInfoCategorias.insets = new Insets(0, 0, 5, 5);
        gbc_lblInfoCategorias.gridx = 2;
        gbc_lblInfoCategorias.gridy = 9;
        getContentPane().add(lblInfoCategorias, gbc_lblInfoCategorias);
        
        lblActividades = new JLabel("Actividades asociadas:");
        GridBagConstraints gbc_lblActividades = new GridBagConstraints();
        gbc_lblActividades.anchor = GridBagConstraints.EAST;
        gbc_lblActividades.insets = new Insets(0, 0, 5, 5);
        gbc_lblActividades.gridx = 1;
        gbc_lblActividades.gridy = 10;
        getContentPane().add(lblActividades, gbc_lblActividades);
        
        comboBoxActividades = new JComboBox();
        comboBoxActividades.setEnabled(false);
        GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
        gbc_comboBoxActividades.gridwidth = 2;
        gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxActividades.gridx = 2;
        gbc_comboBoxActividades.gridy = 10;
        getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);
        comboBoxActividades.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String actividad = comboBoxActividades.getSelectedItem().toString();
        		presentacion.Principal.crearConsultaActividadConActividadSeleccionadaInternalFrame(actividad);
        	}
        });
        
        btnCerrar = new JButton("Cerrar");
        GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
        gbc_btnCerrar.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
        gbc_btnCerrar.gridx = 3;
        gbc_btnCerrar.gridy = 11;
        getContentPane().add(btnCerrar, gbc_btnCerrar);
        
        
        
        
	}
	
	void completarDatosPaquete(IControladorPaqueteActividades controladorPaquete) {
		String nombrePaquete = comboBoxPaquetes.getSelectedItem().toString();
		try {
			DtPaqueteActividades paquete= controladorPaquete.find(nombrePaquete);
			
			lblInfoNombre.setText(paquete.getNombre());
			lblInfoDescripcion.setText(paquete.getDescripcion());
			lblInfoValidez.setText(String.valueOf(paquete.getValidezEnDias()));
			int descuento = (int) ((paquete.getDescuento())*100);
			
			lblInfoDescuento.setText(String.valueOf(descuento));
			lblInfoFechaAlta.setText(paquete.getFechaAlta());
			
			comboBoxActividades.setEnabled(true);
			comboBoxActividades.setModel(new DefaultComboBoxModel<String>((String[]) paquete.getNombreActividades().toArray(new String[0])));
	
			String cat = "";
			for(String categoria: paquete.getCategorias()) {
				cat = cat + "[" + categoria + "]" + " ";
			}
			lblInfoCategorias.setText(cat);
			
		} catch (EntidadNoExisteException | CampoInvalidoException e) {
			JOptionPane.showMessageDialog(this, e.toString() , "Consutar paquete", JOptionPane.ERROR_MESSAGE);
		}
				
		
	}
	
	
	
	
	
	
	
	
	
}