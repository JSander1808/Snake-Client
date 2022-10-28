package Client;

import RSA.RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.ArrayList;

public class Client {

    private String ip = "localhost";
    private int port = 4000;
    public Client(){

    }

    /*

    Config  [0]

    sin
    [3]Benutzername
    [4]Password
    [5]Modus: get-set-init
    [6]Keyword
    [7]value

    acc
    [3]Modus:  login-signup
    [4]Password
     */

    public String sendToServer(String message){
        try {
            Socket client = new Socket(ip, port);

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            KeyPair keypair = RSA.keygen();
            writer.write(RSA.publickeyToString(keypair.getPublic())+"\n");
            writer.flush();

            String temp = null;
            while((temp = reader.readLine())==null);
                PublicKey publickey = RSA.stringToPublickey(temp);
                String[] command = message.split("--");
                writer.write(RSA.decrypt(String.valueOf(command.length),publickey)+"\n");
                writer.flush();
                for(int i = 0;i<command.length;i++){
                    writer.write(RSA.decrypt(command[i], publickey)+"\n");
                    writer.flush();
                }
                temp = null;
                while((temp = reader.readLine())==null);
                reader.close();
                writer.close();
                client.close();
                return RSA.doFinal(temp, keypair.getPrivate());
            } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean ping(){
        try {
            Socket client = new Socket(ip, port);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendUDPToServer(String message){
        try {
            Socket client = new Socket(ip, port);

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            KeyPair keypair = RSA.keygen();
            writer.write(RSA.publickeyToString(keypair.getPublic())+"\n");
            writer.flush();

            String temp = null;
            while((temp = reader.readLine())==null);
            PublicKey publickey = RSA.stringToPublickey(temp);
            String[] command = message.split("--");
            writer.write(RSA.decrypt(String.valueOf(command.length),publickey)+"\n");
            writer.flush();
            for(int i = 0;i<command.length;i++){
                writer.write(RSA.decrypt(command[i], publickey)+"\n");
                writer.flush();
            }
            reader.close();
            writer.close();
            client.close();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String> receive(String prefix){
        ArrayList<String> data = new ArrayList<String>();
        try {
            Socket client = new Socket(ip, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            KeyPair keypair = RSA.keygen();
            writer.write(RSA.publickeyToString(keypair.getPublic())+"\n");
            writer.flush();

            String temp = null;
            while((temp = reader.readLine())==null);
            PublicKey publickey = RSA.stringToPublickey(temp);
            writer.write(RSA.decrypt("1",publickey)+"\n");
            writer.flush();
            writer.write(RSA.decrypt(prefix,publickey)+"\n");
            writer.flush();
            temp = null;
            while((temp = reader.readLine())!=null){
                data.add(RSA.doFinal(temp, keypair.getPrivate()));
            }
            reader.close();
            writer.close();
            client.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
