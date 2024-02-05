package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

import model.DAO;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	private JLabel imgDatabase;
	
	
	public Login() {
		
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				statusConexaoBanco();
			}
		});
		
		setTitle("Login");
		setResizable(false);
		setBounds(new Rectangle(0, 0, 441, 305));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel txtLogin = new JLabel("Login");
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setBounds(0, 80, 146, 14);
		getContentPane().add(txtLogin);

		JLabel txtSenha = new JLabel("Senha");
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setBounds(0, 130, 146, 14);
		getContentPane().add(txtSenha);

		JTextField inputLogin = new JTextField();
		inputLogin.setBounds(132, 77, 195, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		JPasswordField inputSenha = new JPasswordField();
		inputSenha.setBounds(132, 127, 195, 20);
		getContentPane().add(inputSenha);
		
		JButton btnLogin = new JButton("Entrar");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(181, 186, 89, 23);
		getContentPane().add(btnLogin);
		
		JLabel tituloLogin = new JLabel("Acessar conta");
		tituloLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		tituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
		tituloLogin.setBounds(0, 27, 424, 20);
		getContentPane().add(tituloLogin);
		
		imgDatabase = new JLabel("");
		imgDatabase.setIcon(new ImageIcon(Login.class.getResource("/img/databaseOff.png")));
		imgDatabase.setBounds(10, 186, 69, 95);
		getContentPane().add(imgDatabase);
	}
	
	DAO dao = new DAO();
	
	private void statusConexaoBanco() {
		try {
			Connection conexaoBanco = dao.conectar();
			
			if (conexaoBanco == null) {
				//Escolher a imagem para quando não há conexão
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOff.png")));
			}
			
			else {
				//Trocar a imagem se houver conexão
				imgDatabase.setIcon(new ImageIcon (Login.class.getResource("/img/databaseOn.png")));
			}
			conexaoBanco.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	
	private void logar() {
		String read =  "select * from funcionario where login=? and senha= md5(?)";	
		
		try {
			
			
		}
		
		catch (Exception e ) {
			System.out.println(e);
		}
	}
	
	
	
		
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login dialog = new Login();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}