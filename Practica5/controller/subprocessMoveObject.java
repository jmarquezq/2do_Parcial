package controller;

import view.view_lienzo;

public class subprocessMoveObject extends Thread {
    private view_lienzo vl;
    private boolean flag = true;
    private String tipoMancha;

    public subprocessMoveObject(view_lienzo vl_, String tipo) {
        this.vl = vl_;
        this.tipoMancha = tipo;
    }

    private int getRandomX() {
        return 30 + (int)(Math.random() * (vl.pn_lienzo.getWidth() - 100));
    }

    private int getRandomY() {
        return 70 + (int)(Math.random() * (vl.pn_lienzo.getHeight() - 130));
    }

    @Override
    public void run() {
        while (flag) {
            try {
                switch (tipoMancha) {
                    case "manchaRoja":     sleep(3500); break;
                    case "manchaAzul":     sleep(4000); break;
                    case "manchaAmarilla": sleep(4500); break;
                    case "manchaNegra":    sleep(2000); break; 
                    case "manchaGris":     sleep(2500); break;
                    default:               sleep(3000); break;
                }

                if(vl.pn_lienzo.isGameOver() || vl.pn_lienzo.isGanaste()) continue;

                int rx = getRandomX();
                int ry = getRandomY();

                switch (tipoMancha) {
                    case "manchaRoja":     vl.pn_lienzo.setManchaRoja(rx, ry);     break;
                    case "manchaAzul":     vl.pn_lienzo.setManchaAzul(rx, ry);     break;
                    case "manchaAmarilla": vl.pn_lienzo.setManchaAmarilla(rx, ry); break;
                    case "manchaNegra":    vl.pn_lienzo.setManchaNegra(rx, ry);    break;
                    case "manchaGris":     vl.pn_lienzo.setManchaGris(rx, ry);     break;
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
