package Singelplayer.Singelplayer_Special.Game;

import java.util.ArrayList;

public class Drops_special {

    public static int dropwidth = 14;
    public static int dropheight = 14;
    public static int x;
    public static int y;
    public static boolean noonsnake = false;
    public static ArrayList<Drop_special> drop = new ArrayList<Drop_special>();

    public Drops_special(){
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
            for(int i = 0; i< Snake_special.snakekörper.size(); i++){
                if(!noonsnake){
                    if(!(Snake_special.snakekörper.get(i).getX()==x)&&!(Snake_special.snakekörper.get(i).getY()==y)){
                        noonsnake=true;
                    }
                }
            }
        }
        drop.add(new Drop_special(x,y));
    }
}
