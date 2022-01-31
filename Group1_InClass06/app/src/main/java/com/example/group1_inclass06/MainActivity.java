package com.example.group1_inclass06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    int poolNum;
    TextView textViewProgress, textViewAverage;
    ListView listView;
    ArrayList<String> numList = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");

        textViewProgress = findViewById(R.id.textViewProgress);
        textViewAverage = findViewById(R.id.textViewAverage);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, String);
        listView.setAdapter(adapter);


        ExecutorService taskPool = Executors.newFixedThreadPool(poolNum);
        taskPool.execute(new );

    }


    class DoWorkAsync extends AsyncTask<String, Integer, Double> {

        @Override
        protected void onPostExecute(Double aDouble) {
            super.onPostExecute(aDouble);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            String progressValue = values.toString();
            String progressAvg;
            textViewProgress.setText(progressValue);
            textViewAverage.setText(progressAvg);
        }

        @Override
        protected Double doInBackground(String... strings) {
            getNumber;

            return null;
        }
    }

    public class DoWork implements Runnable {

        @Override
        public void run() {

        }
    }
}