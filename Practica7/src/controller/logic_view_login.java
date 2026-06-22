package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.user;
import model.userDAO;
import view.view_bank;
import view.view_login;

public class logic_view_login implements ActionListener{
	private view_login vl;

	public logic_view_login(view_login vl) {
		super();
		this.vl = vl;
		this.vl.btn_ok.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==vl.btn_ok) {
			validate(
					vl.txt_user.getText(), getEchoPSW()
					);
		}	
	}
		
	private boolean validate(String...c) {
		try {
			user u= new userDAO().getDataUser();
			if(u.getUser().equals(c[0])) {
				if(u.getPws().equals(c[1])) {
					JOptionPane.showMessageDialog(vl, "Bienvenido!!", "LOGIN", JOptionPane.INFORMATION_MESSAGE);
					view_bank vb = new view_bank();
		            vb.setVisible(true);
		            vl.dispose(); 
					return true;
				}
			}
			JOptionPane.showMessageDialog(vl, "Credenciales incorrectas", "LOGIN", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(vl, "Fuente desconocida", "LOGIN", JOptionPane.ERROR_MESSAGE);
		}
			return false;
	}
	
	private String getEchoPSW() {
		String psw="";
		for(char c:vl.txt_psw.getPassword()) {
			psw+=String.valueOf(c);
		}
		return psw;
	}
}
