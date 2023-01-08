package presentacion.paqueteactividades;

import javax.swing.JInternalFrame;

import excepciones.UsuarioNoExisteException;
import logica.interfaces.IControladorActividadTuristica;
import logica.interfaces.IControladorDepartamento;
import logica.interfaces.IControladorPaqueteActividades;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AgregarActTurAPaquete extends JInternalFrame {
	
	IControladorPaqueteActividades controladorPaqueteActividades;
	IControladorDepartamento controladorDepartamento;
	IControladorActividadTuristica controladorActividadTuristica;
	
	public AgregarActTurAPaquete(IControladorPaqueteActividades controladorPaqueteActividades, IControladorDepartamento controladorDepartamento, IControladorActividadTuristica controladorActividadTuristica) {
		
		this.controladorPaqueteActividades = controladorPaqueteActividades;
		this.controladorDepartamento = controladorDepartamento;
		this.controladorActividadTuristica = controladorActividadTuristica;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Agregar actividad turistica a paquete");
        setBounds(10, 40, 508, 268);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Proximamente");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel.setBounds(85, 53, 291, 119);
        getContentPane().add(lblNewLabel);
	}
	
	
}