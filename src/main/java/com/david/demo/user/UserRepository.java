package com.david.demo.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    UserEntity findByLastName(String lastName);

    List<UserEntity> findAll();

    UserEntity findByUsername(String username);
}
