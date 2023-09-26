import ConnectionDataBase.ConnectionDB;
import com.zaxxer.hikari.HikariDataSource;
import entity.Vehicle;
import org.junit.jupiter.api.*;
import repository.VehicleRepository;
import repository.VehicleRepositoryImpl;
import service.VehicleService;
import service.VehicleServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

public class VehicleRepositoryTest {

    private HikariDataSource dataSource;
    private VehicleRepository vehicleRepository;

    @BeforeEach
    void beforeEach() {
        dataSource = ConnectionDB.getDataSource();
        vehicleRepository = new VehicleRepositoryImpl(dataSource);
    }

    @Test
    void insertVehicleTest() {
        Vehicle vehicle = new Vehicle();
        vehicle.setNumberPlate("BM 1234 AC");
        vehicle.setTypeVehicle("roda dua");
        vehicle.setEntryTime(new Time(System.currentTimeMillis()));

        vehicleRepository.add(vehicle);
    }

    @Test
    void paiVehicleTest() {
        Vehicle vehicle = new Vehicle();
        String numberPlate = "BM 1234 AC";
        vehicle.setExitTime(new Time(System.currentTimeMillis()));
        vehicle.setLongParkingTime(null);
        vehicle.setParkingFee(20_000);

        vehicleRepository.paid(numberPlate ,vehicle);
    }

    @AfterEach
    void afterEach() {
        dataSource.close();
    }
}
