package solvd.laba.sql;

import solvd.laba.idao.IDaoOrderItem;
import solvd.laba.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoOrderItem extends SqlAbstractDao implements IDaoOrderItem {
    //Impossible to implement (too little data)
    @Override
    public OrderItem create(OrderItem entity) {
        return null;
    }

    @Override
    public OrderItem createByOrder(OrderItem entity, Long orderId) {
        String sqlStatement = "INSERT INTO OrderItems (order_id, product_id_order_items, quantity_order_items) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, entity.getProduct().getId());
            preparedStatement.setInt(3, entity.getQuantity());
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
    public OrderItem read(Long id) {
        return null;
    }

    @Override
    public OrderItem readByIds(Long orderId, Long productId) {
        String sqlStatement = "SELECT * FROM OrderItems WHERE order_id = ? AND product_id_order_items = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return new OrderItem.Builder()
                            .product(new SqlDaoProduct().read(resultSet.getLong("product_id_order_items")))
                            .quantity(resultSet.getInt("quantity_order_items"))
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
    public List<OrderItem> readAll() {
        String sqlStatement = "SELECT * FROM OrderItems";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<OrderItem> items = new ArrayList<>();
                while (resultSet.next()) {
                    items.add(new OrderItem.Builder()
                            .product(new SqlDaoProduct().read(resultSet.getLong("product_id_order_items")))
                            .quantity(resultSet.getInt("quantity_order_items"))
                            .build());
                }
                return items;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderItem> readByOrder(long orderId) {
        String sqlStatement = "SELECT * FROM OrderItems WHERE order_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<OrderItem> items = new ArrayList<>();
                while (resultSet.next()) {
                    items.add(new OrderItem.Builder()
                            .product(new SqlDaoProduct().read(resultSet.getLong("product_id_order_items")))
                            .quantity(resultSet.getInt("quantity_order_items"))
                            .build());
                }
                return items;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Impossible to implement (too little data)
    @Override
    public OrderItem update(OrderItem entity) {
        return null;
    }

    @Override
    public OrderItem updateByOrder(OrderItem entity, Long orderId) {
        String sqlStatement = "UPDATE OrderItems SET quantity_order_items = ? WHERE order_id = ? AND product_id_order_items = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, entity.getQuantity());
            preparedStatement.setLong(2, orderId);
            preparedStatement.setLong(3, entity.getProduct().getId());
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
    public Boolean removeByIds(Long orderId, Long productId) {
        String sqlStatement = "DELETE FROM OrderItems WHERE order_id = ? AND product_id_order_items = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, productId);
            if ( preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean removeByOrder(Long orderId) {
        String sqlStatement = "DELETE FROM OrderItems WHERE order_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, orderId);
            if ( preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Boolean removeByProduct(Long productId) {
        String sqlStatement = "DELETE FROM OrderItems WHERE product_id_order_items = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(2, productId);
            if ( preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


}
