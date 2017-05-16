package V;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import M.BookingDetail;
import M.BookingDetailManager;
import M.RoomDB;
import M.RoomManager;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class PanelBook extends JPanel
{
	private JTextField textField_id;
	private JTextField textField_checkin;
	private JTextField textField_checkout;
	private JTextField textField_roomid;
	private JTextField textField_total;
	private JTable table;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	ArrayList <BookingDetail> list;

	/**
	 * Create the panel.
	 */
	public PanelBook()
	{
		setLayout(null);
		
		JLabel lblBooking = new JLabel("Booking");
		lblBooking.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblBooking.setBounds(348, 11, 101, 32);
		add(lblBooking);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblId.setBounds(42, 68, 46, 21);
		add(lblId);
		
		JLabel lblRoomid = new JLabel("roomid");
		lblRoomid.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRoomid.setBounds(348, 68, 71, 21);
		add(lblRoomid);
		
		JLabel lblcheckin = new JLabel("checkin");
		lblcheckin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblcheckin.setBounds(42, 112, 71, 22);
		add(lblcheckin);
		
		JLabel lblcheckout = new JLabel("checkout");
		lblcheckout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblcheckout.setBounds(307, 108, 92, 30);
		add(lblcheckout);
		
		JLabel lblTotal = new JLabel("total");
		lblTotal.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTotal.setBounds(613, 113, 59, 21);
		add(lblTotal);
		
		textField_id = new JTextField();
		textField_id.setBounds(111, 68, 122, 24);
		add(textField_id);
		textField_id.setColumns(10);
		
		textField_checkin = new JTextField();
		textField_checkin.setBounds(111, 113, 122, 25);
		add(textField_checkin);
		textField_checkin.setColumns(10);
		
		textField_checkout = new JTextField();
		textField_checkout.setBounds(396, 113, 117, 24);
		add(textField_checkout);
		textField_checkout.setColumns(10);
		
		textField_roomid = new JTextField();
		textField_roomid.setBounds(450, 68, 117, 21);
		add(textField_roomid);
		textField_roomid.setColumns(10);
		
		textField_total = new JTextField();
		textField_total.setBounds(658, 113, 122, 24);
		add(textField_total);
		textField_total.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 177, 737, 225);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		load();
		
		btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnSave.setBounds(111, 432, 89, 23);
		add(btnSave);
		
		btnEdit = new JButton("EDIT");
		btnEdit.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnEdit.setBounds(348, 432, 89, 23);
		add(btnEdit);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		btnDelete.setBounds(576, 432, 89, 23);
		add(btnDelete);

	}
	public void load()
	{
		list = BookingDetailManager.getAllBooking();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("booking_detail_id");
		model.addColumn("check_in");
		model.addColumn("check_out");
		model.addColumn("total");
		model.addColumn("room_id");

		for (BookingDetail r : list)
		{
			model.addRow(new Object[]
			{ r.booking_detail_id, r.check_in, r.check_out, r.total, r.room_id });
		}
		
		table.setModel(model);
	}
}
