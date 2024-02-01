package view;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Login extends JDialog  {
	private JTextField inputLogin;
	private JPasswordField inputSenha;
	public Login() {
		setTitle("Sobre");
		setResizable(false);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().setForeground(new Color(0, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		setBounds(new Rectangle(0, 0, 584, 333));
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setLayout(null);
		
		JLabel txtLogin = new JLabel("Login");
		txtLogin.setBounds(42, 65, 46, 14);
		getContentPane().add(txtLogin);
		
		JLabel txtSenha = new JLabel("senha");
		txtSenha.setBounds(42, 155, 46, 14);
		getContentPane().add(txtSenha);
		
		inputLogin = new JTextField();
		inputLogin.setBounds(98, 62, 232, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);
		
		inputSenha = new JPasswordField();
		inputSenha.setBounds(98, 152, 232, 20);
		getContentPane().add(inputSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(174, 228, 89, 23);
		getContentPane().add(btnLogin);
		
		JLabel tituloLogin = new JLabel("Acessa  conta");
		tituloLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tituloLogin.setForeground(new Color(0, 0, 0));
		tituloLogin.setBounds(181, 22, 105, 14);
		getContentPane().add(tituloLogin);
		
		JLabel imgDatabase = new JLabel("New label");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(20, 228, 46, 55);
		getContentPane().add(imgDatabase);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
