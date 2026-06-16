package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class lienzo extends JPanel {

    private Point jugador = new Point(280, 200);
    private Color colorPincel = Color.WHITE;

    private Point manchaRoja    = new Point(80,  100);
    private Point manchaAzul    = new Point(350, 280);
    private Point manchaAmarilla= new Point(500, 100);

    private Point manchaNegra   = new Point(200, 320);
    private Point manchaGris    = new Point(450, 350);

    private int vidas   = 3;
    private int puntos  = 0;
    private boolean rojoObtenido     = false;
    private boolean azulObtenido     = false;
    private boolean amarilloObtenido = false;
    private boolean gameOver  = false;
    private boolean ganaste   = false;

    public lienzo() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(245, 240, 230));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(new Color(200, 195, 185));
        for(int i = 0; i < getWidth(); i += 40)  g2.drawLine(i, 0, i, getHeight());
        for(int j = 0; j < getHeight(); j += 40) g2.drawLine(0, j, getWidth(), j);

        dibujarManchaRoja(g2);
        dibujarManchaAzul(g2);
        dibujarManchaAmarilla(g2);
        dibujarManchaNegra(g2);
        dibujarManchaGris(g2);
        dibujarPincel(g2);

        dibujarHUD(g2);

        if(gameOver) dibujarGameOver(g2);
        if(ganaste)  dibujarGanaste(g2);
    }

    private void dibujarManchaRoja(Graphics2D g2) {
        if(manchaRoja.x < 0) return;
        g2.setColor(new Color(220, 50, 50, 200));
        g2.fillOval(manchaRoja.x, manchaRoja.y, 50, 45);
        g2.fillOval(manchaRoja.x+10, manchaRoja.y-10, 35, 30);
        g2.fillOval(manchaRoja.x-8, manchaRoja.y+10, 30, 25);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.drawString("+30", manchaRoja.x+12, manchaRoja.y+28);
        g2.setColor(new Color(180,20,20));
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString("ROJO", manchaRoja.x+10, manchaRoja.y+42);
    }

    private void dibujarManchaAzul(Graphics2D g2) {
        if(manchaAzul.x < 0) return;
        g2.setColor(new Color(50, 100, 220, 200));
        g2.fillOval(manchaAzul.x, manchaAzul.y, 50, 45);
        g2.fillOval(manchaAzul.x+12, manchaAzul.y-10, 35, 30);
        g2.fillOval(manchaAzul.x-8, manchaAzul.y+8, 30, 25);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.drawString("+30", manchaAzul.x+12, manchaAzul.y+28);
        g2.setColor(new Color(20,60,180));
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString("AZUL", manchaAzul.x+12, manchaAzul.y+42);
    }

    private void dibujarManchaAmarilla(Graphics2D g2) {
        if(manchaAmarilla.x < 0) return;
        g2.setColor(new Color(255, 210, 30, 220));
        g2.fillOval(manchaAmarilla.x, manchaAmarilla.y, 50, 45);
        g2.fillOval(manchaAmarilla.x+10, manchaAmarilla.y-10, 38, 30);
        g2.fillOval(manchaAmarilla.x-8, manchaAmarilla.y+10, 30, 25);
        g2.setColor(new Color(100,70,0));
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.drawString("+30", manchaAmarilla.x+12, manchaAmarilla.y+28);
        g2.setColor(new Color(150, 100, 0));
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString("AMARILLO", manchaAmarilla.x+2, manchaAmarilla.y+42);
    }

    private void dibujarManchaNegra(Graphics2D g2) {
        if(manchaNegra.x < 0) return;
        g2.setColor(new Color(30, 30, 30, 210));
        g2.fillOval(manchaNegra.x, manchaNegra.y, 50, 45);
        g2.fillOval(manchaNegra.x+10, manchaNegra.y-12, 35, 30);
        g2.fillOval(manchaNegra.x-10, manchaNegra.y+8, 30, 28);
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.drawString("-1 vida", manchaNegra.x+4, manchaNegra.y+28);
    }

    private void dibujarManchaGris(Graphics2D g2) {
        if(manchaGris.x < 0) return;
        g2.setColor(new Color(140, 140, 140, 200));
        g2.fillOval(manchaGris.x, manchaGris.y, 50, 45);
        g2.fillOval(manchaGris.x+12, manchaGris.y-10, 32, 28);
        g2.fillOval(manchaGris.x-8, manchaGris.y+10, 28, 25);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 11));
        g2.drawString("-1 vida", manchaGris.x+4, manchaGris.y+28);
    }

    private void dibujarPincel(Graphics2D g2) {
        int px = jugador.x;
        int py = jugador.y;

        g2.setColor(new Color(139, 90, 43));
        g2.setStroke(new BasicStroke(6));
        g2.drawLine(px+10, py+10, px+38, py+38);

        g2.setColor(new Color(180, 180, 180));
        g2.fillOval(px+2, py+2, 18, 18);
        g2.setColor(new Color(120,120,120));
        g2.drawOval(px+2, py+2, 18, 18);

        g2.setColor(colorPincel);
        int[] xPunta = {px, px+8, px+14, px+6};
        int[] yPunta = {py+20, py+14, py+22, py+28};
        g2.fillPolygon(xPunta, yPunta, 4);
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(1));
        g2.drawPolygon(xPunta, yPunta, 4);
    }

    private void dibujarHUD(Graphics2D g2) {
        g2.setColor(new Color(30, 30, 30, 180));
        g2.fillRoundRect(5, 5, getWidth()-10, 55, 10, 10);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("Puntos: " + puntos, 15, 25);

        g2.setFont(new Font("Arial", Font.PLAIN, 18));
        StringBuilder corazones = new StringBuilder("Vidas: ");
        for(int i=0; i<vidas; i++) corazones.append("♥ ");
        g2.setColor(new Color(255, 100, 100));
        g2.drawString(corazones.toString(), 15, 48);

        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.setColor(Color.WHITE);
        g2.drawString("Colores:", getWidth()-200, 22);

        g2.setColor(rojoObtenido ? new Color(220,50,50) : new Color(100,100,100));
        g2.fillOval(getWidth()-115, 8, 22, 22);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString("R", getWidth()-108, 24);

        g2.setColor(azulObtenido ? new Color(50,100,220) : new Color(100,100,100));
        g2.fillOval(getWidth()-88, 8, 22, 22);
        g2.setColor(Color.WHITE);
        g2.drawString("A", getWidth()-82, 24);

        g2.setColor(amarilloObtenido ? new Color(255,210,30) : new Color(100,100,100));
        g2.fillOval(getWidth()-61, 8, 22, 22);
        g2.setColor(new Color(80,60,0));
        g2.drawString("AM", getWidth()-58, 24);

        g2.setColor(new Color(30,30,30,180));
        g2.fillRoundRect(getWidth()-200, 30, 135, 22, 8, 8);
        g2.setColor(colorPincel);
        g2.fillOval(getWidth()-65, 32, 18, 18);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 11));
        g2.drawString("Color actual:", getWidth()-195, 46);
        g2.setColor(new Color(180,180,180));
        g2.drawOval(getWidth()-65, 32, 18, 18);
    }

    private void dibujarGameOver(Graphics2D g2) {
        g2.setColor(new Color(0,0,0,190));
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(new Color(220,50,50));
        g2.setFont(new Font("Arial", Font.BOLD, 38));
        g2.drawString("GAME OVER", getWidth()/2-115, getHeight()/2-20);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Puntos finales: " + puntos, getWidth()/2-80, getHeight()/2+20);
        g2.setColor(new Color(200,200,200));
        g2.setFont(new Font("Arial", Font.PLAIN, 13));
        g2.drawString("Presiona R para reiniciar", getWidth()/2-95, getHeight()/2+50);
    }

    private void dibujarGanaste(Graphics2D g2) {
        g2.setColor(new Color(0,0,0,190));
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(new Color(255,215,0));
        g2.setFont(new Font("Arial", Font.BOLD, 36));
        g2.drawString("¡GANASTE!", getWidth()/2-110, getHeight()/2-20);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("¡Coleccionaste los 3 colores primarios!", getWidth()/2-155, getHeight()/2+20);
        g2.drawString("Puntos: " + puntos, getWidth()/2-50, getHeight()/2+45);
        g2.setColor(new Color(200,200,200));
        g2.setFont(new Font("Arial", Font.PLAIN, 13));
        g2.drawString("Presiona R para jugar de nuevo", getWidth()/2-115, getHeight()/2+75);
    }

    public void setJugadorX(int dx) {
        int nx = jugador.x + dx;
        if(nx >= 0 && nx <= getWidth() - 45) jugador.x = nx;
        repaint();
    }

    public void setJugadorY(int dy) {
        int ny = jugador.y + dy;
        if(ny >= 60 && ny <= getHeight() - 45) jugador.y = ny;
        repaint();
    }

    public void setManchaRoja(int x, int y)     { manchaRoja.setLocation(x, y);     repaint(); }
    public void setManchaAzul(int x, int y)     { manchaAzul.setLocation(x, y);     repaint(); }
    public void setManchaAmarilla(int x, int y) { manchaAmarilla.setLocation(x, y); repaint(); }
    public void setManchaNegra(int x, int y)    { manchaNegra.setLocation(x, y);    repaint(); }
    public void setManchaGris(int x, int y)     { manchaGris.setLocation(x, y);     repaint(); }

    public void verificarColisiones() {
        if(gameOver || ganaste) return;

        if(manchaRoja.x >= 0 && distancia(jugador, manchaRoja) < 48) {
            puntos += 30;
            rojoObtenido = true;
            colorPincel = new Color(220, 50, 50);
            manchaRoja.setLocation(-200, -200);
            System.out.println("🔴 +30 puntos - Color ROJO obtenido");
        }

        if(manchaAzul.x >= 0 && distancia(jugador, manchaAzul) < 48) {
            puntos += 30;
            azulObtenido = true;
            colorPincel = new Color(50, 100, 220);
            manchaAzul.setLocation(-200, -200);
            System.out.println("🔵 +30 puntos - Color AZUL obtenido");
        }

        if(manchaAmarilla.x >= 0 && distancia(jugador, manchaAmarilla) < 48) {
            puntos += 30;
            amarilloObtenido = true;
            colorPincel = new Color(255, 210, 30);
            manchaAmarilla.setLocation(-200, -200);
            System.out.println("🟡 +30 puntos - Color AMARILLO obtenido");
        }

        if(manchaNegra.x >= 0 && distancia(jugador, manchaNegra) < 45) {
            vidas--;
            manchaNegra.setLocation(-200, -200);
            System.out.println("⬛ -1 vida. Vidas restantes: " + vidas);
        }

        if(manchaGris.x >= 0 && distancia(jugador, manchaGris) < 45) {
            vidas--;
            manchaGris.setLocation(-200, -200);
            System.out.println("⬜ -1 vida. Vidas restantes: " + vidas);
        }

        if(rojoObtenido && azulObtenido && amarilloObtenido) ganaste = true;
        if(vidas <= 0) gameOver = true;

        repaint();
    }

    private double distancia(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public boolean isGameOver() { return gameOver || vidas <= 0; }
    public boolean isGanaste()  { return ganaste; }
    public int getPuntos()      { return puntos; }
    public int getVidas()       { return vidas; }

    public void reiniciar() {
        jugador.setLocation(280, 200);
        manchaRoja.setLocation(80, 100);
        manchaAzul.setLocation(350, 280);
        manchaAmarilla.setLocation(500, 100);
        manchaNegra.setLocation(200, 320);
        manchaGris.setLocation(450, 350);
        puntos  = 0;
        vidas   = 3;
        rojoObtenido = false;
        azulObtenido = false;
        amarilloObtenido = false;
        gameOver = false;
        ganaste  = false;
        colorPincel = Color.WHITE;
        repaint();
    }
}
