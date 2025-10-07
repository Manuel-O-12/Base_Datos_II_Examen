package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import models.Account;
import models.Account_model;



public class Transfer_view  extends JFrame{

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
		
		JTextField accounsts = new JTextField();///////////////////////// 
		accounsts.setSize(200,20);
		accounsts.setLocation(300, 100);
		accounsts.setFont(new Font("Calibri", Font.BOLD, 15));
		transfer.add(accounsts);
		
		JLabel destination = new JLabel("Cuenta del destino: ");
		destination.setSize(200, 40);
		destination.setLocation(100, 150);
		destination.setFont(new Font("Calibri", Font.BOLD, 15));
		transfer.add(destination);
		
		JTextField Txt_destination = new JTextField();
		Txt_destination.setSize(200, 20);
		Txt_destination.setLocation(300, 150);
		Txt_destination.setFont(new Font("Calibri", Font.BOLD, 15));
		transfer.add(Txt_destination);
		
		JLabel amount = new JLabel("Monto:");
		amount.setSize(200, 20);
        amount.setLocation(100, 200);
        amount.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(amount);
        
        JTextField Txt_amount = new JTextField();
        Txt_amount.setSize(100, 20);
        Txt_amount.setLocation(270, 200);
        Txt_amount.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(Txt_amount);
        
        JLabel description = new JLabel("Descripción:");
        description.setSize(200, 25);
        description.setLocation(100, 270);
        description.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(description);
        
        JTextArea Area_description= new JTextArea();
        Area_description.setSize(200, 200);
        Area_description.setLocation(200, 270);
        Area_description.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(Area_description);

        
        JButton Btn_transfer = new JButton("TRANSFERIR");
        Btn_transfer.setSize(200, 35);
        Btn_transfer.setLocation(250, 520);
        Btn_transfer.setFont(new Font("Calibri", Font.BOLD, 15));
        transfer.add(Btn_transfer);
		
		
		JButton volver = new JButton("VOLVER");
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
		transfer.add(volver);
		
		this.setContentPane(transfer); // o simplemente add(dashboard);
	    setVisible(true);
		
	}
}
