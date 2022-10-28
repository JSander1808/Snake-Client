package Singelplayer.Singelplayer_Classic.Game;

import javax.swing.*;
import java.awt.*;

public class Draw_classic extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<600;i+=15){
            g2d.fillRect(i,0,1,600);
            g2d.fillRect(0,i,600,1);
        }
        for(int i = 0; i< Drops_classic.drop.size(); i++){
            g2d.setColor(Color.green);
            g2d.fillRect(Drops_classic.drop.get(i).getX(), Drops_classic.drop.get(i).getY(), Drops_classic.dropwidth, Drops_classic.dropheight);
        }
        g2d.setColor(Color.red);
        for(int i = 0; i< Snake_classic.snakekörper.size(); i++){
            g2d.fillRect(Snake_classic.snakekörper.get(i).getX(), Snake_classic.snakekörper.get(i).getY(), Snake_classic.snakewidth, Snake_classic.snakeheight);
            g2d.setColor(Color.black);
        }




        repaint();
    }
}
