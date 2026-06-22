package model;

import java.util.Date;

import Libreria_generica.generic;

public class acount extends generic<Integer, Double> {

    private Date fecha;

    public acount() {
        super();
        this.fecha = null;
    }

    public acount(int numeroCuenta, double saldo,
                  int movimiento, double monto,
                  Date fecha) {

        super(numeroCuenta, movimiento, saldo, monto);
        this.fecha = fecha;
    }

    public int getNumeroCuenta() {
        return getAttributeT1();
    }

    public void setNumeroCuenta(int numeroCuenta) {
        setAttributeT1(numeroCuenta);
    }

    public double getSaldo() {
        return getAttributeS3();
    }

    public void setSaldo(double saldo) {
        setAttributeS3(saldo);
    }

    public int getMovimiento() {
        return getAttributeT2();
    }

    public void setMovimiento(int movimiento) {
        setAttributeT2(movimiento);
    }

    public double getMonto() {
        return getAttributeS4();
    }

    public void setMonto(double monto) {
        setAttributeS4(monto);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return String.format("%d;%.2f;%d;%.2f;%s%n",
                getNumeroCuenta(),
                getSaldo(),
                getMovimiento(),
                getMonto(),
                getFecha());
    }
}