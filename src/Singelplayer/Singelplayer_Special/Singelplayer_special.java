package Singelplayer.Singelplayer_Special;

import Client.Client;
import Config.Config;
import MainGUI.MainGUI;
import Security.loginData;
import Singelplayer.ModusChoos_Singelplayer;
import Singelplayer.Singelplayer_Special.Game.Drops_special;
import Singelplayer.Singelplayer_Special.Game.GUI_special;
import Singelplayer.Singelplayer_Special.Game.Movment_special;
import Singelplayer.Singelplayer_Special.Game.Snake_special;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Singelplayer_special {

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


    public Singelplayer_special(){

        Config config = new Config("Account//stats.txt");
        Thread t1 = new Thread(){
            public void run(){
                if(MainGUI.verified){
                    if(config.existdata("Benutzername")){
                        if(config.existdata("Password")){
                            Client client = new Client();
                            client.sendUDPToServer("special--"+config.get("Benutzername")+"--"+config.get("Password")+"--init");
                        }
                    }
                }
            }
        };
        t1.start();
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
                new GUI_special();
                new Snake_special();
                new Movment_special();
                new Drops_special();
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
        highscore.setBounds(10,screenheight/3,screenwidth,screenheight/10);
        highscore.setFont(new Font("Calibri", Font.BOLD, screenwidth/25));
        highscore.setVisible(true);
        highscore.setText("Highscore: Wird geladen...");
        Client client = new Client();
        Thread t2 = new Thread(){
            public void run(){
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        highscore.setText("Highscore: "+client.sendToServer("special--"+loginData.getName()+"--"+loginData.getPassword()+"--get--specialhighscore"));
                    }else{
                        highscore.setText("Highscore: Bitte Melden sie sich an...");
                    }
                }else{
                    highscore.setText("Highscore: Veraltete Version oder Netzwerk Probleme");
                }
            }
        };
        t2.start();


        rank = new JTextArea();
        rank.setEditable(false);
        rank.setVisible(true);
        rank.setBounds(screenwidth-screenwidth/3,screenheight/3,screenwidth/4,screenheight/2);
        rank.setBorder(new LineBorder(Color.BLACK,1));
        rank.setText("Ranklist wird geladen...");

        reloadscore = new JButton();
        reloadscore.setVisible(true);
        reloadscore.setBounds(screenwidth/2+25,screenheight/2,screenwidth/8,screenheight/12);
        reloadscore.setText("Reload Scores");
        reloadscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadscores_special.reload();
                rank.setText("Ranklist wird geladen...");
            }
        });


        level = new JLabel();
        level.setVisible(true);
        level.setBounds(screenwidth/100, screenheight/2-20, screenwidth/3, screenheight/20);
        level.setFont(new Font("Calibri", Font.BOLD, screenwidth/40));
        level.setText("Level: wird geladen...");
        Thread t3 = new Thread(){
            public void run(){
                Client client = new Client();
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        level.setText("Level: "+client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--level"));
                    }else{
                        level.setText("Level: Bitte Melden sie sich an...");
                    }
                }else{
                    level.setText("Level: Veraltete Version oder Netzwerk Probleme");
                }
            }
        };
        t3.start();

        xp = new JProgressBar();
        xp.setVisible(true);
        xp.setBounds(screenwidth/100,screenheight/2,screenwidth/3,screenheight/12);
        xp.setMaximum(1000);
        xp.setStringPainted(true);
        xp.setForeground(Color.green);
        xp.setFont(new Font("Calibri", Font.BOLD, screenwidth/40));
        Thread t4 = new Thread(){
            public void run(){
                Client client = new Client();
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        String xpvalue = client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--xp");
                        xp.setString(xpvalue+"XP/1000XP");
                        xp.setValue(Integer.valueOf(xpvalue));
                    }else{
                        xp.setString("Bitte Melden sie sich an...");
                    }
                }else{
                    xp.setString("Veraltete Version oder Netzwerk Probleme");
                }
            }
        };
        t4.start();



        frame.add(headline);
        frame.add(play);
        frame.add(level);
        frame.add(xp);
        frame.add(back);
        frame.add(highscore);
        frame.add(rank);
        frame.add(reloadscore);
    }

}
