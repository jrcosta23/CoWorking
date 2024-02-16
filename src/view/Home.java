package view;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.xml.crypto.Data;
import javax.swing.JLabel;
import java.awt.Font;

public class Home extends JDialog {

	public JPanel panelUsuario;
	public JLabel txtUsuarioLogado;
	public JLabel txtData;
	public JLabel txtPerfilLogado;
	
	// Construtor
	public Home() {

		addWindowListener(new WindowAdapter() {

			public void windowActivated(WindowEvent e) {

				Date dataSistema = new Date();

				DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.FULL);

				txtData.setText(formatadorData.format(dataSistema));
			}

		
		});

		// WindowAdapter(),WindowEvent, Date IMPORTAR A PARTIR DO
		// Java.util.date,DateFormat

		setTitle("Home");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/img/logo.png")));
		setBounds(new Rectangle(300, 100, 613, 362));
		getContentPane().setLayout(null);

		JButton btnUser = new JButton("");
		btnUser.setBorderPainted(false);
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setIcon(new ImageIcon(Home.class.getResource("/img/user.png")));
		btnUser.setBounds(71, 101, 96, 96);
		getContentPane().add(btnUser);

		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcionarios = new Funcionarios();
				funcionarios.setVisible(true);
			}
		});

		JButton btnRoom = new JButton("");
		btnRoom.setBorderPainted(false);
		btnRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRoom.setIcon(new ImageIcon(Home.class.getResource("/img/room.png")));
		btnRoom.setBounds(237, 101, 96, 96);
		getContentPane().add(btnRoom);

		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salas salas = new Salas();
				salas.setVisible(true);
			}
		});

		JButton btnReserve = new JButton("");
		btnReserve.setBorderPainted(false);
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.setIcon(new ImageIcon(Home.class.getResource("/img/reserve.png")));
		btnReserve.setBounds(387, 101, 96, 96);
		getContentPane().add(btnReserve);

		panelUsuario = new JPanel();
		panelUsuario.setBounds(0, 232, 587, 80);
		getContentPane().add(panelUsuario);
		panelUsuario.setLayout(null);

		txtUsuarioLogado = new JLabel("");
		txtUsuarioLogado.setBounds(10, 0, 173, 30);
		panelUsuario.add(txtUsuarioLogado);

		txtData = new JLabel("");
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtData.setBounds(247, 26, 280, 15);
		panelUsuario.add(txtData);
		
		
		txtPerfilLogado = new JLabel("");
		txtPerfilLogado.setBounds(10, 41, 173, 30);
		panelUsuario.add(txtPerfilLogado);

		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservas reservas = new Reservas();
				reservas.setVisible(true);
			}
		});
	}

	// Implementação
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home dialog = new Home();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}