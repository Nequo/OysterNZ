package com.tfl.billing;

import com.oyster.OysterCardReader;
import com.oyster.ScanListener;
import com.tfl.external.Customer;

import java.math.BigDecimal;
import java.util.*;

public class TravelTracker implements ScanListener
{
    private final Set<UUID> currentlyTravelling = new HashSet<>();
    private final HashMap<UUID, List<JourneyEventInterface>> customerLog = new HashMap<>();

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
        customerLog.putIfAbsent(customer.cardId(), new ArrayList<>());

        Itinerary itinerary = new Itinerary();
        itinerary.generateItinerary(customerLog.get(customer.cardId()));
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
            customerLog.get(cardId).add(new JourneyEvent(cardId, readerId, JourneyEvent.END));
            currentlyTravelling.remove(cardId);
        } else
        {
            if (customerDatabase.isRegisteredId(cardId))
            {
                currentlyTravelling.add(cardId);
                customerLog.putIfAbsent(cardId, new ArrayList<>());
                customerLog.get(cardId).add(new JourneyEvent(cardId, readerId, JourneyEvent.START));
            } else
            {
                throw new UnknownOysterCardException(cardId);
            }
        }
    }
}