package com.chiqors.gpaytechtest.repository;

import com.chiqors.gpaytechtest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
