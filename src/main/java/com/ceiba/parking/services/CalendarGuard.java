package com.ceiba.parking.services;

import org.springframework.stereotype.Service;

@Service
public class CalendarGuard {

    public int getActualDay(){
        return java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK);
    }

}
