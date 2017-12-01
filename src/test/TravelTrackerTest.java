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
            /*this.add(new Customer("Oliver Morrell", new OysterCard("07b0bcb1-87df-447f-bf5c-d9961ab9d01e")));
            this.add(new Customer("Jesse Schmitz", new OysterCard("3b5a03cb-2be6-4ed3-b83e-94858b43e407")));
            this.add(new Customer("Jenny King", new OysterCard("89adbd1c-4de6-40e5-98bc-b3315c6873f2")));
            this.add(new Customer("Danny Conner", new OysterCard("609e72ac-8be3-4476-8b45-01db8c7f122b")));
            this.add(new Customer("Fern Taylor", new OysterCard("ffa3f53a-e225-43ea-b854-5130a1fa016d")));
            this.add(new Customer("Logan Wakefield", new OysterCard("cd5e097d-e2f1-4bc4-bf7b-d47903d89441")));
            this.add(new Customer("Angela Harris", new OysterCard("365d7f7d-0ff5-4f87-a51d-f6443a36d035")));
            this.add(new Customer("John Smith", new OysterCard("0c79d9e0-3874-4b02-9492-80c232a07640")));
            this.add(new Customer("Mary Clarke", new OysterCard("34bbfc54-916b-42b2-a6d8-5ec2eaf7dd7a")));
            this.add(new Customer("Emily Scott", new OysterCard("267b3378-592d-4da7-825d-3252982d49ba")));
            */
        }
    };

    List<Journey> journeys = new ArrayList<>();
    BigDecimal total = new BigDecimal(0);


    @Test
    public void theDatabaseReturnsTheCorrectDataSet(){

        context.checking(new Expectations(){{
            oneOf(database).getCustomers();will(returnValue(customers));
        }});

        database.getCustomers();
    }

    @Test
    public void allAccountsAreChargedOnce(){

        context.checking(new Expectations(){{
            oneOf(database).getCustomers(); will(returnValue(customers));
            exactly(1).of(payment).charge(customers.get(0), journeys, total);
            exactly(1).of(payment).charge(customers.get(1), journeys, total);
        }});

        travelTracker.chargeAccounts();
    }
}
