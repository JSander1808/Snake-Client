package Singelplayer;

import MainGUI.MainGUI;
import Singelplayer.Singelplayer_Classic.Singelplayer_Classic;
import Singelplayer.Singelplayer_Classic.reloadscores_classic;
import Singelplayer.Singelplayer_Special.Singelplayer_special;
import Singelplayer.Singelplayer_Special.reloadscores_special;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModusChoos_Singelplayer {

    JFrame frame;
    JButton classic;
    JLabel classicheadline;
    JTextArea classicdiscription;
    JButton special;
    JLabel specialheadline;
    JTextArea specialdiscription;
    JButton back;
    int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height/2;

    public ModusChoos_Singelplayer(){
        frame = new JFrame("Snake - Main Menu");
        frame.setVisible(true);
        frame.setSize(screenwidth, screenheight);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        classic = new JButton();
        classic.setText("Classic");
        classic.setBounds(screenwidth/9,screenheight-screenheight/3,screenwidth/3,screenheight/15);
        classic.setVisible(true);
        classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Singelplayer_Classic();
                frame.dispose();
                reloadscores_classic.reload();
            }
        });

        classicheadline = new JLabel();
        classicheadline.setText("Classic");
        classicheadline.setBounds(screenwidth/9,screenheight/14,screenwidth/3,screenheight/8);
        classicheadline.setFont(new Font("Calibri", Font.BOLD, 60));
        classicheadline.setVisible(true);

        classicdiscription = new JTextArea();
        classicdiscription.setText("Sie kennen es doch alle dies hier\nist der normale Snake Modus.\nDas einzige was man machen muss ist\nmit der Schlange über die grünen\nBlöcke zu fahren.\nSollten sie das nun getan haben\nwird ihre Schlange Länger.");
        classicdiscription.setBounds(screenwidth/9,screenheight/5,screenwidth/3,screenheight/3);
        classicdiscription.setVisible(true);
        classicdiscription.setEditable(false);
        classicdiscription.setFont(new Font("Calibri", Font.BOLD, screenwidth/47));

        special = new JButton();
        special.setText("Special");
        special.setBounds(screenwidth-screenwidth/2,screenheight-screenheight/3,screenwidth/3,screenheight/15);
        special.setVisible(true);
        special.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Singelplayer_special();
                frame.dispose();
                reloadscores_special.reload();
            }
        });

        specialheadline = new JLabel();
        specialheadline.setText("Special");
        specialheadline.setBounds(screenwidth-screenwidth/2,screenheight/14,screenwidth/3,screenheight/8);
        specialheadline.setFont(new Font("Calibri", Font.BOLD, 60));
        specialheadline.setVisible(true);

        specialdiscription = new JTextArea();
        specialdiscription.setText("Hier ist der Special Modus.\nIn diesem Modus zeigen sie wie\nAnpassungsreich sie sind.\nWenn sie hier die grünen Boxen einsammeln\nbekommen sie zufällige Effekte.\nEs kann praktisch alles passieren\nund das macht diesen Modus so\nAbwechslungsreich.");
        specialdiscription.setBounds(screenwidth-screenwidth/2,screenheight/5,screenwidth/3+100,screenheight/2);
        specialdiscription.setVisible(true);
        specialdiscription.setFont(new Font("Calibri", Font.BOLD, screenwidth/47));
        specialdiscription.setEditable(false);

        back = new JButton();
        back.setText("Back");
        back.setBounds(screenwidth-screenwidth/2,screenheight-screenheight/4,screenwidth/7,screenheight/15);
        back.setVisible(true);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();
                frame.dispose();
            }
        });

        frame.add(classic);
        frame.add(classicheadline);
        frame.add(classicdiscription);
        frame.add(special);
        frame.add(specialdiscription);
        frame.add(specialheadline);
        frame.add(back);
    }

}
