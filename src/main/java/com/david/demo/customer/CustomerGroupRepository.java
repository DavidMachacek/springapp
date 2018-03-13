package com.david.demo.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerGroupRepository extends CrudRepository<CustomerGroup, Long> {

    CustomerGroup findCustomerGroupByGroupName(String groupName);


}
