package controller;

import view.view_lienzo;

public class subprocessVerificarColisiones extends Thread {
    private view_lienzo vl;
    private boolean flag = true;

    public subprocessVerificarColisiones(view_lienzo vl_) {
        this.vl = vl_;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                sleep(50); 
                vl.pn_lienzo.verificarColisiones();

                if (vl.pn_lienzo.isGameOver()) {
                    System.out.println("=== GAME OVER === Puntos: " + vl.pn_lienzo.getPuntos());
                    this.suspend();
                }

                if (vl.pn_lienzo.isGanaste()) {
                    System.out.println("=== GANASTE === Puntos: " + vl.pn_lienzo.getPuntos());
                    this.suspend();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
