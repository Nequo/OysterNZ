package com.tfl.billing;

import com.oyster.OysterCard;
import com.oyster.OysterCardReader;
import com.tfl.external.Customer;
import com.tfl.underground.OysterReaderLocator;
import com.tfl.underground.Station;

import java.util.ArrayList;
import java.util.List;

public class Example
{
    public static void main(String[] args) throws Exception {
        PaymentService paymentsystem = new PaymentAdapter();
        Database customerDB = new CustomerDBAdapter();
        List<Customer> customers = customerDB.getCustomers();

        OysterCard myCard = new OysterCard("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        OysterCardReader paddingtonReader = OysterReaderLocator.atStation(Station.PADDINGTON);
        OysterCardReader bakerStreetReader = OysterReaderLocator.atStation(Station.BAKER_STREET);
        OysterCardReader kingsCrossReader = OysterReaderLocator.atStation(Station.KINGS_CROSS);
        TravelTracker travelTracker = new TravelTracker();
        travelTracker.connect(paddingtonReader, bakerStreetReader, kingsCrossReader);
        for (int i = 0; i < 5; i++)
        {
            paddingtonReader.touch(myCard);
            minutesPass(1);
            bakerStreetReader.touch(myCard);
            minutesPass(2);
            bakerStreetReader.touch(myCard);
            minutesPass(3);
            kingsCrossReader.touch(myCard);
            minutesPass(4);
        }
        travelTracker.chargeAccounts(paymentsystem, customers);
    }

    private static void minutesPass(int n) throws InterruptedException
    {
        Thread.sleep(n * 60 * 1);
    }
}
