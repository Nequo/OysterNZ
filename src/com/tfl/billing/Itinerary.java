package com.tfl.billing;

import com.tfl.external.Customer;

import java.util.ArrayList;
import java.util.List;

public class Itinerary
{

    private List<Journey> journeys = new ArrayList<Journey>();

    public void generateItinerary(Customer customer, List<JourneyEventInterface> eventLog)
    {
        List<JourneyEventInterface> customerJourneyEvents = createJourneyEventList(customer, eventLog);
        createJourneyList(customerJourneyEvents);
    }

    private List<JourneyEventInterface> createJourneyEventList(Customer customer, List<JourneyEventInterface> eventLog)
    {
        List<JourneyEventInterface> customerJourneyEvents = new ArrayList<JourneyEventInterface>();
        for (JourneyEventInterface journeyEvent : eventLog)
        {
            if (journeyEvent.cardId().equals(customer.cardId()))
            {
                customerJourneyEvents.add(journeyEvent);
            }
        }
        return customerJourneyEvents;
    }

    private void createJourneyList(List<JourneyEventInterface> customerJourneyEvents)
    {
        JourneyEvent start = null;
        for (JourneyEventInterface event : customerJourneyEvents)
        {
            if (event instanceof JourneyStart)
            {
                start = (JourneyEvent) event;
            }
            if (event instanceof JourneyEnd && start != null)
            {
                journeys.add(new Journey(start, event));
                start = null;
            }
        }
    }

    public List<Journey> getJourneys()
    {
        return journeys;
    }
}