package presentacion.categoria;

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

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class AltaCategoria extends JInternalFrame {

    private IControladorActividadTuristica controladorAct;

    private JButton btnAgregar;
    private JButton btnCerrar;
    private JLabel lblNombre;
    private JTextField textFieldNombre;

    public AltaCategoria(IControladorActividadTuristica controladorActividad) {
        this.controladorAct = controladorActividad;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de categoria");
        setBounds(10, 40, 473, 136);
        getContentPane().setLayout(null);


        lblNombre = new JLabel("Nombre de la categoria:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNombre.setBounds(10, 29, 158, 20);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(173, 28, 276, 25);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent exception) {
                intentarRegistrarCategoria(exception);
            }
        });
        btnAgregar.setBounds(239, 64, 100, 30);
        getContentPane().add(btnAgregar);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                limpiarFormulario();
                doDefaultCloseAction();
            }
        });
        btnCerrar.setBounds(349, 65, 100, 30);
        getContentPane().add(btnCerrar);
    }

    protected void intentarRegistrarCategoria(ActionEvent arg0) {

        String nombreCat = this.textFieldNombre.getText();

        try {
            controladorAct.darDeAltaCategoria(nombreCat);
            mostrarCajitaExito("La categoria se ha registrado con exito");
            limpiarFormulario();
            setVisible(false);
        } catch (EntidadRepetidaException | CampoInvalidoException e) {
            mostrarCajitaError(e);
        }
    }

    private void mostrarCajitaError(String str) {
        JOptionPane.showMessageDialog(this, str, "Alta de categoria", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarCajitaError(Exception exception) {
        mostrarCajitaError(exception.getMessage());
    }

    private void mostrarCajitaExito(String str) {
        JOptionPane.showMessageDialog(this, str, "Alta de categoria", JOptionPane.INFORMATION_MESSAGE);
    }

    private void limpiarFormulario() {
        textFieldNombre.setText("");
    }

}
