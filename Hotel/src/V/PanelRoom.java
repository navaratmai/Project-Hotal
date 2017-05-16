package V;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import M.RateDB;
import M.RateManager;
import M.RoomDB;
import M.RoomManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelRoom extends JPanel
{
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_rate_id;
	private JScrollPane scrollPane;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	ArrayList <RoomDB> list;
	private JTextField textField_room_detail;
	
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
	public PanelRoom()
	{
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(114, 51, 538, 195);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index 			= 	table.getSelectedRow();
				int id 				= 	Integer.parseInt(table.getValueAt(index, 0).toString());
				String room_detail 	= 	table.getValueAt(index, 1).toString();
				int rate_id 		= 	Integer.parseInt(table.getValueAt(index, 2).toString());
				
				textField_id.setText("" + id);
				textField_rate_id.setText("" + rate_id);
				textField_room_detail.setText("" + room_detail);
				
			}
		});
		scrollPane.setViewportView(table);
		load();
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblRoom.setBounds(358, 11, 106, 29);
		add(lblRoom);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblId.setBounds(114, 268, 46, 14);
		add(lblId);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRate.setBounds(358, 268, 46, 14);
		add(lblRate);
		
		JLabel lblRoomdetail = new JLabel("Room_Detail");
		lblRoomdetail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRoomdetail.setBounds(114, 293, 123, 23);
		add(lblRoomdetail);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoomDB x = new RoomDB(0, textField_room_detail.getText().trim(),
						Integer.parseInt(textField_rate_id.getText().trim()));
						
				RoomManager.saveNewRoom(x);
				load();
				textField_id.setText("");
				textField_rate_id.setText("");
				textField_room_detail.setText("");
				
				
				JOptionPane.showMessageDialog(PanelRoom.this, "finish!!");
			}
		});
		btnSave.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnSave.setBounds(119, 451, 89, 23);
		add(btnSave);
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoomDB x = new RoomDB();
				x.id = Integer.parseInt(textField_id.getText());
				x.rate_id = Integer.parseInt(textField_rate_id.getText());
				x.room_detail = textField_room_detail.getText();

				RoomManager.editRoom(x);
				JOptionPane.showMessageDialog(PanelRoom.this, "UpDate Finish!!");

				load();
				textField_id.setText("");
				textField_rate_id.setText("");
				textField_room_detail.setText("");
				
				JOptionPane.showMessageDialog(PanelRoom.this, "finish!!");
			}
		});
		btnEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnEdit.setBounds(347, 451, 89, 23);
		add(btnEdit);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
						PanelRoom.this,"Do you want to delete ?",
						"DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
				RoomDB x = new RoomDB(
						Integer.parseInt(textField_id.getText()), 
						(String)textField_room_detail.getText(),
						Integer.parseInt(textField_rate_id.getText().trim()));
				RoomManager.deleteRoom(x);
				load();
				textField_id.setText("");
				textField_room_detail.setText("");
				textField_rate_id.setText("");
				
				
				JOptionPane.showMessageDialog(PanelRoom.this, "finish!!");
				}
			}
		});
		btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnDelete.setBounds(563, 451, 89, 23);
		add(btnDelete);
		
		textField_id = new JTextField();
		textField_id.setBounds(149, 265, 186, 20);
		add(textField_id);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setEditable(false);
		
		textField_rate_id = new JTextField();
		textField_rate_id.setBounds(396, 265, 161, 20);
		add(textField_rate_id);
		textField_rate_id.setColumns(10);
		System.out.println("b");
		setPreferredSize(new Dimension(815, 500));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(114, 327, 538, 99);
		add(scrollPane_1);
		
		textField_room_detail = new JTextField();
		textField_room_detail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane_1.setViewportView(textField_room_detail);
		textField_room_detail.setColumns(10);

	}
	public void load()
	{
		list = RoomManager.getAllRoom();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("room_detail");
		model.addColumn("rate_id");

		for (RoomDB r : list)
		{
			model.addRow(new Object[]
			{ r.id, r.room_detail, r.rate_id });
		}
		
		table.setModel(model);
	}
}
