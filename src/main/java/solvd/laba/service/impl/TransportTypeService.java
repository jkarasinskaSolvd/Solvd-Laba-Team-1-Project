package solvd.laba.service.impl;

import solvd.laba.model.TransportType;
import solvd.laba.service.ITransportTypeService;

import java.util.Arrays;
import java.util.List;

public class TransportTypeService implements ITransportTypeService {
    @Override
    public List<TransportType> getAllTransportTypes() {
        return Arrays.asList(TransportType.values());
    }
}
