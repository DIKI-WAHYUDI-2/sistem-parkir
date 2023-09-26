package view;

import Util.InputUtil;
import service.VehicleService;

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

        System.out.println("MENAMBAH TODOLIST");

        String numberPlate = InputUtil.input("Plat Nomor (x) untuk batal");

        if (numberPlate.equals("x")){

        } else {
            String typeVehicle = InputUtil.input("Jenis Kendaraan");
            vehicleService.registerVehicle(numberPlate, typeVehicle);
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
