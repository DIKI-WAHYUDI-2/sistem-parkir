package view;

import Util.InputUtil;
import service.VehicleService;

import javax.swing.*;

public class VehicleView {

    private VehicleService vehicleService;

    public VehicleView(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void vehicleView(){

        while (true){

            System.out.println("MENU");
            System.out.println("1. Tambah kendaraan");
            System.out.println("2. Bayar Kendaraan");
            System.out.println("3. Keluar");

            String input = InputUtil.input("Pilih");

            if (input.equals("1")){
                addVehicle();
            } else if (input.equals("2")) {
                paidVehicle();
            } else if (input.equals("3")) {
                System.out.println("Bye!");
            }

        }
    }

    public void addVehicle(){

        System.out.println("MENAMBAH KENDARAAN");

        while (true){

            String numberPlate = InputUtil.input("Plat Nomor (Contoh : BM 1234 AA) (x) untuk batal");
            String regex = "^[A-Z]{2}\\s\\d{4}\\s[A-Z]{2}$";

            if (!numberPlate.matches(regex)){
                JOptionPane.showMessageDialog(null, "Plat Nomor yang anda masukkan tidak valid"," Pesan Kesalahan", JOptionPane.ERROR_MESSAGE);
            } else {
                String typeVehicle = InputUtil.input("Jenis Kendaraan (Contoh : roda dua/roda empat)");
                vehicleService.registerVehicle(numberPlate, typeVehicle);
                break;
            }
        }
    }

    public void paidVehicle(){

        System.out.println("PEMBAYARAN KENDARAAN");

        String numberPlate = InputUtil.input("Plat Nomor (x) untuk batal");

        if (numberPlate.equals("x")){

        }else{
            vehicleService.paidVehicle(numberPlate);
        }
    }
}
