package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.view_lienzo;

public class logic_view_lienzo implements ActionListener, KeyListener {
    private view_lienzo vl;

    private subprocessMoveObject hiloRojo;
    private subprocessMoveObject hiloAzul;
    private subprocessMoveObject hiloAmarillo;
    private subprocessMoveObject hiloNegro;
    private subprocessMoveObject hiloGris;

    private subprocessVerificarColisiones hiloColisiones;

    public logic_view_lienzo(view_lienzo vl_) {
        this.vl = vl_;
        this.vl.btn_start.addActionListener(this);
        this.vl.btn_pause.addActionListener(this);
        this.vl.btn_resume.addActionListener(this);
        this.vl.addKeyListener(this);
        this.vl.setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vl.btn_start) {
            detenerHilos();

            vl.pn_lienzo.reiniciar();

            hiloRojo      = new subprocessMoveObject(vl, "manchaRoja");
            hiloAzul      = new subprocessMoveObject(vl, "manchaAzul");
            hiloAmarillo  = new subprocessMoveObject(vl, "manchaAmarilla");
            hiloNegro     = new subprocessMoveObject(vl, "manchaNegra");
            hiloGris      = new subprocessMoveObject(vl, "manchaGris");
            hiloColisiones = new subprocessVerificarColisiones(vl);

            hiloRojo.start();
            hiloAzul.start();
            hiloAmarillo.start();
            hiloNegro.start();
            hiloGris.start();
            hiloColisiones.start();

        } else if (e.getSource() == vl.btn_pause) {
            suspenderHilos();

        } else if (e.getSource() == vl.btn_resume) {
            reanudarHilos();
        }

        vl.requestFocusInWindow();
    }

    private void detenerHilos() {
        try {
            if (hiloRojo      != null) { hiloRojo.setFlag(false);      hiloRojo.interrupt(); }
            if (hiloAzul      != null) { hiloAzul.setFlag(false);      hiloAzul.interrupt(); }
            if (hiloAmarillo  != null) { hiloAmarillo.setFlag(false);  hiloAmarillo.interrupt(); }
            if (hiloNegro     != null) { hiloNegro.setFlag(false);     hiloNegro.interrupt(); }
            if (hiloGris      != null) { hiloGris.setFlag(false);      hiloGris.interrupt(); }
            if (hiloColisiones!= null) { hiloColisiones.setFlag(false);hiloColisiones.interrupt(); }
        } catch (Exception ex) { }
    }

    @SuppressWarnings("deprecation")
    private void suspenderHilos() {
        if (hiloRojo      != null) hiloRojo.suspend();
        if (hiloAzul      != null) hiloAzul.suspend();
        if (hiloAmarillo  != null) hiloAmarillo.suspend();
        if (hiloNegro     != null) hiloNegro.suspend();
        if (hiloGris      != null) hiloGris.suspend();
        if (hiloColisiones!= null) hiloColisiones.suspend();
    }

    @SuppressWarnings("deprecation")
    private void reanudarHilos() {
        if (hiloRojo      != null) hiloRojo.resume();
        if (hiloAzul      != null) hiloAzul.resume();
        if (hiloAmarillo  != null) hiloAmarillo.resume();
        if (hiloNegro     != null) hiloNegro.resume();
        if (hiloGris      != null) hiloGris.resume();
        if (hiloColisiones!= null) hiloColisiones.resume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    vl.pn_lienzo.setJugadorY(-12); break;
            case KeyEvent.VK_DOWN:  vl.pn_lienzo.setJugadorY(12);  break;
            case KeyEvent.VK_LEFT:  vl.pn_lienzo.setJugadorX(-12); break;
            case KeyEvent.VK_RIGHT: vl.pn_lienzo.setJugadorX(12);  break;
            case KeyEvent.VK_R:
                vl.pn_lienzo.reiniciar();
                actionPerformed(new ActionEvent(vl.btn_start, ActionEvent.ACTION_PERFORMED, ""));
                break;
        }
    }

    @Override public void keyTyped(KeyEvent e)   {}
    @Override public void keyReleased(KeyEvent e) {}
}
