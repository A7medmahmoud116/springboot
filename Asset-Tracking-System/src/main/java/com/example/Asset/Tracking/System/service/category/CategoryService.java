package com.example.Asset.Tracking.System.service.category;

import com.example.Asset.Tracking.System.dto.CategoryDto;
import com.example.Asset.Tracking.System.entity.Category;
import com.example.Asset.Tracking.System.exceptions.AlreadyExistException;
import com.example.Asset.Tracking.System.exceptions.ResourceNotFound;
import com.example.Asset.Tracking.System.repository.CategoryRepository;
import com.example.Asset.Tracking.System.request.AddCategoryRequest;
import com.example.Asset.Tracking.System.request.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFound("category with this ID not found"));
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(()-> new ResourceNotFound("category with this name not found"));
    }

    @Override
    public Category addCategory(AddCategoryRequest categoryRequest) {
        if(categoryRepository.existsByName(categoryRequest.getName())){
            throw new AlreadyExistException(categoryRequest.getName() + " Already exists");
        }
        Category category = createCategory(categoryRequest);
        return categoryRepository.save(category);
    }

    private Category createCategory(AddCategoryRequest categoryRequest) {
        return new Category(categoryRequest.getName());
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(UpdateCategoryRequest category, Long id) {
        Category oldCategory = findById(id);
        oldCategory.setName(category.getName());
        return categoryRepository.save(oldCategory);
    }
    @Transactional
    @Override
    public String deleteCategory(String name){
        Category category = findByName(name);
        String catName = category.getName();
        categoryRepository.delete(category);
        return catName;
    }
    @Override
    public CategoryDto covertToDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
    @Override
    public List<CategoryDto> convertAllToDto(List<Category> category){
        return category.stream().map(this::covertToDto).toList();
    }

}
