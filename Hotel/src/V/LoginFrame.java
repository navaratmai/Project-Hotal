package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Window.Type;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_id;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
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
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame()
	{
		setTitle("Login");
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblId.setBounds(131, 95, 66, 31);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblPassword.setBounds(131, 176, 117, 31);
		contentPane.add(lblPassword);
		
		textField_id = new JTextField();
		textField_id.setBounds(293, 95, 132, 31);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					check();
				}
			}
		});
		passwordField.setBounds(293, 176, 132, 30);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		btnLogin.setBounds(131, 296, 117, 31);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		btnExit.setBounds(318, 296, 107, 31);
		contentPane.add(btnExit);
	}
	public void check()
	{
		if(UserManager.checkLogin(textField_id.getText(), new String (passwordField.getPassword())))
		{
			MainJPanel f =  MainJPanel.getInstance();
			f.setVisible(true);
			
			LoginFrame.this.setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(LoginFrame.this, "username or password incorrect!!! ");
		}
	}
}
