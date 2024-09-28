package com.crud.productinventory.service;

import com.crud.productinventory.dto.ProductDto;
import com.crud.productinventory.entity.Product;

import java.util.List;

public interface IProductService {

    void createProduct(ProductDto productDto);

    ProductDto findProduct(Long id);

    ProductDto findProductName(String name);

    List<Product> findAllProducts();

    boolean updateProduct(ProductDto productDto);

    boolean deleteProduct(Long id);


}
