package src.test;

import com.oyster.OysterCard;
import com.tfl.billing.CardReader;
import com.tfl.billing.Journey;
import com.tfl.billing.PaymentService;
import com.tfl.billing.TravelTracker;


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
    Customer customer = new Customer("Nadim", new OysterCard("38400000-8cf0-11bd-b23e-10b96e4ef00d"));
    List<Journey> journeys = new ArrayList<>();
    BigDecimal total = new BigDecimal(0);



    @Test
    public void thePaymentSystemIsCalledWhenTheAccountsAreCharged(){

        context.checking(new Expectations(){{
            exactly(1).of(payment).charge(customer,journeys, total);
        }});

        travelTracker.chargeAccounts(payment);
    }

    @Test
    public void theReaderisRegisteredOnTheTracker(){
        CardReader readerA = context.mock(CardReader.class, "readerA");
        CardReader readerB = context.mock(CardReader.class, "readerB");
        context.checking(new Expectations(){{
            exactly(1).of(readerA).register(travelTracker);
            exactly(1).of(readerB).register(travelTracker);
        }});

        travelTracker.connect(readerA, readerB);
    }

}
