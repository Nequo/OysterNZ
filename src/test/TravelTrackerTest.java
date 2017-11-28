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
    TravelTracker travelTracker = new TravelTracker();

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    PaymentService payment = context.mock(PaymentService.class);
    CustomerDBAdapter customers = new CustomerDBAdapter();
    Customer customer = new Customer("Fred Bloggs", new OysterCard("38400000-8cf0-11bd-b23e-10b96e4ef00d"));
    List<Customer> customerList = customers.getList();
    List<Journey> journeys = new ArrayList<>();
    BigDecimal total = new BigDecimal(0);


    @Test
    public void thePaymentSystemIsCalledWhenTheAccountsAreCharged(){
        customers.addCustomer(customer);

        context.checking(new Expectations(){{
            exactly(1).of(payment).charge(customer,journeys, total);
        }});

        travelTracker.chargeAccounts(payment, customerList);
    }

}
