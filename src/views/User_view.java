package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import models.Account;
import models.Account_model;
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
		
		
		JLabel welcome = new JLabel("BIENVENIDO");
		welcome.setFont(new Font("Calibri", Font.BOLD, 18));
		welcome.setSize(400, 120);
		welcome.setLocation(350, 100);
	    dashboard.add(welcome);
	    
	    JLabel lbl_nombre = new JLabel(user.getFirstName() + user.getFirstName());
	    lbl_nombre.setFont(new Font("Calibri", Font.BOLD, 18));
	    lbl_nombre.setSize(400, 100);
	    lbl_nombre.setLocation(350, 140);
	    dashboard.add(lbl_nombre);
	    
	    JLabel lbl_email = new JLabel(user.getEmail());
	    lbl_email.setFont(new Font("Calibri", Font.BOLD, 18));
	    lbl_email.setSize(400, 120);
	    lbl_email.setLocation(350, 160);
	    dashboard.add(lbl_email);

		JButton Btn_new_account = new JButton("Crear Cuenta");
		Btn_new_account.setSize(200, 30);
		Btn_new_account.setLocation(50, 150);
		Btn_new_account.setFont(new Font("Calibri", Font.BOLD, 15));
		Btn_new_account.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				User_view new_account = new User_view();
				
				new_account.new_account(user);
				
				dispose();
				
			}
		});
		dashboard.add(Btn_new_account);

		JButton Btn_transfer = new JButton("Realizar Transferencia");
		Btn_transfer.setSize(200, 30);
		Btn_transfer.setLocation(50, 200);
		Btn_transfer.setFont(new Font("Calibri", Font.BOLD, 15));
		Btn_transfer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Transfer_view transfer = new Transfer_view();
				transfer.transfer(accounts);
				dispose();

			}
		});
		dashboard.add(Btn_transfer);

		JButton Btn_accounts = new JButton("Ver Mis Cuentas");
		Btn_accounts.setSize(200, 30);
		Btn_accounts.setLocation(50, 250);
		Btn_accounts.setFont(new Font("Calibri", Font.BOLD, 15));
		Btn_accounts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				User_view cuentas = new User_view();
				
				cuentas.accounts(user, accounts);
		        
		        dispose();
		        
			}
		});
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
	
	public void accounts(User user, List<Account>account_list) {
		
		setTitle("CENTRO DE CUENTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setLocationRelativeTo(null);
		setResizable(true);

		JPanel accounts = new JPanel();
		accounts.setBackground(Color.decode("#c8eafa"));
		accounts.setLayout(null);

		JLabel register = new JLabel("MIS CUENTAS");
		register.setSize(500, 40);
		register.setLocation(140, 40);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setFont(new Font("Calibri", Font.BOLD, 30));
		accounts.add(register);
		
		
		JLabel welcome = new JLabel("BIENVENIDO");
		welcome.setFont(new Font("Calibri", Font.BOLD, 18));
		welcome.setSize(400, 120);
		welcome.setLocation(350, 70);
		accounts.add(welcome);
	    
		
		
		// SE CREO LA TABLA CON LAS CUENTAS PERO ESTA EN BLANCO YA QUE NO HAY CUENTAS, TAMBIEN SE CREO PARA QUE NO SEA EDITABLE 
		//YA QUE SE TOMO DE LA RENTA DE PELICULAS
		
	    String[] columnNames = { "ID", "account_number", "account_type", "balance", "user_id", "created_at" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Hacer que la tabla no sea editable
			}
		};

		models.Account_model accoModel = new Account_model();
		List<Account> account_list1 = accoModel.getUserAccounts(user.getId());
		// Llenar la tabla con datos
		for (Account account : account_list1) {
			Object[] rowData = { account.getId(), account.getAccountNumber(), account.getAccountType(),
					account.getBalance(), account.getCreatedAt()};
			model.addRow(rowData);
		}

		// se crea la tabla
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(700,300));
		table.setFillsViewportHeight(true);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);

		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setRowHeight(25);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
		table.setAutoCreateRowSorter(true); // ordenar por columnas

		
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setSize(700,300);
		scrollpane.setLocation(50, 160);
		accounts.add(scrollpane);
		
		
		JButton volver = new JButton("Volver");
		volver.setSize(80, 30);
		volver.setLocation(10, 10);
		volver.setHorizontalTextPosition(JButton.CENTER);
		volver.setFont(new Font("Calibri", Font.BOLD, 15));
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				User_view user_view = new User_view();
				
				user_view.dashboard(user, account_list);

				dispose();

			}
		});
		accounts.add(volver);
		
		this.setContentPane(accounts); // o simplemente add(dashboard);
	    setVisible(true);
		
	}
	
	
	public void new_account(User user) {
		
		setTitle("CREAR CUENTAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 700);
		setLocationRelativeTo(null);
		setResizable(true);

		JPanel accounts = new JPanel();
		accounts.setBackground(Color.decode("#c8eafa"));
		accounts.setLayout(null);
		
		JLabel txt_number;

		JLabel register = new JLabel("CREAR UNA NUEVA CUENTA");
		register.setSize(500, 40);
		register.setLocation(140, 40);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setFont(new Font("Calibri", Font.BOLD, 30));
		accounts.add(register);
		
		JLabel id = new JLabel("ID");
		id.setSize(200,40);
		id.setLocation(200,100);
		id.setFont(new Font("Calibri", Font.BOLD, 15));
		accounts.add(id);
		
		JLabel txt_id = new JLabel(String.valueOf(user.getId()));
		txt_id.setSize(200, 20);
		txt_id.setLocation(350, 110);
//		txt_id.setEditable(false);
	    accounts.add(txt_id);
		
		JLabel type = new JLabel("Tipo de cuenta:");
		type.setSize(200, 40);
		type.setLocation(200, 150);
		type.setFont(new Font("Calibri", Font.BOLD, 15));
		accounts.add(type);

		String[] account_type = {"Normal", "Premium"};
		JComboBox<String> typecombobox = new JComboBox<>(account_type);
		typecombobox.setSize(200, 20);
		typecombobox.setLocation(350, 150);
		accounts.add(typecombobox);
		
		JLabel number = new JLabel("Numero de cuenta");
		number.setSize(200,20);
		number.setLocation(200, 200);
		number.setFont(new Font("Calibri", Font.BOLD, 15));
		accounts.add(number);
		
		txt_number = new JLabel();
		txt_number.setSize(200, 20);
		txt_number.setLocation(350, 200);
//		txt_number.setEditable(false);
	    accounts.add(txt_number);
	    
	    Random rand = new Random();
	    int numeroCuenta = 10000000 + rand.nextInt(90000000);
	    txt_number.setText(String.valueOf(numeroCuenta));
	    
//	    JButton btn_trigger = new JButton("Generar número");
//	    btn_trigger.setSize(200, 30);
//	    btn_trigger.setLocation(350, 250);
//	    btn_trigger.setFont(new Font("Calibri", Font.BOLD, 15));
//	    btn_trigger.addActionListener(new ActionListener() {
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	            Random rand = new Random();
//	            int numeroCuenta = 10000000 + rand.nextInt(99999999);
//	            txt_number.setText(String.valueOf(numeroCuenta));
//	        }
//	    });
//		accounts.add(btn_trigger);
		
		JButton create = new JButton("CREAR CUENTA");
		create.setSize(150,30);
		create.setLocation(300, 560);
		create.setFont(new Font("Calibri", Font.BOLD, 15));
		create.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String tipo = (String) typecombobox.getSelectedItem();
	            String numero = txt_number.getText();

	            if (numero.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Debes de generar un numero de cuenta");
	                return;
	            }

	            JOptionPane.showMessageDialog(null,"Cuenta creada correctamente");
	        }
	    });
		accounts.add(create);
		
		
		JButton volver = new JButton("Volver");
		volver.setSize(80, 30);
		volver.setLocation(10, 10);
		volver.setHorizontalTextPosition(JButton.CENTER);
		volver.setFont(new Font("Calibri", Font.BOLD, 15));
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				User_view user_view = new User_view();
				
				user_view.dashboard(user, null);

				dispose();

			}
		});
		accounts.add(volver);

	    this.setContentPane(accounts); // o simplemente add(dashboard);
	    setVisible(true);
		
	}

}
