package org.sid.billingservice.web;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repo.BillRepository;
import org.sid.billingservice.repo.ProductItemRepository;
import org.sid.billingservice.services.CustomerRestClient;
import org.sid.billingservice.services.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired private ProductItemRepository productItemRepository;
    @Autowired private CustomerRestClient customerRestClient;
    @Autowired private ProductRestClient inventoryRestClient;

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        //customerRestClient qui va envoyer la requete au service CUSTOMERSERVICE POUR savoir le client associe avec la facture
        Customer customer=customerRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer); //set customer de model customer , pour pouvoir l afficher par la suite
        for(ProductItem pi:bill.getProductItemList()){
            Product product=inventoryRestClient.findProductById(pi.getProductId());
            System.out.println(product);
            pi.setProduct(product);
        }
        return bill;
    }
}
