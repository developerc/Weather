package ru.saperov.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Four extends AppCompatActivity {
    ArrayList<Goroda> gorodaArrayList;
    CustomGorodAdapter adapter;
    ListView lvGoroda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);



        setTitle("Select city");
        populateGorodaList();
        lvGoroda.setOnItemClickListener(itemClickListener);
    }

    public void populateGorodaList(){
        gorodaArrayList = Goroda.getGoroda();
        adapter = new CustomGorodAdapter(this, gorodaArrayList);
        lvGoroda = (ListView) findViewById(R.id.lvGorod);
        lvGoroda.setAdapter(adapter);
    }

    //обрабатываем нажатие на списке городов
    protected AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            view.setSelected(true);
            Toast.makeText(getApplicationContext(), "Вы выбрали position " + position+" "+MainActivity.strGorod.get(position), Toast.LENGTH_SHORT).show();
            MainActivity.numGorod = position;
            finish();
        }
    };
    }
