package src.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;

import static org.junit.Assert.assertThat;


public class TravelTrackerTest {
/*
<<<<<<< HEAD
public class TravelTrackerTest
{
    TravelTracker travelTracker = new TravelTracker();

=======

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    PaymentSystem payment = context.mock(PaymentSystem.class);
    TravelTracker travelTracker = new TravelTracker(payment);

    @Test
    public void whenACardIsScanned(){

        context.checking(new Expectations(){{
            exactly(1).of(payment).charge();
        }});

        travelTracker.chargeAccounts();
    }

>>>>>>> TravelTracker_Refactoring*/

}
