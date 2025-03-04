package solvd.laba.service.impl;

import solvd.laba.idao.IDaoTransport;
import solvd.laba.model.Transport;
import solvd.laba.service.ITransportService;

import java.util.List;

public class TransportService implements ITransportService {
    private final IDaoTransport transportDao;

    public TransportService(IDaoTransport transportDao) {
        this.transportDao = transportDao;
    }

    @Override
    public Transport create(Transport transport) {
        return transportDao.create(transport);
    }

    @Override
    public Transport read(Long id) {
        return transportDao.read(id);
    }

    @Override
    public List<Transport> readAll() {
        return transportDao.readAll();
    }

    @Override
    public Transport update(Transport transport) {
        return transportDao.update(transport);
    }

    @Override
    public Long remove(Long id) {
        return transportDao.remove(id);
    }
}
