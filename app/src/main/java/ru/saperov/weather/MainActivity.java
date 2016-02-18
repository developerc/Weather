package ru.saperov.weather;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvCityes;
    ArrayList<Cities> arrayOfCityes;
    CustomCityAdapter adapter;
    String httpPath;
    String httpForecastPath;
    String myAPIKey;
    private static final String TAG = "myLogs";
    static int cod;
    static int weather_id;
    static String weather_main;
    static String weather_description;
    static String weather_icon;
    static double main_temp;
    static double main_pressure;
    static double main_humidity;
    static double main_temp_min;
    static double main_temp_max;
    static double wind_speed;
    static double wind_deg;
    static double clouds_all;
    static String name;
    static int cnt;
    static String city_id;
    static int listLen;
    static double rain_3h;
    static double snow_3h;
    static String dt_txt;
    private static DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    public static ArrayList<String> strForecast = new ArrayList<String>();
    public static ArrayList<String> strIcon = new ArrayList<String>();
    public static ArrayList<String> strGorod = new ArrayList<String>();
    public static int numGorod = -1;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String DATABASE_TABLE = "forecast";
    private boolean flagForecast=false;
    private ProgressBar spinner;
   // public boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateCityesList();
        lvCityes.setOnItemClickListener(itemClickListener);

        mDatabaseHelper = new DatabaseHelper(this);
       // mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

      // findViewById(R.id.marker_progress).setVisibility(View.GONE);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);

        myAPIKey = ",ru&APPID=841ed564504f7b68ef556499ef09fce1";
        httpPath = "http://api.openweathermap.org/data/2.5/weather?q=";
        httpForecastPath = "http://api.openweathermap.org/data/2.5/forecast/city?q=";
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateCityesList();
        Log.d(TAG, "MainActivity: onResume()");
    }

    public void populateCityesList(){
        //construct of data sources
        arrayOfCityes = Cities.getCities();
        //create the adapter to convert the array to a view
        adapter = new CustomCityAdapter(this, arrayOfCityes);
        lvCityes = (ListView) findViewById(R.id.lvCityes);
        lvCityes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //обрабатываем нажатие на списке городов
    protected AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            view.setSelected(true);
            if(isOnline()) {
          //  if(false){
                switch (position) {
                    case 0: {
                        if (flagForecast) {
                            strForecast.clear();
                            strIcon.clear();
                            new GetForecastTask().execute(httpForecastPath + "Moscow" + myAPIKey + "&lang=ru");
                        } else {
                            new GetAsincTask().execute(httpPath + "Moscow" + myAPIKey + "&lang=ru");
                        }


                    }
                    break;
                    case 1: {
                        if (flagForecast) {
                            strForecast.clear();
                            strIcon.clear();
                            new GetForecastTask().execute(httpForecastPath + "Sankt-Peterburg" + myAPIKey + "&lang=ru");
                        } else {
                            new GetAsincTask().execute(httpPath + "Sankt-Peterburg" + myAPIKey + "&lang=ru");
                        }

                        break;
                    }
                    case 2: {
                        if (numGorod >= 0) {
                            if (flagForecast) {
                                strForecast.clear();
                                strIcon.clear();
                                new GetForecastTask().execute(httpForecastPath + strGorod.get(numGorod) + myAPIKey + "&lang=ru");
                            } else {
                                new GetAsincTask().execute(httpPath + strGorod.get(numGorod) + myAPIKey + "&lang=ru");
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Your city not selected!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            } else
            {
                showFromDataBase();
                Toast.makeText(getApplicationContext(), "Internet not connected!", Toast.LENGTH_SHORT).show();
            }
        }
    };



    public class GetAsincTask extends AsyncTask<String, Void, Void> {
        String textResult;

        @Override
        protected void onPreExecute() {
            spinner.setVisibility(View.VISIBLE);
            //listView.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Log.d(TAG, "*******************    Open Connection    *****************************");
                URL url = new URL(params[0]);
                Log.d(TAG, "Received URL:  " + url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                int response = conn.getResponseCode();
                // Log.d(TAG, "The response is: " + response);
                InputStream in = conn.getInputStream();
                // Log.d(TAG, "GetInputStream:  " + in);

                // Log.d(TAG, "*******************    String Builder     *****************************");
                String line = null;

                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String StringBuffer;
                String stringText = "";
                while ((StringBuffer = bufferReader.readLine()) != null) {
                    stringText += StringBuffer;
                }
              //  MyVariables.InOuExcept = false;
                bufferReader.close();

                textResult = stringText;

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                textResult = e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
              //  MyVariables.InOuExcept = true;
                e.printStackTrace();
                textResult = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            spinner.setVisibility(View.GONE);
            //получили JSON строку с сервера
             Log.d(TAG, textResult);
            try {
                WeatherJson(textResult);

                startActivity(new Intent(getApplicationContext(), Two.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void WeatherJson(String jsonString) throws JSONException {

        Log.d(TAG, "*******************    обрабатываем JSON строку     *****************************");
        JSONObject jo =  new JSONObject(jsonString);
        cod = jo.getInt("cod");
        JSONArray WeaArr = new JSONArray(jo.getString("weather"));
        JSONObject json_weather = WeaArr.getJSONObject(0);
        weather_id = json_weather.getInt("id");
        weather_main = json_weather.getString("main");
        weather_description = json_weather.getString("description");
        weather_icon = json_weather.getString("icon");
        JSONObject json_main = new JSONObject(jo.getString("main"));
        main_temp = json_main.getDouble("temp");
        main_pressure = json_main.getDouble("pressure");
        main_humidity = json_main.getDouble("humidity");
        main_temp_min = json_main.getDouble("temp_min");
        main_temp_max = json_main.getDouble("temp_max");
        JSONObject json_wind = new JSONObject(jo.getString("wind"));
        wind_speed = json_wind.getDouble("speed");
        wind_deg = json_wind.getDouble("deg");
        JSONObject json_clouds = new JSONObject(jo.getString("clouds"));
        clouds_all = json_clouds.getDouble("all");
        name = jo.getString("name");

        Log.d(TAG, "cod=" + cod + ", id=" + weather_id + ", main=" + weather_main + ", description=" + weather_description + ", icon=" + weather_icon + ", temp=" + main_temp +
                ", pressure=" + main_pressure + ", humidity=" + main_humidity + ", temp_min=" + main_temp_min + ", temp_max=" + main_temp_max + ", wind_speed=" + wind_speed +
        ", wind_deg=" + wind_deg + ", clouds=" + clouds_all + ", name=" + name);

    }

    public class GetForecastTask extends AsyncTask<String, Void, Void> {
        String textResult;

        @Override
        protected void onPreExecute() {
            spinner.setVisibility(View.VISIBLE);
            //listView.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Log.d(TAG, "*******************    Open Connection    *****************************");
                URL url = new URL(params[0]);
                Log.d(TAG, "Received URL:  " + url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                int response = conn.getResponseCode();
                // Log.d(TAG, "The response is: " + response);
                InputStream in = conn.getInputStream();
                // Log.d(TAG, "GetInputStream:  " + in);

                // Log.d(TAG, "*******************    String Builder     *****************************");
                String line = null;

                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String StringBuffer;
                String stringText = "";
                while ((StringBuffer = bufferReader.readLine()) != null) {
                    stringText += StringBuffer;
                }
                //  MyVariables.InOuExcept = false;
                bufferReader.close();

                textResult = stringText;

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                textResult = e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                //  MyVariables.InOuExcept = true;
                e.printStackTrace();
                textResult = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            spinner.setVisibility(View.GONE);
            //получили JSON строку с сервера
            Log.d(TAG, textResult);
           try {
               ForecastJson(textResult);

               // startActivity(new Intent(getApplicationContext(), Two.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void ForecastJson(String jsonString) throws JSONException {
        Log.d(TAG, "*******************    обрабатываем JSON строку     *****************************");
        JSONObject jo =  new JSONObject(jsonString);
        cod = jo.getInt("cod");
        cnt = jo.getInt("cnt");
        JSONObject city = new JSONObject(jo.getString("city"));
        city_id = city.getString("id");
        name = city.getString("name");
        JSONArray list = new JSONArray(jo.getString("list"));
        listLen = list.length();
        JSONObject listItem ;

        mDatabaseHelper.delAllFrcsts();
        for (int i=0; i<listLen; i++) {
            listItem = list.getJSONObject(i);
           // Log.d(TAG, String.valueOf(listItem));
            JSONObject main = new JSONObject(listItem.getString("main"));
            main_temp = main.getDouble("temp");
            main_temp_min = main.getDouble("temp_min");
            main_temp_max = main.getDouble("temp_max");
            main_pressure = main.getDouble("pressure");
            main_humidity = main.getDouble("humidity");
            JSONArray WeaArr = new JSONArray(listItem.getString("weather"));
            JSONObject json_weather = WeaArr.getJSONObject(0);
            weather_id = json_weather.getInt("id");
            weather_main = json_weather.getString("main");
            weather_description = json_weather.getString("description");
            weather_icon = json_weather.getString("icon");
            JSONObject json_wind = new JSONObject(listItem.getString("wind"));
            wind_speed = json_wind.getDouble("speed");
            wind_deg = json_wind.getDouble("deg");
            JSONObject json_clouds = new JSONObject(listItem.getString("clouds"));
            clouds_all = json_clouds.getDouble("all");
            if (listItem.has("snow")) {
                JSONObject json_snow = new JSONObject(listItem.getString("snow"));
                if (json_snow.has("3h")) snow_3h = json_snow.getDouble("3h");
            }
            if (listItem.has("rain")) {
                JSONObject json_rain = new JSONObject(listItem.getString("rain"));
               if (json_rain.has("3h")) rain_3h = json_rain.getDouble("3h");
            }
            dt_txt = listItem.getString("dt_txt");

            String strTemp = "";
            main_temp = main_temp - 273.15;
            //strTemp = String.format("%+.1f%n",main_temp)+" C";
            if (main_temp<0) {strTemp = String.format("%+.1f",main_temp)+"C";} else
            {strTemp = "+"+String.format("%.1f",main_temp)+"C";}
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt_txt);
                dt_txt = new SimpleDateFormat("dd/MM/yy HH:mm").format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
                strForecast.add(strTemp + " " + dt_txt);
                strIcon.add(weather_icon);


            mDatabaseHelper.addFrcst(new Frcst(name, strTemp, weather_icon, dt_txt));

            Log.d(TAG, "temp=" + main_temp + ", pressure=" + main_pressure + ", humidity=" + main_humidity + ", temp_min=" + main_temp_min + ", temp_max=" + main_temp_max +
                    ", id=" + weather_id + ", main=" + weather_main + ", description=" + weather_description + ", icon=" + weather_icon +
                    ", wind_speed=" + wind_speed + ", wind_deg=" + wind_deg + ", clouds=" + clouds_all + ", snow_3h=" + snow_3h +", rain_3h=" + rain_3h +  ", dt_txt=" + dt_txt);
        }

        Log.d(TAG, "cod =" + String.valueOf(cod) + " cnt=" + cnt + " id=" + city_id + " name=" + name + " listLen=" + listLen);

        for (int k=0; k<strForecast.size(); k++){
            Log.d(TAG, strForecast.get(k) + " " + strIcon.get(k));
        }
        startActivity(new Intent(getApplicationContext(), Three.class));

    }

public void selectCity(){
//здесь будем выбирать город из списка
    strGorod.clear();
    InputStream is = getResources().openRawResource(R.raw.goroda);
    InputStreamReader inputreader = new InputStreamReader(is);
    BufferedReader reader = new BufferedReader(inputreader);
    String line;
    try {
        while ((line = reader.readLine())!=null){
       // line = reader.readLine();
        strGorod.add(line);
            Log.d(TAG, line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    startActivity(new Intent(getApplicationContext(),Four.class));
}

    //проверяем есть ли Internet соединение
    boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            return true;
        }
        else {
            return false;
        }


    }

    public void showFromDataBase(){
        if(mDatabaseHelper.getFrcstCount()>0){
            strForecast.clear();
            strIcon.clear();
            List<Frcst> frcstList= mDatabaseHelper.getAllFrcsts();
            for(int i=0;i<frcstList.size();i++) {
                strForecast.add(frcstList.get(i).getTemp() + " " + frcstList.get(i).getDt_txt());
                strIcon.add(frcstList.get(i).getIcon());
            }
            name=frcstList.get(0).getName();
            startActivity(new Intent(getApplicationContext(), Three.class));
        } else {
            Toast.makeText(getApplicationContext(), "Forecasts are missing!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            Log.d(TAG, "Выбран Вашгород");
            return true;
        }*/

        switch (id) {
            case R.id.action_settings: selectCity();
                break;
            case R.id.action_forecast: flagForecast=true;
                break;
            case R.id.action_weather: flagForecast=false;
                break;
                    }

        return super.onOptionsItemSelected(item);
    }
}
