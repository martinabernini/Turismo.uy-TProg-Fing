package presentacion.paqueteactividades;

import javax.swing.JInternalFrame;

import logica.interfaces.IControladorPaqueteActividades;
import logica.interfaces.IValidador;
import logica.validacion.MensajeError;
import logica.datatypes.DtPaqueteActividades;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import excepciones.CampoInvalidoException;
import excepciones.EntidadRepetidaException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CrearPaqueteActividades extends JInternalFrame {
	
	IControladorPaqueteActividades controladorPaqueteActividades;
	private JTextField textFieldNombre;
	private JTextField textFieldValidez;
	private JTextField textFieldDescuento;
	private JTextArea lblInfoDescripcion;
	private JDateChooser dateChooser;
	private IValidador validador;
	
	public CrearPaqueteActividades(IControladorPaqueteActividades controladorPaqueteActividades, IValidador validador) {
		
		this.controladorPaqueteActividades = controladorPaqueteActividades;
		this.validador=validador;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setTitle("Crear paquete de actividades turisticas");
        setBounds(10, 40, 508, 287);
        getContentPane().setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre: ");
        lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNombre.setBounds(10, 22, 123, 14);
        getContentPane().add(lblNombre);
        
        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(150, 19, 332, 20);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblDescripcion = new JLabel("Descripcion: ");
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescripcion.setBounds(10, 47, 123, 14);
        getContentPane().add(lblDescripcion);
        
        lblInfoDescripcion = new JTextArea("");
        lblInfoDescripcion.setTabSize(0);
        lblInfoDescripcion.setRows(3);
        lblInfoDescripcion.setLineWrap(true);
        lblInfoDescripcion.setBounds(150, 47, 332, 55);
        getContentPane().add(lblInfoDescripcion);
        
        JLabel lblValidez = new JLabel("Validez en dias: ");
        lblValidez.setHorizontalAlignment(SwingConstants.RIGHT);
        lblValidez.setBounds(10, 116, 123, 14);
        getContentPane().add(lblValidez);
        
        textFieldValidez = new JTextField();
        textFieldValidez.setBounds(150, 113, 73, 20);
        getContentPane().add(textFieldValidez);
        textFieldValidez.setColumns(10);
        
        JLabel lblDescuento_1 = new JLabel("Descuento(%): ");
        lblDescuento_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDescuento_1.setBounds(10, 141, 123, 14);
        getContentPane().add(lblDescuento_1);
        
        textFieldDescuento = new JTextField();
        textFieldDescuento.setColumns(10);
        textFieldDescuento.setBounds(150, 138, 73, 20);
        getContentPane().add(textFieldDescuento);
        
        JLabel lblFechaAlta = new JLabel("Fecha de alta: ");
        lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFechaAlta.setBounds(10, 166, 123, 14);
        getContentPane().add(lblFechaAlta);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(150, 166, 123, 20);
        getContentPane().add(dateChooser);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormulario();
				doDefaultCloseAction();
        	}
        });
        btnCerrar.setBounds(272, 223, 163, 23);
        getContentPane().add(btnCerrar);
        
        JButton btnCrearPaquete = new JButton("Crear paquete");
        btnCrearPaquete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		intentarCrearPaquete(e);
        	}
        });
        btnCrearPaquete.setBounds(80, 223, 163, 23);
        getContentPane().add(btnCrearPaquete);
	}
	
	protected void intentarCrearPaquete(ActionEvent arg0) {
		DtPaqueteActividades nuevoPaq = new DtPaqueteActividades();
		nuevoPaq.setNombre(this.textFieldNombre.getText());
		nuevoPaq.setDescripcion(this.lblInfoDescripcion.getText());
		
		try {
			nuevoPaq.setValidezEnDias(Integer.parseInt(this.textFieldValidez.getText()));
			nuevoPaq.setDescuento(Float.parseFloat(this.textFieldDescuento.getText()));
		} catch (NumberFormatException e) {
			mostrarCajitaError("La validez y el descuento deben ser numeros");
			return;
		}
		
	
		LocalDate fechaAlta = this.dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		nuevoPaq.setFechaAlta(fechaAlta.toString());
		nuevoPaq.setNombreActividades(null);
		nuevoPaq.setCategorias(null);
		nuevoPaq.setImagen("cos");
		
		if (validador.campoInvalidoAltaPaquete(nuevoPaq)) {
			mostrarCajitaError(MensajeError.campoInvalidoAltaPaquete(nuevoPaq));
			return;
		}
		try {
			controladorPaqueteActividades.darDeAltaPaquete(nuevoPaq);
			mostrarCajitaExito("El paquete de actividades se ha creado con exito");
			limpiarFormulario();
			setVisible(false);
		} catch (EntidadRepetidaException | CampoInvalidoException e) {
			mostrarCajitaError(e);
		}
	}
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");
		lblInfoDescripcion.setText("");
		textFieldValidez.setText("");
		textFieldDescuento.setText("");
		dateChooser.setCalendar(null);
	}
	
	private void mostrarCajitaError(Exception exception) {
		mostrarCajitaError(exception.getMessage());
	}
	
	private void mostrarCajitaError(String str) {
		JOptionPane.showMessageDialog(this, str, "Crear paquete de actividades turìsticas", JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarCajitaExito(String str) {
		JOptionPane.showMessageDialog(this, str, "Crear paquete de actividades turìsticas", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}