package Singelplayer.Singelplayer_Classic;

import Client.Client;
import MainGUI.MainGUI;

import java.util.ArrayList;

public class reloadscores_classic {

    public static void reload(){
        Thread t1 = new Thread(){
            public void run(){
                Client client = new Client();
                if(MainGUI.verified){
                    if(client.ping()){
                        ArrayList<String> data;
                        data = client.receive("classicscores");
                        StringBuilder message = new StringBuilder();
                        for(int i = 0;i<data.size();i++){
                            String[] temp = data.get(i).split("--");
                            message.append(temp[1]+": "+temp[0]+"\n");
                        }
                        Singelplayer_Classic.rank.setText(message.toString());
                    }else{
                        Singelplayer_Classic.rank.setText("Netzwerk Probleme...");
                    }
                }else{
                    Singelplayer_Classic.rank.setText("Veraltete Version oder Netzwerk Probleme...");
                }
            }
        };
        t1.start();
    }
}
