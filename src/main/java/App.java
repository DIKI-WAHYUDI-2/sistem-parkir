import ConnectionDataBase.ConnectionDB;

import com.zaxxer.hikari.HikariDataSource;
import repository.VehicleRepository;
import repository.VehicleRepositoryImpl;
import service.VehicleService;
import service.VehicleServiceImpl;
import view.VehicleView;



public class App {

    public static void main(String[] args) {
        HikariDataSource dataSource = ConnectionDB.getDataSource();
        VehicleRepository vehicleRepository = new VehicleRepositoryImpl(dataSource);
        VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository);
        VehicleView vehicleView = new VehicleView(vehicleService);
        vehicleView.vehicleView();
    }
}