package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import model.acount;
import model.acountDao;

public class mgAcount {
	private acountDao adao;

	private int getRandom() {
		return (int)(Math.random()*10);
	}

	public synchronized void createAcount() {
		String numberAcount="12";
		for(int i=0;i<=7;i++) {
			numberAcount+=getRandom();
		}
		adao= new acountDao(
				new acount(Integer.parseInt(numberAcount),
					200,
					0,
					0,
					new Date())
				);
		try {
			adao.writerAcount();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized boolean deposit(int numeroCuenta, double monto) {
		try {
			acountDao dao = new acountDao();
			acount last = dao.readLastAcount(numeroCuenta);
			if (last == null) return false;

			double nuevoSaldo = last.getSaldo() + monto;
			acount reg = new acount(numeroCuenta, nuevoSaldo, 1, monto, new Date());
			new acountDao(reg).writerAcount();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public synchronized boolean withdraw(int numeroCuenta, double monto) {
		try {
			acountDao dao = new acountDao();
			acount last = dao.readLastAcount(numeroCuenta);
			if (last == null) return false;
			if (last.getSaldo() < monto) return false;   

			double nuevoSaldo = last.getSaldo() - monto;
			acount reg = new acount(numeroCuenta, nuevoSaldo, 2, monto, new Date());
			new acountDao(reg).writerAcount();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public synchronized acount query(int numeroCuenta) {
		try {
			return new acountDao().readLastAcount(numeroCuenta);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getFirstAccountNumber() {
		File dir = new File("src/resources/");
		File[] files = dir.listFiles((d, n) -> n.endsWith(".txt"));
		if (files == null || files.length == 0) return -1;
		String name = files[0].getName().replace(".txt","");
		try { return Integer.parseInt(name); } catch (NumberFormatException e) { return -1; }
	}
}
