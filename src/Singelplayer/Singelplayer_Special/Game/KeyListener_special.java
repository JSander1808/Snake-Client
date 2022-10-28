package Singelplayer.Singelplayer_Special.Game;

import java.awt.event.KeyEvent;

public class KeyListener_special implements java.awt.event.KeyListener {

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
            if(!Movment_special.down){
                down=false;
                left=false;
                up=true;
                right=false;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_A||e.getKeyCode()==KeyEvent.VK_LEFT){
            if(!Movment_special.right){
                down=false;
                left=true;
                up=false;
                right=false;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_S||e.getKeyCode()==KeyEvent.VK_DOWN){
            if(!Movment_special.up){
                down=true;
                left=false;
                up=false;
                right=false;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_D||e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(!Movment_special.left){
                down=false;
                left=false;
                up=false;
                right=true;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            if(Movment_special.paused){
                Movment_special.paused=false;
                GUI_special.paused.setVisible(false);
            }else{
                Movment_special.paused=true;
            }
        }else if(e.getKeyCode()==KeyEvent.VK_I){
            GUI_special.reload();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
