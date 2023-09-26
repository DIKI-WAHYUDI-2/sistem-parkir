package service;

import java.sql.Time;

public interface VehicleService {

    void showVehicle();
    void registerVehicle(String numberPlate, String typeVehicle);
    void paidVehicle(String numberPlate);
}
