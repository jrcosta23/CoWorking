package view;

import javax.swing.JDialog;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		nomeFunc.setBounds(9, 44, 46, 14);
		getContentPane().add(nomeFunc);

		JLabel loginFunc = new JLabel("Login:");
		loginFunc.setBounds(9, 133, 46, 14);
		getContentPane().add(loginFunc);

		JLabel senhaFunc = new JLabel("Senha:");
		senhaFunc.setBounds(313, 133, 46, 14);
		getContentPane().add(senhaFunc);

		JLabel emailFunc = new JLabel("E-mail:");
		emailFunc.setBounds(9, 242, 46, 14);
		getContentPane().add(emailFunc);

		JLabel perfilFunc = new JLabel("Perfil:");
		perfilFunc.setBounds(313, 242, 36, 14);
		getContentPane().add(perfilFunc);

		inputNome = new JTextField();
		inputNome.setBounds(41, 41, 523, 20);
		getContentPane().add(inputNome);
		inputNome.setColumns(10);


		inputNome.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				buscarFuncionarioNaTabela();

			}
		});

		inputLogin = new JTextField();
		inputLogin.setBounds(41, 130, 262, 20);
		getContentPane().add(inputLogin);
		inputLogin.setColumns(10);

		inputEmail = new JTextField();
		inputEmail.setBounds(41, 239, 262, 20);
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
		inputPerfil.setModel(new DefaultComboBoxModel(
				new String[] { "", "", "Administrador", "Gerência", "Atendimento", "Suporte" }));
		inputPerfil.setBounds(349, 238, 238, 22);
		getContentPane().add(inputPerfil);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 108, 2, 2);
		getContentPane().add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 64, 523, 52);
		getContentPane().add(scrollPane_1);

		tblFuncionarios = new JTable();
		scrollPane_1.setColumnHeaderView(tblFuncionarios);

	}

	// Criar um objeto da classe DAO para estabelecer conexão com banco
	DAO dao = new DAO();
	private JComboBox inputPerfil;
	private JTable tblFuncionarios;

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
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso !");
			conexaoBanco.close();

			limparCampos();
		}

		catch (SQLIntegrityConstraintViolationException erro) {
			JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro nome de usuário.");
		}

		catch (Exception e) {
			System.out.println(e);

		}

	}

	private void buscarFuncionarioNaTabela() {
		String readTabela = "Select idFuncionario as ID, nomeFunc as Nome, email as Email from funcionario"
				+ " where nomeFunc like ?;";

		try {

			// Estabelecer a conexão
			Connection conexaoBanco = dao.conectar();

			// Preparar a excução dos comandos SQL
			PreparedStatement executarSQL = conexaoBanco.prepareStatement(readTabela);

			// substituir o ? pelo conteúdo da caixa dede texto
			executarSQL.setString(1, inputNome.getText() + "%");

			// Executar o comando SQL
			ResultSet resultadoExecucao = executarSQL.executeQuery();

			// Exibir o resultado na tabela, utlizção da biblioteca rs2xml para "popular"
			// a tabela

			tblFuncionarios.setModel(DbUtils.resultSetToTableModel(resultadoExecucao));

			conexaoBanco.close();

		}

		catch (Exception e) {

			System.out.println(e);
		}
	}

	private void setarCaixasTexto() {

		// Crar uma variável para receber a linha da tabela
		int setarLinha = tblFuncionarios.getSelectedRow();

	}

	private void limparCampos() {
		inputNome.setText(null);
		inputLogin.setText(null);
		inputSenha.setText(null);
		inputEmail.setText(null);
		// Para limpar componente JComboBox
		inputPerfil.setSelectedIndex(-1);

		// Posicionar o cursor de volta no campo Nome
		inputNome.requestFocus();

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