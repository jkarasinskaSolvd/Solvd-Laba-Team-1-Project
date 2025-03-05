package solvd.laba.service.impl;

import solvd.laba.idao.IDaoTransport;
import solvd.laba.model.Transport;
import solvd.laba.service.ITransportService;

import java.util.List;

public class TransportServiceImpl implements ITransportService {
    private final IDaoTransport daoTransport;

    public TransportServiceImpl(IDaoTransport daoTransport) {
        this.daoTransport = daoTransport;
    }

    @Override
    public Transport create(Transport transport) {
        if (transport == null) {
            throw new IllegalArgumentException("Transport cannot be null");
        }
        return daoTransport.create(transport);
    }

    @Override
    public Transport read(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid transport ID");
        }
        return daoTransport.read(id);
    }

    @Override
    public List<Transport> readAll() {
        return daoTransport.readAll();
    }

    @Override
    public Transport update(Transport transport) {
        if (transport == null || transport.getId() == null) {
            throw new IllegalArgumentException("Invalid transport or transport ID");
        }
        return daoTransport.update(transport);
    }

    @Override
    public Long remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid transport ID");
        }
        return daoTransport.remove(id);
    }

    @Override
    public List<Transport> readByCompany(Long companyId) {
        if (companyId == null) {
            throw new IllegalArgumentException("Invalid company ID");
        }
        return daoTransport.readByCompany(companyId);
    }
}
