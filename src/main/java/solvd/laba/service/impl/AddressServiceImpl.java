package solvd.laba.service.impl;

import solvd.laba.idao.IDaoAddress;
import solvd.laba.model.Address;
import solvd.laba.service.IAddressService;

import java.util.List;

public class AddressServiceImpl implements IAddressService {
    private final IDaoAddress addressDao;

    public AddressServiceImpl(IDaoAddress addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    public Address create(Address address) {
        return addressDao.create(address);
    }

    @Override
    public Address read(Long id) {
        return addressDao.read(id);
    }

    @Override
    public List<Address> readAll() {
        return addressDao.readAll();
    }

    @Override
    public Address update(Address address) {
        return addressDao.update(address);
    }

    @Override
    public Long remove(Long id) {
        return addressDao.remove(id);
    }

}