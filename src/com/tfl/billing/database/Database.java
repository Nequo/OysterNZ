package com.tfl.billing.database;

import com.tfl.external.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface Database {

    List<Customer> getCustomers();
    boolean isRegisteredUUID(UUID cardId);
}