package V;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import M.CustomerDB;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInvoice extends JPanel
{
	private JTable table;
	CustomerDB xCustomerDB;

	/**
	 * Create the panel.
	 */
	public PanelInvoice()
	{
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 59, 785, 407);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("ใบเสร็จรับเงิน");
		label.setBounds(347, 11, 82, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("โรงแรม เดอะบีช เลขที่ 11/2 ถนนศรีสุนทร ตำบลป่าตอง อำเภอกะทู้ จังหวัดภูเก็ต 83150 โทร. 076-234-578 email theBeach@gmail.com");
		label_1.setBounds(10, 49, 664, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("ได้รับเงินจาก");
		label_2.setBounds(10, 86, 66, 14);
		panel.add(label_2);
		
		JLabel lable_customer_info = new JLabel("New label");
		lable_customer_info.setBounds(102, 86, 108, 14);
		panel.add(lable_customer_info);
		
		JLabel label_3 = new JLabel("รายละเอียด");
		label_3.setBounds(10, 123, 82, 14);
		panel.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 752, 219);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSelect_Custormer = new JButton("Select Custormer");
		btnSelect_Custormer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchCustomer cc = new SearchCustomer();
				cc.setSelectCustomerI(new SelectCustomerI()
				{
					@Override
					public void select(CustomerDB x)
					{
						xCustomerDB = x;
						String s = x.name + " " + x.surname +" ( " + x.phone +") (id " + x.id +" )( " + x.email +") ";
						lable_customer_info.setText(s);
					}
				});
				cc.setVisible(true);
			}
		});
		/**btnSelect_Custormer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchCustomer cc = new SearchCustomer();
				cc.setSelectCustomerI(new SelectCustomerI()
				{
					
					@Override
					public void select(CustomerDB x)
					{
						xCustomerDB = x;
						String s = x.name + " " + x.surname +" ( " + x.phone +") (id " + x.id +" ) ";
						lable_customer_info.setText(s);
					}
				});
				cc.setVisible(true);
			}
		});**/
		btnSelect_Custormer.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnSelect_Custormer.setBounds(40, 11, 165, 37);
		add(btnSelect_Custormer);
		
		JButton btnSelect_Booking = new JButton("Select Booking");
		btnSelect_Booking.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnSelect_Booking.setBounds(253, 11, 156, 37);
		add(btnSelect_Booking);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnSave.setBounds(472, 11, 89, 37);
		add(btnSave);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnPrint.setBounds(588, 11, 89, 37);
		add(btnPrint);

	}
}
