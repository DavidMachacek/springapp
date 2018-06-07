package com.david.demo.customer;

import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository accessing CustomerGroup table
 */
public interface CustomerGroupRepository extends CrudRepository<CustomerGroup, Long> {

    CustomerGroup findCustomerGroupByGroupName(String groupName);


}
