package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import controller.User_controller;
import models.Auth_model;
import models.User;

public class Auth_view extends JFrame {

	public Auth_view() {

		setVisible(true);
		setSize(750, 600);
		setLocationRelativeTo(null);

		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(700, 700));
		setMinimumSize(new Dimension(400, 400));


		this.add(this.login());
		this.repaint();

	}

	public JPanel login() {

		JPanel Panel = new JPanel();

		Panel.setBackground(Color.decode("#c8eafa"));
		Panel.setOpaque(true);
		Panel.setSize(800, 800);
		Panel.setLocation(0, 0);
		Panel.setLayout(null);

		JLabel start = new JLabel("INICIAR SESIÓN");
		start.setSize(300, 40);
		start.setLocation(200, 40);
		start.setHorizontalAlignment(JLabel.CENTER);
		start.setFont(new Font("Calibri", Font.BOLD, 30));
		Panel.add(start);

		JLabel account = new JLabel("Numero de Cuenta: ");
		account.setSize(200, 40);
		account.setLocation(250, 100);
		account.setFont(new Font("Calibri", Font.BOLD, 15));
		Panel.add(account);

		JTextField accountText = new JTextField();
		accountText.setSize(200, 20);
		accountText.setLocation(250, 127);
		accountText.setFont(new Font("Calibri", Font.BOLD, 15));
		Panel.add(accountText);

		JLabel password = new JLabel("Contraseña:");
		password.setSize(200, 40);
		password.setLocation(250, 165);
		password.setFont(new Font("Calibri", Font.BOLD, 15));
		Panel.add(password);

		JPasswordField passwordText = new JPasswordField();
		passwordText.setSize(200, 20);
		passwordText.setLocation(250, 193);
		passwordText.setFont(new Font("Calibri", Font.PLAIN, 15));
		Panel.add(passwordText);

		JButton access = new JButton("ACCEDER");
		access.setSize(120, 40);
		access.setLocation(300, 270);
		access.setFont(new Font("Calibri", Font.BOLD, 15));

		access.addActionListener(new ActionListener() {
			//
			@Override
			public void actionPerformed(ActionEvent e) {

				boolean flag1 = true;

		        String email = accountText.getText().trim();
		        String password = new String(passwordText.getPassword());

		        if (email.equals("")) {
		            accountText.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            flag1 = false;
		        } else {
		            accountText.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		        }

		        if (password.equals("")) {
		            passwordText.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            flag1 = false;
		        } else {
		            passwordText.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		        }

		        if (!flag1) {
		            JOptionPane.showMessageDialog(null, "Se deben de rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        Auth_model am = new Auth_model();
		        User user = am.login(email, password);

		        //si, si conside mandara un user que es el bueno
		        if (user != null) {
		            accountText.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		            passwordText.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

		       		User_controller uc = new User_controller();
		            uc.user_dashboard(user.getId());

		            dispose();
		        } else {
	
		            accountText.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		            passwordText.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

		            JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		Panel.add(access);

		JButton register = new JButton("REGISTRO");
		register.setSize(120, 40);
		register.setLocation(300, 320);
		register.setFont(new Font("Calibri", Font.BOLD, 15));
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Register_view register_view = new Register_view();
				
				register_view.register();
				
				dispose();

			}
		});
		Panel.add(register);
	
		//this.repaint();

		return Panel;
	}

}


