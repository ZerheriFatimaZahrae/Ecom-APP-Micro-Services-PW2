package org.sid.billingservice.services;

import org.sid.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMERSERVICE") // va etre base sur openFeign
//va envoyer la request au costumerservice pour savoir le id de client
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}")
    public Customer findCustomerById(@PathVariable Long id);
    @GetMapping(path = "/customers")
    public PagedModel<Customer> allCustomers();
}
