package com.tfl.billing;

import com.tfl.external.Customer;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {

    private List<JourneyEvent> eventLog = new ArrayList<JourneyEvent>();
    private List<Journey> journeys = new ArrayList<Journey>();

    public Itinerary(List<JourneyEvent> eventLog) {
        this.eventLog = eventLog;
    }

    public void generateItinerary(Customer customer) {
        List<JourneyEvent> customerJourneyEvents = new ArrayList<JourneyEvent>();
        for (JourneyEvent journeyEvent : eventLog) {
            if (journeyEvent.cardId().equals(customer.cardId())) {
                customerJourneyEvents.add(journeyEvent);
            }
        }
        JourneyEvent start = null;
        for (JourneyEvent event : customerJourneyEvents) {
            if (event instanceof JourneyStart) {
                start = event;
            }
            if (event instanceof JourneyEnd && start != null) {
                journeys.add(new Journey(start, event));
                start = null;
            }
        }
    }

    public List<Journey> getCustomerItinerary() {
        return this.journeys;
    }

}