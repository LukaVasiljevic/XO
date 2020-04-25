import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Prozor extends JFrame
{
    private final JPanel prozor;
    JFrame okvir;
    JButton gl, gs, gd,
            sl, ss, sd,
            dol, dos, dod;
    JButton mute, reset;
    JLabel naslov, naPotezu;

    Icon prazno, mil, rad;
    XO xo = new XO();
    STATUS_IGRE statusIgre = STATUS_IGRE.U_TOKU;
    public Prozor(String naziv)
    {
        super(naziv);
        prozor = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550, 480);
        this.setContentPane(prozor);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setLayout(new BorderLayout());

        //TOP
        naslov = new JLabel("Милашин 0 - 0 Радашин", JLabel.CENTER);
        naslov.setFont(new Font("", Font.BOLD, 36));
        naPotezu = new JLabel("На потезу: Радашин", JLabel.CENTER);
        naPotezu.setFont(new Font("", Font.PLAIN, 18));




        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 1));
        top.add(naslov);
        top.add(naPotezu);
        //LEFT
        JPanel levo = new JPanel();
        levo.setLayout(new GridLayout(3, 1));
        levo.setBorder(new EmptyBorder(10, 10, 10, 10));
        reset = new JButton("reset");
        reset.addActionListener(this::ActionPerformed);
        levo.add(new JLabel());
        levo.add(reset);

        //CENTER
        JPanel tabla = new JPanel();
        tabla.setLayout(new GridLayout(3, 3));
        tabla.setBorder(new EmptyBorder(10, 10, 10, 10));
        gl = new JButton();gs = new JButton("");gd = new JButton("");
        sl = new JButton("");ss = new JButton("");sd = new JButton("");
        dol = new JButton("");dos = new JButton("");dod = new JButton("");

        try {
            Image img = ImageIO.read(getClass().getResource("prazno.jpg"));
            prazno = resizeIcon(new ImageIcon(img), 130, 100);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img = ImageIO.read(getClass().getResource("милашин.png"));
            mil = resizeIcon(new ImageIcon(img), 130, 100);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Image img = ImageIO.read(getClass().getResource("радашин.jpg"));
            rad = resizeIcon(new ImageIcon(img), 130, 100);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        initDugmad();

        gl.addActionListener(this::ActionPerformed);gs.addActionListener(this::ActionPerformed);gd.addActionListener(this::ActionPerformed);
        sl.addActionListener(this::ActionPerformed);ss.addActionListener(this::ActionPerformed);sd.addActionListener(this::ActionPerformed);
        dol.addActionListener(this::ActionPerformed);dos.addActionListener(this::ActionPerformed);dod.addActionListener(this::ActionPerformed);

        tabla.add(gl);tabla.add(gs);tabla.add(gd);
        tabla.add(sl);tabla.add(ss);tabla.add(sd);
        tabla.add(dol);tabla.add(dos);tabla.add(dod);

        //RIGHT
        JPanel desno = new JPanel();
        desno.setLayout(new GridLayout(3, 1));
        desno.setBorder(new EmptyBorder(10, 10, 10, 10));
        //mute = new JButton("mute");
        //desno.add(new JLabel());
       // desno.add(mute);


        //BOTTOM
        JLabel autor = new JLabel("Васиљевић Лука", JLabel.RIGHT);
        autor.setFont(new Font("", Font.ITALIC, 14));

        //DODAJ NA GLAVNI PANEL
        prozor.add(top, BorderLayout.NORTH);
        prozor.add(desno, BorderLayout.EAST);
        prozor.add(tabla, BorderLayout.CENTER);
        prozor.add(levo, BorderLayout.WEST);
        prozor.add(autor, BorderLayout.SOUTH);

        prozor.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        JFrame okvir = new Prozor("Милашин-Радашин");

    }
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    private void initDugmad()
    {
        gl.setIcon(prazno);gs.setIcon(prazno);gd.setIcon(prazno);
        sl.setIcon(prazno);ss.setIcon(prazno);sd.setIcon(prazno);
        dol.setIcon(prazno);
        dos.setIcon(prazno);dod.setIcon(prazno);
    }
    public void ActionPerformed(ActionEvent e)
    {
        if (e.getSource() == reset)
        {
            xo.resetuj();
            naslov.setText("Милашин 0 - 0 Радашин");
            naPotezu.setText("На потезу: Радашин");
            statusIgre = STATUS_IGRE.U_TOKU;
            initDugmad();
        }
        if (e.getSource() == gl)
        {
            if(xo.validanPotez(0,0))
                if(xo.getNaPotezu() == 1)
                    gl.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    gl.setIcon(mil);
            statusIgre = xo.odigrajPotez(0,0);
        }
        if (e.getSource() == gs)
        {
            if(xo.validanPotez(0,1))
                if(xo.getNaPotezu() == 1)
                    gs.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    gs.setIcon(mil);
            statusIgre = xo.odigrajPotez(0,1);
        }
        if (e.getSource() == gd)
        {
            if(xo.validanPotez(0,2))
                if(xo.getNaPotezu() == 1)
                    gd.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    gd.setIcon(mil);
            statusIgre = xo.odigrajPotez(0,2);

        }
        if (e.getSource() == sl)
        {
            if(xo.validanPotez(1,0))
                if(xo.getNaPotezu() == 1)
                    sl.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    sl.setIcon(mil);
            statusIgre = xo.odigrajPotez(1,0);
        }
        if (e.getSource() == ss)
        {
            if(xo.validanPotez(1,1))
                if(xo.getNaPotezu() == 1)
                    ss.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    ss.setIcon(mil);
            statusIgre = xo.odigrajPotez(1,1);
        }
        if (e.getSource() == sd)
        {
            if(xo.validanPotez(1,2))
                if(xo.getNaPotezu() == 1)
                    sd.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    sd.setIcon(mil);
            statusIgre = xo.odigrajPotez(1,2);
        }
        if (e.getSource() == dol)
        {
            if(xo.validanPotez(2,0))
                if(xo.getNaPotezu() == 1)
                    dol.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    dol.setIcon(mil);
            statusIgre = xo.odigrajPotez(2,0);
        }
        if (e.getSource() == dos)
        {
            if(xo.validanPotez(2,1))
                if(xo.getNaPotezu() == 1)
                    dos.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    dos.setIcon(mil);
            statusIgre = xo.odigrajPotez(2,1);
        }
        if (e.getSource() == dod)
        {
            if(xo.validanPotez(2,2))
                if(xo.getNaPotezu() == 1)
                    dod.setIcon(rad);
                else if(xo.getNaPotezu() == -1)
                    dod.setIcon(mil);
            statusIgre = xo.odigrajPotez(2,2);
        }

        if(xo.getNaPotezu() == 1)
        {
            naPotezu.setText("На потезу: Радашин");
        }
        else if(xo.getNaPotezu() == -1)
        {
            naPotezu.setText("На потезу: Милашин");
        }

        if(statusIgre != STATUS_IGRE.U_TOKU)
        {
            String doskocica = "Нерешено";

            if(statusIgre == STATUS_IGRE.POBEDIO_X)
                doskocica = String.format("Немо се љутиш Милашине...");
            else if(statusIgre == STATUS_IGRE.POBEDIO_O)
                doskocica = String.format("Мисим немо се љутиш Радашине...");

            Object[] options = {"да", "не"};
            int result = JOptionPane.showOptionDialog(prozor, doskocica +  "\n Нова игра?",
            "КРАЈ ИГРЕ", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title);
            if(result == JOptionPane.YES_OPTION)
            {
                naslov.setText(String.format("Милашин %d - %d Радашин", xo.getPobedaO(),xo.getPobedaX()));
                naPotezu.setText("На потезу: Радашин");
                initDugmad();
                xo.inicijalizuj();
            }
        }
    }

}
