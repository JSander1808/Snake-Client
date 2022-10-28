package Singelplayer.Singelplayer_Classic.Game;

import Client.Client;
import MainGUI.MainGUI;
import Security.loginData;
import Singelplayer.Singelplayer_Classic.Singelplayer_Classic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Singelplayer.Singelplayer_Classic.reloadscores_classic;
import com.sun.jdi.IntegerType;

public class GUI_classic {

    JFrame frame;
    Draw_classic draw;
    static JLabel score;
    static JLabel highscore;
    JLabel headline;
    static JLabel dead;
    JButton restart;
    static JLabel paused;
    JButton back;
    static JLabel levellabel;
    static JProgressBar xpbar;
    public static int xp = 0;
    public static int level = 0;
    JTextArea tutorial;
    public static final int SLOW = 650;
    public static final int MIDDLE = 500;
    public static final int FAST =280;
    public static int movmentspeed = FAST;
    static int SCORE=0;
    int screenwidth = 1000;
    int screenheight = 595;

    public GUI_classic(){
        Thread thread = new Thread(){
            public void run(){
                Client client = new Client();
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        String[] levelxp = client.sendToServer("level--"+loginData.getName()+"--"+loginData.getPassword()+"--get").split("--");
                        level = Integer.valueOf(levelxp[0]);
                        xp = Integer.valueOf(levelxp[1]);

                    }
                }
                reloadlevel();
            }
        };
        thread.start();
        frame = new JFrame();
        frame.setSize(screenwidth,screenheight);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        headline = new JLabel();
        headline.setText("Snake Beta");
        headline.setBounds(10,0,380,100);
        headline.setVisible(true);
        headline.setFont(new Font("Calibri", Font.BOLD, 80));

        score = new JLabel();
        score.setText("Score: ");
        score.setVisible(true);
        score.setBounds(10,80,380,50);
        score.setFont(new Font("Calibri", Font.BOLD, 35));

        highscore = new JLabel();
        highscore.setBounds(10,110,380,50);
        highscore.setFont(new Font("Calibri", Font.BOLD, 20));
        highscore.setVisible(true);
        highscore.setText("Highscore: Wird geladen...");
        Client client = new Client();
        Thread t1 = new Thread(){
            public void run(){
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        if(client.ping()){
                            highscore.setText("Highscore: "+client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--classichighscore"));
                        }else{
                            highscore.setText("Highscore: Netzwerk verbindung überprüfen...");
                        }
                    }else{
                        highscore.setText("Highscore: Bitte Melden sie sich an...");
                    }
                }else{
                    highscore.setText("Highscore: Veraltete Version oder Netzwerk probleme...");
                }
            }
        };
        t1.start();

        draw = new Draw_classic();
        draw.setVisible(true);
        draw.setBounds(400,0,600,600);

        dead = new JLabel();
        dead.setText("Du bist gestorben");
        dead.setForeground(Color.red);
        dead.setBounds(10,screenheight/2,380,50);
        dead.setFont(new Font("Calibri", Font.BOLD, 40));
        dead.setVisible(false);

        paused = new JLabel();
        paused.setText("Spiel Pausiert");
        paused.setForeground(Color.red);
        paused.setBounds(10,screenheight/2,380,50);
        paused.setFont(new Font("Calibri", Font.BOLD, 40));
        paused.setVisible(false);

        back = new JButton();
        back.setText("Back");
        back.setBounds(300,510, 100,50);
        back.setVisible(true);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                Movment_classic.timer.cancel();
                reload();
                reloadscores_classic.reload();
                frame.dispose();
                new Singelplayer_Classic();
            }
        });

        tutorial = new JTextArea();
        tutorial.setText("[i] :Spielfeld zurücksetzen.\n[Esc] :Spiel Pausieren.\n[W,A,S,D] :Snake bewegen.");
        tutorial.setBounds(10,screenheight-screenheight/3,380,100);
        tutorial.setVisible(true);
        tutorial.setEditable(false);
        tutorial.setFont(new Font("Calibri", Font.BOLD, 25));
        tutorial.setFocusable(false);


        levellabel = new JLabel();
        levellabel.setBounds(10,145,380,50);
        levellabel.setText("Level: wird geladen...");
        levellabel.setVisible(true);
        levellabel.setFont(new Font("Calibri", Font.BOLD, 25));

        xpbar = new JProgressBar();
        xpbar.setBounds(10, 180,380, 20);
        xpbar.setVisible(true);
        xpbar.setMaximum(1000);
        xpbar.setStringPainted(true);
        xpbar.setString("wird geladen");
        xpbar.setForeground(Color.green);
        reloadlevel();



        frame.addKeyListener(new KeyListener_classic());
        frame.add(draw);
        frame.add(score);
        frame.add(levellabel);
        frame.add(xpbar);
        frame.add(highscore);
        frame.add(tutorial);
        frame.add(headline);
        frame.add(dead);
        frame.add(paused);
        frame.add(back);
    }

    public static void reloadlevel(){
        Thread thread = new Thread(){
            public void run(){
                Client client = new Client();
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        levellabel.setText("Level: "+level);
                    }else{
                        levellabel.setText("Level: Bitte Melden sie sich an...");
                    }
                }else{
                    levellabel.setText("Level: Veraltete Version oder Netzwerk Probleme");
                }
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        xpbar.setString(xp+"XP/1000XP");
                        xpbar.setValue(Integer.valueOf(xp));
                    }else{
                        xpbar.setString("Bitte Melden sie sich an...");
                    }
                }else{
                    xpbar.setString("Veraltete Version oder Netzwerk Probleme");
                }
            }

        };
        thread.start();
    }
    public static void reload(){
        Client client = new Client();
        Thread t2 = new Thread(){
            public void run(){
                int highscoreint = Integer.valueOf(client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--classichighscore"));
                if(highscoreint <= SCORE){
                    xp+=750;
                    if(xp>=1000){
                        xp-=1000;
                        level++;
                    }
                }
                client.sendUDPToServer("level--"+loginData.getName()+"--"+loginData.getPassword()+"--set--"+level+"--"+xp);
                reloadlevel();
            }
        };
        t2.start();
        Movment_classic.left=false;
        Movment_classic.up=true;
        Movment_classic.down=false;
        Movment_classic.right=false;
        Movment_classic.paused=false;
        KeyListener_classic.up=true;
        KeyListener_classic.left=false;
        KeyListener_classic.right=false;
        KeyListener_classic.down=false;
        new Snake_classic();
        Movment_classic.dead=false;
        dead.setVisible(false);
        Thread t1 = new Thread() {
            public void run(){
                if(MainGUI.verified){
                    if(loginData.getPassword()!=null){
                        int highscoreint = Integer.valueOf(client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--classichighscore"));
                        System.out.println(highscoreint);
                        if(highscoreint <= SCORE){
                            xp+=750;
                            if(GUI_classic.xp>=1000){
                                GUI_classic.xp-=1000;
                                GUI_classic.level++;
                            }
                        }
                        client.sendUDPToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--checkhighscore--"+SCORE);
                        SCORE=0;
                        new Drops_classic();
                        if(MainGUI.verified){
                            if(loginData.getPassword()!=null){
                                if(client.ping()){
                                    highscore.setText("Highscore: "+client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--classichighscore"));
                                }else{
                                    highscore.setText("Highscore: Netzwerk verbindung überprüfen...");
                                }
                            }else{
                                highscore.setText("Highscore: Bitte Melden sie sich an...");
                            }
                        }else{
                            highscore.setText("Highscore: Veraltete Version oder Netzwerk probleme...");
                        }
                    }
                }
            }
        };
        t1.start();
    }
}
