package V;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.RateDB;
import M.RateManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelCustomer extends JPanel
{
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_surname;
	private JTextField textField_phone;
	private JTextField textField_email;
	private JTable table;
	private JButton btndelete;
	private JButton btnedit;
	private JButton btnsave;
	private JScrollPane scrollPane;
	ArrayList<CustomerDB> list;

	/**
	 * Create the panel.
	 */
	public PanelCustomer()
	{
		setLayout(null);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblCustomer.setBounds(322, 11, 105, 26);
		add(lblCustomer);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblId.setBounds(32, 71, 79, 26);
		add(lblId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblName.setBounds(187, 77, 59, 14);
		add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSurname.setBounds(442, 71, 79, 20);
		add(lblSurname);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPhone.setBounds(32, 118, 59, 34);
		add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEmail.setBounds(288, 123, 79, 24);
		add(lblEmail);
		
		textField_id = new JTextField();
		textField_id.setBounds(67, 71, 86, 26);
		add(textField_id);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setEditable(false);
		
		textField_name = new JTextField();
		textField_name.setBounds(256, 71, 171, 26);
		add(textField_name);
		textField_name.setColumns(10);
		
		textField_surname = new JTextField();
		textField_surname.setBounds(531, 71, 232, 26);
		add(textField_surname);
		textField_surname.setColumns(10);
		
		textField_phone = new JTextField();
		textField_phone.setBounds(101, 122, 177, 26);
		add(textField_phone);
		textField_phone.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setBounds(363, 122, 244, 26);
		add(textField_email);
		textField_email.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 174, 788, 247);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id 			= 	Integer.parseInt(table.getValueAt(index, 0).toString());
				String name 	= 	table.getValueAt(index, 1).toString();
				String surname  = 	table.getValueAt(index, 2).toString();
				String phone 	= 	table.getValueAt(index, 3).toString();
				String email 	= 	table.getValueAt(index, 4).toString();
				
				textField_id.setText("" + id);
				textField_name.setText("" + name);
				textField_surname.setText("" + surname);
				textField_phone.setText("" + phone);
				textField_email.setText("" + email);
			}
		});
		scrollPane.setViewportView(table);
		load();
		
		btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDB x = new CustomerDB(0, textField_name.getText().trim(),
						textField_surname.getText().trim(),
						textField_phone.getText().trim(),
						textField_email.getText());
						
				CustomerManager.saveNewCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
				textField_email.setText("");
				
				JOptionPane.showMessageDialog(PanelCustomer.this, "finish!!");
			}
		});
		btnsave.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnsave.setBounds(141, 432, 89, 23);
		add(btnsave);
		
		btnedit = new JButton("EDIT");
		btnedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDB x = new CustomerDB();
				x.id = Integer.parseInt(textField_id.getText());
				x.name = textField_name.getText();
				x.surname = textField_surname.getText();
				x.phone = textField_phone.getText();
				x.email = textField_email.getText();

				CustomerManager.editCustomer(x);
				JOptionPane.showMessageDialog(PanelCustomer.this, "UpDate Finish!!");

				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
				textField_email.setText("");
				
				JOptionPane.showMessageDialog(PanelCustomer.this, "finish!!");
			}
		});
		btnedit.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnedit.setBounds(369, 432, 89, 23);
		add(btnedit);
		
		btndelete = new JButton("DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
						PanelCustomer.this,"Do you want to delete ?",
						"DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
				CustomerDB x = new CustomerDB(
						Integer.parseInt(textField_id.getText()), 
						textField_name.getText().trim(),
						textField_surname.getText().trim(),
						textField_phone.getText().trim(),
						textField_email.getText().trim());
				CustomerManager.deleteCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
				textField_email.setText("");
				
				JOptionPane.showMessageDialog(PanelCustomer.this, "finish!!");
				}
			}
		});
		btndelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btndelete.setBounds(585, 432, 89, 23);
		add(btndelete);

	}
	public void load()
	{
		list = CustomerManager.getAllCustomer();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("surname");
		model.addColumn("phone");
		model.addColumn("email");

		for (CustomerDB r : list)
		{
			model.addRow(new Object[]
			{ r.id, r.name, r.surname, r.phone, r.email });
		}
		
		table.setModel(model);
	}
}
