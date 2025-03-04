package solvd.laba.service.impl;


import solvd.laba.idao.IDaoCompany;
import solvd.laba.model.Company;
import solvd.laba.service.ICompanyService;

import java.util.List;

public class CompanyServiceImpl implements ICompanyService {
    private final IDaoCompany companyDao;
    public CompanyServiceImpl(IDaoCompany companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public Company create(Company company) {
        return companyDao.create(company);
    }

    @Override
    public Company read(Long id) {
        return companyDao.read(id);
    }

    @Override
    public List<Company> readAll() {
        return companyDao.readAll();
    }

    @Override
    public Company update(Company company) {
        return companyDao.update(company);
    }

    @Override
    public Long remove(Long id) {
        return companyDao.remove(id);
    }

}