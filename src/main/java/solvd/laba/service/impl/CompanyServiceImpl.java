package solvd.laba.service.impl;


import solvd.laba.idao.IDaoCompany;
import solvd.laba.model.Company;
import solvd.laba.service.ICompanyService;
import java.sql.Connection;
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
        return daoCompany.create(company);
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
        return daoCompany.update(company);
    }

    @Override
    public Long remove(Long id) {
        return daoCompany.remove(id);
    }
}