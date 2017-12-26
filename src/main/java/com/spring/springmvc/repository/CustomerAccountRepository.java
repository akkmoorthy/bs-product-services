package com.spring.springmvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.springmvc.entity.CustomerAccount;

// This will be AUTO IMPLEMENTED by Spring into a Bean called customerAccountRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer> {

}