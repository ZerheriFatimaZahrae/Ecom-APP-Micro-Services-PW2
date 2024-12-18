package org.sid.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer",types =Customer.class )
//pour la testet on fait localhost:port/customers?projection=fullCustomer
public interface CustomerProjection {
    public Long getId();
    public String getName();
    public String getEmail();
}
