package entity;

import java.sql.Time;

public class Vehicle {

    private String numberPlate;
    private String typeVehicle;
    private Time entryTime;
    private Time exitTime;
    private Long longParkingTime;
    private Integer parkingFee;

    public Vehicle() {
    }

    public Vehicle(String numberPlate, String typeVehicle,Long longParkingTime,Integer parkingFee) {
        this.numberPlate = numberPlate;
        this.typeVehicle = typeVehicle;
        this.entryTime = new Time(System.currentTimeMillis());
        this.exitTime = new Time(System.currentTimeMillis());
        this.longParkingTime = longParkingTime;
        this.parkingFee = parkingFee;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public Time getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Time entryTime) {
        this.entryTime = entryTime;
    }

    public Time getExitTime() {
        return exitTime;
    }

    public void setExitTime(Time exitTime) {
        this.exitTime = exitTime;
    }

    public Integer getParkingFee() {
        return parkingFee;
    }

    public void setParkingFee(Integer parkingFee) {
        this.parkingFee = parkingFee;
    }

    public Long getLongParkingTime() {
        return longParkingTime;
    }

    public void setLongParkingTime(Long longParkingTime) {
        this.longParkingTime = longParkingTime;
    }


}
