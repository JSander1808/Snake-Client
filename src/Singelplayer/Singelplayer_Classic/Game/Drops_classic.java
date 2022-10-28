package Singelplayer.Singelplayer_Classic.Game;

import java.util.ArrayList;

public class Drops_classic {

    public static int dropwidth = 14;
    public static int dropheight = 14;
    public static int x;
    public static int y;
    public static boolean noonsnake = false;
    public static ArrayList<Drop_classic> drop = new ArrayList<Drop_classic>();

    public Drops_classic(){
        drop.clear();
        for(int i = 0;i<10;i++){
            generate();
        }
    }

    public static void generate(){
        noonsnake=false;
        while(!noonsnake){
            x = (int) Math.floor(Math.random()*(37-1)+1)*15+1;
            y = (int) Math.floor(Math.random()*(37-1)+1)*15+1;
            for(int i = 0; i< Snake_classic.snakekörper.size(); i++){
                if(!noonsnake){
                    if(!(Snake_classic.snakekörper.get(i).getX()==x)&&!(Snake_classic.snakekörper.get(i).getY()==y)){
                        noonsnake=true;
                    }
                }
            }
        }
        drop.add(new Drop_classic(x,y));
    }
}
