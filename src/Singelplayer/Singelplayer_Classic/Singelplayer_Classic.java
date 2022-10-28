package Singelplayer.Singelplayer_Classic;

import Client.Client;
import Config.Config;
import MainGUI.MainGUI;
import Security.loginData;
import Singelplayer.ModusChoos_Singelplayer;
import Singelplayer.Singelplayer_Classic.Game.Drops_classic;
import Singelplayer.Singelplayer_Classic.Game.GUI_classic;
import Singelplayer.Singelplayer_Classic.Game.Movment_classic;
import Singelplayer.Singelplayer_Classic.Game.Snake_classic;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Singelplayer_Classic {

    JFrame frame;
    JLabel headline;
    JButton play;
    JButton back;
    JLabel highscore;
    JLabel level;
    JProgressBar xp;
    static JTextArea rank;
    JButton reloadscore;
    int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height/2;


    public Singelplayer_Classic(){

        Config config = new Config("Account//stats.txt");
                if(MainGUI.verified){
                    if(config.existdata("Benutzername")){
                        if(config.existdata("Password")){
                            Client client = new Client();
                            client.sendUDPToServer("classic--"+config.get("Benutzername")+"--"+config.get("Password")+"--init");
                        }
                    }
                }
        frame = new JFrame("Snake - Singelplayer");
        frame.setSize(screenwidth, screenheight);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        headline = new JLabel();
        headline.setText("Snake Beta");
        headline.setBounds(0,0,screenwidth, screenheight/2);
        headline.setVisible(true);
        headline.setFont(new Font("Calibri", Font.BOLD, screenwidth/5));

        play = new JButton();
        play.setVisible(true);
        play.setText("PLAY");
        play.setBounds(screenwidth/100,screenheight-screenheight/3,screenwidth/2,screenheight/12);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI_classic();
                new Snake_classic();
                new Movment_classic();
                new Drops_classic();
                frame.dispose();
            }
        });

        back = new JButton();
        back.setVisible(true);
        back.setBounds(screenwidth/2+25,screenheight-screenheight/3,screenwidth/8,screenheight/12);
        back.setText("BACK");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ModusChoos_Singelplayer();
            }
        });

        highscore = new JLabel();
        highscore.setText("Highscore: Wird geladen...");
        highscore.setBounds(10,screenheight/3,screenwidth,screenheight/10);
        highscore.setFont(new Font("Calibri", Font.BOLD, screenwidth/25));
        highscore.setVisible(true);
        Client client = new Client();


        rank = new JTextArea();
        rank.setEditable(false);
        rank.setVisible(true);
        rank.setText("Ranklist wird geladen...");
        rank.setBounds(screenwidth-screenwidth/3,screenheight/3,screenwidth/4,screenheight/2);
        rank.setBorder(new LineBorder(Color.BLACK,1));

        reloadscore = new JButton();
        reloadscore.setVisible(true);
        reloadscore.setBounds(screenwidth/2+25,screenheight/2,screenwidth/8,screenheight/12);
        reloadscore.setText("Reload Scores");
        reloadscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadscores_classic.reload();
                System.out.println("debug");
                rank.setText("Ranklist wird geladen...");
            }
        });


        level = new JLabel();
        level.setVisible(true);
        level.setBounds(screenwidth/100, screenheight/2-20, screenwidth/3, screenheight/20);
        level.setFont(new Font("Calibri", Font.BOLD, screenwidth/40));
        level.setText("Level: wird geladen...");

        xp = new JProgressBar();
        xp.setVisible(true);
        xp.setBounds(screenwidth/100,screenheight/2,screenwidth/3,screenheight/12);
        xp.setMaximum(1000);
        xp.setStringPainted(true);
        xp.setForeground(Color.green);
        xp.setFont(new Font("Calibri", Font.BOLD, screenwidth/40));


        frame.add(headline);
        frame.add(xp);
        frame.add(play);
        frame.add(back);
        frame.add(highscore);
        frame.add(rank);
        frame.add(reloadscore);
        frame.add(level);

        Thread t2 = new Thread(){
            public void run(){
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        highscore.setText("Highscore: "+client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--classichighscore"));
                    }else{
                        highscore.setText("Highscore: Bitte Melden sie sich an...");
                    }
                }else{
                    highscore.setText("Highscore: Veraltete Version oder Netzwerk Probleme");
                }
            }
        };
        t2.start();
        Thread t3 = new Thread(){
            public void run(){
                Client client = new Client();
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        String[] levelxp = client.sendToServer("level--"+loginData.getName()+"--"+loginData.getPassword()+"--get").split("--");
                        level.setText("Level: "+levelxp[0]);
                        xp.setString(levelxp[1]+"XP / 1000XP");
                        xp.setValue(Integer.valueOf(levelxp[1]));
                    }else{
                        level.setText("Level: Bitte Melden sie sich an...");
                        xp.setString("Bitte Melden sie sich an...");
                    }
                }else{
                    level.setText("Level: Veraltete Version oder Netzwerk Probleme");
                    xp.setString("Veraltete Version oder Netzwerk Probleme");
                }
            }
        };
        t3.start();

    }

}
