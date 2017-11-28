package com.tfl.billing;

import com.oyster.*;
import com.tfl.external.Customer;
import com.tfl.external.CustomerDatabase;

import java.math.BigDecimal;
import java.util.*;

public class TravelTracker implements ScanListener
{
    private final List<JourneyEvent> eventLog = new ArrayList<JourneyEvent>();
    private final Set<UUID> currentlyTravelling = new HashSet<UUID>();


    public void chargeAccounts(PaymentService paymentsystem, List<Customer> customerDatabase)
    {
        for (Customer customer : customerDatabase)
        {
            totalJourneysFor(customer, paymentsystem);
        }
    }

    private void totalJourneysFor(Customer customer, PaymentService paymentsystem)
    {
        List<JourneyEvent> customerJourneyEvents = CustomerItinerary(customer);

        List<Journey> journeys = Journeys(customerJourneyEvents);

        BigDecimal customerTotal = JourneyPriceCalculator.TotalJourneyPrice(journeys);

        paymentsystem.charge(customer, journeys, customerTotal);
    }


    private List<Journey> Journeys(List<JourneyEvent> customerJourneyEvents)
    {
        List<Journey> journeys = new ArrayList<>();

        JourneyEvent start = null;
        for (JourneyEvent event : customerJourneyEvents)
        {
            if (event instanceof JourneyStart)
            {
                start = event;
            }
            if (event instanceof JourneyEnd && start != null)
            {
                journeys.add(new Journey(start, event));
                start = null;
            }
        }
        return journeys;
    }

    private List<JourneyEvent> CustomerItinerary(Customer customer)
    {
        List<JourneyEvent> customerJourneyEvents = new ArrayList<JourneyEvent>();
        for (JourneyEvent journeyEvent : eventLog)
        {
            if (journeyEvent.cardId().equals(customer.cardId()))
            {
                customerJourneyEvents.add(journeyEvent);
            }
        }
        return customerJourneyEvents;
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
