package test;

import com.tfl.billing.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ItineraryTest
{

    private Itinerary itinerary = new Itinerary();

    @Test
    public void theListOfJourneysIsInitialisedEmpty()
    {
        assertTrue(itinerary.getJourneys().isEmpty());
    }


    @Test
    public void doesNotCreateJourneyIfOnlyGivenStarts()
    {
        UUID cardId = UUID.randomUUID();

        JourneyEvent start = new JourneyEvent(cardId, UUID.randomUUID(), JourneyEvent.START);
        JourneyEvent start2 = new JourneyEvent(cardId, UUID.randomUUID(), JourneyEvent.START);

        List<JourneyEventInterface> events = new ArrayList<>();
        events.add(start);
        events.add(start2);

        itinerary.generateItinerary(events);

        assertTrue(itinerary.getJourneys().isEmpty());
    }

    @Test
    public void doesNotCreateJourneyIfOnlyGivenEnds()
    {
        UUID cardId = UUID.randomUUID();

        JourneyEvent end = new JourneyEvent(cardId, UUID.randomUUID(), JourneyEvent.END);
        JourneyEvent end2 = new JourneyEvent(cardId, UUID.randomUUID(), JourneyEvent.END);

        List<JourneyEventInterface> events = new ArrayList<>();
        events.add(end);
        events.add(end2);

        itinerary.generateItinerary(events);

        assertTrue(itinerary.getJourneys().isEmpty());
    }

    @Test
    public void createsSingleJourneyIfGivenAStartAndEnd()
    {
        UUID cardId = UUID.randomUUID();

        JourneyEvent start = new JourneyEvent(cardId, UUID.randomUUID(), JourneyEvent.START);
        JourneyEvent end = new JourneyEvent(cardId, UUID.randomUUID(), JourneyEvent.END);

        List<JourneyEventInterface> events = new ArrayList<>();
        events.add(start);
        events.add(end);

        itinerary.generateItinerary(events);

        assertThat(itinerary.getJourneys().size(), is(1));
    }
}
