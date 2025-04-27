package com.example.e_commerce.service.product;

import com.example.e_commerce.dto.ProductDto;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.request.AddProductRequest;
import com.example.e_commerce.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest product);

    List<Product> getAllProducts();

    Product getProductById(Long Id);

    void deleteProductById(Long productId);

    Product updateProduct(ProductUpdateRequest product, Long productId);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
