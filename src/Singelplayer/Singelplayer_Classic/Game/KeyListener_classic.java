package Singelplayer.Singelplayer_Classic.Game;

import java.awt.event.KeyEvent;

public class KeyListener_classic implements java.awt.event.KeyListener {

    public static boolean up = true;
    public static boolean left = false;
    public static boolean down = false;
    public static boolean right = false;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W||e.getKeyCode()==KeyEvent.VK_UP){
            if(!Movment_classic.down){
                down=false;
                left=false;
                up=true;
                right=false;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_LEFT){
            if(!Movment_classic.right){
                down=false;
                left=true;
                up=false;
                right=false;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_S||e.getKeyCode()==KeyEvent.VK_DOWN){
            if(!Movment_classic.up){
                down=true;
                left=false;
                up=false;
                right=false;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_D||e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(!Movment_classic.left){
                down=false;
                left=false;
                up=false;
                right=true;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            if(Movment_classic.paused){
                Movment_classic.paused=false;
                GUI_classic.paused.setVisible(false);
            }else{
                Movment_classic.paused=true;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_I){
            GUI_classic.reload();
        }else if(e.getKeyCode()==KeyEvent.VK_CONTROL){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
