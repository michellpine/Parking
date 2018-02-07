package exception;

import com.ceiba.parking.services.ParkingGuardService;

public class ParkingException extends RuntimeException {

    public ParkingException(String str){
        super(str);
    }
}
