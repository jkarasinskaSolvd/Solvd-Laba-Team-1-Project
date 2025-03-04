package solvd.laba.service.impl;

import solvd.laba.idao.IDaoAddress;
import solvd.laba.model.Address;
import solvd.laba.service.IAddressService;

import java.util.List;


public class AddressServiceImpl implements IAddressService {
    private final IDaoAddress daoAddress;

    public AddressServiceImpl(IDaoAddress daoAddress) {
        this.daoAddress = daoAddress;
    }

    @Override
    public Address create(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }
        return daoAddress.create(address);
    }

    @Override
    public Address read(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid address ID");
        }
        return daoAddress.read(id);
    }

    @Override
    public List<Address> readAll() {
        return daoAddress.readAll();
    }

    @Override
    public Address update(Address address) {
        if (address == null || address.getId() == null || address.getId() <= 0) {
            throw new IllegalArgumentException("Invalid address");
        }
        return daoAddress.update(address);
    }

    @Override
    public Long remove(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid address ID");
        }
        return daoAddress.remove(id);
    }
}