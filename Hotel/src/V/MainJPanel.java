package V;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class MainJPanel extends JFrame
{

	private JPanel contentPane;

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
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try
				{
					MainJPanel frame = getInstance();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	JPanel panel_left ;
	JPanel panel_center;
	private PanelMenu panelMenu;
	
	private static MainJPanel THIS;
	private JPanel panel_1;
	public static MainJPanel getInstance()
	{
		if(THIS == null)
		{
			THIS = new MainJPanel();
		}
		return THIS;
	}
	/**
	 * Create the frame.
	 */
	public MainJPanel()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 482);
		setSize(1200,600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		 panel_left = new JPanel();
		contentPane.add(panel_left, BorderLayout.WEST);
		panel_left.setBackground(Color.YELLOW);
		panel_left.setPreferredSize(new Dimension(300,500));
		panel_left.setLayout(new BorderLayout(0, 0));
		
		panelMenu = new PanelMenu();
		panel_left.add(panelMenu);
		
		panel_center = new JPanel();
		panel_center.setLayout(new BorderLayout(0,0));
		contentPane.add(panel_center, BorderLayout.CENTER);
		try
		{
			ImagePanel imageP= new ImagePanel(ImageIO.read(new File("C:\\Users\\ArmYo\\Desktop\\ทะเล.jpg")));
			panel_center.add(imageP, BorderLayout.CENTER);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		panel_center.setBackground(Color.pink);
		
		
		
	}
	
	
	
	public void setNewMainPanel(UI_Panel panel)
	{
		
		if(panel == UI_Panel.Room)
		{
			panel_center.removeAll();
			PanelRoom pr = new PanelRoom();
			panel_center.add(pr, BorderLayout.CENTER);
		}
		if(panel == UI_Panel.Booking)
		{
			panel_center.removeAll();
			PanelBook pb = new PanelBook();
			panel_center.add(pb, BorderLayout.CENTER);
		}
		if(panel == UI_Panel.Customer)
		{
			panel_center.removeAll();
			PanelCustomer pc = new PanelCustomer();
			panel_center.add(pc, BorderLayout.CENTER);
		}
		if(panel == UI_Panel.Rate)
		{
			panel_center.removeAll();
			PanelRate pr = new PanelRate();
			panel_center.add(pr, BorderLayout.CENTER);
		}
		if(panel == UI_Panel.User)
		{
			panel_center.removeAll();
			PanelUser pu = new PanelUser();
			panel_center.add(pu, BorderLayout.CENTER);
		}
		if(panel == UI_Panel.Invoice)
		{
			panel_center.removeAll();
			PanelInvoice pi = new PanelInvoice();
			panel_center.add(pi, BorderLayout.CENTER);
		}
		panel_center.revalidate();
		
	}
}
