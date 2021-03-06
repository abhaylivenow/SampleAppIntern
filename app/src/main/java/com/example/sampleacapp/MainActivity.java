package com.example.sampleacapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn,btnDelete;
    RecyclerView recyclerView;
    ArrayList<AC> AcList;
    TextView txtAddAc;

    DatabaseManager databaseManager = new DatabaseManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        recyclerView = findViewById(R.id.recView);
        AcList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = databaseManager.readAllData();

        /*
        GO THROUGH ALL ROWS IN DATABASE AND CREATE
        AC OBJECT ACCORDINGLY
         */
        while(cursor.moveToNext()){
            AC currentAC = new AC (cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            AcList.add(currentAC);
        }

        // SET RECYCLERVIEW ADAPTER
        RecylerviewAdapter adapter = new RecylerviewAdapter(AcList);
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,add_ac.class);
                startActivity(intent);
            }
        });

        /*
        THIS WILL DELETE ALL ROWS FROM THE TABLE
         */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTable();
            }
        });

        /*
        CHECK IF THERE IS NO ELEMENT IN RECYCLER VIEW
        AND USE TEXT ACCORDINGLY
         */
        if(AcList.size() == 0){
            txtAddAc.setText("No Ac. Add some ACs.");
        }else {
            txtAddAc.setText("List of AC you added");
        }
    }
    private void deleteTable(){
        databaseManager.delete();
    }

    // INIT ALL VIEWS
    private void initView(){
        btn = findViewById(R.id.button);
        btnDelete = findViewById(R.id.btnDelete);
        txtAddAc = findViewById(R.id.txt_add_ac);
    }
}