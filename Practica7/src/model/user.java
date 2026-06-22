package model;

import Libreria_generica.generic;

public class user {
	private generic<String,?>dt_u;
	public user() {
		dt_u=new generic();
	}
	public user (String user, String pws) {
		this.dt_u=new generic(user,pws);
	}
	public String getUser() {
		return dt_u.getAttributeT1();
	}
	public String getPws() {
		return dt_u.getAttributeT2();
	}
	public void setUser(String user) {
		this.dt_u.setAttributeT1(user);
	}
	public void setPSW(String psw) {
		this.dt_u.setAttributeT2(psw);
	}
	

}
