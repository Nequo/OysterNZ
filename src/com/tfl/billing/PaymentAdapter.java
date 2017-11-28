package com.tfl.billing;

import com.tfl.external.Customer;
import com.tfl.external.PaymentsSystem;

import java.math.BigDecimal;
import java.util.List;

public class PaymentAdapter implements PaymentService {

    @Override
    public void charge(Customer customer, List<Journey> journeys, BigDecimal customerTotal){
        PaymentsSystem.getInstance().charge(customer, journeys, roundToNearestPenny(customerTotal));
    }


    private BigDecimal roundToNearestPenny(BigDecimal poundsAndPence)
    {
        return poundsAndPence.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
