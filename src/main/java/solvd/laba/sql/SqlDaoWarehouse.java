package solvd.laba.sql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solvd.laba.idao.IDaoWarehouse;
import solvd.laba.model.Product;
import solvd.laba.model.TransportType;
import solvd.laba.model.Warehouse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoWarehouse extends SqlAbstractDao implements IDaoWarehouse {
    static final Logger logger = LoggerFactory.getLogger(SqlDaoWarehouse.class);
    @Override
    public Warehouse create(Warehouse entity) {
        String sqlStatement = "INSERT INTO Warehouses (address_id) VALUES (?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entity.getAddress().getId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                    logger.info("Executed INSERT INTO Warehouses with id {}", entity.getId());
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        createConnections(entity);
        return entity;
    }

    @Override
    public Warehouse read(Long id) {
        String sqlStatement = "SELECT * FROM Warehouses WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("Executed SELECT FROM Warehouses with id {}", id);
                    return new Warehouse.Builder()
                            .id(resultSet.getLong("id"))
                            .address(new SqlDaoAddress().read(resultSet.getLong("address_id")))
                            .availableProducts(new SqlDaoProduct().readByWarehouse(resultSet.getLong("id")))
                            .allowedTransportTypes(readTransportTypes(resultSet.getLong("id")))
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
    public List<Warehouse> readAll() {
        String sqlStatement = "SELECT * FROM Warehouses";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Warehouse> warehouses = new ArrayList<>();
                while (resultSet.next()) {
                    warehouses.add(new Warehouse.Builder()
                            .id(resultSet.getLong("id"))
                            .address(new SqlDaoAddress().read(resultSet.getLong("address_id")))
                            .availableProducts(new SqlDaoProduct().readByWarehouse(resultSet.getLong("id")))
                            .allowedTransportTypes(readTransportTypes(resultSet.getLong("id")))
                            .build());
                }
                logger.info("Executed full SELECT FROM Warehouses");
                return warehouses;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Warehouse update(Warehouse entity) {
        String sqlStatement = "UPDATE Warehouses SET address_id = ? WHERE id = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entity.getAddress().getId());
            preparedStatement.setLong(2, entity.getId());
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        removeConnections(entity.getId());
        createConnections(entity);
        logger.info("Executed UPDATE Warehouses with id {}", entity.getId());
        return entity;
    }

    @Override
    public Long remove(Long id) {
        removeConnections(id);
        String sqlStatement = "DELETE FROM Warehouses WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Executed DELETE FROM Warehouses with id {}", id);
        return id;
    }

    private void createConnections(Warehouse entity) {
        for (Product product : entity.getAvailableProducts()) {
            String sqlStatement = "INSERT INTO WarehouseProducts (warehouse_id, product_id) VALUES (?, ?)";
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
                preparedStatement.setLong(1, entity.getId());
                preparedStatement.setLong(2, product.getId());
                preparedStatement.executeUpdate();
                logger.info("Executed INSERT INTO WarehouseProducts with warehouse_id {}", entity.getId());
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (TransportType transportType : entity.getAllowedTransportTypes()) {
            String sqlStatement = "INSERT INTO WarehouseAllowedTransport (warehouse_allowed_transport_id, transport_type_warehouse) VALUES (?, ?)";
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
                preparedStatement.setLong(1, entity.getId());
                preparedStatement.setString(2, transportType.toString());
                preparedStatement.executeUpdate();
                logger.info("Executed INSERT INTO WarehouseAllowedTransport with warehouse_allowed_transport_id {}", entity.getId());
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void removeConnections(Long id) {
        String sqlStatement = "DELETE FROM WarehouseProducts WHERE warehouse_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("Executed DELETE FROM WarehouseProducts with warehouse_id {}", id);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        sqlStatement = "DELETE FROM WarehouseAllowedTransport WHERE warehouse_allowed_transport_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("Executed DELETE FROM WarehouseAllowedTransport with warehouse_allowed_transport_id {}", id);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<TransportType> readTransportTypes(Long id) {
        String sqlStatement = "SELECT * FROM WarehouseAllowedTransport WHERE warehouse_allowed_transport_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<TransportType> transportTypes = new ArrayList<>();
                while (resultSet.next()) {
                    transportTypes.add(TransportType.valueOf(resultSet.getString("transport_type_warehouse").toUpperCase()));
                }
                logger.info("Executed SELECT FROM WarehouseAllowedTransport with warehouse_allowed_transport_id {}", id);
                return transportTypes;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
