package V;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import M.RateDB;
import M.RateManager;
import M.UserDB;
import M.UserManager;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelUser extends JPanel
{
	private JTextField textField_id;
	private JTextField textField_username;
	private JPasswordField passwordField;
	private JTable table;
	private JComboBox comboBox_usertype;
	private JButton btnSave;
	ArrayList<UserDB> list;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					PanelRate panel = new PanelRate();
					panel.setVisible(true);
				} catch (Exception e)//เป็นคลาสแม่ ทำครั้งเดียวใช้ได้หมด
				{
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the panel.
	 */
	public PanelUser()
	{
		setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblUser.setBounds(396, 22, 65, 28);
		add(lblUser);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblId.setBounds(32, 70, 46, 28);
		add(lblId);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUsername.setBounds(212, 70, 78, 28);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPassword.setBounds(32, 121, 78, 28);
		add(lblPassword);
		
		JLabel lblUsertype = new JLabel("UserType");
		lblUsertype.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUsertype.setBounds(374, 121, 87, 28);
		add(lblUsertype);
		
		textField_id = new JTextField();
		textField_id.setBounds(64, 77, 86, 20);
		add(textField_id);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setEditable(false);
		
		textField_username = new JTextField();
		textField_username.setBounds(300, 77, 313, 20);
		add(textField_username);
		textField_username.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 128, 209, 20);
		add(passwordField);
		
		comboBox_usertype = new JComboBox();
		comboBox_usertype.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		comboBox_usertype.setModel(new DefaultComboBoxModel(new String[] {"Admin", "User"}));
		comboBox_usertype.setBounds(460, 122, 138, 27);
		add(comboBox_usertype);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 188, 653, 275);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = 			Integer.parseInt(table.getValueAt(index, 0).toString());
				String username = 	table.getValueAt(index, 1).toString();
				String password = 	table.getValueAt(index, 2).toString();
				String usertype = 	table.getValueAt(index, 3).toString();
				
				textField_id.setText("" + id);
				textField_username.setText("" + username);
				passwordField.setText("" + password);
				comboBox_usertype.setSelectedItem(usertype);
			}
		});
		scrollPane.setViewportView(table);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDB x = new UserDB();
				x.username = textField_username.getText();
				x.password = new String(passwordField.getPassword());
				x.usertype = (String) comboBox_usertype.getSelectedItem();

				UserManager.saveNewUser(x);
				load();
				textField_id.setText("");
				textField_username.setText("");
				passwordField.setText("");
				
				
				JOptionPane.showMessageDialog(PanelUser.this, "finish!!");
			}
		});
		btnSave.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnSave.setBounds(714, 205, 89, 23);
		add(btnSave);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDB x = new UserDB();
				x.id = Integer.parseInt(textField_id.getText());
				x.username = textField_username.getText();
				x.password = new String(passwordField.getPassword());
				x.usertype = (String) comboBox_usertype.getSelectedItem();

				UserManager.editUser(x);
				JOptionPane.showMessageDialog(PanelUser.this, "UpDate Finish!!");

				load();
				textField_id.setText("");
				textField_username.setText("");
				passwordField.setText("");
				
				JOptionPane.showMessageDialog(PanelUser.this, "finish!!");
			}
		});
		btnEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnEdit.setBounds(714, 291, 89, 23);
		add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
						PanelUser.this,"Do you want to delete ?",
						"DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
					UserDB x = new UserDB();
					x.id = Integer.parseInt(textField_id.getText());
					x.username = textField_username.getText();
					x.password = new String(passwordField.getPassword());
					x.usertype = (String) comboBox_usertype.getSelectedItem();
					
				UserManager.deleteUser(x);
				load();
				textField_id.setText("");
				textField_username.setText("");
				passwordField.setText("");
				
				JOptionPane.showMessageDialog(PanelUser.this, "finish!!");
				}
			}
		});
		btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnDelete.setBounds(714, 384, 89, 23);
		add(btnDelete);
		load();

	}
	
	public void load()
	{
		list = UserManager.getAllUser();
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("usertype");

		for (UserDB c : list)
		{
			model.addRow(new Object[]
			{ c.id, c.username, c.password, c.usertype });
		}
		
		table.setModel(model);
	}
}
