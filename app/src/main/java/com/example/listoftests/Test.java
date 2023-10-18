package com.example.listoftests;

import java.util.ArrayList;
import java.util.Calendar;

public class Test {
    private String name;
    private Calendar calendar;
    private String desc;

    public Test(String name, Calendar calendar) {
        this.name = name;
        this.calendar = calendar;
        this.desc = "sth about test";
    }

    public String getName() {
        return name;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return getName() + "\t\t\t\t\t\t"
                + calendar.get(Calendar.YEAR) + "-" +
                calendar.get(Calendar.MONTH) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH);
    }
}
