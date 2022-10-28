package Singelplayer.Singelplayer_Special.Game;

import java.util.ArrayList;

public class Snake_special {

    public static ArrayList<Körper_special> snakekörper = new ArrayList<Körper_special>();
    public static int snakeheight = 14;
    public static int snakewidth = 14;
    public static int x = 301;
    public static int y = 301;

    public Snake_special(){
        Snake_special.snakekörper.clear();
        snakekörper.add(new Körper_special(x,y));
        snakekörper.add(new Körper_special(x, y+snakewidth+1));
        snakekörper.add(new Körper_special(x, y+(2*snakewidth)+1));
        snakekörper.add(new Körper_special(x, y+(3*snakewidth)+1));
    }
}
