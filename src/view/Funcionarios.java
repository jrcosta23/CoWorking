package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Funcionarios extends JDialog {
	private JTextField inputNome;
	private JTextField inputLogin;
	private JTextField inputEmail;
	private JPasswordField inputSenha;

	public Funcionarios() {
		setTitle("Funcionários");
		setResizable(false);
		setBounds(new Rectangle(300, 100, 613, 362));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/logo.png")));
		getContentPane().setLayout(null);

		JLabel nomeFunc = new JLabel("Nome:");
		nomeFunc.setBounds(26, 44, 46, 14);
		getContentPane().add(nomeFunc);

		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(26, 133, 46, 14);
		getContentPane().add(loginFunc);

		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(313, 133, 46, 14);
		getContentPane().add(senhaFunc);

		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(26, 242, 46, 14);
		getContentPane().add(emailFunc);

		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(313, 242, 36, 14);
		getContentPane().add(perfilFunc);

		inputNome = new JTextField();
		inputNome.setBounds(82, 41, 482, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);

		inputLogin = new JTextField();
		inputLogin.setBounds(65, 130, 238, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputEmail = new JTextField();
		inputEmail.setBounds(65, 239, 238, 20);
		getContentPane().add(inputEmail);
		inputEmail.setColumns(10);

		inputSenha = new JPasswordField();
		inputSenha.setBounds(357, 130, 230, 20);
		getContentPane().add(inputSenha);

		JButton imgUpdate = new JButton("");
		imgUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUpdate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/update.png")));
		imgUpdate.setBounds(384, 270, 66, 52);
		getContentPane().add(imgUpdate);

		JButton imgCreate = new JButton("");
		imgCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgCreate.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/create.png")));
		imgCreate.setBounds(295, 267, 66, 52);
		getContentPane().add(imgCreate);

		imgCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFuncionario();
			}

		});

		JButton imgDelete = new JButton("");
		imgDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgDelete.setIcon(new ImageIcon(Funcionarios.class.getResource("/img/delete.png")));
		imgDelete.setBounds(470, 270, 66, 49);
		getContentPane().add(imgDelete);

		inputPerfil = new JComboBox();
		inputPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "", "Administrador", "Gerência", "Atendimento", "Suporte"}));
		inputPerfil.setBounds(349, 238, 238, 22);
		getContentPane().add(inputPerfil);

	}

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JComboBox inputPerfil;

	private void adicionarFuncionario() {
		String create = "insert into funcionario(nomeFunc, login, senha, Perfil, email)values (?, ?,md5(?), ?, ?);";

		try {

			// Estabelecer a conexao
			Connection conexaoBanco = dao.conectar();

			// Preparar a execusão do script SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(create);

			// Substituir os pontos de interrogação pelo conteú das caixas de texto (inputs)

			executarSQL.setString(1, inputNome.getText());
			executarSQL.setString(2, inputLogin.getText());
			executarSQL.setString(3, inputSenha.getText());

			 executarSQL.setString(4, inputPerfil.getSelectedItem().toString());

			executarSQL.setString(5, inputEmail.getText());

			// Executar os coamndos SQL e inserir o funcionário no banco de dados
			executarSQL.executeUpdate();

			conexaoBanco.close();

		}

		catch (SQLIntegrityConstraintViolationException erro) {
			JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro nome de usuário.");
		}

		catch (Exception e) {
			System.out.println(e);

		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Funcionarios dialog = new Funcionarios();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}