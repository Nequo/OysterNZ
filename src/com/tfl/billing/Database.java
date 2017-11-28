package com.tfl.billing;

import com.tfl.external.Customer;

import java.util.ArrayList;
import java.util.List;

public interface Database {

    void addCustomer(Customer customer);

    List<Customer> getCustomers();

    List<Customer> getList();
}
