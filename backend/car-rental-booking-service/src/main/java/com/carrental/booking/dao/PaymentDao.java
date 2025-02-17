package com.carrental.booking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrental.booking.entity.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {

	Payment findByBookingId(String bookingId);

	Payment findByTransactionRefId(String transactionId);

	List<Payment> findByCustomerId(int customerId);

}
