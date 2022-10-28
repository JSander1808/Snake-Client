package Singelplayer.Singelplayer_Special.Game;

import java.util.Timer;
import java.util.TimerTask;

public class RandomDropEffekt implements Runnable{

    private int temp;

    public RandomDropEffekt(){
    }

    @Override
    public void run() {
        int x = (int) Math.floor(Math.random()*(8-1)+1);
        System.out.println(x);
        if(x==1){
            if(GUI_special.speedmulti!=8&&!(GUI_special.speedmulti==0.5)){
                GUI_special.speedmulti+=2;
               Timer timer = new Timer();
               timer.schedule(new TimerTask() {
                   @Override
                   public void run() {
                       if(!Movment_special.paused){
                           temp++;
                       }
                       if(temp==30){
                           if(!(GUI_special.speedmulti<=0)){
                               GUI_special.speedmulti-=2;
                               temp = 0;
                               timer.cancel();
                           }
                       }
                   }
               },0,1000);
            }
        }
        if(x==2){
            new Drops_special();
        }
        if(x==3){
            GUI_special.scoremulti=0.5;
        }
        if(x==4){
            GUI_special.scoremulti=2;
        }
        if(x==5){
            GUI_special.scoremulti=4;
        }
        if(x==6){
            GUI_special.scoremulti=1;
        }
        if(x==7){
            temp = 15;
            GUI_special.acrossnake=true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(!Movment_special.paused){
                        temp--;
                    }
                    if(temp==0){
                        GUI_special.acrossnake=false;
                        temp = 15;
                        timer.cancel();
                    }
                }
            },0,1000);
        }
    }
}
