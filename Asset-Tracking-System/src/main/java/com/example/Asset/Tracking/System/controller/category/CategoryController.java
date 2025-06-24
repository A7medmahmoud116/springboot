package com.example.Asset.Tracking.System.controller.category;

import com.example.Asset.Tracking.System.dto.CategoryDto;
import com.example.Asset.Tracking.System.entity.Category;
import com.example.Asset.Tracking.System.repository.CategoryRepository;
import com.example.Asset.Tracking.System.request.AddCategoryRequest;
import com.example.Asset.Tracking.System.request.UpdateCategoryRequest;
import com.example.Asset.Tracking.System.response.ApiResponse;
import com.example.Asset.Tracking.System.service.category.CategoryService;
import com.example.Asset.Tracking.System.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDto = categoryService.convertAllToDto(categories);
        return ResponseEntity.ok(new ApiResponse("Success",categoryDto));
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody AddCategoryRequest category){
        Category savedCategory = categoryService.addCategory(category);
        CategoryDto categoryDto = categoryService.covertToDto(savedCategory);
        return ResponseEntity.ok(new ApiResponse("Success",categoryDto));
    }
    @GetMapping("/name")
    public ResponseEntity<ApiResponse> getCategoryByName(@RequestParam String name){
        Category category = categoryService.findByName(name);
        CategoryDto categoryDtos = categoryService.covertToDto(category);
        return ResponseEntity.ok(new ApiResponse("Success",categoryDtos));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = categoryService.covertToDto(category);
        return ResponseEntity.ok(new ApiResponse("Success",categoryDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(
            @RequestBody UpdateCategoryRequest categoryRequest,
            @PathVariable Long id
            ){
        Category category = categoryService.updateCategory(categoryRequest,id);
        CategoryDto categoryDto = categoryService.covertToDto(category);
        return ResponseEntity.ok(new ApiResponse("Success",categoryDto));
    }
    @DeleteMapping("")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestParam String name){
        String catName = categoryService.deleteCategory(name);
        return ResponseEntity.ok(
                new ApiResponse(
                        "Category with name: " + catName + " Deleted successfully",
                        null)
        );
    }


}
