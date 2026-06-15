package view;

import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import controller.logic_view_lienzo;

public class view_lienzo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JButton btn_start;
    public JButton btn_pause;
    public JButton btn_resume;
    public lienzo pn_lienzo;

    public static void main(String[] args) {
        System.out.println("INICIANDO JUEGO - LADRÓN DE COLORES...");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    view_lienzo frame = new view_lienzo();
                    frame.setVisible(true);
                    System.out.println("VENTANA CREADA Y VISIBLE");
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public view_lienzo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 580);
        setResizable(false);
        setTitle("LADRÓN DE COLORES - Colecciona los 3 colores primarios");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(30, 20, 60));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Panel de control superior
        JPanel pn_control = new JPanel();
        pn_control.setBackground(new Color(50, 40, 80));
        pn_control.setBounds(10, 10, 634, 40);
        contentPane.add(pn_control);

        btn_start = new JButton("START");
        btn_start.setBackground(new Color(50, 180, 50));
        btn_start.setForeground(Color.WHITE);
        btn_start.setFont(new Font("Arial", Font.BOLD, 12));
        btn_start.setFocusable(false);
        pn_control.add(btn_start);

        btn_pause = new JButton("PAUSE");
        btn_pause.setBackground(new Color(200, 150, 0));
        btn_pause.setForeground(Color.WHITE);
        btn_pause.setFont(new Font("Arial", Font.BOLD, 12));
        btn_pause.setFocusable(false);
        pn_control.add(btn_pause);

        btn_resume = new JButton("RESUME");
        btn_resume.setBackground(new Color(0, 120, 200));
        btn_resume.setForeground(Color.WHITE);
        btn_resume.setFont(new Font("Arial", Font.BOLD, 12));
        btn_resume.setFocusable(false);
        pn_control.add(btn_resume);

        JLabel lbl_instruccion = new JLabel("Flechas: mover | R: reiniciar");
        lbl_instruccion.setForeground(new Color(200, 200, 200));
        lbl_instruccion.setFont(new Font("Arial", Font.PLAIN, 11));
        pn_control.add(lbl_instruccion);

        pn_lienzo = new lienzo();
        pn_lienzo.setBounds(10, 58, 634, 470);
        contentPane.add(pn_lienzo);

        new logic_view_lienzo(this);
    }
}
