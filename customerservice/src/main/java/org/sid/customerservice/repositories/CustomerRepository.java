package org.sid.customerservice.repositories;

import org.sid.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource //mettere un web service resfull qui permet de gerer les customers
public interface CustomerRepository extends JpaRepository<Customer,Long>{
}
