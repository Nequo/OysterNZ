package com.tfl.billing;


import java.util.ArrayList;
import java.util.List;

public class Itinerary
{

    private List<Journey> journeys = new ArrayList<>();

    public void generateItinerary(List<JourneyEventInterface> customerJourneyEvents)
    {
        JourneyEvent start = null;
        for (JourneyEventInterface event : customerJourneyEvents)
        {
            if (event.getEventType() == JourneyEvent.START)
            {
                start = (JourneyEvent) event;
            }
            if (event.getEventType() == JourneyEvent.END && start != null)
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