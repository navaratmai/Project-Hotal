package V;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import M.RateDB;
import M.RateManager;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelRate extends JPanel
{
	private JTextField textField_id;
	private JTextField textField_normal_price;
	private JTextField textField_special_day_price;
	private JTable table;
	ArrayList <RateDB> list;
	private JComboBox comboBox_typeroom;
	private JScrollPane scrollPane;
	
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
	public PanelRate()
	{
		
		setLayout(null);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblRate.setBounds(364, 30, 96, 23);
		add(lblRate);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblId.setBounds(32, 72, 46, 14);
		add(lblId);
		
		JLabel lblTyperoom = new JLabel("TypeRoom");
		lblTyperoom.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTyperoom.setBounds(364, 65, 131, 29);
		add(lblTyperoom);
		
		JLabel lblSpecialDayPrice = new JLabel("Special day price");
		lblSpecialDayPrice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSpecialDayPrice.setBounds(32, 129, 159, 29);
		add(lblSpecialDayPrice);
		
		JLabel lblNormalPrice = new JLabel("Normal price");
		lblNormalPrice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNormalPrice.setBounds(364, 129, 121, 29);
		add(lblNormalPrice);
		
		textField_id = new JTextField();
		textField_id.setBounds(77, 65, 86, 20);
		add(textField_id);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setEditable(false);
		
		textField_normal_price = new JTextField();
		textField_normal_price.setBounds(524, 136, 184, 20);
		add(textField_normal_price);
		textField_normal_price.setColumns(10);
		
		textField_special_day_price = new JTextField();
		textField_special_day_price.setBounds(188, 136, 159, 20);
		add(textField_special_day_price);
		textField_special_day_price.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RateDB x = new RateDB(0, comboBox_typeroom.getSelectedItem().toString().trim(),
						Double.parseDouble(textField_special_day_price.getText().trim()),
						Double.parseDouble(textField_normal_price.getText().trim()));
						
				RateManager.saveNewRate(x);
				load();
				textField_id.setText("");
				textField_special_day_price.setText("");
				textField_normal_price.setText("");
				
				
				JOptionPane.showMessageDialog(PanelRate.this, "finish!!");
			}
		});
		btnSave.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnSave.setBounds(156, 189, 89, 23);
		add(btnSave);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RateDB x = new RateDB();
				x.id = Integer.parseInt(textField_id.getText());
				x.type_room = (String) comboBox_typeroom.getSelectedItem();
				x.special_day_price = Double.parseDouble(textField_special_day_price.getText());
				x.normal_price = Double.parseDouble(textField_normal_price.getText());

				RateManager.editRate(x);
				JOptionPane.showMessageDialog(PanelRate.this, "UpDate Finish!!");

				load();
				textField_id.setText("");
				textField_special_day_price.setText("");
				textField_normal_price.setText("");
				
				JOptionPane.showMessageDialog(PanelRate.this, "finish!!");
			}
		});
		btnEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnEdit.setBounds(354, 189, 89, 23);
		add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
						PanelRate.this,"Do you want to delete ?",
						"DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
				RateDB x = new RateDB(
						Integer.parseInt(textField_id.getText()), 
						(String)comboBox_typeroom.getSelectedItem(),
						Double.parseDouble(textField_special_day_price.getText().trim()),
						Double.parseDouble(textField_normal_price.getText().trim()));
				RateManager.deleteRate(x);
				load();
				textField_id.setText("");
				textField_special_day_price.setText("");
				textField_normal_price.setText("");
				
				
				JOptionPane.showMessageDialog(PanelRate.this, "finish!!");
				}
			}
		});
		btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnDelete.setBounds(537, 189, 89, 23);
		add(btnDelete);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 249, 775, 198);
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
				String type_room = 	table.getValueAt(index, 1).toString();
				String special_day_price = 	table.getValueAt(index, 2).toString();
				String normal_price = 	table.getValueAt(index, 3).toString();
				
				textField_id.setText("" + id);
				comboBox_typeroom.setSelectedItem(type_room);
				textField_special_day_price.setText("" + special_day_price);
				textField_normal_price.setText("" + normal_price);
				
			}
		});
		scrollPane.setViewportView(table);
		load();
		
		comboBox_typeroom = new JComboBox();
		comboBox_typeroom.setModel(new DefaultComboBoxModel(new String[] {"Standard", "Superior", "Deluxe", "Suite"}));
		comboBox_typeroom.setBounds(524, 72, 184, 20);
		add(comboBox_typeroom);
		
	}
	public void load()
	{
		list = RateManager.getAllRate();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("type_room");
		model.addColumn("special_day_price");
		model.addColumn("normal_price");

		for (RateDB r : list)
		{
			model.addRow(new Object[]
			{ r.id, r.type_room, r.special_day_price, r.normal_price });
		}
		
		table.setModel(model);
	}
}
