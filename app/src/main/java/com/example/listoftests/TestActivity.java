package com.example.listoftests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TestActivity extends AppCompatActivity {

    private Calendar calendar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();

        calendar = Calendar.getInstance();
        int id = getIntent().getExtras().getInt(MainActivity.TEST_ID, 0);
        Test test = MainActivity.tests.get(id);

        textView = findViewById(R.id.textView);

        textView.setText(
                test.getName() + "\n" +
                        calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
                        + calendar.get(Calendar.DAY_OF_MONTH) + "\n" +
                        test.getDesc()
        );
    }
}