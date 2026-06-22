package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.logic_view_login;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;

public class view_login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txt_user;
	public JPasswordField txt_psw;
	public JButton btn_ok;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_login frame = new view_login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view_login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 370, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USUARIO:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(36, 29, 70, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CLAVE:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(36, 66, 77, 18);
		contentPane.add(lblNewLabel_1);
		
		txt_user = new JTextField();
		txt_user.setBounds(131, 27, 152, 25);
		contentPane.add(txt_user);
		txt_user.setColumns(10);
		
		btn_ok = new JButton("INICIAR");
		btn_ok.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btn_ok.setBounds(86, 121, 116, 25);
		contentPane.add(btn_ok);
		
		txt_psw = new JPasswordField();
		txt_psw.setBounds(131, 68, 152, 25);
		contentPane.add(txt_psw);
		
		new logic_view_login(this);

	}
}
