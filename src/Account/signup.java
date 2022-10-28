package Account;

import Client.Client;
import Config.Config;
import MainGUI.MainGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class signup {

    JFrame frame;
    JLabel namelabel;
    JLabel passwordlabel;
    JTextField name;
    JPasswordField password;
    JButton submit;
    JLabel error;
    JLabel erroremty;
    JLabel errorversion;

    public signup(){
        frame = new JFrame("Account erstellen");
        frame.setSize(300,250);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        name = new JTextField();
        name.setVisible(true);
        name.setBounds(75,50,125,20);
        name.setBorder(new LineBorder(Color.BLACK,1));

        password = new JPasswordField();
        password.setVisible(true);
        password.setBounds(75,100,125,20);
        password.setBorder(new LineBorder(Color.BLACK,1));

        namelabel = new JLabel();
        namelabel.setText("Benutzername");
        namelabel.setBounds(75, 35, 100, 15);

        passwordlabel = new JLabel();
        passwordlabel.setText("Passwort");
        passwordlabel.setBounds(75, 85, 100, 15);

        error = new JLabel();
        error.setText("Account bereits vorhanden...");
        error.setForeground(Color.red);
        error.setVisible(false);
        error.setBounds(50, 125, 175, 30);

        erroremty = new JLabel();
        erroremty.setText("Bitte füllen sie die Felder aus...");
        erroremty.setForeground(Color.red);
        erroremty.setVisible(false);
        erroremty.setBounds(50, 125, 175, 30);

        errorversion = new JLabel();
        errorversion.setText("Veraltete Version oder Netzwerk problem");
        errorversion.setForeground(Color.red);
        errorversion.setVisible(false);
        errorversion.setBounds(25, 125, 250, 30);

        submit = new JButton();
        submit.setText("Account erstellen");
        submit.setBounds(50, 150, 175, 30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t1 = new Thread(){
                    public void run(){
                        Client client = new Client();
                        if(MainGUI.verified){
                            if(client.ping()==true){
                                if(!(name.getText().equalsIgnoreCase(""))&&!(password.getText().equalsIgnoreCase(""))){
                                    Client c = new Client();
                                    if(c.sendToServer("acc--"+name.getText()+"--sign--"+password.getText()).equalsIgnoreCase("false")){
                                        error.setVisible(true);
                                        erroremty.setVisible(false);
                                        errorversion.setVisible(false);
                                        name.setText("");
                                        password.setText("");
                                    }else{
                                        Config config = new Config("C://Snake Beta//Account//stats.txt");
                                        config.init();
                                        c.sendUDPToServer("classic--"+name.getText()+"--"+password.getText()+"--set--classichighscore--0");
                                        c.sendUDPToServer("special--"+name.getText()+"--"+password.getText()+"--set--specialhighscore--0");
                                        c.sendUDPToServer("classic--"+name.getText()+"--"+password.getText()+"--set--level--0");
                                        c.sendUDPToServer("classic--"+name.getText()+"--"+password.getText()+"--set--xp--0");
                                        frame.dispose();
                                    }
                                }else{
                                    erroremty.setVisible(true);
                                    error.setVisible(false);
                                    errorversion.setVisible(false);
                                }
                            }else{
                                error.setVisible(false);
                                erroremty.setVisible(false);
                                errorversion.setVisible(false);
                            }
                        }else{
                            errorversion.setVisible(true);
                            error.setVisible(false);
                            erroremty.setVisible(false);
                        }
                    }
                };
                t1.start();
            }
        });


        frame.add(password);
        frame.add(name);
        frame.add(namelabel);
        frame.add(passwordlabel);
        frame.add(submit);
        frame.add(error);
        frame.add(erroremty);
        frame.add(errorversion);
    }

}
