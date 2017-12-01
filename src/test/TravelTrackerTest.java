package src.test;

import com.oyster.OysterCard;
import com.tfl.billing.*;


import com.tfl.external.Customer;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class TravelTrackerTest
{


    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    PaymentService payment = context.mock(PaymentService.class);
    Database database = context.mock(Database.class);
    TravelTracker travelTracker = new TravelTracker(database, payment);


    private List<Customer> customers = new ArrayList<Customer>() {
        {
            this.add(new Customer("Fred Bloggs", new OysterCard("38400000-8cf0-11bd-b23e-10b96e4ef00d")));
            this.add(new Customer("Shelly Cooper", new OysterCard("3f1b3b55-f266-4426-ba1b-bcc506541866")));
            this.add(new Customer("Oliver Morrell", new OysterCard("07b0bcb1-87df-447f-bf5c-d9961ab9d01e")));
        }
    };

    private List<Journey> journeys = new ArrayList<>();
    private BigDecimal total = new BigDecimal(0);


    @Test
    public void theDatabaseReturnsTheExpectedDataSet(){

        context.checking(new Expectations(){{
            oneOf(database).getCustomers();will(returnValue(customers));
        }});

        database.getCustomers();
    }

    @Test
    public void theDatabaseIsCalledWhenYouChargeTheAccounts(){
        context.checking(new Expectations(){{
            exactly(1).of(database).getCustomers();
        }});
        travelTracker.chargeAccounts();
    }

    
    @Test
    public void eachCustomerInTheListIsChargedOnce(){

        context.checking(new Expectations(){{
            oneOf(database).getCustomers(); will(returnValue(customers));
            exactly(1).of(payment).charge(customers.get(0), journeys, total);
            exactly(1).of(payment).charge(customers.get(1), journeys, total);
            exactly(1).of(payment).charge(customers.get(2), journeys, total);
        }});

        travelTracker.chargeAccounts();
    }
}
