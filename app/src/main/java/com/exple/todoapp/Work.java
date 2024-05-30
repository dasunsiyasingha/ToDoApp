package com.exple.todoapp;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Work {
    public String title = "";
    public String description = "";
    public  String status = "false";

    public String location = "";
    SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd/MM/yyyy"+ "", Locale.US);
    String time = sdfDateTime.format(new Date(System.currentTimeMillis()));

}
