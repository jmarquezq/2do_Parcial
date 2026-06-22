package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class acountDao {
	private acount acount;
	private final String path="src/resources/";

	public acountDao() {
		super();
		this.acount=new acount();
	}

	public acountDao(model.acount acount) {
		super();
		this.acount = acount;
	}

	public boolean writerAcount() throws IOException {
		FileWriter out=new FileWriter (path+acount.getNumeroCuenta()+".txt", true);
		out.write(acount.toString());
		out.close();
		return true;
	}

	public acount readLastAcount(int numeroCuenta) throws IOException {
		String filePath = path + numeroCuenta + ".txt";
		String lastLine = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					lastLine = line.trim();
				}
			}
		}
		if (lastLine == null) return null;

		String[] parts = lastLine.split(";");
		if (parts.length < 4) return null;

		int nro      = Integer.parseInt(parts[0].trim());
		double saldo = Double.parseDouble(parts[1].replace(",", ".").trim());
		int mov      = Integer.parseInt(parts[2].trim());
		double monto = Double.parseDouble(parts[3].replace(",", ".").trim());

		return new acount(nro, saldo, mov, monto, null);
	}
}
