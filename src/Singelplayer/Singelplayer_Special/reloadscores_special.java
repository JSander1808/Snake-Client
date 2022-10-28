package Singelplayer.Singelplayer_Special;

import Client.Client;
import MainGUI.MainGUI;

import java.util.ArrayList;

public class reloadscores_special {

    public static void reload(){
        Thread t1 = new Thread(){
            public void run(){
                Client client = new Client();
                if(MainGUI.verified){
                    if(client.ping()){
                        ArrayList<String> data;
                        data = client.receive("specialscores");
                        StringBuilder message = new StringBuilder();
                        for(int i = 0;i<data.size();i++){
                            String[] temp = data.get(i).split("--");
                            message.append(temp[1]+": "+temp[0]+"\n");
                        }
                        Singelplayer_special.rank.setText(message.toString());
                    }else{
                        Singelplayer_special.rank.setText("Netzwerk Probleme...");
                    }
                }else{
                    Singelplayer_special.rank.setText("Veraltete Version oder Netzwerk Probleme...");
                }
            }
        };
        t1.start();
    }
}
