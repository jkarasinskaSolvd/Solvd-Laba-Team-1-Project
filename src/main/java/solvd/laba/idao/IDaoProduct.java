package solvd.laba.idao;

import solvd.laba.model.Product;

import java.util.List;

public interface IDaoProduct extends IDao<Product> {
    List<Product> readByWarehouse(long id);
}
