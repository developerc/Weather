package ru.saperov.weather;

import java.util.ArrayList;

/**
 * Created by saperov on 14.02.16.
 */
public class Goroda {
    public String myGorod;
    static ArrayList<Goroda> goroda;

    public Goroda(String myGorod) {this.myGorod=myGorod;}

    public static ArrayList<Goroda> getGoroda(){
        goroda=new ArrayList<Goroda>();
        for (int i=0;i<MainActivity.strGorod.size();i++)
       // for (int i=0;i<5;i++)
        goroda.add(new Goroda(MainActivity.strGorod.get(i)));

        return goroda;
    }
}
