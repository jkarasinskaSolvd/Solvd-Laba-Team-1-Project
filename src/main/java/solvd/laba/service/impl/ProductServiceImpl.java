package solvd.laba.service.impl;

import solvd.laba.idao.IDaoProduct;
import solvd.laba.model.Product;
import solvd.laba.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    private final IDaoProduct daoProduct;

    public ProductServiceImpl(IDaoProduct daoProduct) {

        this.daoProduct = daoProduct;
    }

    @Override
    public Product create(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return daoProduct.create(product);
    }

    @Override
    public Product read(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        return daoProduct.read(id);
    }

    @Override
    public List<Product> readAll() {
        return daoProduct.readAll();
    }

    @Override
    public Product update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Invalid product or productID");
        }
        return daoProduct.update(product);
    }

    @Override
    public Long remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        return daoProduct.remove(id);
    }
}
