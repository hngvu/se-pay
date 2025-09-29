package com.impera.sepayment.repository;

import com.impera.sepayment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Integer, Payment> {
}
