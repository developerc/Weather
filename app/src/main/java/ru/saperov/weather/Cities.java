package ru.saperov.weather;

import java.util.ArrayList;

/**
 * Created by saperov on 31.01.16.
 */
public class Cities {
    public String mycity;
    static ArrayList<Cities> cities;

    public Cities(String mycity){
        this.mycity = mycity;
    }

    public static ArrayList<Cities> getCities(){
        cities = new ArrayList<Cities>();
        cities.add(new Cities("Moscow"));
        cities.add(new Cities("Sankt-Peterburg"));
        if(MainActivity.numGorod<0) {
        cities.add(new Cities("Your city"));}
        else {
            cities.add(new Cities(MainActivity.strGorod.get(MainActivity.numGorod)));
        }

        return cities;
    }


}
