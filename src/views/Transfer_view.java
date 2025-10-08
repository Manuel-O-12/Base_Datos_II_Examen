package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import models.Account;
import models.Account_model;
import models.Transaction_model;
import models.User;



public class Transfer_view  extends JFrame{
	
	private User currentUser;
	private List<Account> currentAccounts;

	public void setUserData(User user, List<Account> accounts) {
	    this.currentUser = user;
	    this.currentAccounts = accounts;
	    transfer(accounts);
	}

	public Transfer_view() {
			
			setVisible(true);
			setSize(750, 600);
			setLocationRelativeTo(null);
		
			setTitle("TRANSFER");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setMaximumSize(new Dimension(700, 700));
			setMinimumSize(new Dimension(400, 400));
		
		
			this.transfer(null);
			//this.add(this.registro());
			// this.add(this.registro());
			this.repaint();
		
		}
	
	public void transfer( List<Account> accounts) {
		
		JPanel transfer = new JPanel();

		transfer.setBackground(Color.decode("#c8eafa"));
		transfer.setOpaque(true);
		transfer.setSize(500, 800);
		transfer.setLocation(1000 / 2, 0);
		transfer.setLayout(null);

		JLabel register = new JLabel("TRANSFERENCIAS");
		register.setSize(500, 40);
		register.setLocation(140, 40);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setFont(new Font("Calibri", Font.BOLD, 30));
		transfer.add(register);
		
		JLabel origin = new JLabel("Cuenta de origen: ");
		origin.setSize(200, 20);
		origin.setLocation(100, 100);
		origin.setFont(new Font("Calibri", Font.BOLD, 15));
		transfer.add(origin);
		
		JTextField txt_origin = new JTextField();///////////////////////// 
		txt_origin.setSize(200,20);
		txt_origin.setLocation(300, 100);
		txt_origin.setFont(new Font("Calibri", Font.BOLD, 15));
		txt_origin.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        // Permitir teclas de control (backspace, delete, etc.)
		        if (Character.isISOControl(c)) {
		            return;
		        }
		        // Solo permitir dígitos y hasta 8 caracteres
		        if (!Character.isDigit(c) || txt_origin.getText().length() >= 8) {
		            e.consume();
		        }
		    }
		});
		transfer.add(txt_origin);
		
		JLabel destination = new JLabel("Cuenta del destino: ");
		destination.setSize(200, 40);
		destination.setLocation(100, 150);
		destination.setFont(new Font("Calibri", Font.BOLD, 15));
		transfer.add(destination);
		
		JTextField txt_destination = new JTextField();
		txt_destination.setSize(200, 20);
		txt_destination.setLocation(300, 150);
		txt_destination.setFont(new Font("Calibri", Font.BOLD, 15));
		txt_destination.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (Character.isISOControl(c)) {
		            return;
		        }
		        if (!Character.isDigit(c) || txt_destination.getText().length() >= 8) {
		            e.consume();
		        }
		    }
		});
		transfer.add(txt_destination);
		
		JLabel amount = new JLabel("Monto:");
		amount.setSize(200, 20);
        amount.setLocation(100, 200);
        amount.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(amount);
        
        JTextField txt_amount = new JTextField();
        txt_amount.setSize(100, 20);
        txt_amount.setLocation(270, 200);
        txt_amount.setFont(new Font("Calibri", Font.BOLD, 15));
        // Validación para solo números (incluyendo decimales)
        txt_amount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = txt_amount.getText();
                
                // Permitir solo dígitos y un punto decimal
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                    return;
                }
                
                // Validar que solo haya un punto decimal
                if (c == '.' && text.contains(".")) {
                    e.consume();
                    return;
                }
                
                // Limitar la longitud total
                if (text.length() >= 9) {
                    e.consume();
                }
            }
        });
        transfer.add(txt_amount);
        
        JLabel description = new JLabel("Descripción:");
        description.setSize(200, 25);
        description.setLocation(100, 270);
        description.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(description);
        
        JTextArea area_description= new JTextArea();
        area_description.setSize(200, 200);
        area_description.setLocation(200, 270);
        area_description.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(area_description);

        
        JButton btn_transfer = new JButton("TRANSFERIR");
        btn_transfer.setSize(200, 35);
        btn_transfer.setLocation(250, 520);
        btn_transfer.setFont(new Font("Calibri", Font.BOLD, 15));
        LineBorder normalBorder = new LineBorder(Color.GRAY, 1, true);
        LineBorder errorBorder = new LineBorder(Color.RED, 2, true);
        btn_transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que ningún campo esté vacío
                if (txt_origin.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La cuenta de origen no puede estar vacía");
                    txt_origin.setBorder(errorBorder);
                    txt_origin.requestFocus();
                    return;
                }else {
                    txt_origin.setBorder(normalBorder);
                }
                
                if (txt_origin.getText().trim().length() != 8) {
                    JOptionPane.showMessageDialog(null, "La cuenta de origen debe tener exactamente 8 dígitos");
                    txt_origin.setBorder(errorBorder);
                    txt_origin.requestFocus();
                    return;
                }else {
                    txt_origin.setBorder(normalBorder);
                }
                
                if (txt_destination.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La cuenta de destino no puede estar vacía");
                    txt_destination.setBorder(errorBorder);
                    txt_destination.requestFocus();
                    return;
                }else {
                	txt_destination.setBorder(normalBorder);
                }
                
                if (txt_destination.getText().trim().length() != 8) {
                    JOptionPane.showMessageDialog(null, "La cuenta de destino debe tener exactamente 8 dígitos");
                    txt_destination.setBorder(errorBorder);
                    txt_destination.requestFocus();
                    return;
                }else {
                	txt_destination.setBorder(normalBorder);
                }
                
                if (txt_amount.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El monto no puede estar vacío");
                    txt_amount.setBorder(errorBorder);
                    txt_amount.requestFocus();
                    return;
                }else {
                	txt_amount.setBorder(normalBorder);
                }
                
                if (area_description.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía");
                    area_description.setBorder(errorBorder);
                    area_description.requestFocus();
                    return;
                }else {
                	area_description.setBorder(normalBorder);
                }
                
                
                // Validar que el monto sea un número válido y positivo
                try {
                    double monto = Double.parseDouble(txt_amount.getText().trim());
                    if (monto <= 0) {
                        JOptionPane.showMessageDialog(null, "El monto debe ser mayor a cero");
                        txt_amount.setBorder(errorBorder);
                        txt_amount.requestFocus();
                        return;
                    }else {
                    	txt_amount.setBorder(normalBorder);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El monto debe ser un número válido");
                    txt_amount.setBorder(errorBorder);
                    txt_amount.requestFocus();
                    return;
                }
                
                // Validar que no se transfiera a la misma cuenta
                if (txt_origin.getText().trim().equals(txt_destination.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "No puedes transferir a la misma cuenta");
                    txt_origin.setBorder(errorBorder);
                    return;
                }else {
                    txt_origin.setBorder(normalBorder);
                }
                
                txt_origin.setBorder(normalBorder);
                txt_destination.setBorder(normalBorder);
                txt_amount.setBorder(normalBorder);
                area_description.setBorder(normalBorder);
                
                Double monto_transfer = Double.parseDouble(txt_amount.getText().trim());
                
                Transaction_model tm = new Transaction_model();
                tm.transfer(txt_origin.getText(), txt_destination.getText(), monto_transfer);
                
                // Si pasa todas las validaciones, proceder con la transferencia
                JOptionPane.showMessageDialog(null, "Transferencia realizada correctamente");
                // Aquí iría la lógica real de la transferencia
            }
        });
        transfer.add(btn_transfer);
		
		
		JButton volver = new JButton("VOLVER");
		volver.setSize(80, 30);
		volver.setLocation(10, 10);
		volver.setHorizontalTextPosition(JButton.CENTER);
		volver.setFont(new Font("Calibri", Font.BOLD, 15));
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (currentUser != null) {
		            User_view userView = new User_view();
		            userView.dashboard(currentUser, currentAccounts);
		        } else {
		            JOptionPane.showMessageDialog(null, "Error: No se encontró la información del usuario.");
		        }

				dispose();

			}
		});
		transfer.add(volver);
		
		this.setContentPane(transfer); // o simplemente add(dashboard);
	    setVisible(true);
		
	}
}
