package solvd.laba.sql;

import solvd.laba.idao.IDaoDistance;
import solvd.laba.model.Distance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoDistance extends SqlAbstractDao implements IDaoDistance {

    @Override
    public Distance create(Distance entity) {
        String sqlStatement = "INSERT INTO Distance (warehouse_id_distance, delivery_address_id_distance, distance_km) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entity.getWarehouse().getId());
            preparedStatement.setLong(2, entity.getDeliveryAddress().getId());
            preparedStatement.setBigDecimal(3, entity.getDistance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    //Impossible to implement
    @Override
    public Distance read(Long id) {
        return null;
    }

    @Override
    public List<Distance> readAll() {
        String sqlStatement = "SELECT * FROM Distance";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<Distance> distances = new ArrayList<>();
                while (resultSet.next()) {
                    distances.add(new Distance.Builder()
                            .warehouse(new SqlDaoWarehouse().read(resultSet.getLong("warehouse_id_distance")))
                            .deliveryAddress(new SqlDaoAddress().read(resultSet.getLong("delivery_address_id_distance")))
                            .distance(resultSet.getBigDecimal("distance_km"))
                            .build());
                }
                return distances;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Distance update(Distance entity) {
        String sqlStatement = "UPDATE Address SET distance_km = ? WHERE warehouse_id_distance = ? AND delivery_address_id_distance = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setBigDecimal(1, entity.getDistance());
            preparedStatement.setLong(2, entity.getWarehouse().getId());
            preparedStatement.setLong(3, entity.getDeliveryAddress().getId());
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    //Impossible to implement
    @Override
    public Long remove(Long id) {
        return null;
    }

    @Override
    public Distance readByIds(Long warehouseId, Long deliveryAddressId) {
        String sqlStatement = "SELECT * FROM Distance WHERE warehouse_id_distance = ? AND delivery_address_id_distance = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, warehouseId);
            preparedStatement.setLong(2, deliveryAddressId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return new Distance.Builder()
                            .warehouse(new SqlDaoWarehouse().read(resultSet.getLong("warehouse_id_distance")))
                            .deliveryAddress(new SqlDaoAddress().read(resultSet.getLong("delivery_address_id_distance")))
                            .distance(resultSet.getBigDecimal("distance_km"))
                            .build();
                } else {
                    return null;
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean removeByIds(Long warehouseId, Long deliveryAddressId) {
        String sqlStatement = "DELETE FROM Distance WHERE warehouse_id_distance = ? AND delivery_address_id_distance = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, warehouseId);
            preparedStatement.setLong(2, deliveryAddressId);
            if ( preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean removeByDelivery(Long deliveryAddressId) {
        String sqlStatement = "DELETE FROM Distance WHERE delivery_address_id_distance = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, deliveryAddressId);
            if ( preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean removeByWarehouse(Long warehouseId) {
        String sqlStatement = "DELETE FROM Distance WHERE warehouse_id_distance = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, warehouseId);
            if ( preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
