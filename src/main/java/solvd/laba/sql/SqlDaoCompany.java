package solvd.laba.sql;


import solvd.laba.idao.IDaoCompany;
import solvd.laba.model.Company;
import solvd.laba.model.Transport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoCompany extends SqlAbstractDao implements IDaoCompany {

    @Override
    public Company create(Company entity) {
        String sqlStatement = "INSERT INTO LogisticsCompanies (name) VALUES (?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        createConnections(entity);
        return entity;
    }

    @Override
    public Company read(Long id) {
        String sqlStatement = "SELECT * FROM LogisticsCompanies WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return new Company.Builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .availableVehicles(new SqlDaoTransport().readByCompany(id))
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
    public List<Company> readAll() {
        String sqlStatement = "SELECT * FROM LogisticsCompanies";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<Company> companies = new ArrayList<>();
                while (resultSet.next()) {
                    companies.add(new Company.Builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .availableVehicles(new SqlDaoTransport().readByCompany(resultSet.getLong("id")))
                            .build());
                }
                return companies;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company update(Company entity) {
        String sqlStatement = "UPDATE LogisticsCompanies SET name = ? WHERE id = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setLong(2, entity.getId());
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        removeConnections(entity.getId());
        createConnections(entity);
        return entity;
    }

    @Override
    public Long remove(Long id) {
        removeConnections(id);
        String sqlStatement = "DELETE FROM LogisticsCompanies WHERE id = ?";
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

    private void createConnections(Company entity) {
        for (Transport transport : entity.getAvailableVehicles()) {
            String sqlStatement = "INSERT INTO CompanyTransport (company_id, transport_id) VALUES (?, ?)";
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
                preparedStatement.setLong(1, entity.getId());
                preparedStatement.setLong(2, transport.getId());
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void removeConnections(Long id) {
        String sqlStatement = "DELETE FROM CompanyTransport WHERE company_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
