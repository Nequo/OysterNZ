package com.tfl.billing;

import com.oyster.OysterCardReader;
import com.oyster.ScanListener;
import com.tfl.external.Customer;
import com.tfl.external.CustomerDatabase;

import java.math.BigDecimal;
import java.util.*;

public class TravelTracker implements ScanListener
{
    private final List<JourneyEventInterface> eventLog = new ArrayList<JourneyEventInterface>();
    private final Set<UUID> currentlyTravelling = new HashSet<UUID>();

    private Database customerDatabase;
    private PaymentService paymentService;

    public TravelTracker()
    {
        this.customerDatabase = CustomerDBAdapter.getInstance();
        this.paymentService = PaymentAdapter.getInstance();
    }

    public TravelTracker(Database customerDatabase, PaymentService paymentService)
    {
        this.customerDatabase = customerDatabase;
        this.paymentService = paymentService;
    }


    public void chargeAccounts()
    {
        for (Customer customer : customerDatabase.getCustomers())
        {
            totalJourneysFor(customer);
        }
    }

    private void totalJourneysFor(Customer customer)
    {
        Itinerary itinerary = new Itinerary();
        itinerary.generateItinerary(customer, eventLog);
        List<Journey> journeys = itinerary.getJourneys();

        BigDecimal customerTotal = JourneyPriceCalculator.TotalJourneyPrice(journeys);

        paymentService.charge(customer, journeys, customerTotal);
    }


    public void connect(OysterCardReader... cardReaders)
    {
        for (OysterCardReader cardReader : cardReaders)
        {
            cardReader.register(this);
        }
    }

    @Override
    public void cardScanned(UUID cardId, UUID readerId)
    {
        if (currentlyTravelling.contains(cardId))
        {
            eventLog.add(new JourneyEnd(cardId, readerId));
            currentlyTravelling.remove(cardId);
        } else
        {
            if (CustomerDatabase.getInstance().isRegisteredId(cardId))
            {
                currentlyTravelling.add(cardId);
                eventLog.add(new JourneyStart(cardId, readerId));
            } else
            {
                throw new UnknownOysterCardException(cardId);
            }
        }
    }
}