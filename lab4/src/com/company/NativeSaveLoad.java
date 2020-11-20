package com.company;

import java.io.*;

public class NativeSaveLoad implements IBaseSaveLoad {
    public void save(Object obj, String path) throws Exception{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
    }

    public  <T extends Object> T load(String path) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fis);
        return (T) oin.readObject();
    }
}
