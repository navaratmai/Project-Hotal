package V;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelMenu extends JPanel
{

	/**
	 * Create the panel.
	 */
	public PanelMenu()
	{
		setLayout(new GridLayout(6, 1, 0, 0));
		
		JButton btnRoom = new JButton("Room");
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainJPanel.getInstance().setNewMainPanel(UI_Panel.Room);
			}
		});
		add(btnRoom);
		
		JButton btnBooking = new JButton("Booking");
		btnBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainJPanel.getInstance().setNewMainPanel(UI_Panel.Booking);
			}
		});
		add(btnBooking);
		
		JButton btnNewButton_3 = new JButton("Customer");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJPanel.getInstance().setNewMainPanel(UI_Panel.Customer);
			}
		});
		add(btnNewButton_3);
		
		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainJPanel.getInstance().setNewMainPanel(UI_Panel.Invoice);
			}
		});
		add(btnInvoice);
		
		JButton btnRate = new JButton("Rate");
		btnRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainJPanel.getInstance().setNewMainPanel(UI_Panel.Rate);
			}
		});
		add(btnRate);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJPanel.getInstance().setNewMainPanel(UI_Panel.User);
			}
		});
		add(btnUser);

	}

}
