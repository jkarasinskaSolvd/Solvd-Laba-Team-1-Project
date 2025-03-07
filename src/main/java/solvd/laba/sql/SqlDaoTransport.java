package solvd.laba.sql;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solvd.laba.idao.IDaoTransport;
import solvd.laba.model.Transport;
import solvd.laba.model.TransportType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDaoTransport extends SqlAbstractDao implements IDaoTransport {
    static final Logger logger = LoggerFactory.getLogger(SqlDaoTransport.class);
    @Override
    public Transport create(Transport entity) {
        String sqlStatement = "INSERT INTO Transport (type, max_capacity, cost_per_km) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getType().toString());
            preparedStatement.setBigDecimal(2, entity.getMaxCapacity());
            preparedStatement.setBigDecimal(3, entity.getCostPerKm());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                    logger.info("Executed INSERT INTO Transport with id {}", entity.getId());
                }
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public Transport read(Long id) {
        String sqlStatement = "SELECT * FROM Transport WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    logger.info("Executed SELECT FROM Transport with id {}", id);
                    return new Transport.Builder()
                            .id(resultSet.getLong("id"))
                            .type(TransportType.valueOf(resultSet.getString("type").toUpperCase()))
                            .maxCapacity(resultSet.getBigDecimal("max_capacity"))
                            .costPerKm(resultSet.getBigDecimal("cost_per_km"))
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
    public List<Transport> readAll() {
        String sqlStatement = "SELECT * FROM Transport";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Transport> transports = new ArrayList<>();
                while (resultSet.next()) {
                    transports.add(new Transport.Builder()
                            .id(resultSet.getLong("id"))
                            .type(TransportType.valueOf(resultSet.getString("type").toUpperCase()))
                            .maxCapacity(resultSet.getBigDecimal("max_capacity"))
                            .costPerKm(resultSet.getBigDecimal("cost_per_km"))
                            .build());
                }
                logger.info("Executed full SELECT FROM Transport");
                return transports;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transport update(Transport entity) {
        String sqlStatement = "UPDATE Transport SET type = ?, max_capacity = ?, cost_per_km = ? WHERE id = ?;";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getType().toString());
            preparedStatement.setBigDecimal(2, entity.getMaxCapacity());
            preparedStatement.setBigDecimal(3, entity.getCostPerKm());
            preparedStatement.setLong(4, entity.getId());
            if ( preparedStatement.executeUpdate() == 0) {
                return null;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Executed UPDATE Transport with id {}", entity.getId());
        return entity;
    }

    @Override
    public Long remove(Long id) {
        removeConnections(id);
        String sqlStatement = "DELETE FROM Transport WHERE id = ?";
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
        logger.info("Executed DELETE FROM Transport with id {}", id);
        return id;
    }

    @Override
    public List<Transport> readByCompany(Long companyId) {
        String sqlStatement = "SELECT * FROM CompanyTransport WHERE company_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, companyId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                logger.info("Executed SELECT FROM CompanyTransport with company_id {}", companyId);
                List<Transport> transports = new ArrayList<>();
                while (resultSet.next()) {
                    Transport transport = read(resultSet.getLong("transport_id"));
                    transports.add(transport);
                }
                return transports;
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeConnections(Long id) {
        String sqlStatement = "DELETE FROM CompanyTransport WHERE transport_id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("Executed DELETE FROM CompanyTransport with transport_id {}", id);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
