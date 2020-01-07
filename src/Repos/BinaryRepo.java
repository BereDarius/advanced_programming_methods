package Repos;

import java.io.*;
import java.util.ArrayList;

public class BinaryRepo<Object> {

    private   String  filepath ;

    public BinaryRepo(String filepath) {
        this.filepath = filepath;
    }



    public void Serialization(ArrayList<Object> entities, String filepath){
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream(filepath));
            out.writeObject(entities);
            System.out.println("success");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public ArrayList<Object> deserialization(String filepath){
        ObjectInputStream in = null;
        ArrayList<Object> list = null;
        try{
            in = new ObjectInputStream(new FileInputStream(filepath));
            list =  (ArrayList<Object>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public String getFilepath() {
        return filepath;
    }
}