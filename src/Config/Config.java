package Config;

import java.io.*;
import java.util.ArrayList;

public class Config {

    private String path;

    public Config(String path){
        this.path=path;
    }

    public void init(){
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        if(!file.exists()){
            String[] pathname = path.split("//");
            StringBuilder resultpath = new StringBuilder();
            for(int i = 0;i<pathname.length-1;i++){
                resultpath.append(pathname[i]+"//");
                File tempfile = new File(resultpath.toString());
                tempfile.mkdir();
            }
            try {
                File tempfile = new File(path);
                tempfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void set(String keyword, String value){
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList data = new ArrayList();
            String temp = null;
            boolean exist = false;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split("->");
                if(result[0].equalsIgnoreCase(keyword)){
                    data.add(keyword+"->"+value+"\n");
                    exist = true;
                }else{
                    data.add(temp+"\n");
                }
            }
            if(!exist){
                data.add(keyword+"->"+value+"\n");
            }
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0;i<data.size();i++){
                writer.write(data.get(i).toString());
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String keyword){
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split("->");
                if(result[0].equalsIgnoreCase(keyword)){
                    return result[1];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exist(){
        File file = new File(path);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public boolean existdata(String keyword){
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = reader.readLine())!=null){
                String[] result = temp.split("->");
                if(result[0].equalsIgnoreCase(keyword)){
                    if(result.length==2){
                        System.out.println(result.length);
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
