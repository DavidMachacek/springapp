package com.david.demo.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps transfer object and entity using mapstruct
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerTO customerToCustomerTO(Customer customer);
    Customer customerTOToCustomer(CustomerTO customer);
}
