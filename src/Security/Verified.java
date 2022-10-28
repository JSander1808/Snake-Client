package Security;

import Client.Client;
import Config.Config;
import MainGUI.MainGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;

public class Verified implements Runnable{

    public Verified(){
    }

    @Override
    public void run() {
        Client client = new Client();
        try {
            if(InetAddress.getByName("google.de").isReachable(1000)){
                String serverversion = client.sendToServer("ver");
                if(serverversion.equalsIgnoreCase(MainGUI.version)){
                    MainGUI.verified=true;
                }else{
                    MainGUI.verified=false;
                    JFrame frame = new JFrame();
                    frame.setSize(300,250);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setLayout(null);
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    JLabel message = new JLabel();
                    message.setText("Neue Version verfügbar");
                    message.setBounds(50,10,150,30);
                    message.setFont(new Font("Calibri", Font.BOLD, 15));
                    message.setVisible(true);

                    JLabel upgrade = new JLabel();
                    upgrade.setText(MainGUI.version+" ---> "+serverversion);
                    upgrade.setVisible(true);
                    upgrade.setBounds(50,60,175,30);
                    upgrade.setFont(new Font("Calibri", Font.BOLD, 15));

                    JButton download = new JButton();
                    download.setText("Hier Downloaden");
                    download.setBounds(50, 130,150,30);
                    download.setVisible(true);
                    download.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Desktop.getDesktop().browse(new URL("https://snakebeta.netlify.app").toURI());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (URISyntaxException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    frame.add(message);
                    frame.add(download);
                    frame.add(upgrade);
                }
            }else{
                MainGUI.verified=false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
