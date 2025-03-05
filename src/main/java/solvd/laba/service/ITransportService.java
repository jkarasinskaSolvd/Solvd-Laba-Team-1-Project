package solvd.laba.service;

import solvd.laba.model.Transport;

import java.util.List;

public interface ITransportService extends GenericService<Transport>{
    List<Transport> readByCompany(Long companyId);
}
