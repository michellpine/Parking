package com.ceiba.parking.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParkingTicket {

    protected Date dateArrive;
    protected Date dateOut;

    public ParkingTicket() {
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public long getCountHours() {
        //Se tiene en cuenta la diferencia en minutos, ya que despues de pasado un
        //minuto, se cobra la siguiente hora
        double differenceInMinutes = (dateOut.getTime() - dateArrive.getTime())/(60*1000);
        if((differenceInMinutes/60) % 1 == 0){
            return (dateOut.getTime() - dateArrive.getTime())/(60*60*1000);
        }else{
            return  (long) Math.ceil(differenceInMinutes/60);
        }
    }
}
