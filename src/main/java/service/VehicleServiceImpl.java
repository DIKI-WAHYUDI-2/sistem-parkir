package service;

import entity.Vehicle;
import repository.VehicleRepository;

import javax.swing.*;
import java.sql.*;

public class VehicleServiceImpl implements VehicleService{

    private VehicleRepository vehicleRepository;
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void showVehicle() {

    }

    @Override
    public void registerVehicle(String numberPlate, String typeVehicle) {
        Vehicle vehicle = new Vehicle();

        vehicle.setNumberPlate(numberPlate);
        vehicle.setTypeVehicle(typeVehicle);
        vehicle.setEntryTime(new Time(System.currentTimeMillis()));

        vehicleRepository.add(vehicle);
        JOptionPane.showMessageDialog(null, "KENDARAAN " + vehicle.getNumberPlate() + " BERHASIL DITAMBAH", "Pesan Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    public void getDataVehicle(String numberPlate){

        Vehicle[] model = vehicleRepository.getAll(numberPlate);

        for (Vehicle data : model) {
            System.out.println("Info Pembayaran Kendaraan");
            System.out.println("Plat Nomor : " + data.getNumberPlate());
            System.out.println("Jenis Kendaraan : " + data.getTypeVehicle());
            System.out.println("Lama Parkir : " + data.getLongParkingTime() + " menit");
            System.out.println("Biaya Parkir : Rp" + data.getParkingFee());
        }
    }

    @Override
    public void paidVehicle(String numberPlate) {
        Vehicle vehicle = new Vehicle();
        vehicle.setExitTime(new Time(System.currentTimeMillis()));

        vehicleRepository.getExitTime(numberPlate, vehicle);
        Long longParkingTime = vehicleRepository.countLongParkingTime(numberPlate);

        Integer parkingFee = vehicleRepository.countParkingFee(numberPlate);

        vehicle.setLongParkingTime(longParkingTime);
        vehicle.setParkingFee(parkingFee);

        vehicleRepository.paid(numberPlate,vehicle);
        getDataVehicle(numberPlate);
    }

}
