package com.ignaciosaccomano.proyecto_market.domain.repository;

import com.ignaciosaccomano.proyecto_market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarceProducts(int quantity);
    Product save(Product product);
    void delete(int productId);

}
