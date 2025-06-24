package com.example.Asset.Tracking.System.service.category;

import com.example.Asset.Tracking.System.dto.CategoryDto;
import com.example.Asset.Tracking.System.entity.Category;
import com.example.Asset.Tracking.System.request.AddCategoryRequest;
import com.example.Asset.Tracking.System.request.UpdateCategoryRequest;

import java.util.List;

public interface ICategoryService {
    Category findById(long id);

    Category findByName(String name);


    Category addCategory(AddCategoryRequest categoryRequest);

    List<Category> getAllCategories();


    Category updateCategory(UpdateCategoryRequest category, Long id);

    String deleteCategory(String name);

    CategoryDto covertToDto(Category category);

    List<CategoryDto> convertAllToDto(List<Category> category);

}
