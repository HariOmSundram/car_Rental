package com.carrental.booking.service;

import java.util.List;

import com.carrental.booking.entity.Payment;

public interface PaymentService {

	Payment addPayment(Payment payment);

	Payment updatePayment(Payment payment);

	Payment getById(int paymentId);

	Payment getByBookingId(String bookingId);

	Payment getByTransactionRefId(String transactionId);

	List<Payment> getByCustomerId(int userId);

}
