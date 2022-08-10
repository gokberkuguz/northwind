package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.OrderDetailsService;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {
    private OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/getall")
    public List<OrderDetailsListResponse> getAll() {
        return this.orderDetailsService.getAll();
    }

    /*
        @PostMapping("/add")
        public void add(@RequestBody @Valid CreateOrderDetailRequest createOrderDetailRequest){
            this.orderDetailsService.add(createOrderDetailRequest);
        }

        @PostMapping("/update")
        public void update(@RequestBody @Valid UpdateOrderDetailRequest updateOrderDetailRequest){
            this.orderDetailsService.update(updateOrderDetailRequest);
        }

        @DeleteMapping("/delete/{orderDetailsId}")
        public void delete(@Valid @PathVariable OrderDetailsId orderDetailsId){
            this.orderDetailsService.delete(orderDetailsId);
        }


    */
    @GetMapping("/getOrderDetailsByProductId/{productId}")
    public List<OrderDetailsListResponse> getOrderDetailsByProductId(@PathVariable int productId) {
        return this.orderDetailsService.getOrderDetailsByProductId(productId);
    }

    @GetMapping("/getByPage/{pageNumber}/{orderDetailsAmountInPage}")
    public PageDataResponse<OrderDetailsListResponse> getByPage(int pageNumber, int orderDetailsAmountInPage) {
        return this.orderDetailsService.getByPage(pageNumber, orderDetailsAmountInPage);
    }

    @GetMapping("/getByPageWithSorting/{pageNumber}/{orderDetailsAmountInPage}/{fieldName}/{isAsc}")
    public PageDataResponse<OrderDetailsListResponse> getByPageWithSorting(int pageNumber, int orderDetailsAmountInPage, String fieldName, boolean isAsc) {
        return this.orderDetailsService.getByPageWithSorting(pageNumber, orderDetailsAmountInPage, fieldName, isAsc);
    }
}
