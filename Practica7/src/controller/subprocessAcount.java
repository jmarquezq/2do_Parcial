package controller;

import model.acount;
import view.view_bank;

public class subprocessAcount extends Thread {

	private mgAcount mga;
	private view_bank vb;
	private int numeroCuenta;
	private volatile boolean running = true;

	public subprocessAcount(mgAcount mga) {
		super();
		this.mga = mga;
	}

	public subprocessAcount(mgAcount mga, view_bank vb, int numeroCuenta) {
		super();
		this.mga = mga;
		this.vb  = vb;
		this.numeroCuenta = numeroCuenta;
	}

	public void stopRunning() {
		running = false;
		this.interrupt();
	}

	@Override
	public void run() {
		if (vb == null) {
			try {
				sleep(1000);
				mga.createAcount();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			return;
		}

		while (running) {
			try {
				acount last = mga.query(numeroCuenta); 
				if (last != null) {
					javax.swing.SwingUtilities.invokeLater(() -> {
						String tipoMov = "";
						if (last.getMovimiento() == 1) {
							tipoMov = String.format("Depósito: +%.2f", last.getMonto());
						} else if (last.getMovimiento() == 2) {
							tipoMov = String.format("Retiro: -%.2f", last.getMonto());
						} else {
							tipoMov = "Apertura";
						}
						String saldoStr = String.format("%.2f", last.getSaldo());
						vb.modeloTabla.setRowCount(0);
						vb.modeloTabla.addRow(new Object[]{
							last.getNumeroCuenta(),
							tipoMov,
							saldoStr
						});
					});
				}
				sleep(3000);   
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}
}
