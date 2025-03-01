package solvd.laba.service.impl;

import solvd.laba.idao.IDaoProduct;
import solvd.laba.model.Product;
import solvd.laba.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    private final IDaoProduct productDao;

    public ProductServiceImpl(IDaoProduct productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product read(Long id) {
        return productDao.read(id);
    }

    @Override
    public List<Product> readAll() {
        return productDao.readAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public Long remove(Long id) {
        return productDao.remove(id);
    }
}
