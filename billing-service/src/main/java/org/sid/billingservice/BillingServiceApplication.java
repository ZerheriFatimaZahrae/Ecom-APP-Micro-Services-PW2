package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repo.BillRepository;
import org.sid.billingservice.repo.ProductItemRepository;
import org.sid.billingservice.services.CustomerRestClient;
import org.sid.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient inventoryRestClient){
        return args -> {
            Long customerId=1L;
            Customer customer=customerRestClient.findCustomerById(customerId);
            List<Customer> customers=customerRestClient.allCustomers().getContent().stream().toList();
            List<Product> productList=inventoryRestClient.allProducts().getContent().stream().toList();
            System.out.println(customer.toString());
            System.out.println(productList);
            for (int i = 1; i <5 ; i++) {
                var index=new Random().nextInt(customers.size());
                Customer randomCustomer=customers.get(index);
                Bill bill= Bill.builder().billDate(new Date()).customerId(randomCustomer.getId()).build();
                Bill savedBill = billRepository.save(bill);
                productList.forEach(p->{
                    if(Math.random()>0.5){
                        ProductItem productItem= ProductItem.builder()
                                .productId(p.getId())
                                .price(p.getPrice())
                                .quantity(1+new Random().nextInt(5))
                                .bill(savedBill)
                                .discount(Math.random())
                                .productId(p.getId())
                                .build();
                        productItemRepository.save(productItem);
                    }
                });
            }

        };
    }
    //@Bean


}
