package solvd.laba.sql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solvd.laba.idao.IDaoProduct;
import solvd.laba.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoProduct extends SqlAbstractDao implements IDaoProduct {
    static final Logger logger = LoggerFactory.getLogger(SqlDaoProduct.class);
    @Override
    public Product create(Product entity) {
        String sqlStatement = "INSERT INTO Products (name, price, volume) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBigDecimal(2, entity.getPrice());
            preparedStatement.setBigDecimal(3, entity.getVolume());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                    logger.info("Executed INSERT INTO Products with id {}", entity.getId());
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public Product read(Long id) {
        String sqlStatement = "SELECT * FROM Products WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    logger.info("Executed SELECT FROM Products with id {}", id);
                    return new Product.Builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .price(resultSet.getBigDecimal("price"))
                            .volume(resultSet.getBigDecimal("volume"))
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
    public List<Product> readAll() {
        String sqlStatement = "SELECT * FROM Products";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(new Product.Builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .price(resultSet.getBigDecimal("price"))
                            .volume(resultSet.getBigDecimal("volume"))
                            .build());
                }
                logger.info("Executed full SELECT FROM Products");
                return products;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(Product entity) {
        String sqlStatement = "UPDATE Products SET name = ?, price = ?, volume = ? WHERE id = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setBigDecimal(2, entity.getPrice());
            preparedStatement.setBigDecimal(3, entity.getVolume());
            preparedStatement.setLong(4, entity.getId());
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Executed UPDATE Products with id {}", entity.getId());
        return entity;
    }

    @Override
    public Long remove(Long id) {
        removeConnections(id);
        String sqlStatement = "DELETE FROM Products WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Executed DELETE FROM Products with id {}", id);
        return id;
    }

    @Override
    public List<Product> readByWarehouse(long warehouseId) {
        String sqlStatement = "SELECT * FROM WarehouseProducts WHERE warehouse_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, warehouseId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                logger.info("Executed SELECT FROM WarehouseProducts with warehouse_id {}", warehouseId);
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = read(resultSet.getLong("product_id"));
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
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
        new SqlDaoOrderItem().removeByProduct(id);
    }
}
