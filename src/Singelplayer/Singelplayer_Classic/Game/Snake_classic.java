package Singelplayer.Singelplayer_Classic.Game;

import java.util.ArrayList;

public class Snake_classic {

    public static ArrayList<Körper_classic> snakekörper = new ArrayList<Körper_classic>();
    public static int snakeheight = 14;
    public static int snakewidth = 14;
    public static int x = 301;
    public static int y = 301;

    public Snake_classic(){
        Snake_classic.snakekörper.clear();
        snakekörper.add(new Körper_classic(x,y));
        snakekörper.add(new Körper_classic(x, y+snakewidth+1));
        snakekörper.add(new Körper_classic(x, y+(2*snakewidth)+1));
        snakekörper.add(new Körper_classic(x, y+(3*snakewidth)+1));
    }
}
