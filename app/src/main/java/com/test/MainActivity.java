package com.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.spotify.test.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class MainActivity extends AppCompatActivity {

    RecyclerView nameList;
    DetailsAdapter listAdatper;
    PersonViewModel viewModel;
    TextView dnt;

    public static final String COMMA = ",";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameList = findViewById(R.id.List);
        nameList.setLayoutManager(new LinearLayoutManager(this));
        listAdatper = new DetailsAdapter();
        nameList.setAdapter(listAdatper);
        viewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        viewModel.getDetailsList().observe(this, list -> listAdatper.submitList(list));
        dnt = findViewById(R.id.datetime);
        DateFormat dt = getDateInstance();;
        DateFormat tm = getTimeInstance();
        String time = tm.format(new Date()) + "  " + dt.format(new Date());
        dnt.setText(time);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}