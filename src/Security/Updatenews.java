package Security;

import Config.Config;
import MainGUI.MainGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Updatenews {

    public JFrame frame;
    public JLabel headline;
    public JTextArea news;
    public JButton button;
    public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width/4;


    public Updatenews(){
        frame = new JFrame("Was ist neu?");
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        headline = new JLabel();
        headline.setVisible(true);
        headline.setText("Was ist neu?");
        headline.setBounds(10,10,300,50);
        headline.setFont(new Font("Calibri", Font.BOLD, 45));

        news = new JTextArea();
        news.setVisible(true);
        news.setBounds(10,70,WIDTH-40,HEIGHT-160);
        news.setText("---Version 1.05.5---\n-Beta Level-System hizugefügt\n-Was ist neu Nahricht hizugefügt\n-Pfeiltasten zum steuern der Schlange möglich\n-Probelm mit Boxen behoben");
        news.setEditable(false);
        news.setBorder(new LineBorder(Color.BLACK,1));
        news.setFont(new Font("Calibri", Font.BOLD, 20));

        button = new JButton();
        button.setBounds(10,WIDTH-20,WIDTH-40,30);
        button.setText("OK - nicht mehr anzeigen");
        button.setVisible(true);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Config config = new Config("C://Snake Beta//Account//stats.txt");
                config.set("version", MainGUI.version);
            }
        });

        frame.add(headline);
        frame.add(news);
        frame.add(button);
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
