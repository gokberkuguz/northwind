package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.productRequests.CreateProductRequest;
import com.etiya.northwind.business.requests.productRequests.UpdateProductRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public List<ProductListResponse> getAll(){
        return this.productService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateProductRequest createProductRequest){
        this.productService.add(createProductRequest);
    }

    @PostMapping("/update")
    public void update(@RequestBody @Valid UpdateProductRequest updateProductRequest){
        this.productService.update(updateProductRequest);
    }

    @DeleteMapping("/delete/{productId}")
    public void delete(@Valid @PathVariable int productId){
        this.productService.delete(productId);
    }

    @GetMapping("/getbyid/{productId}")
    public ProductListResponse getById(@PathVariable int productId){
        return this.productService.getById(productId);
    }

    @GetMapping("/getByPage/{pageNumber}/{productAmountInPage}")
    public PageDataResponse<ProductListResponse> getByPage(int pageNumber, int productAmountInPage){
        return this.productService.getByPage(pageNumber,productAmountInPage);
    }

    @GetMapping("/getByPageWithSorting/{pageNumber}/{productAmountInPage}/{fieldName}/{isAsc}")
    public PageDataResponse<ProductListResponse> getByPageWithSorting(int pageNumber, int productAmountInPage, String fieldName, boolean isAsc){
        return this.productService.getByPageWithSorting(pageNumber,productAmountInPage,fieldName,isAsc);
    }
}
