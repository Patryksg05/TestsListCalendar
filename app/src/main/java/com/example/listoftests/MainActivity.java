package com.example.listoftests;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.wifi.p2p.nsd.WifiP2pUpnpServiceInfo;
import android.os.Bundle;
import android.os.Message;
import android.telephony.ims.ImsMmTelManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText testNameEditText;
    private Button getDateBtn, addNewTestBtn;

    private Calendar calendar;
    public static ArrayList<Test> tests = new ArrayList<Test>();
    private ArrayList<Test> sortedTests;
    private DatePicker datePicker;

    public static final String TEST_ID = "TEST_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        calendar = Calendar.getInstance(); // initialize calendar
        calendar.set(2023, 10, 18);
        tests.add(new Test("german", this.getCalendar(2023, 10, 25)));
        tests.add(new Test("polish", this.getCalendar(2023, 10, 24)));
        tests.add(new Test("physics", this.getCalendar(2023, 11, 17)));
        listView = (ListView) findViewById(R.id.listView);
        testNameEditText = (EditText) findViewById(R.id.newTestEditText);
        getDateBtn = (Button) findViewById(R.id.getDateBtn);
        addNewTestBtn = (Button) findViewById(R.id.addTestBtn);
        tests.sort(((o1, o2) -> {
            if(o1.getCalendar().before(o2.getCalendar())){
                return -1;
            }
            else if(o1.getCalendar().after(o2.getCalendar())){
                return 1;
            }
            else
                return 0;
        }));

        ArrayAdapter<Test> arrayAdapter = new ArrayAdapter<Test>(
                this, android.R.layout.simple_list_item_1, tests
        );
        listView.setAdapter(arrayAdapter);

        getDateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(MainActivity.this,
                                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dt, int i, int i1, int i2) {
                                calendar = Calendar.getInstance();
                                calendar.set(i, i1, i2);
//                                Toast.makeText(
//                                        MainActivity.this,
//                                        calendar.get(Calendar.YEAR) + ":" + calendar.get(Calendar.MONTH) + ":" + calendar.get(Calendar.DAY_OF_MONTH),
//                                        Toast.LENGTH_SHORT).show();
                            }
                        }, 2023,10,18);
                        datePickerDialog.show();
//                        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker timePicker, int i, int minute) {
//                                calendar.set(Calendar.HOUR_OF_DAY, i);
//                                calendar.set(Calendar.MINUTE, minute);
//                            }
//                        }, 1,1,true);
//                        timePickerDialog.show();
                    }
                }
        );

        addNewTestBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(correctDate(calendar)) {
                            tests.add(new Test(testNameEditText.getText().toString(), calendar));
                            tests.sort(((o1, o2) -> {
                                if (o1.getCalendar().before(o2.getCalendar())) {
                                    return -1;
                                } else if (o1.getCalendar().after(o2.getCalendar())) {
                                    return 1;
                                } else
                                    return 0;
                            }));
                            arrayAdapter.notifyDataSetChanged();
                        }
                        else
                            Toast.makeText(MainActivity.this, "correct date", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(MainActivity.this, TestActivity.class);
                        intent.putExtra(TEST_ID, i);
                        startActivity(intent);
                    }
                }
        );
    }

    private boolean correctDate(Calendar userCalendar)
    {
        Calendar c = Calendar.getInstance();
        c.set(2023, 10, 18);

        if(c.get(Calendar.DAY_OF_MONTH) > userCalendar.get(Calendar.DAY_OF_MONTH))
            return false;
        return true;
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