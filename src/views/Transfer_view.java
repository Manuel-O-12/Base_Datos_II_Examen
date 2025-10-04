package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Transfer_view  extends JFrame{

	public Transfer_view() {
			
			setVisible(true);
			setSize(750, 600);
			setLocationRelativeTo(null);
		
			setTitle("TRANSFER");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setMaximumSize(new Dimension(700, 700));
			setMinimumSize(new Dimension(400, 400));
		
		
			this.add(this.transfer());
			//this.add(this.registro());
			// this.add(this.registro());
			this.repaint();
		
		}
	
	public JPanel transfer() {
		
		JPanel transfer = new JPanel();

		transfer.setBackground(Color.decode("#c8eafa"));
		transfer.setOpaque(true);
		transfer.setSize(500, 800);
		transfer.setLocation(1000 / 2, 0);
		transfer.setLayout(null);

		JLabel register = new JLabel("TRANFERENCIAS");
		register.setSize(500, 40);
		register.setLocation(140, 40);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setFont(new Font("Calibri", Font.BOLD, 30));
		transfer.add(register);
		
		return transfer;
	}
}
