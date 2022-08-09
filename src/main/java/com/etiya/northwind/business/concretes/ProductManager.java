package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.productRequests.CreateProductRequest;
import com.etiya.northwind.business.requests.productRequests.UpdateProductRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.ProductRepository;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {
    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public ProductManager(ProductRepository productRepository, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateProductRequest createProductRequest) {
        Product product = this.modelMapperService.forRequest().map(createProductRequest, Product.class);
        productRepository.save(product);
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        Product product = this.modelMapperService.forRequest().map(updateProductRequest, Product.class);
        productRepository.save(product);
    }

    @Override
    public void delete(int productId) {
        if (this.productRepository.existsById(productId)){
            this.productRepository.deleteById(productId);
        }else{
            System.out.println("Gecersiz Ürün ID");
        }
    }

    @Override
    public List<ProductListResponse> getAll() {
        List<Product> result = this.productRepository.findAll();
        List<ProductListResponse> response = result.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, ProductListResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public ProductListResponse getById(int productId) {
        ProductListResponse response = new ProductListResponse();
        if (this.productRepository.existsById(productId)){
            Product product = this.productRepository.findById(productId).get();
            response = this.modelMapperService.forResponse().map(product, ProductListResponse.class);
        }
        else{
            System.out.println("Gecersiz Ürün ID");
        }
        return response;
    }

    @Override
    public PageDataResponse<ProductListResponse> getByPage(int pageNumber, int productAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber-1,productAmountInPage);
        Page<Product> pages = this.productRepository.findAllProducts(pageable);
        List<ProductListResponse> response =
                pages.getContent().stream().map(product -> this.modelMapperService.forResponse().map(product, ProductListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<ProductListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    @Override
    public PageDataResponse<ProductListResponse> getByPageWithSorting(int pageNumber, int productAmountInPage, String fieldName, boolean isAsc) {
        Pageable pageable;
        if (isAsc){
            pageable = PageRequest.of(pageNumber-1,productAmountInPage, Sort.by(fieldName).ascending());
        }else {
            pageable = PageRequest.of(pageNumber-1,productAmountInPage, Sort.by(fieldName).descending());
        }
        Page<Product> pages = this.productRepository.findAllProducts(pageable);
        List<ProductListResponse> response =
                pages.getContent().stream().map(product -> this.modelMapperService.forResponse().map(product, ProductListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<ProductListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }
}
