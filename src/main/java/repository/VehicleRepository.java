package repository;

import entity.Vehicle;

public interface VehicleRepository {

    Vehicle[] getAll(String numberPlate);

    void add(Vehicle vehicle);
    void getExitTime(String numberPlate, Vehicle vehicle);
    Long countLongParkingTime(String numberPlate);
    Integer countParkingFee(String numberPlate);
    void paid(String numberPlate, Vehicle vehicle);
}
