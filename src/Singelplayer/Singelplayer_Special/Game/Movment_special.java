package Singelplayer.Singelplayer_Special.Game;


import Client.Client;
import Security.loginData;
import Singelplayer.Singelplayer_Classic.Game.GUI_classic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Movment_special {

    public static boolean up = true;
    public static boolean left = false;
    public static boolean down = false;
    public static boolean right = false;
    public static boolean paused = false;
    public static boolean dead = false;
    public static boolean go = false;
    public static int pausedcounter = 0;
    public int temp = 0;
    public static Timer timer;
    public static ExecutorService executor = Executors.newFixedThreadPool(30);

    public Movment_special(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    if(!dead){
                        if(!paused){
                            if(go){
                                up= KeyListener_special.up;
                                left= KeyListener_special.left;
                                down= KeyListener_special.down;
                                right= KeyListener_special.right;
                                for(int i = Snake_special.snakekörper.size()-1; i>0; i--){
                                    Snake_special.snakekörper.get(i).setX(Snake_special.snakekörper.get(i-1).getX());
                                    Snake_special.snakekörper.get(i).setY(Snake_special.snakekörper.get(i-1).getY());
                                }
                                if(up){
                                    Snake_special.snakekörper.get(0).setX(Snake_special.snakekörper.get(0).getX());
                                    Snake_special.snakekörper.get(0).setY(Snake_special.snakekörper.get(0).getY()-Snake_special.snakeheight-1);
                                }
                                if(left){
                                    Snake_special.snakekörper.get(0).setX(Snake_special.snakekörper.get(0).getX()-Snake_special.snakewidth-1);
                                    Snake_special.snakekörper.get(0).setY(Snake_special.snakekörper.get(0).getY());
                                }
                                if(down){
                                    Snake_special.snakekörper.get(0).setX(Snake_special.snakekörper.get(0).getX());
                                    Snake_special.snakekörper.get(0).setY(Snake_special.snakekörper.get(0).getY()+Snake_special.snakeheight+1);
                                }
                                if(right){
                                    Snake_special.snakekörper.get(0).setX(Snake_special.snakekörper.get(0).getX()+Snake_special.snakewidth+1);
                                    Snake_special.snakekörper.get(0).setY(Snake_special.snakekörper.get(0).getY());
                                }
                                go=false;
                            }

                            if(GUI_special.speedmulti==0.5){
                                if(temp==16){
                                    go=true;
                                    temp=0;
                                }
                                temp++;
                            }

                            if(GUI_special.speedmulti<=0.0){
                                if(temp>=8){
                                    go=true;
                                    temp=0;
                                }
                                temp++;
                            }
                            if(GUI_special.speedmulti==2.0){
                                if(temp>=6){
                                    go=true;
                                    temp=0;
                                }
                                temp++;
                            }
                            if(GUI_special.speedmulti==4.0){
                                if(temp>=4){
                                    go=true;
                                    temp=0;
                                }
                                temp++;
                            }
                            if(GUI_special.speedmulti==6.0){
                                if(temp>=2){
                                    go=true;
                                    temp=0;
                                }
                                temp++;
                            }
                            if(GUI_special.speedmulti>=8.0){
                                go=true;
                            }

                            GUI_special.speedmultilabel.setText("Speed Multiplikator: "+GUI_special.speedmulti+"x");
                            GUI_special.scoremultilabel.setText("Score Multiplikator: "+GUI_special.scoremulti+"x");
                            if(GUI_special.acrossnake){
                                GUI_special.acrossnakelabel.setText("Ghostmode");
                            }else{
                                GUI_special.acrossnakelabel.setText("");
                            }



                            if(Snake_special.snakekörper.get(0).getX()<=-1){
                                dead=true;
                                GUI_special.paused.setVisible(false);
                                GUI_special.dead.setVisible(true);
                            }
                            if(Snake_special.snakekörper.get(0).getY()<=-1){
                                dead=true;
                                GUI_special.paused.setVisible(false);
                                GUI_special.dead.setVisible(true);
                            }
                            if(Snake_special.snakekörper.get(0).getX()>=575){
                                dead=true;
                                GUI_special.paused.setVisible(false);
                                GUI_special.dead.setVisible(true);
                            }
                            if(Snake_special.snakekörper.get(0).getY()>=553){
                                dead=true;
                                GUI_special.paused.setVisible(false);
                                GUI_special.dead.setVisible(true);
                            }


                            for(int i = 0; i< Drops_special.drop.size(); i++){
                                if(Drops_special.drop.get(i).getX()== Snake_special.snakekörper.get(0).getX()&& Drops_special.drop.get(i).getY()== Snake_special.snakekörper.get(0).getY()){
                                    GUI_special.SCORE+=(5000*GUI_special.scoremulti);
                                    Drops_special.drop.remove(i);
                                    Drops_special.generate();
                                    Snake_special.snakekörper.add(new Körper_special(Snake_special.snakekörper.get(1).getX(), Snake_special.snakekörper.get(1).getY()));
                                    executor.execute(new RandomDropEffekt());
                                    GUI_special.xp+=5;
                                    if(GUI_special.xp>=1000){
                                        GUI_special.xp-=1000;
                                        GUI_special.level++;
                                    }
                                    GUI_special.reloadlevel();
                                }
                            }

                            if(!GUI_special.acrossnake){
                                for(int i = 1; i< Snake_special.snakekörper.size(); i++){
                                    if(Snake_special.snakekörper.get(0).getX()== Snake_special.snakekörper.get(i).getX()&& Snake_special.snakekörper.get(0).getY()== Snake_special.snakekörper.get(i).getY()){
                                        dead=true;
                                        GUI_special.paused.setVisible(false);
                                        GUI_special.dead.setVisible(true);
                                        Thread t2 = new Thread(){
                                            public void run(){
                                                Client client = new Client();
                                                int highscore = Integer.valueOf(client.sendToServer("special--"+ loginData.getName()+"--"+loginData.getPassword()+"--get--specialhighscore"));
                                                if(highscore <= GUI_special.SCORE){
                                                    GUI_special.xp+=750;
                                                    if(GUI_special.xp>=1000){
                                                        GUI_special.xp-=1000;
                                                        GUI_special.level++;
                                                    }
                                                }
                                                client.sendUDPToServer("level--"+loginData.getName()+"--"+loginData.getPassword()+"--set--"+GUI_special.level+"--"+GUI_special.xp);
                                            }
                                        };
                                        t2.start();
                                    }
                                }
                            }


                            GUI_special.score.setText("Score: "+ GUI_special.SCORE);
                        }else{
                            if(pausedcounter>=8){
                                if(GUI_special.paused.isVisible()){
                                    GUI_special.paused.setVisible(false);
                                }else{
                                    GUI_special.paused.setVisible(true);
                                }
                                pausedcounter=0;
                            }else{
                                pausedcounter++;
                            }
                        }
                    }
            }
        },0, GUI_special.movmentspeed);
    }
}
