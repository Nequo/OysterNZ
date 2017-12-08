package com.tfl.billing;

import com.tfl.external.Customer;
import com.tfl.external.CustomerDatabase;

import java.util.List;
import java.util.UUID;

public class CustomerDBAdapter implements Database
{

    private static CustomerDBAdapter instance = new CustomerDBAdapter();
    private CustomerDatabase customerDatabase = CustomerDatabase.getInstance();

    public static CustomerDBAdapter getInstance()
    {
        return instance;
    }

    @Override
    public List<Customer> getCustomers()
    {
        return customerDatabase.getCustomers();
    }

    @Override
    public boolean isRegisteredId(UUID cardID)
    {
        return customerDatabase.isRegisteredId(cardID);
    }


}