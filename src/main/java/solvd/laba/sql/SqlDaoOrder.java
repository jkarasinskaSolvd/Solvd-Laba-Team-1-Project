package solvd.laba.sql;

import solvd.laba.idao.IDaoOrder;
import solvd.laba.model.Order;
import solvd.laba.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoOrder extends SqlAbstractDao implements IDaoOrder {
    SqlDaoOrderItem sqlDaoOrderItem = new SqlDaoOrderItem();
    @Override
    public Order create(Order entity) {
        String sqlStatement = "INSERT INTO Orders (delivery_address_id) VALUES (?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entity.getAddress().getId());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (OrderItem item : entity.getOrderItems())
        {
            sqlDaoOrderItem.create(item);
        }
        return entity;
    }

    @Override
    public Order read(Long id) {
        String sqlStatement = "SELECT * FROM Orders WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return new Order.Builder()
                            .id(resultSet.getLong("id"))
                            .address(new SqlDaoAddress().read(resultSet.getLong("delivery_address_id")))
                            .orderItems(new SqlDaoOrderItem().readByOrder(resultSet.getLong("id")))
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
    public List<Order> readAll() {
        String sqlStatement = "SELECT * FROM Orders";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<Order> orders = new ArrayList<>();
                while (resultSet.next()) {
                    orders.add(new Order.Builder()
                            .id(resultSet.getLong("id"))
                            .address(new SqlDaoAddress().read(resultSet.getLong("delivery_address_id")))
                            .orderItems(new SqlDaoOrderItem().readByOrder(resultSet.getLong("id")))
                            .build());
                }
                return orders;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order update(Order entity) {
        String sqlStatement = "UPDATE Orders SET delivery_address_id = ? WHERE id = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entity.getAddress().getId());
            preparedStatement.setLong(2, entity.getId());
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        sqlDaoOrderItem.removeByOrder(entity.getId());
//        removeConnections(entity.getId());
        for (OrderItem item : entity.getOrderItems())
        {
            sqlDaoOrderItem.createByOrder(item, entity.getId());
        }
        return entity;
    }

    @Override
    public Long remove(Long id) {
        sqlDaoOrderItem.removeByOrder(id);
        String sqlStatement = "DELETE FROM Orders WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
}
