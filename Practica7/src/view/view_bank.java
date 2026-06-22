package view;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class view_bank extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public JButton btn_cuentas;
	public JButton btn_depositos;
	public JButton btn_pagos;
	public JButton btn_myAccount;   
	public JTable tb_cuentas;
	public DefaultTableModel modeloTabla;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_bank frame = new view_bank();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public view_bank() {
		setTitle("Bank - MVC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 420);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(10, 10));
		setContentPane(contentPane);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(4, 1, 5, 10));

		btn_cuentas   = new JButton("Cuentas");
		btn_cuentas.setFont(new Font("Tahoma", Font.BOLD, 10));

		btn_depositos = new JButton("Depósitos");
		btn_depositos.setFont(new Font("Tahoma", Font.BOLD, 10));

		btn_pagos     = new JButton("Pagos / Retiros");
		btn_pagos.setFont(new Font("Tahoma", Font.BOLD, 10));

		btn_myAccount = new JButton("Mi Cuenta");
		btn_myAccount.setFont(new Font("Tahoma", Font.BOLD, 10));

		panelBotones.add(btn_cuentas);
		panelBotones.add(btn_depositos);
		panelBotones.add(btn_pagos);
		panelBotones.add(btn_myAccount);

		contentPane.add(panelBotones, BorderLayout.WEST);

		String[] columnas = {"Cuenta", "Depósito / Retiro", "Saldo"};
		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override public boolean isCellEditable(int r, int c) { return false; }
		};
		tb_cuentas = new JTable(modeloTabla);
		tb_cuentas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tb_cuentas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
		tb_cuentas.setRowHeight(24);

		JScrollPane scrollTabla = new JScrollPane(tb_cuentas);
		contentPane.add(scrollTabla, BorderLayout.CENTER);

		new controller.logic_view_bank(this);
	}
}
