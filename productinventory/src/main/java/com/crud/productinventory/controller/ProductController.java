package com.crud.productinventory.controller;

import com.crud.productinventory.constants.Constants;
import com.crud.productinventory.dto.ProductDto;
import com.crud.productinventory.dto.ResponseDto;
import com.crud.productinventory.entity.Product;
import com.crud.productinventory.service.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/product", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class ProductController {

    private IProductService iProductService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody ProductDto productDto){
        iProductService.createProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @GetMapping("/findAll")
    public List<Product> fetchAllProduct(){
        List<Product> products = iProductService.findAllProducts();
        if(products.isEmpty()){
            throw new RuntimeException("No products to display");
        }
        return products;
    }

    @GetMapping("/findProductId")
    public ResponseEntity<ProductDto> findProductId(@RequestParam Long id){
        ProductDto productDto = iProductService.findProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @GetMapping("/findProductName")
    public ResponseEntity<ProductDto> findProductName(@RequestParam String name){
        ProductDto productDto = iProductService.findProductName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProduct(@Valid @RequestBody ProductDto productDto) {
        boolean isUpdated = iProductService.updateProduct(productDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(Constants.STATUS_417, Constants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteProduct(@RequestParam Long id) {
        boolean isDeleted = iProductService.deleteProduct(id);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(Constants.STATUS_200, Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(Constants.STATUS_417, Constants.MESSAGE_417_DELETE));
        }
    }

}
