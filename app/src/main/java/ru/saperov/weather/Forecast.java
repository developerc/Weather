package ru.saperov.weather;

/**
 * Created by saperov on 06.02.16.
 */
public class Forecast {
    private int _id;
    private String mName;
    private String mCity_id;
    private double mTemp;
    private double mPressure;
    private double mHumidity;
    private double mTemp_min;
    private double mTemp_max;
    private int mWeather_id;
    private String mMain;
    private String mDescription;
    private String mIcon;
    private double mWind_speed;
    private double mWind_deg;
    private double mClouds;
    private double mSnow_3h;
    private double mRain_3h;
    private String mDt_txt;

    // Пустой констуктор
    public Forecast(){

    }
    public Forecast(int id, String name, String city_id) {
        this._id = id;
        this.mName = name;
        this.mCity_id = city_id;
    }

    public Forecast(String name, String city_id){
        this.mName = name;
        this.mCity_id = city_id;
    }

    // Конструктор с параметрами
    public Forecast(int id, String name, String city_id, double temp, double pressure, double humidity, double temp_min, double temp_max, int weather_id,
                    String main, String description, String icon, double wind_speed, double wind_deg, double clouds, double snow_3h, double rain_3h, String dt_txt){
        this._id = id;
        this.mName = name;
        this.mCity_id = city_id;
        this.mTemp = temp;
        this.mPressure = pressure;
        this.mHumidity = humidity;
        this.mTemp_min = temp_min;
        this.mTemp_max = temp_max;
        this.mWeather_id = weather_id;
        this.mMain = main;
        this.mDescription = description;
        this.mIcon = icon;
        this.mWind_speed = wind_speed;
        this.mWind_deg = wind_deg;
        this.mClouds = clouds;
        this.mSnow_3h = snow_3h;
        this.mRain_3h = rain_3h;
        this.mDt_txt = dt_txt;
    }

    // Конструктор с параметрами
    public Forecast(String name, String city_id, double temp, double pressure, double humidity, double temp_min, double temp_max, int weather_id,
                    String main, String description, String icon, double wind_speed, double wind_deg, double clouds, double snow_3h, double rain_3h, String dt_txt){
        this.mName = name;
        this.mCity_id = city_id;
        this.mTemp = temp;
        this.mPressure = pressure;
        this.mHumidity = humidity;
        this.mTemp_min = temp_min;
        this.mTemp_max = temp_max;
        this.mWeather_id = weather_id;
        this.mMain = main;
        this.mDescription = description;
        this.mIcon = icon;
        this.mWind_speed = wind_speed;
        this.mWind_deg = wind_deg;
        this.mClouds = clouds;
        this.mSnow_3h = snow_3h;
        this.mRain_3h = rain_3h;
        this.mDt_txt = dt_txt;
    }

    // Создание геттеров-сеттеров
    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public  String getName() {
        return mName;
    }

    public void setName (String name) {
        this.mName = name;
    }

    public String getCity_id() {
        return this.mCity_id;
    }

    public void  setCity_id(String city_id) {
        this.mCity_id = city_id;
    }

    public double getTemp(){return this.mTemp;}

    public void setTemp(double temp){this.mTemp = temp;}

    public double getPressure(){return mPressure;}

    public void setPressure(double pressure){this.mPressure = pressure;}

    public double getHumidity () {return mHumidity;}

    public void setHumidity(double humidity){this.mHumidity=humidity;}

    public double getTemp_min(){return mTemp_min;}

    public void setTemp_min(double temp_min){this.mTemp_min=temp_min;}

    public double getTemp_max(){return mTemp_max;}

    public void setTemp_max(double temp_max){this.mTemp_max=temp_max;}

    public int getWeather_id(){return mWeather_id;}

    public void setWeather_id(int weather_id){this.mWeather_id=weather_id;}

    public String getMain(){return mMain;}

    public void setMain(String main){this.mMain=main;}

    public String getDescription(){return mDescription;}

    public void setDescription(String description){this.mDescription=description;}

    public String getIcon(){return mIcon;}

    public void setIcon(String icon){this.mIcon=icon;}

    public double getWind_speed(){return mWind_speed;}

    public void setWind_speed(double wind_speed){this.mWind_speed=wind_speed;}

    public double getWind_deg(){return mWind_deg;}

    public void setWind_deg(double wind_deg){this.mWind_deg=wind_deg;}

    public double getClouds(){return mClouds;}

    public void setClouds(double clouds){this.mClouds=clouds;}

    public double getSnow_3h(){return mSnow_3h;}

    public void setSnow_3h(double snow_3h){this.mSnow_3h=snow_3h;}

    public double getRain_3h(){return mRain_3h;}

    public void setRain_3h(double rain_3h){this.mRain_3h=rain_3h;}

    public String getDt_txt(){return mDt_txt;}

    public void setDt_txt(String dt_txt){this.mDt_txt=dt_txt;}

    @Override
    public String toString() {
        return this._id + " , " + this.mCity_id;
    }
}
