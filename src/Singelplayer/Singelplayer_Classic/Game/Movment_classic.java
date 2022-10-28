package Singelplayer.Singelplayer_Classic.Game;


import Client.Client;
import MainGUI.MainGUI;
import Security.loginData;

import java.util.Timer;
import java.util.TimerTask;

public class Movment_classic {

    public static boolean up = true;
    public static boolean left = false;
    public static boolean down = false;
    public static boolean right = false;
    public static boolean paused = false;
    public static boolean dead = false;
    public static Timer timer;

    public Movment_classic(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!dead){
                    if(!paused){

                        up= KeyListener_classic.up;
                        left= KeyListener_classic.left;
                        down= KeyListener_classic.down;
                        right= KeyListener_classic.right;
                        for(int i = Snake_classic.snakekörper.size()-1; i>0; i--){
                            Snake_classic.snakekörper.get(i).setX(Snake_classic.snakekörper.get(i-1).getX());
                            Snake_classic.snakekörper.get(i).setY(Snake_classic.snakekörper.get(i-1).getY());
                        }
                        if(up){
                            Snake_classic.snakekörper.get(0).setX(Snake_classic.snakekörper.get(0).getX());
                            Snake_classic.snakekörper.get(0).setY(Snake_classic.snakekörper.get(0).getY()- Snake_classic.snakeheight-1);
                        }
                        if(left){
                            Snake_classic.snakekörper.get(0).setX(Snake_classic.snakekörper.get(0).getX()- Snake_classic.snakewidth-1);
                            Snake_classic.snakekörper.get(0).setY(Snake_classic.snakekörper.get(0).getY());
                        }
                        if(down){
                            Snake_classic.snakekörper.get(0).setX(Snake_classic.snakekörper.get(0).getX());
                            Snake_classic.snakekörper.get(0).setY(Snake_classic.snakekörper.get(0).getY()+ Snake_classic.snakeheight+1);
                        }
                        if(right){
                            Snake_classic.snakekörper.get(0).setX(Snake_classic.snakekörper.get(0).getX()+ Snake_classic.snakewidth+1);
                            Snake_classic.snakekörper.get(0).setY(Snake_classic.snakekörper.get(0).getY());
                        }

                        if(Snake_classic.snakekörper.get(0).getX()<=-1){
                            dead=true;
                            GUI_classic.paused.setVisible(false);
                            GUI_classic.dead.setVisible(true);
                        }
                        if(Snake_classic.snakekörper.get(0).getY()<=-1){
                            dead=true;
                            GUI_classic.paused.setVisible(false);
                            GUI_classic.dead.setVisible(true);
                        }
                        if(Snake_classic.snakekörper.get(0).getX()>=575){
                            dead=true;
                            GUI_classic.paused.setVisible(false);
                            GUI_classic.dead.setVisible(true);
                        }
                        if(Snake_classic.snakekörper.get(0).getY()>=553){
                            dead=true;
                            GUI_classic.paused.setVisible(false);
                            GUI_classic.dead.setVisible(true);
                        }


                        for(int i = 0; i< Drops_classic.drop.size(); i++){
                                if(Drops_classic.drop.get(i).getX()== Snake_classic.snakekörper.get(0).getX()&& Drops_classic.drop.get(i).getY()== Snake_classic.snakekörper.get(0).getY()){
                                    GUI_classic.SCORE+=5000;
                                    Drops_classic.drop.remove(i);
                                    Drops_classic.generate();
                                    Snake_classic.snakekörper.add(new Körper_classic(Snake_classic.snakekörper.get(1).getX(), Snake_classic.snakekörper.get(1).getY()));
                                    GUI_classic.xp+=5;
                                    if(GUI_classic.xp>=1000){
                                        GUI_classic.xp-=1000;
                                        GUI_classic.level++;
                                    }
                                    GUI_classic.reloadlevel();
                            }
                        }

                        for(int i = 1; i< Snake_classic.snakekörper.size(); i++){
                            if(Snake_classic.snakekörper.get(0).getX()== Snake_classic.snakekörper.get(i).getX()&& Snake_classic.snakekörper.get(0).getY()== Snake_classic.snakekörper.get(i).getY()){
                                dead=true;
                                GUI_classic.paused.setVisible(false);
                                GUI_classic.dead.setVisible(true);
                                Thread t2 = new Thread(){
                                    public void run(){
                                        Client client = new Client();
                                        int highscore = Integer.valueOf(client.sendToServer("classic--"+loginData.getName()+"--"+loginData.getPassword()+"--get--classichighscore"));
                                        if(highscore <= GUI_classic.SCORE){
                                            GUI_classic.xp+=750;
                                            if(GUI_classic.xp>=1000){
                                                GUI_classic.xp-=1000;
                                                GUI_classic.level++;
                                            }
                                        }
                                        client.sendUDPToServer("level--"+loginData.getName()+"--"+loginData.getPassword()+"--set--"+GUI_classic.level+"--"+GUI_classic.xp);
                                    }
                                };
                                t2.start();
                            }
                        }


                        GUI_classic.score.setText("Score: "+ GUI_classic.SCORE);
                        GUI_classic.SCORE+=20;
                    }else{
                        if(GUI_classic.paused.isVisible()){
                            GUI_classic.paused.setVisible(false);
                        }else{
                            GUI_classic.paused.setVisible(true);
                        }
                    }
                }
            }
        },0, GUI_classic.movmentspeed);
    }
}
