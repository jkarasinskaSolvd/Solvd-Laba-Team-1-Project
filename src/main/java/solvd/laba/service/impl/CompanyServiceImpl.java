package solvd.laba.service.impl;


import solvd.laba.idao.IDaoCompany;
import solvd.laba.model.Company;
import solvd.laba.model.Transport;
import solvd.laba.service.ICompanyService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static solvd.laba.sql.SqlAbstractDao.connectionPool;


public class CompanyServiceImpl implements ICompanyService {

    private final IDaoCompany daoCompany;

    public CompanyServiceImpl(IDaoCompany daoCompany) {
        this.daoCompany = daoCompany;
    }
    Connection getConnection() throws SQLException, InterruptedException {
        return connectionPool.getConnection();
    }

    @Override
    public Company create(Company company) {

        Company createdCompany = daoCompany.create(company);
        createConnections(createdCompany);
        return createdCompany;
    }

    @Override
    public Company read(Long id) {
        return daoCompany.read(id);
    }

    @Override
    public List<Company> readAll() {
        return daoCompany.readAll();
    }

    @Override
    public Company update(Company company) {
        Company updatedCompany = daoCompany.update(company);
        removeConnections(company.getId());
        createConnections(updatedCompany);
        return updatedCompany;
    }

    @Override
    public Long remove(Long id) {
        removeConnections(id);
        return daoCompany.remove(id);
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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}