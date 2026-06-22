package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.acount;
import view.view_bank;

public class logic_view_bank implements ActionListener {
	private view_bank vb;
	private mgAcount mga;
	private subprocessAcount hiloConsulta;

	public logic_view_bank(view_bank vb) {
		super();
		this.vb = vb;
		this.vb.btn_cuentas.addActionListener(this);
		this.vb.btn_depositos.addActionListener(this);
		this.vb.btn_pagos.addActionListener(this);
		this.vb.btn_myAccount.addActionListener(this);
		this.mga = new mgAcount();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vb.btn_cuentas) {
			subprocessAcount hilo = new subprocessAcount(mga);
			hilo.start();
			JOptionPane.showMessageDialog(vb, "Creando cuenta...", "Cuentas", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == vb.btn_depositos) {

			String cuentaStr = JOptionPane.showInputDialog(vb, "Número de cuenta:", "Depósito", JOptionPane.PLAIN_MESSAGE);
			if (cuentaStr == null || cuentaStr.isBlank()) return;
			String montoStr  = JOptionPane.showInputDialog(vb, "Monto a depositar:", "Depósito", JOptionPane.PLAIN_MESSAGE);
			if (montoStr == null || montoStr.isBlank()) return;
			try {
				int    cuenta = Integer.parseInt(cuentaStr.trim());
				double monto  = Double.parseDouble(montoStr.trim().replace(",", "."));
				boolean ok    = mga.deposit(cuenta, monto);   
				if (ok) {
					JOptionPane.showMessageDialog(vb, "Depósito realizado con éxito.", "Depósito", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(vb, "No se pudo realizar el depósito.\nVerifique el número de cuenta.", "Depósito", JOptionPane.WARNING_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(vb, "Valores inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == vb.btn_pagos) {
	
			String cuentaStr = JOptionPane.showInputDialog(vb, "Número de cuenta:", "Retiro", JOptionPane.PLAIN_MESSAGE);
			if (cuentaStr == null || cuentaStr.isBlank()) return;
			String montoStr  = JOptionPane.showInputDialog(vb, "Monto a retirar:", "Retiro", JOptionPane.PLAIN_MESSAGE);
			if (montoStr == null || montoStr.isBlank()) return;
			try {
				int    cuenta = Integer.parseInt(cuentaStr.trim());
				double monto  = Double.parseDouble(montoStr.trim().replace(",", "."));
				boolean ok    = mga.withdraw(cuenta, monto); 
				if (ok) {
					JOptionPane.showMessageDialog(vb, "Retiro realizado con éxito.", "Retiro", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(vb, "Fondos insuficientes o cuenta no encontrada.", "Retiro", JOptionPane.WARNING_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(vb, "Valores inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == vb.btn_myAccount) {
			String cuentaStr = JOptionPane.showInputDialog(vb, "Número de cuenta a monitorear:", "Mi Cuenta", JOptionPane.PLAIN_MESSAGE);
			if (cuentaStr == null || cuentaStr.isBlank()) return;
			try {
				int cuenta = Integer.parseInt(cuentaStr.trim());

				acount verificacion = mga.query(cuenta);   
				if (verificacion == null) {
					JOptionPane.showMessageDialog(vb, "Cuenta no encontrada.", "Mi Cuenta", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (hiloConsulta != null && hiloConsulta.isAlive()) {
					hiloConsulta.stopRunning();
				}
				hiloConsulta = new subprocessAcount(mga, vb, cuenta);
				hiloConsulta.setDaemon(true);  
				hiloConsulta.start();
				JOptionPane.showMessageDialog(vb, "Monitoreando cuenta " + cuenta + " cada 3 segundos.", "Mi Cuenta", JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(vb, "Número de cuenta inválido.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
