package solvd.laba.idao;

import solvd.laba.model.Transport;

import java.util.List;

public interface IDaoTransport extends IDao<Transport> {
    List<Transport> readByCompany(Long companyId);
}
