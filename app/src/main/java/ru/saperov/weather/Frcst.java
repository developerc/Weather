package ru.saperov.weather;

/**
 * Created by saperov on 10.02.16.
 */
public class Frcst {
    private int _id;
    private String mName;
    private String mTemp;
    private String mIcon;
    private String mDt_txt;

// Пустой констуктор
    public Frcst(){

    }

    // Конструктор с параметрами
    public Frcst(int id, String name, String temp, String icon, String dt_txt){
        this._id = id;
        this.mName = name;
        this.mTemp = temp;
        this.mIcon = icon;
        this.mDt_txt = dt_txt;
    }

    // Конструктор с параметрами
    public Frcst(String name, String temp, String icon, String dt_txt){
        this.mName = name;
        this.mTemp = temp;
        this.mIcon = icon;
        this.mDt_txt = dt_txt;
    }
    // Создание геттеров-сеттеров

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getTemp(){
        return this.mTemp;
    }

    public void setTemp(String temp){
        this.mTemp = temp;
    }

    public String getIcon(){
        return this.mIcon;
    }

    public void setIcon(String icon){
        this.mIcon = icon;
    }

    public String getDt_txt(){
        return this.mDt_txt;
    }

    public void setDt_txt(String dt_txt){
        this.mDt_txt = dt_txt;
    }
}
