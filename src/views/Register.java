package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame {
	
	public Register() {
		
		setVisible(true);
		setSize(750, 600);
		setLocationRelativeTo(null);
	
		setTitle("REGISTER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(700, 700));
		setMinimumSize(new Dimension(400, 400));
	
	
		this.add(this.register());
		//this.add(this.registro());
		// this.add(this.registro());
		this.repaint();
	
	}
	
	
	public JPanel register() {

		JPanel registro = new JPanel();

		registro.setBackground(Color.decode("#c8eafa"));
		registro.setOpaque(true);
		registro.setSize(500, 800);
		registro.setLocation(1000 / 2, 0);
		registro.setLayout(null);

		JLabel register = new JLabel("REGISTRO DE USUARIO");
		register.setSize(500, 40);
		register.setLocation(140, 40);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setFont(new Font("Calibri", Font.BOLD, 30));
		registro.add(register);

		JLabel name = new JLabel("Nombre:");
		name.setSize(200, 40);
		name.setLocation(200, 100);
		name.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(name);

		JTextField Txt_name = new JTextField();
		Txt_name.setSize(200, 20);
		Txt_name.setLocation(350, 110);
		Txt_name.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Txt_name);
		
		JLabel Paternal_surname = new JLabel("Apellido Paterno:");
		Paternal_surname.setSize(100, 40);
		Paternal_surname.setLocation(200, 140);
		//title2.setHorizontalAlignment(JLabel.CENTER);
		Paternal_surname.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Paternal_surname);
		
		JTextField Txt_paternal_surname = new JTextField();
		Txt_paternal_surname.setSize(200, 20);
		Txt_paternal_surname.setLocation(350, 147);
		Txt_paternal_surname.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Txt_paternal_surname);
		
		JLabel Mother_surname = new JLabel("Apellido Materno:");
		Mother_surname.setSize(150, 40);
		Mother_surname.setLocation(200, 180);
		//title3.setHorizontalAlignment(JLabel.CENTER);
		Mother_surname.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Mother_surname);
		
		JTextField Txt_mother_surname = new JTextField();
		Txt_mother_surname.setSize(200, 20);
		Txt_mother_surname.setLocation(350, 187);
		Txt_mother_surname.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Txt_mother_surname);
		
		JLabel Password = new JLabel("Contraseña:");
		Password.setSize(100, 40);
		Password.setLocation(50, 220);
		//title4.setHorizontalAlignment(JLabel.CENTER);
		Password.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Password);
		
		JPasswordField Txt_password = new JPasswordField();
		Txt_password.setSize(200, 20);
		Txt_password.setLocation(190, 227);
		Txt_password.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Txt_password);
		
		JLabel Confirm_password  = new JLabel("Confirmar Contraseña:");
		Confirm_password.setSize(100, 40);
		Confirm_password.setLocation(50, 260);
		//title5.setHorizontalAlignment(JLabel.CENTER);
		Confirm_password.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Confirm_password);
		
		JPasswordField Txt_confirm_password = new JPasswordField();
		Txt_confirm_password.setSize(200, 20);
		Txt_confirm_password.setLocation(190, 267);
		Txt_confirm_password.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Txt_confirm_password);
		
		JButton create = new JButton("Crear");
		create.setSize(120, 40);
		create.setLocation(500, 480);
		create.setFont(new Font("Calibri", Font.BOLD, 15));
		create.setBackground(Color.GREEN);
		create.addActionListener(new ActionListener() {
			//
			@Override
			public void actionPerformed(ActionEvent e) {

				boolean flag1 = false;///////////////////////////////////////////////////////
				if (Txt_name.getText().equals("")) {

					Txt_name.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				}

				else {
					Txt_name.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					flag1 = true;

				}
				
				boolean flag2 = false;///////////////////////////////////////////////////////////
				if (Txt_paternal_surname.getText().equals("")) {

					Txt_paternal_surname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				}

				else {
					Txt_paternal_surname.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					flag2 = true;

				}
				
				boolean flag3 = false;////////////////////////////////////////////////////////
				if (Txt_mother_surname.getText().equals("")) {

					Txt_mother_surname.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				}

				else {
					Txt_mother_surname.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					flag3 = true;

				}
				
				
				boolean flag4 = false;////////////////////////////////////////////////////////
				if (Txt_password.getText().equals("")) {

					Txt_password.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				}

				else {
					Txt_password.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					flag4 = true;

				}
				
				
				boolean flag5 = false;///////////////////////////////////////////////////////////
				if (Txt_confirm_password.getText().equals("")) {

					Txt_confirm_password.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				}

				else {
					Txt_confirm_password.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					flag5 = true;

				}
				

				if (flag1 && flag2 && flag3 && flag4 && flag5) {
					JOptionPane.showMessageDialog(null, "Usuario creado");
				}

			}
		});
		registro.add(create);
		
		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setSize(120, 40);
		Cancelar.setLocation(350, 480);
		Cancelar.setFont(new Font("Calibri", Font.BOLD, 15));
		registro.add(Cancelar);
		
		JButton volver = new JButton("Volver");
		volver.setSize(80, 30);
		volver.setLocation(10, 10);
		volver.setHorizontalTextPosition(JButton.CENTER);
		volver.setFont(new Font("Calibri", Font.BOLD, 15));
		volver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Auth_view login = new Auth_view();
				
				login.login();
		        
		        dispose();
				
			}
		});
		registro.add(volver);
		
		return registro;


	}
}
