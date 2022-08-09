package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categoryRequests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categoryRequests.UpdateCategoryRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.CategoryRepository;
import com.etiya.northwind.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateCategoryRequest createCategoryRequest) {
        Category category = this.modelMapperService.forRequest().map(createCategoryRequest, Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public void update(UpdateCategoryRequest updateCategoryRequest) {
        Category category = this.modelMapperService.forRequest().map(updateCategoryRequest, Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(int categoryId) {

        if(this.categoryRepository.existsById(categoryId)){
            this.categoryRepository.deleteById(categoryId);
        }
        else{
            System.out.println("Geçersiz Kategori ID");
        }

    }

    @Override
    public List<CategoryListResponse> getAll() {
        List<Category> result = this.categoryRepository.findAll();
        List<CategoryListResponse> response =
                result.stream().map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public CategoryListResponse getById(int categoryId) {
        
        CategoryListResponse response = new CategoryListResponse();
        if(this.categoryRepository.existsById(categoryId)){
          Category category = this.categoryRepository.findById(categoryId).get();
          response = modelMapperService.forResponse().map(category, CategoryListResponse.class);
        }
        else{
            System.out.println("Geçersiz Kategori ID");
        }
        return response;
    }

    @Override
    public PageDataResponse<CategoryListResponse> getByPage(int pageNumber, int categoryAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber-1,categoryAmountInPage);
        Page<Category> pages = this.categoryRepository.findAllCategories(pageable);
        List<CategoryListResponse> response =
                pages.getContent().stream().map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<CategoryListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    @Override
    public PageDataResponse<CategoryListResponse> getByPageWithSorting(int pageNumber, int categoryAmountInPage, String fieldName, boolean isAsc) {
        if (Arrays.stream(Category.class.getDeclaredFields()).noneMatch(field -> field.getName().equals(fieldName))){
            System.out.println("Field does not exist.");
            return new PageDataResponse<>(new ArrayList<CategoryListResponse>(), 0, 0, 0);
        }
        Pageable pageable;
        if (isAsc){
            pageable = PageRequest.of(pageNumber-1,categoryAmountInPage, Sort.by(fieldName).ascending());
        }else {
            pageable = PageRequest.of(pageNumber-1,categoryAmountInPage, Sort.by(fieldName).descending());
        }
        Page<Category> pages = this.categoryRepository.findAllCategories(pageable);
        List<CategoryListResponse> response =
                pages.getContent().stream().map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<CategoryListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }
}
