package com.tfl.billing;

import com.tfl.external.Customer;
import com.tfl.external.CustomerDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomerDBAdapter implements Database {

    private CustomerDatabase customerDatabase = CustomerDatabase.getInstance();
    private List<Customer> customers = new ArrayList<Customer>();

    @Override
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    @Override
    public List<Customer> getCustomers(){
        return customerDatabase.getCustomers();
    }

    public List<Customer> getList(){
        return customers;
    }





}
