package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Account;
import models.User;

public class User_view extends JFrame {

	public User_view() {
		
	}

	public void dashboard(User user, List<Account> accounts) {

		setTitle("PANEL DEL USUARIO - ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setLocationRelativeTo(null);
		setResizable(true);

		JPanel dashboard = new JPanel();
		dashboard.setBackground(Color.decode("#c8eafa"));
		dashboard.setLayout(null);

		JLabel register = new JLabel("PANEL DEL USUARIO");
		register.setSize(500, 40);
		register.setLocation(140, 40);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setFont(new Font("Calibri", Font.BOLD, 30));
		dashboard.add(register);

		JButton Btn_new_account = new JButton("Crear Cuenta");
		Btn_new_account.setSize(200, 30);
		Btn_new_account.setLocation(50, 150);
		Btn_new_account.setFont(new Font("Calibri", Font.BOLD, 15));
		dashboard.add(Btn_new_account);

		JButton Btn_transfer = new JButton("Realizar Transferencia");
		Btn_transfer.setSize(200, 30);
		Btn_transfer.setLocation(50, 200);
		Btn_transfer.setFont(new Font("Calibri", Font.BOLD, 15));
		Btn_transfer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Transfer_view trafer = new Transfer_view();
				trafer.transfer();
				dispose();

			}
		});
		dashboard.add(Btn_transfer);

		JButton Btn_accounts = new JButton("Ver Mis Cuentas");
		Btn_accounts.setSize(200, 30);
		Btn_accounts.setLocation(50, 250);
		Btn_accounts.setFont(new Font("Calibri", Font.BOLD, 15));
		dashboard.add(Btn_accounts);

		JButton Btn_logout = new JButton("Cerrar Sesión");
		Btn_logout.setSize(200, 30);
		Btn_logout.setLocation(50, 300);
		Btn_logout.setFont(new Font("Calibri", Font.BOLD, 15));
		dashboard.add(Btn_logout);

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
		dashboard.add(volver);

		// leftPanel.add(btnCerrarSesion);
		
	    this.setContentPane(dashboard); // o simplemente add(dashboard);
	    setVisible(true);

	}

}
