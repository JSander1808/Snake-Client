package Singelplayer.Singelplayer_Special.Game;

import javax.swing.*;
import java.awt.*;

public class Draw_special extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0;i<600;i+=15){
            g2d.fillRect(i,0,1,600);
            g2d.fillRect(0,i,600,1);
        }
        for(int i = 0; i< Drops_special.drop.size(); i++){
            g2d.setColor(Color.green);
            g2d.fillRect(Drops_special.drop.get(i).getX(), Drops_special.drop.get(i).getY(), Drops_special.dropwidth, Drops_special.dropheight);
        }
        g2d.setColor(Color.red);
        for(int i = 0; i< Snake_special.snakekörper.size(); i++){
            g2d.fillRect(Snake_special.snakekörper.get(i).getX(), Snake_special.snakekörper.get(i).getY(), Snake_special.snakewidth, Snake_special.snakeheight);
            g2d.setColor(Color.black);
        }




        repaint();
    }
}
