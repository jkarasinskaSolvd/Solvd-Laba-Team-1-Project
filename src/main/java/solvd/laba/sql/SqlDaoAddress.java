package solvd.laba.sql;

import solvd.laba.idao.IDaoAddress;
import solvd.laba.model.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoAddress extends SqlAbstractDao implements IDaoAddress {
    @Override
    public Address create(Address entity) {
        String sqlStatement = "INSERT INTO Address (country, city, street, building, apartment, postal_code, coordinate_x, coordinate_y) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getCountry());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setString(4, entity.getBuilding());
            preparedStatement.setString(5, entity.getApartment());
            preparedStatement.setString(6, entity.getPostalCode());
            preparedStatement.setDouble(7, entity.getX());
            preparedStatement.setDouble(8, entity.getY());
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
    public Address read(Long id) {
        String sqlStatement = "SELECT * FROM Address WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return new Address.Builder()
                            .id(resultSet.getLong("id"))
                            .country(resultSet.getString("country"))
                            .city(resultSet.getString("city"))
                            .street(resultSet.getString("street"))
                            .building(resultSet.getString("building"))
                            .apartment(resultSet.getString("apartment"))
                            .postalCode(resultSet.getString("postal_code"))
                            .x(resultSet.getDouble("coordinate_x"))
                            .y(resultSet.getDouble("coordinate_y"))
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
    public List<Address> readAll() {
        String sqlStatement = "SELECT * FROM Address";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<Address> addresses = new ArrayList<>();
                while (resultSet.next()) {
                    addresses.add(new Address.Builder()
                            .id(resultSet.getLong("id"))
                            .country(resultSet.getString("country"))
                            .city(resultSet.getString("city"))
                            .street(resultSet.getString("street"))
                            .building(resultSet.getString("building"))
                            .apartment(resultSet.getString("apartment"))
                            .postalCode(resultSet.getString("postal_code"))
                            .x(resultSet.getDouble("coordinate_x"))
                            .y(resultSet.getDouble("coordinate_y"))
                            .build());
                }
                return addresses;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address update(Address entity) {
        String sqlStatement = "UPDATE Address SET country = ?, city = ?, street = ?, building = ?, apartment = ?, postal_code = ?, coordinate_x = ?, coordinate_y = ? WHERE id = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getCountry());
            preparedStatement.setString(2, entity.getCity());
            preparedStatement.setString(3, entity.getStreet());
            preparedStatement.setString(4, entity.getBuilding());
            preparedStatement.setString(5, entity.getApartment());
            preparedStatement.setString(6, entity.getPostalCode());
            preparedStatement.setDouble(7, entity.getX());
            preparedStatement.setDouble(8, entity.getY());
            preparedStatement.setLong(9, entity.getId());
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
        String sqlStatement = "DELETE FROM Address WHERE id = ?";
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
