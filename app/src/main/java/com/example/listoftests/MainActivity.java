package com.example.listoftests;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.telephony.ims.ImsMmTelManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText testNameEditText;
    private Button getDateBtn, addNewTestBtn;

    private Calendar calendar;
    private ArrayList<Test> tests = new ArrayList<Test>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        calendar = Calendar.getInstance(); // initialize calendar
        tests.add(new Test("polish", this.getCalendar(2023, 10, 24)));
        tests.add(new Test("german", this.getCalendar(2023, 10, 25)));
        tests.add(new Test("physics", this.getCalendar(2023, 11, 17)));
        listView = (ListView) findViewById(R.id.listView);
        testNameEditText = (EditText) findViewById(R.id.newTestEditText);
        getDateBtn = (Button) findViewById(R.id.getDateBtn);
        addNewTestBtn = (Button) findViewById(R.id.addTestBtn);

        ArrayAdapter<Test> arrayAdapter = new ArrayAdapter<Test>(
                this, android.R.layout.simple_list_item_1, tests
        );
        listView.setAdapter(arrayAdapter);
    }

    private Calendar getCalendar(int year, int month, int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar;
    }
}