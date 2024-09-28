package com.crud.productinventory.mapper;

import com.crud.productinventory.dto.ProductDto;
import com.crud.productinventory.entity.Product;

public class ProductMapper {

    public static ProductDto mapToProductDto(Product product, ProductDto productDto){
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setType(product.getType());
        productDto.setQuantity(product.getQuantity());
        productDto.setUnitPrice(product.getUnitPrice());
        return productDto;
    }

    public static Product mapToProduct(ProductDto productDto, Product product){
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setType(productDto.getType());
        product.setQuantity(productDto.getQuantity());
        product.setUnitPrice(productDto.getUnitPrice());
        return product;
    }
}
