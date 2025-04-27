package com.example.e_commerce.service.product;


import com.example.e_commerce.dto.ImageDto;
import com.example.e_commerce.dto.ProductDto;
import com.example.e_commerce.exceptions.AlreadyExistsException;
import com.example.e_commerce.exceptions.ResourceNotFoundException;
import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Image;
import com.example.e_commerce.model.Product;
import com.example.e_commerce.repository.CategoryRepository;
import com.example.e_commerce.repository.ImageRepository;
import com.example.e_commerce.repository.ProductRepository;
import com.example.e_commerce.request.AddProductRequest;
import com.example.e_commerce.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;


    @Override
    public Product addProduct(AddProductRequest request) {
        // check if category is found
        // if yes set it as new product category
        // if no create a new category
        // then set as the new product category
        if (productExists(request.getName(), request.getBrand())) {
            throw new AlreadyExistsException(request.getBrand() + " " + request.getName() +" already exists you may update it");
        }
        Category category = Optional
                .ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(
                        ()->{
                            Category newCategory = new Category(request.getCategory().getName());
                            return categoryRepository.save(newCategory);
                        }
                );
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private boolean productExists(String name, String brand) {
        return productRepository.existsByNameAndBrand(name, brand);
    }


    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }



    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long Id) {
        return productRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete,()-> {
                    throw new ResourceNotFoundException("Product not found");
                });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest product, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, product))
                .map(productRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }



    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());


        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);


        return existingProduct;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }
    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product,ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image,ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
