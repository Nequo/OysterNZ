package test;

import com.tfl.billing.Journey;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JourneyTest
{
    private final UUID cardExampleID = UUID.randomUUID();
    private final UUID readerOriginID = UUID.randomUUID();
    private final UUID readerDestinationID = UUID.randomUUID();
    private final long startTime = 1493701200000L;
    private final int journeyLenghtInMin = 1;
    private final Journey journey = new FakeJourneyCreator().createFakeJourney
            (startTime, journeyLenghtInMin, cardExampleID, readerOriginID, readerDestinationID);


    @Test
    public void JourneyDurationMinutesTest()
    {
        assertThat(journey.durationMinutes(), is("1:0"));
    }

    @Test
    public void JourneyDurationSecondsTest()
    {
        assertThat(journey.durationSeconds(), is(60));
    }

    @Test
    public void originIdTest()
    {
        assertThat(journey.originId(), is(readerOriginID));
    }

    @Test
    public void destinationIdTest()
    {
        assertThat(journey.destinationId(), is(readerDestinationID));
    }

    @Test
    public void startTimeTest()
    {
        assertThat(journey.startTime(), is(new Date(startTime)));
    }

    @Test
    public void endTimeTest()
    {
        assertThat(journey.endTime(), is(new Date(startTime + journeyLenghtInMin * 60 * 1000)));
    }

}