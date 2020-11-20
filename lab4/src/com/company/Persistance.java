package com.company;

import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Getter
public class Persistance {
    public String _savePath = "save";
    IBaseSaveLoad _base;

    Persistance(IBaseSaveLoad base){
        _base = base;
    }

    Persistance(IBaseSaveLoad base, String savePath){
        _base = base;
        _savePath = savePath;
    }

    public void save(Object obj){
        try {
            _base.save(obj, _savePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String alternativeTo(String path){
        System.out.println("Can't load path: " + path);
        System.out.print("Input file to load: ");
        Scanner s = new Scanner(System.in);
        return s.next();
    };

    private <T> T load_savetry(String path){
        T res;
        while (true){
            try {
                res = _base.<T>load(path);
                break;
            } catch (Exception e) {
                path = alternativeTo(path);
            }
        }
        makeCopy(path);
        return res;
    }

    public <T> T load(){
        return load_savetry(_savePath);
    }

    private String copyPath(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return _savePath + "_" + dtf.format(now);
    }

    private void makeCopy(String path){
        try {
            FileUtils.copyFile(new File(path), new File(copyPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
