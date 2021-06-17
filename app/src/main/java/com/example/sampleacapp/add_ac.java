package com.example.sampleacapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class add_ac extends AppCompatActivity {

    private EditText edtAcModel, edtSerialNumber,edtPurchaseDate, edtInstalledPlace, edtPurchasedFrom;
    private Button btn;
    private Spinner spinner;
    private Calendar calendar = Calendar.getInstance();

    String[] items = new String[]{"Window" , "Split"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ac);

        initViews();
        initCalender();
        initSpinner();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String acType = spinner.getSelectedItem().toString();
                String heading = edtAcModel.getText().toString();
                String purchaseDetail = "Purchased on "+ edtPurchaseDate.getText().toString();
                String installedPlace = edtInstalledPlace.getText().toString();
                insertData(heading,purchaseDetail,installedPlace);

                Intent intent = new Intent(add_ac.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertData(String model, String serialNumber, String installedPlace){
        String res = new DatabaseManager(this).addRecord(model,serialNumber,installedPlace);
        Toast.makeText(this, "successfully inserted", Toast.LENGTH_SHORT).show();
    }

    private void initViews(){
        edtAcModel = findViewById(R.id.edt_acModel);
        edtSerialNumber = findViewById(R.id.edt_serial_number);
        edtInstalledPlace = findViewById(R.id.edt_installed_place);
        edtPurchasedFrom = findViewById(R.id.edt_purchased_from);
        edtPurchaseDate = findViewById(R.id.edt_date);
        btn = findViewById(R.id.btn_addAc);
    }

    private void initCalender(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edtPurchaseDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(add_ac.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtPurchaseDate.setText(sdf.format(calendar.getTime()));
    }

    private void initSpinner(){
        spinner = findViewById(R.id.spinner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }
}