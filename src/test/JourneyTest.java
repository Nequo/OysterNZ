package test;

import com.tfl.billing.Journey;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JourneyTest
{
    private UUID cardExampleID = UUID.randomUUID();
    private UUID readerOriginID = UUID.randomUUID();
    private UUID readerDestinationID = UUID.randomUUID();
    private  Journey journey = new FakeJourneyCreator().createFakeJourney
            (1493701200000L, 1, cardExampleID, readerOriginID, readerDestinationID);


    @Test
    public void JourneyDurationMinutesTest() throws InterruptedException
    {
        assertThat(journey.durationMinutes(), is("1:0"));
    }

    @Test
    public void JourneyDurationSecondsTest() throws InterruptedException
    {
        assertThat(journey.durationSeconds(), is( 60));
    }

    @Test
    public void originIdTest() throws InterruptedException
    {
        assertThat(journey.originId(), is(readerOriginID));
    }

    @Test
    public void destinationIdTest() throws InterruptedException
    {
        assertThat(journey.destinationId(), is(readerDestinationID));
    }
}