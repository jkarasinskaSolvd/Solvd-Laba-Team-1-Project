package solvd.laba.sql;


import solvd.laba.idao.IDaoProduct;
import solvd.laba.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoProduct extends SqlAbstractDao implements IDaoProduct {


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
        return id;
    }

    @Override
    public List<Product> readByWarehouse(long warehouseId) {
        String sqlStatement = "SELECT * FROM WarehouseProducts WHERE warehouse_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, warehouseId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = read(resultSet.getLong("transport_id"));
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
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        new SqlDaoOrderItem().removeByProduct(id);
    }
}
