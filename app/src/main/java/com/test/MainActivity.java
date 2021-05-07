package com.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.spotify.test.R;
import com.test.DatabaseHelper.DetailsAdapter;
import com.test.DatabaseHelper.LenderViewModel;

import java.text.DateFormat;
import java.util.Date;

import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class MainActivity extends AppCompatActivity {

    RecyclerView nameList;
    DetailsAdapter listAdapter;
    LenderViewModel viewModel;
    TextView dnt;

    public static final String COMMA = ",";
    public static final String LENDERID = "LenderId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameList = findViewById(R.id.List);
        nameList.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new DetailsAdapter(this);
        nameList.setAdapter(listAdapter);
        viewModel = new ViewModelProvider(this).get(LenderViewModel.class);
        viewModel.getDetailsList().observe(this, list -> listAdapter.submitList(list));
        dnt = findViewById(R.id.datetime);
        DateFormat dt = getDateInstance();
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

    public void LaunchNewLenderActivity(View view) {
        Intent i = new Intent(this,AddNewLender.class);
        startActivity(i);
    }
}