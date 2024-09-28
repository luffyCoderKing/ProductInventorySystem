package com.crud.productinventory.service.impl;

import com.crud.productinventory.dto.ProductDto;
import com.crud.productinventory.entity.Product;
import com.crud.productinventory.exception.ProductAlreadyExistsException;
import com.crud.productinventory.exception.ResourceNotFoundException;
import com.crud.productinventory.mapper.ProductMapper;
import com.crud.productinventory.repository.ProductRepository;
import com.crud.productinventory.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto, new Product());

        if(isTypeNotValid(product.getType())){
            throw new RuntimeException("Product type is not valid! " +
                    "Product types are either: Food, Sports, Household, Music, Electronic,and Appliance");
        }
        Optional<Product> optionalCustomer = productRepository.findByName(productDto.getName());
        if(optionalCustomer.isPresent()){
            throw new ProductAlreadyExistsException("Product already exist with given product name "
                    + productDto.getName());
        }

        productRepository.save(product);
    }

    @Override
    public ProductDto findProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", String.valueOf(id))
        );
        return ProductMapper.mapToProductDto(product, new ProductDto());
    }

    @Override
    public ProductDto findProductName(String name) {
        Product product = productRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productName", name)
        );
        return ProductMapper.mapToProductDto(product, new ProductDto());
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        boolean isUpdated = false;
        if(productDto !=null ){

            if(isTypeNotValid(productDto.getType())){
                throw new RuntimeException("Product type is not valid! " +
                        "Product types are either: Food, Sports, Household, Music, Electronic,and Appliance");
            }

            Product product = productRepository.findByName(productDto.getName()).orElseThrow(
                        () -> new ResourceNotFoundException("Product", "productName", productDto.getName())
            );

            ProductMapper.mapToProduct(productDto, product);

            productRepository.save(product);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", String.valueOf(id))
        );
        productRepository.deleteById(product.getId());
        return true;
    }

    private boolean isTypeNotValid(String type){
        boolean isTypeNotValid = false;
        Set<String> typeSet = new HashSet<>();
        typeSet.add("Food");
        typeSet.add("Sports");
        typeSet.add("Household");
        typeSet.add("Music");
        typeSet.add("Electronic");
        typeSet.add("Appliance");

        if(!typeSet.contains(type)){
            isTypeNotValid=true;
        }

        return isTypeNotValid;
    }
}
