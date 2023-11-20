package com.nentu.lab3.lab3.start.start;

import jakarta.annotation.ManagedBean;
import jakarta.inject.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ManagedBean
@Named
public class TimerBean {
    public String getTime(){
        var formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
