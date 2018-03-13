package com.david.demo.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByLastName(String lastName);
}
