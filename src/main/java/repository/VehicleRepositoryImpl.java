package repository;

import ConnectionDataBase.ConnectionDB;
import com.zaxxer.hikari.HikariDataSource;
import entity.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryImpl implements VehicleRepository{

    private DataSource dataSource;

    public VehicleRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Vehicle[] getAll(String numberPlate) {

        try(Connection connection = ConnectionDB.getDataSource().getConnection()){

            String sql = "SELECT plat_nomor, jenis_kendaraan, lama_parkir, biaya_parkir FROM kendaraan WHERE plat_nomor = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, numberPlate);
                try(ResultSet resultSet = statement.executeQuery()) {

                    List<Vehicle> list = new ArrayList<>();
                    if (resultSet.next()){

                        Vehicle vehicle = new Vehicle();
                        vehicle.setNumberPlate(resultSet.getString("plat_nomor"));
                        vehicle.setTypeVehicle(resultSet.getString("jenis_kendaraan"));
                        vehicle.setLongParkingTime(resultSet.getLong("lama_parkir"));
                        vehicle.setParkingFee(resultSet.getInt("biaya_parkir"));

                        list.add(vehicle);
                    }
                    return list.toArray(new Vehicle[]{});
                }
            }
        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void add(Vehicle vehicle) {
        String sql = "INSERT INTO kendaraan(plat_nomor,jenis_kendaraan,waktu_masuk,waktu_keluar,lama_parkir,biaya_parkir)" +
                "VALUES (?,?,?,?,?,?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vehicle.getNumberPlate());
            statement.setString(2, vehicle.getTypeVehicle());
            statement.setTime(3, vehicle.getEntryTime());
            statement.setNull(4, Types.TIME);
            statement.setNull(5, Types.INTEGER);
            statement.setNull(6, Types.INTEGER);

            statement.executeUpdate();


        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }

    }
    @Override
    public void getExitTime(String numberPlate, Vehicle vehicle){
        String sql = "UPDATE kendaraan SET waktu_keluar = ? WHERE plat_nomor = ?";

        try(Connection connection = ConnectionDB.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTime(1, vehicle.getExitTime());
            statement.setString(2, numberPlate);

            statement.executeUpdate();

        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Long countLongParkingTime(String numberPlate) {
        try (Connection connection = ConnectionDB.getDataSource().getConnection()) {
            String sql = "SELECT waktu_masuk, waktu_keluar FROM kendaraan WHERE plat_nomor = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, numberPlate);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                    Time entryTime = resultSet.getTime("waktu_masuk");
                    Time exitTime = resultSet.getTime("waktu_keluar");

                    long entry = entryTime.getTime();
                    long exit = exitTime.getTime();

                    long differenceMilisecond = exit - entry;
                    long differenceMinute = differenceMilisecond / (60 * 1000);

                    return differenceMinute;
                }else {
                        return null;
                    }
                }

            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Integer countParkingFee(String numberPlate) {

        try(Connection connection = ConnectionDB.getDataSource().getConnection()){
            String sql = "SELECT jenis_kendaraan, lama_parkir FROM kendaraan WHERE plat_nomor = ?";

            try(PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, numberPlate);

                try(ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()){

                        String typeVehile = resultSet.getString("jenis_kendaraan");
                        Long longParkingTime = resultSet.getLong("lama_parkir");

                        if (typeVehile.toLowerCase().equals("roda dua")){
                            Integer parkingFee = 2000;

                            if (longParkingTime >= 120){
                                parkingFee =+ 1000;
                            } else if(longParkingTime >= 240){
                                parkingFee =+ 2000;
                            } else if(longParkingTime >= 360){
                                parkingFee =+ 3000;
                            } else if (longParkingTime >= 480){
                                parkingFee = 6000;
                            }

                            return parkingFee;
                            
                        } else if (typeVehile.toLowerCase().equals("roda empat")) {
                            Integer parkingFee = 3000;

                            if (longParkingTime >= 120){
                                parkingFee =+ 1000;
                            } else if(longParkingTime >= 240){
                                parkingFee =+ 2000;
                            } else if(longParkingTime >= 360){
                                parkingFee =+ 3000;
                            } else if (longParkingTime >= 480){
                                parkingFee = 6000;
                            }

                             return parkingFee;
                        }

                    }

                }

            }


        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
        return null;
    }

    @Override
    public void paid(String numberPlate, Vehicle vehicle) {
        String sql = "UPDATE kendaraan SET lama_parkir = ?, biaya_parkir = ? WHERE plat_nomor = ?";

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, vehicle.getLongParkingTime());
            statement.setInt(2, vehicle.getParkingFee());
            statement.setString(3, numberPlate);

            statement.executeUpdate();

        }catch (SQLException exception){
            throw new RuntimeException(exception);
        }
    }
}
