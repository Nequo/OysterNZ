package com.tfl.billing;

import com.tfl.external.Customer;
import com.tfl.external.CustomerDatabase;

import java.util.List;
import java.util.UUID;

public class CustomerDBAdapter implements Database {

    private static CustomerDBAdapter instance = new CustomerDBAdapter();
    private CustomerDatabase customerDatabase = CustomerDatabase.getInstance();

    private CustomerDBAdapter(){}

    public static CustomerDBAdapter getInstance(){return instance;}

    @Override
    public List<Customer> getCustomers(){
        return customerDatabase.getCustomers();
    }

    @Override
    public boolean isRegisteredUUID(UUID cardID){return customerDatabase.isRegisteredId(cardID);}


}
