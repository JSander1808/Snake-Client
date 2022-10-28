package MainGUI;

import Account.signup;
import Account.login;
import Client.Client;
import Config.Config;
import Security.Updatenews;
import Security.Verified;
import Security.loginData;
import Singelplayer.ModusChoos_Singelplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    public static String version = "1.05.5 Beta";

    public static boolean verified = false;
    public JFrame frame;
    public JLabel headline;
    public JButton singelplayer;
    public JButton multiplayer;
    public static JButton login;
    public static JButton signup;
    public static JButton logout;
    public JLabel versionlabel;

    int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height/2;

    public MainGUI(){

        Thread verified = new Thread(new Verified());
        verified.start();

        Config config = new Config("C://Snake Beta//Account//stats.txt");

        frame = new JFrame("Snake - Main Menu");
        frame.setVisible(true);
        frame.setSize(screenwidth, screenheight);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        if(config.get("version")==null){
            config.set("version",version);
            new Updatenews();
        }else if(!config.get("version").equalsIgnoreCase(version)){
            new Updatenews();
        }

        headline = new JLabel();
        headline.setText("Snake Beta");
        headline.setBounds(0,0,screenwidth, screenheight/2);
        headline.setVisible(true);
        headline.setFont(new Font("Calibri", Font.BOLD, screenwidth/5));

        signup = new JButton();
        signup.setText("Account erstellen");
        signup.setBounds(screenwidth-screenwidth/2,screenheight/3,screenwidth/5,screenheight/26);
        signup.setVisible(true);
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new signup();
            }
        });

        login = new JButton();
        login.setText("Anmelden");
        login.setBounds(screenwidth-screenwidth/2+screenwidth/5+5,screenheight/3,screenwidth/8,screenheight/26);
        login.setVisible(true);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
            }
        });

        logout = new JButton();
        logout.setText("Abmelden");
        logout.setBounds(screenwidth-screenwidth/2+screenwidth/5+5,screenheight/3,screenwidth/8,screenheight/26);
        logout.setVisible(false);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config c = new Config("C://Snake Beta//Account//stats.txt");
                c.set("Benutzername","");
                c.set("Password","");
                loginData.setName(null);
                loginData.setPassword(null);
                logout.setVisible(false);
                login.setVisible(true);
            }
        });
        if(config.existdata("Password")){
            loginData.setName(config.get("Benutzername"));
            loginData.setPassword(config.get("Password"));
            Client client = new Client();
            if(client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--level")==null){
                client.sendUDPToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--set--level--0");
            }
        }

        if(loginData.getPassword()==null){
            login.setVisible(true);
            logout.setVisible(false);
        }else{
            logout.setVisible(true);
            login.setVisible(false);
        }

        singelplayer = new JButton();
        singelplayer.setText("Singelplayer");
        singelplayer.setBounds(screenwidth/7,screenheight-screenheight/3,screenwidth/3,screenheight/12);
        singelplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ModusChoos_Singelplayer();
            }
        });

        multiplayer = new JButton();
        multiplayer.setText("Multiplayer");
        multiplayer.setBounds(screenwidth-screenwidth/2, screenheight-screenheight/3,screenwidth/3,screenheight/12);
        multiplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        versionlabel = new JLabel();
        versionlabel.setText("Version: "+version);
        versionlabel.setBounds(0,screenheight-screenheight/10,200,20);
        versionlabel.setVisible(true);
        versionlabel.setForeground(Color.gray);
        versionlabel.setFont(new Font("Calibri", Font.BOLD, screenwidth/60));

        frame.add(headline);
        frame.add(singelplayer);
        frame.add(multiplayer);
        frame.add(login);
        frame.add(signup);
        frame.add(logout);
        frame.add(versionlabel);
        SwingUtilities.updateComponentTreeUI(frame);
    }

}
