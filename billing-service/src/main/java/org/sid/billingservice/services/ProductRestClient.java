package org.sid.billingservice.services;

import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE") // va etre base sur openFeign
//qui va permetre de communiquer avec les services via rest

public interface ProductRestClient {
    @GetMapping(path = "/products")
    public PagedModel<Product> allProducts();
    @GetMapping(path = "/products/{id}")
    public Product findProductById(@PathVariable(name = "id") Long id);
}
