package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Persistance p = new Persistance(new NativeSaveLoad(), "custom");
        Triangle t = p.load();

        System.out.println(t.description());

        p.save(t);
    }
}
