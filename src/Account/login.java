package Account;

import Client.Client;
import Config.Config;
import MainGUI.MainGUI;
import Security.loginData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {

    JFrame frame;
    JLabel namelabel;
    JLabel passwordlabel;
    JTextField name;
    JPasswordField password;
    JButton submit;
    JLabel error;
    JLabel erroremty;
    JCheckBox savelogin;
    JLabel saveloginlabel;
    JLabel errorversion;
    public login(){
        frame = new JFrame("Anmelden");
        frame.setSize(300,250);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        name = new JTextField();
        name.setVisible(true);
        name.setBounds(75,30,125,20);
        name.setBorder(new LineBorder(Color.BLACK,1));

        password = new JPasswordField();
        password.setVisible(true);
        password.setBounds(75,80,125,20);
        password.setBorder(new LineBorder(Color.BLACK,1));

        savelogin = new JCheckBox();
        savelogin.setBounds(75, 110,20,20);
        savelogin.setVisible(true);

        saveloginlabel = new JLabel();
        saveloginlabel.setText("Angemeldet bleiben");
        saveloginlabel.setBounds(100, 110, 150,20);
        saveloginlabel.setVisible(true);

        namelabel = new JLabel();
        namelabel.setText("Benutzername");
        namelabel.setBounds(75, 15, 100, 15);

        passwordlabel = new JLabel();
        passwordlabel.setText("Passwort");
        passwordlabel.setBounds(75, 65, 100, 15);

        error = new JLabel();
        error.setText("Password oder Benutzername falsch");
        error.setForeground(Color.red);
        error.setVisible(false);
        error.setBounds(0, 125, 300, 30);

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
        submit.setText("Anmelden");
        submit.setBounds(50, 150, 175, 30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t1 = new Thread(){
                    public void run(){
                        Client c = new Client();
                        if(MainGUI.verified){
                            if(!(name.getText().equalsIgnoreCase(""))&&!(password.getText().equalsIgnoreCase(""))){
                                if(c.sendToServer("acc--"+name.getText()+"--log--"+password.getText()).equalsIgnoreCase("false")){
                                    error.setVisible(true);
                                    erroremty.setVisible(false);
                                    errorversion.setVisible(false);
                                    name.setText("");
                                    password.setText("");
                                }else{
                                    Config config = new Config("C://Snake Beta//Account//stats.txt");
                                    config.init();
                                    if(savelogin.isSelected()){
                                        config.set("Benutzername",name.getText());
                                        config.set("Password",password.getText());
                                        loginData.setName(name.getText());
                                        loginData.setPassword(password.getText());
                                    }else{
                                        loginData.setName(name.getText());
                                        loginData.setPassword(password.getText());
                                    }
                                    frame.dispose();
                                    MainGUI.login.setVisible(false);
                                    MainGUI.logout.setVisible(true);
                                    Client client = new Client();
                                    if(client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--level")==null){
                                        client.sendUDPToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--set--level--0");
                                        client.sendUDPToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--set--xp--0");
                                        System.out.println("debug");
                                    }
                                }
                            }else{
                                erroremty.setVisible(true);
                                error.setVisible(false);
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
        frame.add(savelogin);
        frame.add(saveloginlabel);
        frame.add(errorversion);
    }
}
