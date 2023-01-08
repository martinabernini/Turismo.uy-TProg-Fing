package presentacion.actividadturistica;

import javax.swing.JInternalFrame;

import excepciones.CampoInvalidoException;
import excepciones.EntidadNoExisteException;
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
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Button;

public class AceptarRechazarAct extends JInternalFrame {

	private IControladorActividadTuristica controladorAct;
	private IValidador validador;
	
	private String[] listaActividadesAgregadas;
	
	private JLabel lblActividadesAgregadas;
	
	public AceptarRechazarAct(IControladorActividadTuristica controladorActividad, IValidador validador) {

		
		this.controladorAct = controladorActividad;
		this.validador = validador;
		
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setClosable(true);
		setTitle("Aceptar/Rechazar actividad turistica");
		setBounds(10, 40, 547, 173);
					
		lblActividadesAgregadas = new JLabel("Actividades agregadas:");
		lblActividadesAgregadas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblActividadesAgregadas.setBounds(25, 24, 150, 25);
		getContentPane().add(lblActividadesAgregadas);
					
		JComboBox<String> comboBoxActividades = new JComboBox<String>();
		comboBoxActividades.setEnabled(true);
		comboBoxActividades.setBounds(182, 24, 339, 25);
		getContentPane().add(comboBoxActividades);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("Aceptar actividad");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String act =comboBoxActividades.getSelectedItem().toString();
				try {
					controladorActividad.rechazarAceptarActividad(act, true);
					mostrarCajitaExito("La actividad turistica fue aceptada");
					doDefaultCloseAction();
				} catch (EntidadNoExisteException | CampoInvalidoException e1) {
					// TODO Auto-generated catch block
					mostrarCajitaError(e1);
				}
			}
		});
		
		
		try {
				
				
				listaActividadesAgregadas = (String[]) controladorActividad.listarActividadesEnEstadoAgregada().toArray(new String[0]);
				comboBoxActividades.setModel(new DefaultComboBoxModel<String>(listaActividadesAgregadas));
				

				button.setFont(new Font("Arial", Font.PLAIN, 14));
				button.setBounds(75, 60, 179, 30);
				getContentPane().add(button);
				
				JButton button_1 = new JButton("Rechazar actividad");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String act =comboBoxActividades.getSelectedItem().toString();
						try {
							controladorActividad.rechazarAceptarActividad(act, false);
							mostrarCajitaExito("La actividad turistica fue rechazada");
							doDefaultCloseAction();
							
						} catch (EntidadNoExisteException | CampoInvalidoException e1) {
							// TODO Auto-generated catch block
							mostrarCajitaError(e1);
						}
					}
				});
				button_1.setFont(new Font("Arial", Font.PLAIN, 14));
				button_1.setBounds(275, 60, 193, 30);
				getContentPane().add(button_1);
				
				JButton button_2 = new JButton("Cerrar");
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						doDefaultCloseAction();
					}
				});
				
				button_2.setFont(new Font("Arial", Font.PLAIN, 14));
				button_2.setBounds(421, 102, 100, 30);
				getContentPane().add(button_2);
			
	
			
		} catch (NoHayEntidadesParaListarException e1) {
			// TODO Auto-generated catch block
				
		}
		
		

		

	}
	
	private void mostrarCajitaError(Exception e) {
		JOptionPane.showMessageDialog(this, e.getMessage(), "Aceptar/Rechazar Actividad", JOptionPane.ERROR_MESSAGE);
	}
	
	private void mostrarCajitaError(String str) {
		JOptionPane.showMessageDialog(this, str, "Aceptar/Rechazar Actividad", JOptionPane.ERROR_MESSAGE);
	}
	
	private void mostrarCajitaExito(String str) {
		JOptionPane.showMessageDialog(this, str, "Aceptar/Rechazar Actividad", JOptionPane.INFORMATION_MESSAGE);
	}
}