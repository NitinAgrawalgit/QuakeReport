package com.example.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.quickaccesswallet.GetWalletCardsCallback;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView earthQuakeListView;
    private CustomAdapter customAdapter;
    ImageView prev;
    ImageView next;
    TextView monthTV;
    TextView fromDate;
    TextView toDate;
    TextView searchBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prev = findViewById(R.id.prevButton);
        next = findViewById(R.id.nextButton);

        fromDate = (TextView) findViewById(R.id.fromDate);
        toDate = (TextView) findViewById(R.id.toDate);
        searchBtn = (TextView) findViewById(R.id.searchBtn);

        Calendar calendar = Calendar.getInstance();

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 += 1;
                        String selectedDate = i + "-" + i1 + "-" + i2;
                        DateUtils.setStartDate(selectedDate);

                        fromDate.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 += 1;
                        String selectedDate = i + "-" + i1 + "-" + i2;
                        DateUtils.setEndDate(selectedDate);

                        toDate.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetQuakeDataTask task = new GetQuakeDataTask();
                task.execute(DateUtils.stringUrl);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = DateUtils.decrementMonth();

                GetQuakeDataTask task = new GetQuakeDataTask();
                task.execute(DateUtils.stringUrl);

                updateMonth(n);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = DateUtils.incrementMonth();

                GetQuakeDataTask task = new GetQuakeDataTask();
                task.execute(DateUtils.stringUrl);

                updateMonth(n);
            }
        });

        earthQuakeListView = findViewById(R.id.list);

        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        customAdapter = new CustomAdapter(this, R.layout.custom_list_item, earthquakes);
        earthQuakeListView.setAdapter(customAdapter);

        earthQuakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(earthquakes.get(i).getmUrl()));
                startActivity(intent);
            }
        });

        GetQuakeDataTask task = new GetQuakeDataTask();
        task.execute(DateUtils.stringUrl);
    }

    public void updateMonth(int n){
        monthTV = (TextView) findViewById(R.id.monthTextView);
        String s = DateUtils.getMonthName(n);

        monthTV.setText(s);
    }

    public class GetQuakeDataTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {
        @Override
        protected ArrayList<Earthquake> doInBackground(String... strings) {
            if(DateUtils.stringUrl == null){
                return null;
            }

            ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthQuakeData(DateUtils.stringUrl);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            customAdapter.clear();

            if(earthquakes!=null && !earthquakes.isEmpty()){
                customAdapter.addAll(earthquakes);
            }
        }
    }
}