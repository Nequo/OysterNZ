package com.tfl.billing;

import com.tfl.external.Customer;
import com.tfl.external.PaymentsSystem;

import java.math.BigDecimal;
import java.util.List;

public class PaymentAdapter implements PaymentService
{
    private static PaymentAdapter instance = new PaymentAdapter();


    public static PaymentAdapter getInstance()
    {
        return instance;
    }

    @Override
    public void charge(Customer customer, List<Journey> journeys, BigDecimal customerTotal)
    {
        PaymentsSystem.getInstance().charge(customer, journeys, customerTotal);
    }
}