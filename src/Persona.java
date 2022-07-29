import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class Persona extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txtClave;
	private JTextField txtNombre;
	private JTextField txtDomicilio;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtFechaNacimiento;
	private JTextField txtID;
	private JComboBox cbxGenero;
	private JButton btnGuardar;

	public static final String controlador = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/escuela";
	public static final String usuario = "root";
	public static final String clave = "Tripa317";
	
	PreparedStatement ps;
	ResultSet rs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Persona frame = new Persona();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static Connection getConection()
	{
		Connection con = null;
		
		try{
			Class.forName(controlador);
			con = (Connection)DriverManager.getConnection(url,usuario,clave);
			JOptionPane.showMessageDialog(null,"Conexion Exitosa");
		}catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	private void limpiarCajas() {
		
		txtClave.setText(null);
		txtNombre.setText(null);
		txtDomicilio.setText(null);
		txtTelefono.setText(null);
		txtEmail.setText(null);
		txtFechaNacimiento.setText(null);
		cbxGenero.setSelectedIndex(0);
	}
	
	public Persona() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Clave:");
		
		lblNewLabel_1 = new JLabel("Nombre:");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Domicilio");
		
		lblNewLabel_3 = new JLabel("Telefono:");
		
		lblNewLabel_4 = new JLabel("Email:");
		
		lblNewLabel_5 = new JLabel("Fecha de nacimiento:");
		
		lblNewLabel_6 = new JLabel("Genero:");
		
		txtDomicilio = new JTextField();
		txtDomicilio.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setColumns(10);
		
		cbxGenero = new JComboBox();
		cbxGenero.setModel(new DefaultComboBoxModel(new String[] {"Selecciona", "Hombre", "Mujer"}));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					Connection con = null;
					con = getConection();
					
					ps = con.prepareStatement("insert into persona (clave,nombre,domicilio,telefono,email,fecha_nacimiento,genero)values(?,?,?,?,?,?,?)");
					ps.setString(1,txtClave.getText());
					ps.setString(2,txtNombre.getText());
					ps.setString(3,txtDomicilio.getText());
					ps.setString(4,txtTelefono.getText());
					ps.setString(5,txtEmail.getText());
					ps.setDate(6,Date.valueOf(txtFechaNacimiento.getText()));
					ps.setString(7,cbxGenero.getSelectedItem().toString());
					
					int res = ps.executeUpdate();

					if(res > 0){
						JOptionPane.showMessageDialog(null,"Persona Guardada");
						limpiarCajas();
					}else {
						JOptionPane.showMessageDialog(null,"Error al guardar persona");
						limpiarCajas();
					}
					
										
					con.close();
				}catch(Exception e1){
					System.out.println(e1);
				}
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		
		JButton btnEliminar = new JButton("Eliminar");
		
		JButton btnLimpiar = new JButton("Limpiar");
		
		JButton btnBuscar = new JButton("Buscar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(57)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
							.addGap(51)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtClave, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtID, 0, 0, Short.MAX_VALUE))
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDomicilio)
								.addComponent(cbxGenero, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtFechaNacimiento)
								.addComponent(txtEmail)
								.addComponent(txtTelefono, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(btnBuscar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(btnGuardar)
							.addGap(18)
							.addComponent(btnModificar)
							.addGap(26)
							.addComponent(btnEliminar)
							.addGap(18)
							.addComponent(btnLimpiar)))
					.addGap(43))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBuscar)
								.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDomicilio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFechaNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbxGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLimpiar)
								.addComponent(btnGuardar)
								.addComponent(btnEliminar)
								.addComponent(btnModificar)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
